package com.aaa.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class EncryptionUtil {
    private static String algorithmName = "MD5";    //使用的加密算法的名字
    private static int hashIteration = 1024;    //迭代次数

    /**
     * 将密码进行加密的方法
     * @param oldPassword 传入的原始密码
     * @param salt 盐值
     * @return 返回加密后的密码
     */
    public static String encPassword(String oldPassword,Object salt){
        SimpleHash simpleHash = new SimpleHash(algorithmName,oldPassword,salt,hashIteration);
        String str1 = simpleHash.toBase64();//转换64位的密码
        String str2 = simpleHash.toHex();//转换16进制的密码
        return str1;
    }
}
