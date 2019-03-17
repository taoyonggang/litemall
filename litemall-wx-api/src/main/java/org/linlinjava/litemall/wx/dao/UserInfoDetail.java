package org.linlinjava.litemall.wx.dao;

public class UserInfoDetail extends UserInfo {


    private Integer userId;
    private String userName;
    private String birthday;
    private String babybirthday;
    private String address;
    private String fromSource;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBabybirthday() {
        return babybirthday;
    }

    public void setBabybirthday(String babybirthday) {
        this.babybirthday = babybirthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFromSource() {
        return fromSource;
    }

    public void setFromSource(String fromSource) {
        this.fromSource = fromSource;
    }
}
