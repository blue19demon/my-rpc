package net.cc.service;


import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWTVerifier;

public class EncryptionUtil {
	 /**
     * 解析从client过来的经过JWT加密后的签名
     * @param sign
     * @return
     */
    public static Map<String, Object> decodeJWTPackage(String sign, String key) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = (new JWTVerifier(key).verify(sign));
        } catch (Exception e) {
           e.printStackTrace();
        }
        return map;
    }


    public static String md5(String str) {

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes("utf-8"));
            byte[] digest = md5.digest();
            char[] chars = toHexChars(digest);
            return new String(chars);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    private static char[] md5Chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static char[] toHexChars(byte[] digest) {
        char[] chars = new char[digest.length * 2];
        int i = 0;
        byte[] var6 = digest;
        int var5 = digest.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            byte b = var6[var4];
            char c0 = md5Chars[(b & 240) >> 4];
            chars[i++] = c0;
            char c1 = md5Chars[b & 15];
            chars[i++] = c1;
        }

        return chars;
    }
    
}
