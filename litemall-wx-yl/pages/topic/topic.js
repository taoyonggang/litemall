var util = require('../../utils/util.js');
var api = require('../../config/api.js');
var app = getApp()
Page({
  data: {
    topicType: 0,
    topicList: [],
    page: 1,
    size: 10,
    count: 0,
    scrollTop: 0,
    showPage: false,
    sort: 'end_time'
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    if (options.id) {
      this.setData({
        topicType: parseInt(options.id)
      });
      this.getTopic();
    }

  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },
  nextPage: function (event) {
    var that = this;
    if (this.data.page > that.data.count / that.data.size) {
      return true;
    }


    that.setData({
      page: that.data.page + 1
    });

    this.getTopic();

  },
  getTopic: function () {

    let that = this;
    that.setData({
      scrollTop: 0,
      showPage: false,
      topicList: []
    });
    // 页面渲染完成
    wx.showToast({
      title: '加载中...',
      icon: 'loading',
      duration: 2000
    });

    util.request(api.TopicList, {
      page: that.data.page,
      size: that.data.size,
      topicType: that.data.topicType,
      sort: that.data.sort
    }).then(function (res) {
      if (res.errno === 0) {

        that.setData({
          scrollTop: 0,
          topicList: res.data.data,
          showPage: true,
          count: res.data.count
        });
      }
      wx.hideToast();
    });

  },
  prevPage: function (event) {
    if (this.data.page <= 1) {
      return false;
    }

    var that = this;
    that.setData({
      page: that.data.page - 1
    });
    this.getTopic();
  }
})