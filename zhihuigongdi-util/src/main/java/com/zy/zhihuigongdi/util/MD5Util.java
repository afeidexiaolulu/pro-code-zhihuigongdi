package com.zy.zhihuigongdi.util;

import org.springframework.util.StringUtils;

import java.security.MessageDigest;

/**
 * MD5算法 哈希算法 MD5算法具有以下特点： 1、压缩性：任意长度的数据，算出的MD5值长度都是固定的。 2、容易计算：从原数据计算出MD5值很容易。
        * 3、抗修改性：对原数据进行任何改动，哪怕只修改1个字节，所得到的MD5值都有很大区别。
        * 4、强抗碰撞：已知原数据和其MD5值，想找到一个具有相同MD5值的数据（即伪造数据）是非常困难的。
        */
public class MD5Util {
    public static String digest16(String inStr) {
        return digest(inStr, 16);
    }

    public static String digest(String inStr) {
        return digest(inStr, 32);
    }

    /**
     *
     * @param inStr
     * @param rang
     * @return
     */
    private static String digest(String inStr, int rang) {
        //创建md5实例
        MessageDigest md5 = null;
        //如果字符串为空，返回空串
        if (StringUtils.isEmpty(inStr)) {
            return "";
        }

        //获取md5实例，类自带的静态方法，输入算法名称
        try {
            md5 = MessageDigest.getInstance("MD5"); // 取得算法
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        //
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }

        byte[] md5Bytes = md5.digest(byteArray); // 加密

        StringBuilder hexValue = new StringBuilder();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        if (rang == 32) {
            return hexValue.toString();
        } else {
            return hexValue.toString().substring(8, 24);// 转换为32位字符串
        }
    }

    public static void main(String args[]) {
        String s = new String("123");
        System.out.println(digest(s));
    }
}