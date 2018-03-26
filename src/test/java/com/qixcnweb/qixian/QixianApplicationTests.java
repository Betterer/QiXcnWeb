package com.qixcnweb.qixian;

import com.netflix.discovery.converters.Auto;
import com.qixcnweb.qixian.configuration.AliyunOSSProperties;
import com.qixcnweb.qixian.constant.Constant;
import com.qixcnweb.qixian.utils.FileUploadUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QixianApplicationTests {


	@Autowired
	private AliyunOSSProperties aliyunOSSProperties;

	@Autowired
    private FileUploadUtils fileUploadUtils;

	@Test
	public void contextLoads() {
	}

	@Test
	public void uploadFile2OSS() throws FileNotFoundException {
		File file = new File("F:\\example.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        fileUploadUtils.upload2OSS(Constant.USER_HEAD_IMAGE+file.getName(),fileInputStream);
	}

	@Test
	public void readProperties(){
        String endpoint = aliyunOSSProperties.getEndpoint();
        System.out.println(endpoint);

        String accessKeyId = aliyunOSSProperties.getAccessKeyId();
        System.out.println(accessKeyId);

        String accessKeySecret = aliyunOSSProperties.getAccessKeySecret();
        System.out.println(accessKeySecret);

        String bucketName = aliyunOSSProperties.getBucketName();
        System.out.println(bucketName);

    }

}
