package com.aliyun.oss;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class AliyunTest {
    @Autowired
    AliyunOSSOperator aliyunOSSOperator;
    @Test
    public void test2() throws Exception {
        File file = new File("E:\\java\\develop\\testimage\\001.jpg");
        String url = aliyunOSSOperator.upload(file, file.getName());
        System.out.println( url);
    }

}
