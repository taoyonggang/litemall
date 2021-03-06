package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.asn1.eac.UnsignedInteger;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.IntegralSum;
import org.linlinjava.litemall.db.domain.LitemallIntegrals;
import org.linlinjava.litemall.db.service.LitemallOrderService;
import org.linlinjava.litemall.db.service.LitemallIntegralsService;
import org.linlinjava.litemall.db.util.IntegralStatus;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.linlinjava.litemall.wx.service.UserInfoDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.linlinjava.litemall.wx.util.WxResponseCode.ORDER_PAY_FAIL;

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
    @Autowired
    private UserInfoDetailService userInfoDetailService;

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

    @GetMapping("useIntegral")
    public Object useIntegral(@LoginUser Integer userId, @RequestParam Integer integral) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        int sum = litemallIntegralsService.queryIntegralSum(userId);
        if (Math.abs(integral)>sum || sum<=0){
            return ResponseUtil.fail(ORDER_PAY_FAIL,"积分不足");
        }
        // 扣积分
        Integer resultId  = litemallIntegralsService.addIntegral("公益行",
                -Math.abs(integral),
                userId,
                IntegralStatus.STATUS_OUT,IntegralStatus.EFFECTIVE_YES,-1);

        sum = litemallIntegralsService.queryIntegralSum(userId);
        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("integralSum", sum);
        return ResponseUtil.ok(data);
    }

    @GetMapping("listIntegralByType")
    public Object integraListOut(@LoginUser Integer userId,
                                 @RequestParam(defaultValue = "1") Integer type,
                                 @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer size,
                                 @Sort(accepts = {"add_time", "id"}) @RequestParam(defaultValue = "add_time") String sort,
                                 @Order @RequestParam(defaultValue = "desc") String order) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        List<LitemallIntegrals>  list = litemallIntegralsService.queryIntegralsByType(userId,type,page,size,sort,order);
        Integer sum = litemallIntegralsService.queryIntegralSum(userId);
        Map<Object, Object> data = new HashMap<Object, Object>();
        int count = litemallIntegralsService.queryTotalCountByType(userId,type);
        int totalPages = (int) Math.ceil((double) count / size);
        data.put("integrals", list);
        data.put("totalPages", totalPages);
        data.put("integralSum", sum);
        return ResponseUtil.ok(data);
    }

    /**
     *
     * @param userId
     * @param type 积分种类，为null时，统计所有积分记录
     * @param abs 统计种类，true统计>0积分记录，false 统计<0积分记录，null统计所有积分记录
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return 返回按积分种类的汇总排名，可以统计总消费积分，总产生消费积分，总积分等各种组合排名，并返回自己积分值
     */
    @GetMapping("listTopIntegralByType")
    public Object listTopIntegralByType(@LoginUser Integer userId,
                                 @RequestParam(defaultValue = "1") Integer type, Boolean abs,
                                 @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer size,
                                 @Sort(accepts = {"add_time", "id"}) @RequestParam(defaultValue = "add_time") String sort,
                                 @Order @RequestParam(defaultValue = "desc") String order) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        List<IntegralSum>  list = litemallIntegralsService.queryTopIntegrals(type,null,abs,page,size);
        int count = litemallIntegralsService.queryTopIntegralsCount(type,null,abs);
        int totalPages = (int) Math.ceil((double) count / size);
        List<IntegralSum>  myList = litemallIntegralsService.queryTopIntegrals(type,userId,abs,page,size);
        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("integrals", list);
        data.put("myIntegrals", myList);
        data.put("totalPages", totalPages);
        return ResponseUtil.ok(data);
    }

    /**
     * 用户个人页面数据
     * <p>
     * 目前是用户订单统计信息
     *
     * @param userId 用户ID
     * @return 用户个人页面数据
     */
    @GetMapping("userDetail")
    public Object userDetail(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("userDetail", userInfoDetailService.getInfo(userId));
        return ResponseUtil.ok(data);
    }
}