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
    integral: 0,

  },
  onLoad: function (options) {
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
      integral: parseInt(e.detail.value)
    });
  },
  saveDonate() {
    let that = this;
    if (parseInt(that.data.integral) < 500 || parseInt(that.data.integral) === 500) {
      wx.showToast({
        title: '请输入大于等于500的正整数',
        icon: 'none',
        duration: 2000
      });
      return false;
    } else {
      util.request(api.SaveDonate, {
        integral: that.data.integral,
      }).then(function (res) {
        if (res.errno === 0) {
          wx.showToast({
            title: '捐赠成功',
            icon: 'success',
          });
        } else {
          util.showErrorToast(res.errmsg);
        }

      });
    }


  },
  listDnonate() {
    wx.navigateTo({
      url: "/pages/ucenter/commonwealrecord/commonwealrecord"
    });
  },
  rankDonate() {
    wx.navigateTo({
      url: "/pages/ucenter/commonwealrank/commonwealrank"
    });
  }

})