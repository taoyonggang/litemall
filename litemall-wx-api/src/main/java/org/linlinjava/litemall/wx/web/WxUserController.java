package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallIntegrals;
import org.linlinjava.litemall.db.service.LitemallOrderService;
import org.linlinjava.litemall.db.service.LitemallIntegralsService;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户服务
 */
@RestController
@RequestMapping("/wx/user")
@Validated
public class WxUserController {
    private final Log logger = LogFactory.getLog(WxUserController.class);

    @Autowired
    private LitemallOrderService orderService;
    @Autowired
    private LitemallIntegralsService litemallIntegralsService;

    /**
     * 用户个人页面数据
     * <p>
     * 目前是用户订单统计信息
     *
     * @param userId 用户ID
     * @return 用户个人页面数据
     */
    @GetMapping("index")
    public Object list(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("order", orderService.orderInfo(userId));
        data.put("integralSum", litemallIntegralsService.queryIntegralSum(userId));
        return ResponseUtil.ok(data);
    }

    @GetMapping("integrals")
    public Object integralList(@LoginUser Integer userId, @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer size,
                               @Sort(accepts = {"add_time", "id"}) @RequestParam(defaultValue = "add_time") String sort,
                               @Order @RequestParam(defaultValue = "desc") String order) {
        //userId = 3;
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        int count = litemallIntegralsService.queryTotalCount(userId);
        int totalPages = (int) Math.ceil((double) count / size);

        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("integrals", litemallIntegralsService.queryIntegrals(userId,page,size,sort,order));
        data.put("integralSum", litemallIntegralsService.queryIntegralSum(userId));
        data.put("totalPages", totalPages);
        return ResponseUtil.ok(data);
    }

}