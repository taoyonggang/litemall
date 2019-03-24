var api = require('../../../config/api.js');
var util = require('../../../utils/util.js');
var user = require('../../../utils/user.js');

var app = getApp();
Page({
  data: {
    fromId: '',
  },
  onLoad: function(options) {
    // 页面初始化 options为页面跳转所带来的参数
    // 页面渲染完成
    if (options.q) {
      console.log("index 生命周期 onload" + JSON.stringify(options));
      try {
        let q = decodeURIComponent(options.q)
        if (q) {
          console.log("index 生命周期 onload url=" + q)
          var fromId = utils.getQueryString(q, 'id');
          console.log("index 生命周期 onload 参数 id=" + fromId)
          that.setdata({
            fromId: fromId,
          })
        }
      } catch (err) {
        console.error(err) // 可执行
      };

    }

  },
  onReady: function() {

  },
  onShow: function() {
    // 页面显示
  },
  onHide: function() {
    // 页面隐藏

  },
  onUnload: function() {
    // 页面关闭

  },
  wxLogin: function(e) {
    if (e.detail.userInfo == undefined) {
      app.globalData.hasLogin = false;
      util.showErrorToast('微信登录失败');
      return;
    }

    user.checkLogin().catch(() => {
      var that = this;
      user.loginByWeixin(e.detail.userInfo).then(res => {
        app.globalData.hasLogin = true;
        if (that.data.fromId != ""){
          wx.navigateTo({
             url: "/pages/ucenter/userinfo/userinfo"

           });
        }else{
          wx.navigateBack({
            delta: 1
          })
        }
        
      }).catch((err) => {
        app.globalData.hasLogin = false;
        util.showErrorToast('微信登录失败');
      });

    });
  },
  accountLogin: function() {
    wx.navigateTo({
      url: "/pages/auth/accountLogin/accountLogin"
      
    });
  }
})