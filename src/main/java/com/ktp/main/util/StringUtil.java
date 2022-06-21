package com.ktp.main.util;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.keyvalue.core.IdentifierGenerator;

import java.util.Locale;
import java.util.UUID;

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

    public static String getRandomCourseId(){
        return RandomStringUtils.randomAlphanumeric(6).toUpperCase(Locale.ROOT);
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","").substring(0,16);
    }

}
