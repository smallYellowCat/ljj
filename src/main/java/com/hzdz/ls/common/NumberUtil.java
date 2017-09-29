package com.hzdz.ls.common;

import java.util.Random;

public class NumberUtil {
    public  static int createNum(int length){
        int max, min;
        max = (int) (Math.pow(10, length) - 1);
        min = (int) Math.pow(10, length - 1);
        return new Random().nextInt(max) % (max - min + 1) + min;
    }
}
