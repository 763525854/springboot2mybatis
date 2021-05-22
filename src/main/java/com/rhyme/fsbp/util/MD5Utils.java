package com.rhyme.fsbp.util;

import org.apache.shiro.crypto.hash.Md5Hash;

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
}
