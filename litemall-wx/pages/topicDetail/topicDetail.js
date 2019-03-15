var app = getApp();
var WxParse = require('../../lib/wxParse/wxParse.js');
var util = require('../../utils/util.js');
var api = require('../../config/api.js');


Page({
    data: {
        id: 0,
        topic: {},
        topicList: [],
        commentCount: 0,
        commentList: [],
        topicGoods: [],
        pageBackgroundColor: '#c79935',
        checkstatus: "签到",
        activityId: 0,
        promoterId: 0,
        userId: 0,
        userInfo: {
            nickName: 'test',
            avatarUrl: 'http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png',
            id: 0,
        },
        hasLogin: false,

    },
    onLoad: function (options) {
        // 页面初始化 options为页面跳转所带来的参数
        var that = this;
        that.setData({
            id: options.id,
            activityId: options.activityId
        });
        if (options.promoterId !== undefined) {
            that.setData({
                promoterId: options.promoterId
            });
        }

        console.log("index 生命周期 onload" + JSON.stringify(options))
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
            }
        } catch (err) {
            console.log(err) // 可执行
        }


        //获取用户的登录信息
        if (app.globalData.hasLogin) {
            let userInfo = wx.getStorageSync('userInfo');
            this.setData({
                userInfo: userInfo,
                hasLogin: true
            });
        }
        util.request(api.TopicDetail, {
            id: that.data.id
        }).then(function (res) {
            if (res.errno === 0) {
                that.setData({
                    topic: res.data.topic,
                    topicGoods: res.data.goods
                });

                WxParse.wxParse('topicDetail', 'html', res.data.topic.content, that);
            }
        });

        util.request(api.TopicRelated, {
            id: that.data.id
        }).then(function (res) {
            if (res.errno === 0) {
                that.setData({
                    topicList: res.data
                });
            }
        });

        util.request(api.SelectActivity, {
            activityId: that.data.id,
        }, 'GET').then(function (res) {
            res = res.data;
            if (res.result == 0) {
                if (res.joinCount > 0) {
                    that.setData({
                        pageBackgroundColor: '#35c735',
                        checkstatus: "已签到"
                    });
                }
            }
        });

    },
    getCommentList() {
        let that = this;
        util.request(api.CommentList, {
            valueId: that.data.id,
            type: 1,
            showType: 0,
            page: 1,
            size: 5
        }).then(function (res) {
            if (res.errno === 0) {
                that.setData({
                    commentList: res.data.data,
                    commentCount: res.data.count
                });
            }
        });
    },
    postComment() {
        if (!app.globalData.hasLogin) {
            wx.navigateTo({
                url: "/pages/auth/login/login"
            });
        } else {
            wx.navigateTo({
                url: '/pages/topicCommentPost/topicCommentPost?valueId=' + this.data.id + '&type=1',
            })
        }
    },
    onReady: function () {

    },
    onShow: function () {
        // 页面显示
        this.getCommentList();
    },
    onHide: function () {
        // 页面隐藏

    },
    onUnload: function () {
        // 页面关闭

    },
    clearCheckIn: function () {
        this.setData({
            code: ''
        });
    },
    goCheckIn: function () {
        let that = this;
        util.request(api.SelectActivity, {
            activityId: that.data.id,
        }, 'GET').then(function (res) {
            res = res.data;
            if (res.result == 0) {
                if (res.joinCount > 0) {
                    wx.showToast({
                        title: "已签到过",
                        duration: 2000
                    })
                } else if (res.actived == 0) {
                    wx.showToast({
                        title: "活动过期",
                        duration: 2000
                    })
                }
            } else if (res.result == 1) {
                util.request(api.CheckIn, {
                    activityId: that.data.id,
                    promoterId: that.data.promoterId,
                    orign: "线下推广"
                }, 'GET').then(function (res) {
                    if (res.errno === 0) {
                        that.clearCheckIn();
                        that.setData({
                            pageBackgroundColor: '#35c735',
                            checkstatus: "签到成功"
                        });
                        wx.showToast({
                            title: "签到成功",
                            duration: 2000
                        })

                        if (that.data.checkstatus == '签到成功') {
                            wx.showToast({
                                title: "已签到",
                                duration: 2000
                            })
                        }
                    }
                })
            }

        })

    },
    shareCode: function () {
        wx.navigateTo({
            //url: '/pages/qrcode/qrcode?id=' + this.data.id + '&promoterId=' + this.data.userInfo.id
            url: '/pages/qrcode/qrcode?id=' + this.data.id
        });
        return {
            title: '生成分享二维码',
            desc: '生成分享二维码',
            path: '/pages/qrcode/qrcode'
        }
    },
    goOut: function () {
        wx.reLaunch({
            url: '/pages/index/index'
        });
    }
})