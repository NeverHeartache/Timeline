package org.expensive.time.toolkit;

public class StringUtils {
    public static boolean isEmpty(String str){
        if(StringUtils.isNull(str)) return true;
        if("".equals(str.trim())){
            return true;
        }
        return false;
    }
    public static boolean isNull(String string){
        return string == null;
    }
    public static boolean isNotNull(String str){
        return !StringUtils.isNull(str);
    }
}
