package org.linlinjava.litemall.db.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class LitemallActivityMore extends LitemallActivity{
      private String title = "";
      private LocalDateTime topicAddTime;
      private LocalDateTime endTime;
      private String username;
      private String nickname;
      private String mobile;
      private LocalDate babybirthday;
      private LocalDate babybirthday2;
      private String memberUsername;
      private String subtitle;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public LocalDate getBabybirthday() {
        return babybirthday;
    }

    public void setBabybirthday(LocalDate babybirthday) {
        this.babybirthday = babybirthday;
    }

    public LocalDate getBabybirthday2() {
        return babybirthday2;
    }

    public void setBabybirthday2(LocalDate babybirthday2) {
        this.babybirthday2 = babybirthday2;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getTopicAddTime() {
        return topicAddTime;
    }

    public void setTopicAddTime(LocalDateTime topicAddTime) {
        this.topicAddTime = topicAddTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}