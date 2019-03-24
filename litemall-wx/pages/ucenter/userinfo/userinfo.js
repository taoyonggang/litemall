var util = require('../../../utils/util.js');
var check = require('../../../utils/check.js');
var api = require('../../../config/api.js');

var app = getApp();

Page({
  data: {
    array: ['选择信息来源','活动推荐','门店','专业推荐','广告推荐','自主注册','其他'],
    index: 0,
    content: '',
    contentLength: 0,
    datePickerValueUser: ['', '', ''],
    datePickerIsShowUser: false,
    datePickerValue: ['', '', ''],
    datePickerIsShow: false,
    address: '',
    cityPickerValue: [0, 0],
    cityPickerIsShow: false,
    nickname:'',
    mobile: '',
    birthday: '',
    babybirthday: '',
    userInfo: {
            nickName: 'test',
            avatarUrl: 'http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png',
            id: 0,
            birthday:'',
            babybirthday:'',
            address:'',
            fromSource:''
        },
    udername:'',
    id:'',
    fromSource:'',
  },
  bindPickerChange: function (e) {
    this.setData({
      index: e.detail.value
    });
  },
  nicknameInput: function (e) {
    this.setData({
      nickname: e.detail.value
    });
  },
  addressInput:function(e){
    this.setData({
      address: e.detail.value
    });
  },
  showUserDatePicker: function (e) {
    // this.data.datePicker.show(this);
    this.setData({
      datePickerIsShowUser: true,
    });
  },
  showBabyDatePicker: function (e) {
    // this.data.datePicker.show(this);
    this.setData({
      datePickerIsShow: true,
    });
  },
  datePickerOnSureClickUser: function (e) {
    console.log('datePickerOnSureClickUser');
    console.log(e);
    this.setData({
      birthday: `${e.detail.value[0]}-${e.detail.value[1]}-${e.detail.value[2]}`,
      datePickerValueUser: e.detail.value,
      datePickerIsShowUser: false,
    });
  },

  datePickerOnCancelClickUser: function (event) {
    console.log('datePickerOnCancelClickUser');
    console.log(event);
    this.setData({
      datePickerIsShowUser: false,
    });
  },
  datePickerOnSureClick: function (e) {
    console.log('datePickerOnSureClick');
    console.log(e);
    this.setData({
      babybirthday: `${e.detail.value[0]}-${e.detail.value[1]}-${e.detail.value[2]}`,
      datePickerValue: e.detail.value,
      datePickerIsShow: false,
    });
  },

  datePickerOnCancelClick: function (event) {
    console.log('datePickerOnCancelClick');
    console.log(event);
    this.setData({
      datePickerIsShow: false,
    });
  },
  /**
   * 城市选择确认
   */
  cityPickerOnSureClick: function (e) {
    console.log('cityPickerOnSureClick');
    console.log(e);
    this.setData({
      address: e.detail.valueName[0] + e.detail.valueName[1],
      cityPickerValue: e.detail.valueCode,
      cityPickerIsShow: false,
    });

  },
  /**
   * 城市选择取消
   */
  cityPickerOnCancelClick: function (event) {
    console.log('cityPickerOnCancelClick');
    console.log(event);
    this.setData({
      cityPickerIsShow: false,
    });
  },


  showCityPicker() {
    // this.data.cityPicker.show()
    this.setData({
      cityPickerIsShow: true,
    });
  },
  submitFeedback: function (e) {
    if (!app.globalData.hasLogin) {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    }

    let that = this;

    var fromsouce = that.data.array[that.data.index];
    this.setData({
      fromSource: fromsouce
    })
    if (that.data.nickname == '') {
       util.showErrorToast('请输入会员姓名');
       return false;
     }
    if (that.data.birthday == '') {
      util.showErrorToast('请输入会员生日');
      return false;
    }
    if (that.data.babybirthday == '') {
      util.showErrorToast('请输入宝宝生日');
      return false;
    }
    if (that.data.fromSource == '') {
      util.showErrorToast('请输入来源');
      return false;
    }
    if (that.data.address == '') {
      util.showErrorToast('请输入地址');
      return false;
    }

    wx.showLoading({
      title: '提交中...',
      mask: true,
      success: function () {

      }
    });
    

    util.request(api.UpdateUser, {
      nickname: that.data.nickname,
      //mobile: that.data.mobile,
      fromSource: that.data.fromSource,
      birthday: that.data.birthday,
      babybirthday: that.data.babybirthday,
      address: that.data.address,
      //id: that.data.id,
    }, 'POST').then(function (res) {
      wx.hideLoading();
      if (res.errno === 0) {
        wx.showToast({
          title: '提交成功',
          icon: 'success',
          duration: 2000,
          complete: function () {
            that.setData({
            });
            // wx.navigateTo({
            //   url: "/pages/index/index"
            // });
             wx.navigateBack({
              delta: 2
            })
          }
        });
      } else {
        util.showErrorToast(res.errmsg);
      }

    });
  },
  onLoad: function (options) {
    let that = this;
    util.request(api.GetUserDeatil, {
    }).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          nickname: res.data.userDetail.nickName,
          fromSource: res.data.userDetail.fromSource,
          birthday: res.data.userDetail.birthday,
          babybirthday: res.data.userDetail.babybirthday,
          address: res.data.userDetail.address,
        });
      }
    });
  },
  onReady: function () {

  },
  onShow: function () {

  },
  onHide: function () {
    // 页面隐藏

  },
  onUnload: function () {
    // 页面关闭
  },
  clearInput: function (e) {
    switch (e.currentTarget.id) {
      case 'clear-nickname':
        this.setData({
          nickname: ''
        });
        break;
      case 'clear-birthday':
        this.setData({
          birthday: ''
        });
        break;
      case 'clear-babybirthday':
        this.setData({
          babybirthday: ''
        });
        break;
      case 'clear-address':
      this.setData({
        address:''
      }) ; 
    }
  }
  
})