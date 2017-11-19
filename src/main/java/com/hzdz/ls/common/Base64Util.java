package com.hzdz.ls.common;

import java.io.File;
import java.lang.reflect.Method;

public class Base64Util {

    public static byte[] decodeBase64(String path ,String input) throws Exception {
        Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod = clazz.getMethod("decode", String.class);
        mainMethod.setAccessible(true);
        Object retObj = mainMethod.invoke(null, input);
        return (byte[]) retObj;
    }
}
