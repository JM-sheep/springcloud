package com.aaa.test;

import com.aaa.util.EncryptionUtil;

public class test {
    public static void main(String[] args) {
        String user04 = EncryptionUtil.encPassword("123456", "喜羊羊");
        System.out.println(user04);
    }
}
