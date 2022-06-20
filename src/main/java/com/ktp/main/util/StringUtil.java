package com.ktp.main.util;

public class StringUtil {

    public static boolean isEmpty(String ...str){
        if (str.length == 0){
            return true;
        }
        for (String s : str) {
            if (s == null || s.length() == 0){
                return true;
            }
        }
        return false;
    }

}
