package com.group.five.utils;

import com.group.five.exceptions.ParamsException;

public class AssertUtil {

    public  static void isTrue(Boolean flag,String msg){
        if(flag){
            throw  new ParamsException(msg);
        }
    }

}
