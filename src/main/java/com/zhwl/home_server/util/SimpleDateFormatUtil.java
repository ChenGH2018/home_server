package com.zhwl.home_server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2018/1/29.
 */
public class SimpleDateFormatUtil {

    public static SimpleDateFormat YMDFormat(){
        return new SimpleDateFormat("yyyy-MM-dd");
    }


    public static String YMDStr(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static Date YMDDate(String dateStr){
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

}
