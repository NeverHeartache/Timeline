package org.expensive.common.utils;

public class StringUtils {
    public static boolean isEmpty(String str){
        if(StringUtils.isNull(str)) return true;
        return "".equals(str.trim());
    }
    public static boolean isNull(String string){
        return string == null;
    }
    public static boolean isNotNull(String str){
        return !StringUtils.isNull(str);
    }
}
