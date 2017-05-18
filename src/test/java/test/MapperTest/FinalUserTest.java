package test.MapperTest;

import com.baomidou.springwind.entity.FinalUser;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import test.BasicTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by zizou on 2017/5/18.
 */
public class FinalUserTest extends BasicTest {

    @Test
    public void addTest(){

//        FinalUserMapper fum = sch.getBean(FinalUserMapper.class);

        ArrayList<FinalUser> list = new ArrayList<>();
        for (int i = 1;i<= 20;i++){
            FinalUser u = new FinalUser();

            u.setMobileNo(RandomStringUtils.randomNumeric(11));
            u.setMemberNo(RandomStringUtils.randomAlphanumeric(10));
            u.setUserName(RandomStringUtils.randomAlphabetic(5));
            u.setUserType(Integer.parseInt(RandomStringUtils.random(1,new char[]{'1','2','3'})));
            u.setReportDate(randomDate());
            u.setRegisterTime(randomDate());
            u.setIsVipuser(Integer.parseInt(RandomStringUtils.random(1,new char[]{'0','1'})));
            u.setVipDate(randomDate());
            u.setAdvisorId(Integer.parseInt(RandomStringUtils.randomNumeric(4)));
            u.setAdvisorName(RandomStringUtils.randomAlphabetic(6));
            u.setUserMark(RandomStringUtils.randomAlphanumeric(6));
            u.setIsPerformancePool(Integer.parseInt(RandomStringUtils.random(1,new char[]{'0','1'})));
            u.setCreateTime(new Date());
            u.setUpdateTime(u.getCreateTime());

            System.out.println(u);
        }

    }

    public Date randomDate() {
        Random random = new Random();
        Calendar can = Calendar.getInstance();
        can.setTimeInMillis(random.nextLong());
        return can.getTime();

    }




}
