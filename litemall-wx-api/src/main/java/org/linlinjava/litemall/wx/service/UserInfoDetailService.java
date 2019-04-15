package org.linlinjava.litemall.wx.service;

import org.linlinjava.litemall.core.util.DateTimeUtil;
import org.linlinjava.litemall.db.domain.LitemallUser;
import org.linlinjava.litemall.db.service.LitemallUserService;
import org.linlinjava.litemall.wx.dao.UserInfo;
import org.linlinjava.litemall.wx.dao.UserInfoDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserInfoDetailService {
    @Autowired
    private LitemallUserService userService;

    public UserInfoDetail getInfo(Integer userId) {
        LitemallUser user = userService.findById(userId);
        Assert.state(user != null, "用户不存在");
        UserInfoDetail userInfo = new UserInfoDetail();
        userInfo.setUserId(userId);
        userInfo.setUserName(user.getUsername());
        userInfo.setNickName(user.getNickname());
        userInfo.setAvatarUrl(user.getAvatar());
        userInfo.setBirthday(DateTimeUtil.getDateDisplayString(user.getBirthday()));
        userInfo.setBabybirthday(DateTimeUtil.getDateDisplayString(user.getBabybirthday()));
        userInfo.setAddress(user.getAddress());
        userInfo.setFromSource(user.getFromsouce());
        userInfo.setBabybirthday2(DateTimeUtil.getDateDisplayString(user.getBabybirthday2()));
        userInfo.setMobile(user.getMobile());
        userInfo.setBabysex(user.getBabysex());
        userInfo.setBabysex2(user.getBabysex2());
        userInfo.setMemberUsername(user.getMemberUsername());
        return userInfo;
    }
}
