package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.admin.util.QRCodeUtil;
import org.linlinjava.litemall.core.util.DateTimeUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallActivity;
import org.linlinjava.litemall.db.domain.LitemallActivityMore;
import org.linlinjava.litemall.db.service.LitemallActivityService;
import org.linlinjava.litemall.db.service.LitemallActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/Activity")
@Validated
public class AdminActivityController {
    private final Log logger = LogFactory.getLog(AdminActivityController.class);

    @Autowired
    private LitemallActivityService activityService;

    @RequiresPermissions("admin:Activity:list")
    @RequiresPermissionsDesc(menu={"推广管理" , "专题管理"}, button="查询")
    @GetMapping("/list")
    public Object list(Integer activityId,Integer promoterId,Integer userId,LocalDateTime addTime,LocalDateTime endTime,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<LitemallActivity> ActivityList = activityService.querySelective(activityId, promoterId, userId, addTime, endTime, page, limit, sort, order);
        int total = activityService.countSelective(activityId, promoterId, userId, addTime, endTime, page, limit, sort, order);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", ActivityList);

        return ResponseUtil.ok(data);
    }

    /**
     *
     * @param activityId
     * @param promoterId
     * @param userId
     * @param title
     * @param orign
     * @param nickname
     * @param beginTime
     * @param endTime
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @RequiresPermissions("admin:Activity:list")
    @RequiresPermissionsDesc(menu={"推广管理" , "专题管理"}, button="查询")
    @GetMapping("/listMore")
    public Object listMore(Integer activityId,Integer promoterId,Integer userId,
                           String title,String orign,String nickname,
                           String beginTime,String endTime,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        LitemallActivityMore activityMore =  new LitemallActivityMore();
        if(beginTime != null){
            activityMore.setAddTime(DateTimeUtil.StringToLocalDateTime(beginTime));
        }
        if(endTime != null){
            activityMore.setEndTime(DateTimeUtil.StringToLocalDateTime(endTime));
        }
        activityMore.setTitle(title);
        activityMore.setNickname(nickname);
        activityMore.setUserId(userId);
        activityMore.setPromoterId(promoterId);
        activityMore.setActivityId(activityId);
        activityMore.setOrign(orign);

        List<LitemallActivityMore> ActivityList = activityService.queryListMore(activityMore,page, limit, sort, order);
        int total = activityService.queryListMoreCount(activityMore, page, limit, sort, order);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", ActivityList);

        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:Activity:create")
    @RequiresPermissionsDesc(menu={"推广管理" , "专题管理"}, button="添加")
    @PostMapping("/create")
    public Object create(@RequestBody LitemallActivity Activity) {
        activityService.add(Activity);
        return ResponseUtil.ok(Activity);
    }

    @RequiresPermissions("admin:Activity:read")
    @RequiresPermissionsDesc(menu={"推广管理" , "专题管理"}, button="详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        LitemallActivity Activity = activityService.findById(id);
        return ResponseUtil.ok(Activity);
    }

    @RequiresPermissions("admin:Activity:update")
    @RequiresPermissionsDesc(menu={"推广管理" , "专题管理"}, button="编辑")
    @PostMapping("/update")
    public Object update(@RequestBody LitemallActivity Activity) {
        if (activityService.updateById(Activity) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(Activity);
    }

    @RequiresPermissions("admin:Activity:delete")
    @RequiresPermissionsDesc(menu={"推广管理" , "专题管理"}, button="删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody LitemallActivity Activity) {
        activityService.deleteById(Activity.getId());
        return ResponseUtil.ok();
    }


}
