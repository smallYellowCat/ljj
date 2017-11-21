package com.hzdz.ls.common;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

public class Base64Util {

    public static String decodeBase64(String path ,String input) throws Exception {
        Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod = clazz.getMethod("decode", String.class);
        mainMethod.setAccessible(true);
        byte[] bt = (byte[]) mainMethod.invoke(null, input);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(path));
            out.write(bt);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  path;
    }
}
