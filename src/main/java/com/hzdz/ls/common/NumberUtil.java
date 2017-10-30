package com.hzdz.ls.common;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
*数字处理工具类
*@author 豆豆
*时间:
*/
public class NumberUtil{

    /**
     * 产生指定长度的随机数
     * @param length
     * @return
     */
    public  static int createNum(int length){
        int max, min;
        max = (int) (Math.pow(10, length) - 1);
        min = (int) Math.pow(10, length - 1);
        return new Random().nextInt(max) % (max - min + 1) + min;
    }

    /***
     * 判断是否是数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        }
        return false;
    }

}
