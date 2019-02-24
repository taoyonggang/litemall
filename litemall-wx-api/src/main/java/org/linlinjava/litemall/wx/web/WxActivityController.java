package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallActivity;
import org.linlinjava.litemall.db.domain.LitemallGoods;
import org.linlinjava.litemall.db.domain.LitemallTopic;
import org.linlinjava.litemall.db.service.LitemallActivityService;
import org.linlinjava.litemall.db.service.LitemallGoodsService;
import org.linlinjava.litemall.db.service.LitemallTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

/**
 * 专题服务
 */
@RestController
@RequestMapping("/wx/activity")
@Validated
public class WxActivityController {
    private final Log logger = LogFactory.getLog(WxActivityController.class);

    @Autowired
    private LitemallTopicService topicService;
    @Autowired
    private LitemallActivityService activityService;
    @Autowired
    private LitemallGoodsService goodsService;

    /**
     * 某个专题列表活动，自己推荐的用户列表
     *
     * @param page 分页页数
     * @param size 分页大小
     * @return 推荐人列表
     */
    @GetMapping("list")
    public Object list(@RequestParam(defaultValue = "-1") Integer activityId,
                       @RequestParam(defaultValue = "-1") Integer promoterId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<LitemallActivity> topicList = activityService.queryList(activityId,promoterId,page, size, sort, order);
        int total = activityService.queryTotal(activityId,promoterId);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", topicList);
        data.put("count", total);
        return ResponseUtil.ok(data);
    }

    /**
     * 添加活动用户
     * @param activityId
     * @param promoterId
     * @param userId
     * @param orign
     * @return
     */
    @GetMapping("addActivityUser")
    public Object addActivityUser(@RequestParam(defaultValue = "-1") Integer activityId,
                               @RequestParam(defaultValue = "-1") Integer promoterId,
                               @RequestParam(defaultValue = "-1") Integer userId,
                               @RequestParam(defaultValue = "线下推广") String orign
                               ){
        LitemallActivity activity =  new LitemallActivity();
        activity.setActivityId(activityId);
        activity.setPromoterId(promoterId);
        activity.setUserId(userId);
        activity.setAddTime(now());
        activity.setOrign(orign);
        int result = activityService.add(activity);

        Map<String, Object> data = new HashMap<String, Object>();
        if (result>0) {
            data.put("result", result);
        }
        return ResponseUtil.ok(data);
    }


}