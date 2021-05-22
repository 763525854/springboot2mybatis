package com.rhyme.fsbp.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/22 17:55
 */
public class MD5Utils {
    /**
     * md5加密返回String
     *
     * @param str
     * @return
     */
    public static final String encryptToString(String str) {
        return new Md5Hash(str).toString();
    }

    /**
     * md5带盐加密返回String
     *
     * @param str
     * @param salt
     * @return
     */
    public static final String encryptToStringWithSalt(String str, String salt) {
        return new Md5Hash(str, salt).toString();
    }

    /**
     * md5加密返回Hex
     *
     * @param str
     * @return
     */
    public static final String encryptToHex(String str) {
        return new Md5Hash(str).toHex();
    }

    /**
     * md5带盐加密返回Hex
     *
     * @param str
     * @param salt
     * @return
     */
    public static final String encryptToHexWithSalt(String str, String salt) {
        return new Md5Hash(str, salt).toHex();
    }

    public static void main1(String[] args) {
        String str=MD5Utils.encryptToString("nima2");
        System.out.println(str);
        String ok=new SimpleHash("MD5","nima2").toString();
        System.out.println(ok);

        ok=new SimpleHash("MD5","nima2").toHex();
        System.out.println(ok);
        ok=new Md5Hash("nima2").toString();
        System.out.println(ok);

        String str2=MD5Utils.encryptToHex("nima2");
        System.out.println(str2);
    }
}
