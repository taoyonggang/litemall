var util = require('../../../utils/util.js');
var check = require('../../../utils/check.js');
var api = require('../../../config/api.js');

var app = getApp();

Page({
  data: {
    array: ['选择信息来源', '专业推荐', '活动推荐', '门店', '广告推荐', '自主注册', '其他'],
    babysexarray: ['宝宝性别', '男', '女'],
    babysexarray2: ['宝宝性别（二胎）', '男', '女'],
    indexsex: 0,
    indexsex2: 0,
    index: 0,
    content: '',
    contentLength: 0,
    datePickerValueUser: ['', '', ''],
    datePickerIsShowUser: false,
    datePickerValue: ['', '', ''],
    datePickerIsShow: false,
    datePickerValue2: ['', '', ''],
    datePickerIsShow2: false,
    address: '',
    cityPickerValue: [0, 0],
    cityPickerIsShow: false,
    nickname: '',
    mobile: '',
    birthday: '',
    babybirthday: '',
    babybirthday2: '',
    userInfo: {
      nickName: 'test',
      avatarUrl: 'http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png',
      id: 0,
      birthday: '',
      babybirthday: '',
      babybirthday2: '',
      address: '',
      fromSource: '',
      mobile: '',
      babysex: '',
      babysex2: '',
      memberUsername: ''
    },
    integral: {
      type: 0,
      integralSum: 0,
      integral: [],
      page: 1,
      size: 10,
      totalPages: 1
    },
    udername: '',
    id: '',
    fromSource: '',
    babysex: '',
    babysex2: '',
    code: '',
    memberUsername: '',
    region: ['地址'],
   // customItem: '全部',
    regioncode: '',
  },
  bindRegionChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      region: e.detail.value,
      address: e.detail.value[0] + " " + e.detail.value[1] + " " + e.detail.value[2],
      regioncode: e.detail.code[0] + " " + e.detail.code[1] + " " + e.detail.code[2],
    })
    console.log('picker发送选择改变，address', this.data.address)
    console.log('picker发送选择改变，code', this.data.regioncode)
  },
  bindPickerChange: function (e) {
    this.setData({
      index: e.detail.value
    });
  },
  bindPickerChangeSex: function (e) {
    this.setData({
      indexsex: e.detail.value
    });
  },
  bindPickerChangeSex2: function (e) {
    this.setData({
      indexsex2: e.detail.value
    });
  },
  nicknameInput: function (e) {
    this.setData({
      nickname: e.detail.value
    });
  },
  memberUsernameInput: function (e) {
    this.setData({
      memberUsername: e.detail.value
    });
  },
  mobileInput: function (e) {
    this.setData({
      mobile: e.detail.value
    });
  },
  addressInput: function (e) {
    this.setData({
      address: e.detail.value
    });
  },
  bindCodeInput: function (e) {
    this.setData({
      code: e.detail.value
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
  showBabyDatePicker2: function (e) {
    // this.data.datePicker.show(this);
    this.setData({
      datePickerIsShow2: true,
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
  datePickerOnSureClick2: function (e) {
    console.log('datePickerOnSureClick2');
    console.log(e);
    this.setData({
      babybirthday2: `${e.detail.value[0]}-${e.detail.value[1]}-${e.detail.value[2]}`,
      datePickerValue2: e.detail.value,
      datePickerIsShow2: false,
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
      address: e.detail.valueName[0] +" "+ e.detail.valueName[1],
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
  /**
   * 宝宝生日（一胎）选择取消
   */
  datePickerOnCancelClick: function (event) {
    console.log('datePickerOnCancelClick');
    console.log(event);
    this.setData({
      datePickerIsShow: false,
    });
  },
  /**
 *  宝宝生日（二胎）选择取消
 */
  datePickerOnCancelClick2: function (event) {
    console.log('datePickerOnCancelClick2');
    console.log(event);
    this.setData({
      datePickerIsShow2: false,
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
    var babysex = that.data.babysexarray[that.data.indexsex];
    var babysex2 = that.data.babysexarray2[that.data.indexsex2];
    if (babysex == '男') {
      babysex = 1
    } else if (babysex == '女') {
      babysex = 2
    } else {
      babysex = 0
    }
    if (babysex2 == '男') {
      babysex2 = 1
    } else if (babysex2 == '女') {
      babysex2 = 2
    } else {
      babysex2 = 0
    }
    this.setData({
      fromSource: fromsouce,
      babysex: babysex,
      babysex2: babysex2
    })
    // if (that.data.nickname == '') {
    //    util.showErrorToast('请输入会员姓名');
    //    return false;
    //  }
    if (that.data.memberUsername == '') {
      util.showErrorToast('请输入会员姓名');
      return false;
    }
    if (that.data.mobile == '') {
      util.showErrorToast('请输入手机号码');
      return false;
    }
    if (that.data.babybirthday == '') {
      util.showErrorToast('请输入宝宝生日');
      return false;
    }
    if (that.data.babysex == '') {
      util.showErrorToast('请输入宝宝性别');
      return false;
    }
    // if (that.data.babybirthday == '') {
    //   util.showErrorToast('请输入宝宝生日(二胎)');
    //   return false;
    // }
    // if (that.data.babysex2 == '') {
    //   util.showErrorToast('请输入宝宝生日(二胎)性别');
    //   return false;
    // }
    if (that.data.fromSource == '') {
      util.showErrorToast('请输入来源');
      return false;
    }
    if (that.data.address == '') {
      util.showErrorToast('请输入地址');
      return false;
    }
    if (that.data.region == '') {
      util.showErrorToast('请输入地址');
      return false;
    }
    if (that.data.mobile == '') {
      util.showErrorToast('请输入手机号');
      return false;
    }
    if (that.data.code == '') {
      util.showErrorToast('请输入验证码');
      return false;
    }
    if (!check.isValidPhone(this.data.mobile)) {
      wx.showModal({
        title: '错误信息',
        content: '手机号输入不正确',
        showCancel: false
      });
      return false;
    }
    wx.showLoading({
      title: '提交中...',
      mask: true,
      success: function () {

      }
    });


    util.request(api.UpdateUser, {
      memberUsername: that.data.memberUsername,
      nickname: that.data.nickname,
      mobile: that.data.mobile,
      fromSource: that.data.fromSource,
      babybirthday: that.data.babybirthday,
      babybirthday2: that.data.babybirthday2,
      address: that.data.address,
      regioncode: that.data.regioncode,
      babysex: that.data.babysex,
      babysex2: that.data.babysex2,
      code: that.data.code
      //id: that.data.id,
    }, 'POST').then(function (res) {
      wx.hideLoading();
      if (res.errno === 0) {
        wx.showToast({
          title: '提交成功',
          icon: 'success',
          duration: 2000,
          complete: function () {
            // wx.navigateTo({
            //   url: "/pages/index/index"
            // });
            var that = this

            //判断页面栈里面的页面数是否大于2
            if (getCurrentPages().length > 2) {
              //获取页面栈
              let pages = getCurrentPages()
              //给上一个页面设置状态
              let curPage = pages[pages.length - 2];
              let data = curPage.data;
              curPage.setData({ 'isBack': true });
            }
            wx.navigateBack({
              delta: 1
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
        var fromSource = res.data.userDetail.fromSource;
        var babysex = res.data.userDetail.babysex;
        var babysex2 = res.data.userDetail.babysex2;
        if (fromSource == '活动推荐') {
          that.setData({
            index: 1
          })
        } else if (fromSource == '门店') {
          that.setData({
            index: 2
          })
        } else if (fromSource == '专业推荐') {
          that.setData({
            index: 3
          })
        } else if (fromSource == '广告推荐') {
          that.setData({
            index: 4
          })
        } else if (fromSource == '自主注册') {
          that.setData({
            index: 5
          })
        } else if (fromSource == '其他') {
          that.setData({
            index: 6
          })
        }
        if (babysex == 1) {
          that.setData({
            indexsex: 1
          })
        } else if (babysex == 2) {
          that.setData({
            indexsex: 2
          })
        } else if (babysex == 0) {
          that.setData({
            indexsex: 0
          })
        }
        if (babysex2 == 1) {
          that.setData({
            indexsex2: 1
          })
        } else if (babysex2 == 2) {
          that.setData({
            indexsex2: 2
          })
        } else if (babysex2 == 0) {
          that.setData({
            indexsex2: 0
          })
        }
        if (res.data.userDetail.memberUsername != undefined) {
          that.setData({
            memberUsername: res.data.userDetail.memberUsername,
          });
        }
        if (res.data.userDetail.nickName != undefined) {
          that.setData({
            nickname: res.data.userDetail.nickName,
          });
        }
        if (res.data.userDetail.mobile != undefined) {
          that.setData({
            mobile: res.data.userDetail.mobile,
          });
        }
        if (res.data.userDetail.babybirthday != undefined) {
          that.setData({
            babybirthday: res.data.userDetail.babybirthday,
          });
        }
        if (res.data.userDetail.babybirthday2 != undefined) {
          that.setData({
            babybirthday2: res.data.userDetail.babybirthday2,
          });
        }
        if (res.data.userDetail.address != undefined) {
          that.setData({
            address: res.data.userDetail.address,
          });
        }
        if (res.data.userDetail.address != undefined) {
          var address1 = res.data.userDetail.address.trim().split(/\s+/);
         console.log(address1);
          that.setData({
            region: address1,
          });
        }
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
    var that = this

    //判断页面栈里面的页面数是否大于2
    if (getCurrentPages().length > 2) {
      //获取页面栈
      let pages = getCurrentPages()
      //给上一个页面设置状态
      let curPage = pages[pages.length - 2];
      let data = curPage.data;
      curPage.setData({ 'isBack': true });
    }
  },
  clearInput: function (e) {
    switch (e.currentTarget.id) {
      case 'clear-nickname':
        this.setData({
          nickname: ''
        });
        break;
      case 'clear-mobile':
        this.setData({
          mobile: ''
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
          address: ''
        });
    }
  },
  sendCode: function () {
    let that = this;

    if (this.data.mobile.length == 0) {
      wx.showModal({
        title: '错误信息',
        content: '手机号不能为空',
        showCancel: false
      });
      return false;
    }

    if (!check.isValidPhone(this.data.mobile)) {
      wx.showModal({
        title: '错误信息',
        content: '手机号输入不正确',
        showCancel: false
      });
      return false;
    }

    wx.request({
      url: api.AuthRegisterCaptcha,
      data: {
        mobile: that.data.mobile
      },
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (res.data.errno == 0) {
          wx.showModal({
            title: '发送成功',
            content: '验证码已发送',
            showCancel: false
          });
        } else {
          wx.showModal({
            title: '错误信息',
            content: res.data.errmsg,
            showCancel: false
          });
        }
      }
    });
  },
})
