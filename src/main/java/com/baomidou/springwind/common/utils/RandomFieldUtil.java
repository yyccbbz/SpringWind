package com.baomidou.springwind.common.utils;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by zizou on 2017/5/18.
 */
public class RandomFieldUtil {


    /**
     * 产生随机的18位数字字符串
     * 用于身份证号
     *
     * @return
     */
    public static String randomIDNo() {
        String s = "";
        Random random = new Random();
        s += random.nextInt(9) + 1;
        for (int j = 0; j < 18 - 1; j++) {
            s += random.nextInt(10);
        }
        return s;
    }

    /**
     * 产生随机的2位数字字符串
     * 用于身份证号
     *
     * @return
     */
    public static String randomAgeNo() {
        String s = "";
        Random random = new Random();
        s += random.nextInt(9) + 1;
        for (int j = 0; j < 2 - 1; j++) {
            s += random.nextInt(10);
        }
        return s;
    }
    /**
     * 产生随机的6位数字字符串
     * 用于资产余额
     *
     * @return
     */
    public static String randomAssetNo() {
        String s = "";
        Random random = new Random();
        s += random.nextInt(9) + 1;
        for (int j = 0; j < 6 - 1; j++) {
            s += random.nextInt(10);
        }
        return s;
    }

    /**
     * 产生随机的11位数字字符串
     * 用于手机号
     *
     * @return
     */
    public static String randomPhoneNo() {
        String s = "";
        Random random = new Random();
        s += random.nextInt(9) + 1;
        for (int j = 0; j < 11 - 1; j++) {
            s += random.nextInt(10);
        }
        return s;
    }

    /**
     * 产生随机的11位数字
     * 用于手机号
     *
     * @return
     */
    public static Long randomPhoneNumber() {
        String s = "";
        Random random = new Random();
        s += random.nextInt(9) + 1;
        for (int j = 0; j < 11 - 1; j++) {
            s += random.nextInt(10);
        }
        return Long.parseLong(s);
    }

    public static void main(String[] a) {
        String s = "";
        Random random = new Random();
        s += random.nextInt(9) + 1;
        for (int i = 0; i < 11 - 1; i++) {
            s += random.nextInt(10);
            System.out.println("s = " + s);
        }
        BigInteger bigInteger = new BigInteger(s);
        System.out.println(bigInteger);
    }


}
