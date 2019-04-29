var util = require('../../utils/util.js');
var api = require('../../config/api.js');
var WxParse = require('../../lib/wxParse/wxParse.js');
var app = getApp();

Page({
  data: {
    id: 0,
    topic: {},
    topicGoods: [],
    picUrl:'',
    type: 0,
    status: 0,
    page: 1,
    size: 8,
    totalPages: 1,
    scrollTop: 0,
    showPage: false,
    hasMoreData: true,
    integralSum: 0,
    integral:0,
    hasLogin: false,


  },
  getIntegralsIndex() {
    var that = this;
    util.request(api.IntegralsIndex, {
      type: that.data.type,
      page: that.data.page,
      size: that.data.size
    }).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          integralSum: res.data.integralSum,
        });
      }
      wx.hideLoading();
    });
  },
  onLoad: function(options) {
    // 页面初始化 options为页面跳转所带来的参数
    var that = this;
    if (options.id !== undefined) {
      that.setData({
        id: options.id
      });
    }
    util.request(api.TopicDetail, {
      id: that.data.id
    }).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          topic: res.data.topic,
          topicGoods: res.data.goods,
          picUrl: res.data.topic.picUrl
        });
        WxParse.wxParse('topicDetail', 'html', res.data.topic.content, that, 5);
      }
    });
    this.getIntegralsIndex();
  },
  bindExchange: function (e) {
    this.setData({
      integral: parseInt(e.detail.value)
    });
  },
  saveDonate(){
    let that = this;
    if (that.data.integral < 500) {
        wx.showToast({
          title: '请输入大于等于500的正整数',
          icon: 'none',
          duration: 2000
        });
        return false;
        }else{
          util.request(api.SaveDonate, {
            integral: that.data.integral,
          }).then(function (res) {
            if (res.errno === 0) {
              wx.showToast({
                title: '捐赠成功',
                icon: 'success',
                duration: 2000
              });
              that.getIntegralsIndex();
            } else {
              util.showErrorToast(res.errmsg);
            }

          });
        }
      
   
  },
  listDnonate(){
    wx.navigateTo({
      url: "/pages/ucenter/commonwealrecord/commonwealrecord"
    });
  },
  rankDonate(){
    wx.navigateTo({
      url: "/pages/ucenter/commonwealrank/commonwealrank"
    });
  }

})