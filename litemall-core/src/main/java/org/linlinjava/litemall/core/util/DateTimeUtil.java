package org.linlinjava.litemall.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期格式化工具类
 */
public class DateTimeUtil {

    /**
     * 格式 yyyy年MM月dd日 HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String getDateTimeDisplayString(LocalDateTime dateTime) {
        try {
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
            String strDate2 = dtf2.format(dateTime);
            return strDate2;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static String getDateDisplayString(LocalDate date) {
        try {
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String strDate2 = dtf2.format(date);
            return strDate2;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static LocalDate StringToLocalDate(String dayDate) {
        Date birthdayDate = null;
        Instant instant = null;
        ZoneId zoneId = ZoneId.systemDefault();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            birthdayDate = format.parse(dayDate);
            instant = birthdayDate.toInstant();
            LocalDate birthdayLocalDate = instant.atZone(zoneId).toLocalDate();
            return birthdayLocalDate;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static LocalDateTime StringToLocalDateTime(String dayDate) {
        Date birthdayDate = null;
        Instant instant = null;
        ZoneId zoneId = ZoneId.systemDefault();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            birthdayDate = format.parse(dayDate);
            instant = birthdayDate.toInstant();
            LocalDateTime birthdayLocalDate = instant.atZone(zoneId).toLocalDateTime();
            return birthdayLocalDate;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
