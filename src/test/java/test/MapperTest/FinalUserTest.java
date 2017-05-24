package test.MapperTest;

import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.entity.FinalUser;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import test.BasicTest;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zizou on 2017/5/18.
 */
public class FinalUserTest extends BasicTest {

    @Test
    public void addTest(){

//        FinalUserMapper fum = sch.getBean(FinalUserMapper.class);

        ArrayList<FinalUser> list = new ArrayList<>();

        for (int i = 1;i<= 80;i++){

            FinalUser u = new FinalUser();

            u.setMobileNo(RandomStringUtils.randomNumeric(11));
            u.setMemberNo(RandomStringUtils.randomAlphanumeric(10));
            u.setUserName(RandomStringUtils.randomAlphabetic(5));
            u.setUserType(Integer.parseInt(RandomStringUtils.random(1,new char[]{'1','2','3'})));
            u.setReportDate(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            u.setRegisterTime(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            u.setIsVipuser(Integer.parseInt(RandomStringUtils.random(1,new char[]{'0','1'})));
            u.setVipDate(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            u.setAdvisorId(Integer.parseInt(RandomStringUtils.randomNumeric(4)));
            u.setAdvisorName(RandomStringUtils.randomAlphabetic(6));
            u.setUserMark(RandomStringUtils.randomAlphanumeric(6));
            u.setIsPerformancePool(Integer.parseInt(RandomStringUtils.random(1,new char[]{'0','1'})));
            u.setCreateTime(new Date());
            u.setUpdateTime(u.getCreateTime());

            list.add(u);

            System.err.println(u);
        }

    }

}
