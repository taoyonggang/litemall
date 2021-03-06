package org.linlinjava.litemall.wx.service;

import org.joda.time.DateTimeUtils;
import org.linlinjava.litemall.core.util.DateTimeUtil;
import org.linlinjava.litemall.db.domain.LitemallUser;
import org.linlinjava.litemall.db.service.LitemallUserService;
import org.linlinjava.litemall.wx.dao.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserInfoService {
    @Autowired
    private LitemallUserService userService;


    public UserInfo getInfo(Integer userId) {
        LitemallUser user = userService.findById(userId);
        Assert.state(user != null, "用户不存在");
        UserInfo userInfo = new UserInfo();
/*        userInfo.setUserId(userId);
        userInfo.setUserName(user.getUsername());*/
        userInfo.setNickName(user.getNickname());
        userInfo.setAvatarUrl(user.getAvatar());
/*        userInfo.setBirthday(DateTimeUtil.getDateDisplayString(user.getBirthday()));
        userInfo.setBabybirthday(DateTimeUtil.getDateDisplayString(user.getBabybirthday()));
        userInfo.setAddress(user.getAddress());
        userInfo.setFromSource(user.getFromsouce());*/
        return userInfo;
    }
}
