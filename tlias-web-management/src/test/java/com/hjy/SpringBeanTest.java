package com.hjy;

import com.hjy.utils.AliyunOSSOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@SpringBootTest
public class SpringBeanTest {

    @Autowired
    private ApplicationContext applicationContext;
    @Test
    public void test1(){
        for (int i = 0; i < 100; i++) {
            applicationContext.getBean("empController");
            System.out.println(applicationContext.getBean("empController"));
        }

        // 创建IOC容器

    }
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @Test
    public void test2() throws Exception {
        File file = new File("E:\\java\\develop\\testimage\\001.jpg");
        String url = aliyunOSSOperator.upload(file, file.getName());
        System.out.println( url);
    }

}
