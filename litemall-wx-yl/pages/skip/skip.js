const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const user = require('../../utils/user.js');

//获取应用实例
const app = getApp();

Page({
  data: {
    id:'',
  },
  onLoad: function (options) {
    var ids= options.id;
    console.log("ids:" + ids);
    // // 页面初始化 options为页面跳转所带来的参数
    // if (options.scene) {
    //   //这个scene的值存在则证明首页的开启来源于朋友圈分享的图,同时可以通过获取到的goodId的值跳转导航到对应的详情页
    //   var scene = decodeURIComponent(options.scene);
    //   console.log("scene:" + scene);

    //   let info_arr = [];
    //   info_arr = scene.split(',');
    //   let _type = info_arr[0];
    //   let id = info_arr[1];

    //   if (_type == 'goods') {
    //     wx.navigateTo({
    //       url: '../goods/goods?id=' + id
    //     });
    //   } else if (_type == 'groupon') {
    //     wx.navigateTo({
    //       url: '../goods/goods?grouponId=' + id
    //     });
    //   } else {
    //     wx.navigateTo({
    //       url: '../index/index'
    //     });
    //   }
    // }

    // 页面初始化 options为页面跳转所带来的参数
      if (options.q) {
      console.log("index 生命周期 onload" + JSON.stringify(options));
      //在此函数中获取扫描普通链接二维码参数
      try {
        let q = decodeURIComponent(options.q)
        if (q) {
          console.log("index 生命周期 onload url=" + q)
          var fromId = util.getQueryString(options, 'id');
          console.log("index 生命周期 onload 参数 id=" + fromId)
          that.setData({
            id: fromId
          });
          wx.navigateTo({
            url: '/pages/topicDetail/topicDetail?id=' + id
          });
          return {
            title: '签到',
            desc: '签到',
            path: '/pages/topicDetail/topicDetail?id=' + id
          }
        }
      } catch (err) {
        console.log(err) // 可执行
      };
     
    }
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },
})