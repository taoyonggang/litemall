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
    integralSum: 0,
    ingegral:0,

  },
  onLoad: function(options) {
    let that = this;
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
  bindExchange: function (e) {
    this.setData({
      ingegral: e.detail.value
    });
  },
  saveDonate(){
    let that = this;
    if (!(/(^[1-9]\d*$)/.test(that.data.ingegral))) {
      if (parseInt(that.data.ingegral) <= 500) {
        wx.showToast({
          title: '请输入大于等于500的正整数',
          icon: 'none',
          duration: 2000
        });
        return false;
        }
      }
    util.request(api.SaveDonate, {
      ingegral: that.data.ingegral,
    }, 'POST').then(function (res) {
      wx.hideLoading();
      if (res.errno === 0) {
        wx.showToast({
          title: '提交成功',
          icon: 'success',
          duration: 2000,
          complete: function () {
            var that = this

          }
        });
      } else {
        util.showErrorToast(res.errmsg);
      }

    });
  }

})