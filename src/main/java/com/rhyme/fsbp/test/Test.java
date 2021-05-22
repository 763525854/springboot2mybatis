package com.rhyme.fsbp.test;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.security.Key;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/22 14:12
 */
public class Test {
    //Base64加密
    public static void main1(String[] args) {
        String str = "hello";
        //aGVsbG8=
        String base64Encode = Base64.encodeToString(str.getBytes());
        System.out.println(base64Encode);
        String str2 = Base64.decodeToString(base64Encode);
        System.out.println(str2);
    }

    //Hex 16加密解密
    public static void main2(String[] args) {
        String str = "hello";
        //68656c6c6f
        String base64 = Hex.encodeToString(str.getBytes());
        System.out.println(base64);
        System.out.println(new String(Hex.decode(base64.getBytes())));
    }

    //md5加盐加密
    public static void main3(String[] args) {
        String str = "hello";
        String salt = "123";
        String md5 = new Md5Hash(str, salt).toString();
        System.out.println(md5);
    }

    //sha256，带盐加密
    public static void main4(String[] args) {
        String str = "hello";
        String salt = "123";
        String sha256 = new Sha256Hash(str, salt).toString();
        System.out.println(sha256);
    }

    //sha256,输出格式为128
    public static void main5(String[] args) {
        String str = "hello";
        String salt = "123";
        String sha512 = new Sha512Hash(str, salt).toString();
        System.out.println(sha512);
    }

    public static void main6(String[] args) {
        //默认算法SHA-512
        DefaultHashService hashService = new DefaultHashService();
        //修改默认算法，此处实则为没有修改
        hashService.setHashAlgorithmName("SHA-512");//
        //私盐，默认无
        //hashService.setPrivateSalt(new SimpleByteSource("123"));
        //是否生成公盐，默认无
        hashService.setGeneratePublicSalt(true);
        //用于生成公盐，默认值为SecureRandomNumberGenerator
        //hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        //生成hash值得迭代次数
        hashService.setHashIterations(1);
        //request的salt是公盐
        HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello")).setSalt(ByteSource.Util.bytes("123")).setIterations(1).build();
        //HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello")).setIterations(1).build();
        String str=hashService.computeHash(request).toString();
        System.out.println(str);
    }

    //对称加密
    public static void main(String[] args) {
        AesCipherService aesCipherService=new AesCipherService();
        aesCipherService.setKeySize(128);
        Key key=aesCipherService.generateNewKey();
        String text="hello";
        String encrptText=aesCipherService.encrypt(text.getBytes(),key.getEncoded()).toHex();
        System.out.println(encrptText);
        System.out.println(encrptText.length());
        String text2=new String(aesCipherService.decrypt(Hex.decode(encrptText),key.getEncoded()).getBytes());
        System.out.println(text2);
    }
}
