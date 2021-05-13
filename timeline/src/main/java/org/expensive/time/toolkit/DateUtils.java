package org.expensive.time.toolkit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final String DEFAULT_DATE_PATTER = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date){
        return format(date, DEFAULT_DATE_PATTER);
    }

    public static String format(Date date, String pattern){
        if(StringUtils.isEmpty(pattern)) pattern = DateUtils.DEFAULT_DATE_PATTER;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
