namespace java com.thrift.server  // defines the namespace
  
typedef i32 int

struct User {
  1: i32 id = 0,
  2: string username,
  3: string password,
  4: optional i8 gender,
  5: optional string birthday,
  7: optional string lastLoginTime,
  8: optional string lastLoginIp,
  9: optional i8 userLevel,
  10: string nickname,
  11: string mobile,
  12: string avatar,
  13: string weixinOpenid,
  14: i8 status,
  15: string addTime,
  16: string updateTime,
  17: int integral,
  18: int grade,
  19: string fromsouce,
  20: string address,
  21: string babybirthday,
  22: string babybirthday2,
  23: i8 babysex,
  24: i8 babysex2,
  25: string memberUsername
}
  
service AyService {
     //-1=failed,0=exist,500=add success
     int addUser(1:User user),
     User getUserInfo(1:string mobile)
}