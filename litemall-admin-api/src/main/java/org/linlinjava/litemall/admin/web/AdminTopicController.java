package org.linlinjava.litemall.admin.web;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.admin.util.QRCodeUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.IntegralSum;
import org.linlinjava.litemall.db.domain.LitemallTopic;
import org.linlinjava.litemall.db.service.LitemallIntegralsService;
import org.linlinjava.litemall.db.service.LitemallTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/topic")
@Validated
public class AdminTopicController {
    private final Log logger = LogFactory.getLog(AdminTopicController.class);

    @Autowired
    private LitemallTopicService topicService;
    @Autowired
    private LitemallIntegralsService litemallIntegralsService;

    @RequiresPermissions("admin:topic:list")
    @RequiresPermissionsDesc(menu={"推广管理" , "专题管理"}, button="查询")
    @GetMapping("/list")
    public Object list(String title, String subtitle,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<LitemallTopic> topicList = topicService.querySelective(title, subtitle, page, limit, sort, order);
        int total = topicService.countSelective(title, subtitle, page, limit, sort, order);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", topicList);

        return ResponseUtil.ok(data);
    }

    private Object validate(LitemallTopic topic) {
        String title = topic.getTitle();
        if (StringUtils.isEmpty(title)) {
            return ResponseUtil.badArgument();
        }
        String content = topic.getContent();
        if (StringUtils.isEmpty(content)) {
            return ResponseUtil.badArgument();
        }
        BigDecimal price = topic.getPrice();
        if (price == null) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    @RequiresPermissions("admin:topic:create")
    @RequiresPermissionsDesc(menu={"推广管理" , "专题管理"}, button="添加")
    @PostMapping("/create")
    public Object create(@RequestBody LitemallTopic topic) {
        Object error = validate(topic);
        if (error != null) {
            return error;
        }
        topicService.add(topic);
        return ResponseUtil.ok(topic);
    }

    @RequiresPermissions("admin:topic:read")
    @RequiresPermissionsDesc(menu={"推广管理" , "专题管理"}, button="详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        LitemallTopic topic = topicService.findById(id);
        return ResponseUtil.ok(topic);
    }

    @RequiresPermissions("admin:topic:update")
    @RequiresPermissionsDesc(menu={"推广管理" , "专题管理"}, button="编辑")
    @PostMapping("/update")
    public Object update(@RequestBody LitemallTopic topic) {
        Object error = validate(topic);
        if (error != null) {
            return error;
        }
        if (topicService.updateById(topic) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(topic);
    }

    @RequiresPermissions("admin:topic:delete")
    @RequiresPermissionsDesc(menu={"推广管理" , "专题管理"}, button="删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody LitemallTopic topic) {
        topicService.deleteById(topic.getId());
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:topic:createCode")
    @RequiresPermissionsDesc(menu={"推广管理" , "专题管理"}, button="生成二维码")
    @PostMapping("/createCode")
    public Object createCode(@RequestBody LitemallTopic topic,OutputStream output, boolean needCompress) throws Exception {
        topic = topicService.findById(topic.getId());
        String url = "https://hpnk.1897.com/activity/?id="+topic.getId();
        topic.setCodeurl(url);
        topicService.updateById(topic);
        //String binary = Qrcode2.creatRrCode(url, "D:/barcode/milk.jpg",200,200);
        String binary = QRCodeUtil.encode(url, "/opt/www/hpnk/dist/static/img/hpnk.jpg",output,true);
        return ResponseUtil.ok(binary);
    }

    /**
     *
     * @param type 积分种类，为null时，统计所有积分记录
     * @param abs 统计种类，true统计>0积分记录，false 统计<0积分记录，null统计所有积分记录
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return 返回按积分种类的汇总排名，可以统计总消费积分，总产生消费积分，总积分等各种组合排名
     */
    @GetMapping("listTopIntegralByType")
    @RequiresPermissions("admin:topic:list")
    @RequiresPermissionsDesc(menu={"推广管理" , "专题管理"}, button="查询")
    public Object listTopIntegralByType(@RequestParam(defaultValue = "1") Integer type, Boolean abs,
                                        @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer size,
                                        @Sort(accepts = {"add_time", "id"}) @RequestParam(defaultValue = "add_time") String sort,
                                        @Order @RequestParam(defaultValue = "desc") String order) {
        List<IntegralSum>  list = litemallIntegralsService.queryTopIntegrals(type,null,abs,page,size);
        int count = litemallIntegralsService.queryTopIntegralsCount(type,null,abs);
        int totalPages = (int) Math.ceil((double) count / size);
        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("integrals", list);
        data.put("totalPages", totalPages);
        return ResponseUtil.ok(data);
    }

}
