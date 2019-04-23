package org.linlinjava.litemall.wx.dao;

public class UserInfoDetail extends UserInfo {


    private Integer userId;
    private String userName;
    private String birthday;
    private String babybirthday;
    private String address;
    private String fromSource;
    private String babybirthday2;
    private String mobile;
    private Byte babysex;
    private Byte babysex2;
    private String memberUsername;
    private String regioncode;

    public String getRegioncode() {
        return regioncode;
    }

    public void setRegioncode(String regioncode) {
        this.regioncode = regioncode;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

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

    public String getBabybirthday2() {
        return babybirthday2;
    }

    public void setBabybirthday2(String babybirthday2) {
        this.babybirthday2 = babybirthday2;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Byte getBabysex() {
        return babysex;
    }

    public void setBabysex(Byte babysex) {
        this.babysex = babysex;
    }

    public Byte getBabysex2() {
        return babysex2;
    }

    public void setBabysex2(Byte babysex2) {
        this.babysex2 = babysex2;
    }
}
