package com.qixcnweb.qixian.utils;

import com.aliyun.oss.OSSClient;
import com.qixcnweb.qixian.configuration.AliyunOSSProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Random;

/**
 * 文件上传工具类
 * Created by dingxiaochi on 2018/3/26.
 */
@Component

@EnableConfigurationProperties(AliyunOSSProperties.class)
public class FileUploadUtils {

    @Autowired
    private AliyunOSSProperties aliyunOSSProperties;

    @Value("${aliyunOSS.endpoint}")
    private String endpoint;

    @Value("${aliyunOSS.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyunOSS.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyunOSS.bucketName}")
    private String bucketName;


    /**
     * 将文件上传到 阿里云OSS (文件格式)
     * @param fileName 自定义文件名
     * @param file  目标文件
     * @throws FileNotFoundException
     */
    public void upload2OSS(String fileName,File file){
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件
        ossClient.putObject(bucketName, fileName, file);
        // 关闭client
        ossClient.shutdown();
    }


    /**
     * 将文件上传到 阿里云OSS (输入流格式)
     * @param fileName 自定义文件名
     * @param inputStream   目标文件输入流
     * @throws FileNotFoundException
     */
    public void upload2OSS(String fileName, InputStream inputStream){
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件
        ossClient.putObject(bucketName, fileName, inputStream);
        // 关闭client
        ossClient.shutdown();
    }


    /**
     * 获取OSS文件的访问URL
     * @param fileName
     * @return
     */
    public String getFileUrl(String fileName,Integer overtime){
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        //设置访问过期时间为1小时
        Date date = new Date(new Date().getTime() + overtime);
        //获取访问url
        URL url = ossClient.generatePresignedUrl(bucketName, fileName, date);

        return url.toString();
    }
}
