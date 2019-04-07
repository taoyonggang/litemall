var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

var app = getApp();

Page({
  data: {
    type: 0,
    status: 0,
    page: 1,
    size: 8,
    totalPages: 1,
    scrollTop: 0,
    showPage: false,
    hasMoreData: true,
    topiclist:[],

  },
  getActivity() {
    // wx.showLoading({
    //   title: '加载中...',
    // });
    let that = this;
    util.request(api.SelectActivityRecord, {
      type: that.data.type,
      page: that.data.page,
      size: that.data.size
    }).then(function(res) {
      if (res.errno === 0) {
        that.setData({
          topiclist: res.data.data,
          totalPages: res.data.count,
          showPage: true,
          scrollTop: 0,
        });
      }
      wx.hideLoading();
    });
  },
  onLoad: function(options) {
    this.getActivity();
  },
  onPullDownRefresh() {
    wx.showNavigationBarLoading() //在标题栏中显示加载
    if (this.data.totalPages > this.data.page) {
      this.setData({
        page: this.data.page + 1
      });
      this.getActivity();
    } else {
      wx.showToast({
        title: '没有更多用户活动参与记录了',
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
      this.getActivity();
    } else {
      wx.showToast({
        title: '没有更多用户活动参与记录了',
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
  switchTab: function(e) {

    this.setData({
      topiclist: [],
      status: e.currentTarget.dataset.index,
      page: 1,
      size: 8,
      count: 0,
      scrollTop: 0,
      showPage: false
    });

    this.getActivity();
  }
})