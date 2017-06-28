package test;

import java.io.*;

public class Test {

    /*{ field: 'userName', title: '客户姓名', align: 'center', valign: 'middle' },*/

    public static void main(String[] args) throws IOException {

        File file = new File("E:\\Workspace\\IdeaProjects\\SpringWind\\src\\test\\java\\test\\product_expires.txt");

        if (file.isFile() && file.exists()) { //判断文件是否存在

            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;

            StringBuffer sb = new StringBuffer();

            while ((lineTxt = bufferedReader.readLine()) != null) {
                String[] strs = lineTxt.split(",");
                String s1 = strs[0];
                String s2 = strs[1];

                String field = "{ field: '" + s1 + "', title: '" + s2 + "', align: 'center', valign: 'middle' },";
                System.err.println(field);
                sb.append(s1);
                sb.append(",");

            }

            System.out.println("sb = " + sb);
        }


    }

}
