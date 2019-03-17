package org.linlinjava.litemall.core.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
}
