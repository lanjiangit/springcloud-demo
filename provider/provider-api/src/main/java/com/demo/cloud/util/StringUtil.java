package com.demo.cloud.util;

/**
 * @author 蓝健
 * @version 1.0
 * @date 2020/8/20 15:48
 */
public class StringUtil {
    private StringUtil(){
        throw new RuntimeException("系统异常");
    }
    
    public static boolean isBlank(String str){
        if (null == str || str.isEmpty()) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
