var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

var app = getApp();

Page({
  data: {
    type: 4,
    abs: false,
    status: 0,
    page: 1,
    size: 8,
    totalPages: 1,
    scrollTop: 0,
    showPage: false,
    hasMoreData: true,
    integralSum: 0,
    ingegral: 0,
    integrals: [],
    nickname: '',
    avatar: '',
    userInfo:'',
    hasLogin: false,
  },
  getIntegrals() {
    let that = this;
    util.request(api.ListTopDonate, {
      type: that.data.type,
      page: that.data.page,
      size: that.data.size,
      abs: that.data.abs
    }).then(function (res) {
      if (res.errno === 0) {
        if (res.data.myIntegrals.length === 0) {
          that.setData({
            integralSum: 0,
            nickname: that.data.userInfo.nickName,
            avatar: that.data.userInfo.avatarUrl,
            integrals: that.data.integrals.concat(res.data.integrals),
            totalPages: res.data.totalPages,
            showPage: true,
            scrollTop: 0,
          })
        } else {
          that.setData({
            integralSum: res.data.myIntegrals[0].sum,
            nickname: res.data.myIntegrals[0].nickname,
            avatar: res.data.myIntegrals[0].avatar,
            integrals: that.data.integrals.concat(res.data.integrals),
            totalPages: res.data.totalPages,
            showPage: true,
            scrollTop: 0,
          });
        }

      }
      wx.hideLoading();
    });
  },
  onLoad: function (options) {
    //获取用户的登录信息
    if (app.globalData.hasLogin) {
      let userInfo = wx.getStorageSync('userInfo');
      this.setData({
        userInfo: userInfo,
        hasLogin: true
      });
      this.getIntegrals();
    }
  
  },
  onPullDownRefresh() {
    wx.showNavigationBarLoading() //在标题栏中显示加载
    if (this.data.totalPages > this.data.page) {
      this.setData({
        page: this.data.page + 1
      });
      this.getIntegrals();
    } else {
      wx.showToast({
        title: '没有更多捐赠积分记录了',
        icon: 'none',
        duration: 2000
      });
      wx.hideNavigationBarLoading() //完成停止加载
      wx.stopPullDownRefresh() //停止下拉刷新
      return false;
    }

  },
  onReachBottom() {
    if (this.data.totalPages > this.data.page) {
      this.setData({
        page: this.data.page + 1
      });
      this.getIntegrals();
    } else {
      wx.showToast({
        title: '没有更多捐赠积分记录了',
        icon: 'none',
        duration: 2000
      });
      return false;
    }
  },
  onReady: function () {

  },
  onShow: function () {

  },
  onHide: function () {
    // 页面隐藏

  },
  onUnload: function () {
    // 页面关闭
  },
  //按下事件开始  
  touchStart: function (e) {
    let that = this;
    that.setData({
      touchStart: e.timeStamp
    })
  },
  //按下事件结束  
  touchEnd: function (e) {
    let that = this;
    that.setData({
      touchEnd: e.timeStamp
    })
  },
  switchTab: function (e) {

    this.setData({
      integrals: [],
      status: e.currentTarget.dataset.index,
      page: 1,
      size: 8,
      count: 0,
      scrollTop: 0,
      showPage: false
    });

    this.getIntegrals();
  }

})