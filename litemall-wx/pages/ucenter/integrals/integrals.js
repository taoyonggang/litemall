var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

var app = getApp();

Page({
  data: {
    type: 0,
    integralsum,
    integrals: [],
    page: 1,
    size: 10,
    totalPages: 1
  },
  getIntegrals() {
    wx.showLoading({
      title: '加载中...',
    });
    let that = this;
    util.request(api.IntegralsIndex, {
      type: that.data.type,
      page: that.data.page,
      size: that.data.size
    }).then(function(res) {
      if (res.errno === 0) {
        that.setData({
          integralsum: res.data.integralSum,
          integrals: that.data.integrals.concat(res.data.integrals),
          totalPages: res.data.totalPages
        });
      }
      wx.hideLoading();
    });
  },
  onLoad: function(options) {
    this.getIntegrals();
  },
  onReachBottom() {
    if (this.data.totalPages > this.data.page) {
      this.setData({
        page: this.data.page + 1
      });
      this.getIntegrals();
    } else {
      wx.showToast({
        title: '没有更多用户积分记录了',
        icon: 'none',
        duration: 2000
      });
      return false;
    }
  },
  onReady: function() {

  },
  onShow: function() {

  },
  onHide: function() {
    // 页面隐藏

  },
  onUnload: function() {
    // 页面关闭
  },
  //按下事件开始  
  touchStart: function(e) {
    let that = this;
    that.setData({
      touchStart: e.timeStamp
    })
  },
  //按下事件结束  
  touchEnd: function(e) {
    let that = this;
    that.setData({
      touchEnd: e.timeStamp
    })
  },
})