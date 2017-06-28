package test;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/28
 * @Time: 17:25
 * @Description:
 */
public class RandomStringUtilsTest {


    public static void main(String[] args) {
        //产生5位长度的随机字符串，中文环境下是乱码
        String s1 = RandomStringUtils.random(5);
        System.out.println("s1 = " + s1);

        //使用指定的字符生成5位长度的随机字符串
        String s2 = RandomStringUtils.random(5, new char[]{'a', 'b', 'c', 'd', 'e', 'f', '1', '2', '3'});
        System.out.println("s2 = " + s2);

        String random = RandomStringUtils.random(5, "abcdefghijklmnopqrstuvwxyz");
        System.out.println("random = " + random);

        //生成指定长度的字母和数字的随机组合字符串
        String s3 = RandomStringUtils.randomAlphanumeric(5);
        System.out.println("s3 = " + s3);

        //生成随机数字字符串
        String s4 = RandomStringUtils.randomNumeric(5);
        System.out.println("s4 = " + s4);

        //生成随机[a-z]字符串，包含大小写
        String s5 = RandomStringUtils.randomAlphabetic(5);
        System.out.println("s5 = " + s5);

        //生成从ASCII 32到126组成的随机字符串
        String s6 = RandomStringUtils.randomAscii(4);
        System.out.println("s6 = " + s6);
    }


}
