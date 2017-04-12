package com.baomidou.springwind.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by cuican on 16-7-26.
 *
 * 文件上传工具
 */
public class UploadUtil {


    /* -----------上传文件,工具方法--------- */
    private static final int BUFFER_SIZE = 10 * 1024;

    /**
     * @param src 源文件
     * @param dst 目标位置
     */
	private static void copy(File src, File dst) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
            out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 上传copy文件方法(for MultipartFile)
     *
     * @param savePath 在linux上要保存完整路径
     * @param newname  新的文件名称，采用系统时间做文件名防止中文报错的问题
     * @throws Exception
     */
    public static void copy(MultipartFile file, String savePath, String newname) throws Exception {
        try {
            File targetFile = new File(savePath, newname);
            if (!targetFile.exists()) {
                //判断文件夹是否存在，不存在就创建
                targetFile.mkdirs();
            }

            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 文件操作 获取文件扩展名
     *
     * @param filename 文件名称包含扩展名
     * @return
     * @Author: sunny
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * IP地址+时间戳 作为文件名 防止文件名重复
     *
     * @param ip
     * @param suffixString
     * @return
     */
    public static String getNewFileName(String ip,String suffixString){

        return ip+"-"+System.currentTimeMillis()+"."+suffixString;
    }
}
