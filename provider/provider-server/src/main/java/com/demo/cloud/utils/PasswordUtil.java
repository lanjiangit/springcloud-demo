package com.demo.cloud.utils;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * @author 蓝健
 * @version 1.0
 * @date 2020/8/4 11:17
 */
public class PasswordUtil {
    
    public static String decode(String key, String after){
        try {
            String password = ConfigTools.decrypt(key, after);
            return password;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String encode(String key, String before){
        
        try {
            String password = ConfigTools.encrypt(key, before);
            return password;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
