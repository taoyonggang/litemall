namespace java com.thrift.server  // defines the namespace
  
typedef i32 int

struct User {
  1: i32 id = 0,
  2: optional string username,
  3: optional string password,
  4: optional i8 gender,
  5: optional string birthday,
  7: optional string lastLoginTime,
  8: optional string lastLoginIp,
  9: optional i8 userLevel,
  10:optional string nickname,
  11:optional string mobile,
  12:optional string avatar,
  13:optional string weixinOpenid,
  14:optional i8 status,
  15:optional string addTime,
  16:optional string updateTime,
  17:optional int integral,
  18:optional int grade,
  19:optional string fromsouce,
  20:optional string address,
  21:optional string babybirthday,
  22:optional string babybirthday2,
  23:optional i8 babysex,
  24:optional i8 babysex2,
  25:optional string memberUsername,
  26:optional int company_id,
  27:optional string user_type,
  28:optional string origin,
  29:optional string province,
  30:optional string city,
  31:optional string area

}
  
service AyService {
     //-1=failed,0=exist,500=add success
     int addUser(1:User user),
     User getUserInfo(1:string mobile)
}