package test;

import com.baomidou.springwind.common.utils.DateUtil;

import java.util.Date;

public class Test{

    public static void main(String[] args) {

        Date randomDate = DateUtil.randomDate("2007-01-01", "2007-05-01");
        System.out.println(randomDate.toString());

    }

}
