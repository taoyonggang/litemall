var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var user = require('../../../utils/user.js');
var app = getApp();

Page({
  data: {
    userInfo: {
      nickName: '点击登录',
      avatarUrl: 'http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png'
    },
    order: {
      unpaid: 0,
      unship: 0,
      unrecv: 0,
      uncomment: 0
    },
    hasLogin: false,
    integral: {
      type: 0,
      integralSum: 0,
      integral: [],
      page: 1,
      size: 10,
      totalPages: 1
    },
    integralSum: 0,
  },
  changeData: function (data) {
    var options = { 'integral': this.data }
    this.onLoad(options)
  },
  onloadData: function () {
    let that = this;
    util.request(api.IntegralsIndex, {
      type: that.data.integral.type,
      page: that.data.integral.page,
      size: that.data.integral.size
    }).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          integral: res.data,
          integralSum: res.data.integralSum,
        });
      }
    });
  },
  onLoad: function(options) {
    // 页面初始化 options为页面跳转所带来的参数
  },
  onReady: function() {

  },
  onShow: function () {

    //获取用户的登录信息
    if (app.globalData.hasLogin) {
      let userInfo = wx.getStorageSync('userInfo');
      this.setData({
        userInfo: userInfo,
        hasLogin: true
      });

      let that = this;
      util.request(api.UserIndex).then(function(res) {
        if (res.errno === 0) {
          that.setData({
            order: res.data.order
          });
        }
      });
    }
      let that = this;
      util.request(api.IntegralsIndex, {
        type: that.data.integral.type,
        page: that.data.integral.page,
        size: that.data.integral.size
      }).then(function (res) {
        if (res.errno === 0) {
          that.setData({
            integral: res.data,
            integralSum: res.data.integralSum,
           // integrals: that.data.integrals.concat(res.data.integrals),
           // totalPages: res.data.totalPages
          });
        }
        wx.hideLoading();
      });


  },
  onHide: function() {
    // 页面隐藏

  },
  onUnload: function() {
    // 页面关闭
  },
  goLogin() {
    if (!this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    }
  },
  goOrder() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/order/order"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    }
  },
  goOrderIndex(e) {
    if (this.data.hasLogin) {
      let tab = e.currentTarget.dataset.index
      let route = e.currentTarget.dataset.route
      try {
        wx.setStorageSync('tab', tab);
      } catch (e) {

      }
      wx.navigateTo({
        url: route,
        success: function(res) {},
        fail: function(res) {},
        complete: function(res) {},
      })
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goCoupon() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/couponList/couponList"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goGroupon() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/groupon/myGroupon/myGroupon"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goIntegral() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/integrals/integrals"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    }
  },
  goEventCheckIn() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/eventCheckIn/eventCheckIn"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    }
  },
  goIntegrals() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/integrals/integrals"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    }
  },
  goRecord() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/activityrecord/activityrecord"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    }
  },
  goCommonWeal() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/commonweal/commonweal"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    }
  },
  goCollect() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/collect/collect"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goFeedback(e) {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/feedback/feedback"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goUserInfo(e) {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/userinfo/userinfo"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goFootprint() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/footprint/footprint"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  goAddress() {
    if (this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/ucenter/address/address"
      });
    } else {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    };
  },
  bindPhoneNumber: function(e) {
    if (e.detail.errMsg !== "getPhoneNumber:ok") {
      // 拒绝授权
      return;
    }

    if (!this.data.hasLogin) {
      wx.showToast({
        title: '绑定失败：请先登录',
        icon: 'none',
        duration: 2000
      });
      return;
    }

    util.request(api.AuthBindPhone, {
      iv: e.detail.iv,
      encryptedData: e.detail.encryptedData
    }, 'POST').then(function(res) {
      if (res.errno === 0) {
        wx.showToast({
          title: '绑定手机号码成功',
          icon: 'success',
          duration: 2000
        });
      }
    });
  },
  goAfterSale: function() {
    wx.showToast({
      title: '目前不支持',
      icon: 'none',
      duration: 2000
    });
  },
  aboutUs: function() {
    wx.navigateTo({
      url: '/pages/about/about'
    });
  },
  exitLogin: function() {
    wx.showModal({
      title: '',
      confirmColor: '#b4282d',
      content: '退出登录？',
      success: function(res) {
        if (!res.confirm) {
          return;
        }

        util.request(api.AuthLogout, {}, 'POST');
        app.globalData.hasLogin = false;
        wx.removeStorageSync('token');
        wx.removeStorageSync('userInfo');
        wx.reLaunch({
          url: '/pages/index/index'
        });
      }
    })

  },
  onPullDownRefresh() {
    wx.showNavigationBarLoading() //在标题栏中显示加载
    this.getIntegrals();
    wx.hideLoading();
    wx.hideNavigationBarLoading() //完成停止加载
    wx.stopPullDownRefresh() //停止下拉刷新
    }
})