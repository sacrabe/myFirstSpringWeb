package com.itheima;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.aliyun.oss.AliyunOSSOperator;
import jakarta.websocket.ClientEndpoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
@SpringBootTest
public class AliyunTest {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @Test
    public void test2() throws Exception {
        File file = new File("E:\\java\\develop\\testimage\\001.jpg");
        String url = aliyunOSSOperator.upload(file, file.getName());
        System.out.println( url);
    }
}
