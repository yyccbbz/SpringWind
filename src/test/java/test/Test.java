package test;


import com.baomidou.framework.common.util.DateUtil;

import java.io.File;
import java.util.Date;

public class Test {
    public static void main(String[] args) {

        System.out.println("System.getProperty(\"user.dir\") = " + System.getProperty("user.dir"));

        StringBuffer filePath = new StringBuffer("/opt/upload/excel");

        filePath.append(File.separator);
        filePath.append(DateUtil.format(new Date(), "yyyy-MM"));
        filePath.append(File.separator);

        System.out.println("filePath.toString() = " + filePath.toString());

        File file = new File(filePath.toString());

        if ( !file.exists() ) {
            file.mkdirs();
        }
        String path = file.getPath();

        System.out.println("path = " + path);
    }
}
