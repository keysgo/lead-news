package com.heima.minio;

import com.heima.file.service.FileStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest(classes = MinioApplocation.class)
@RunWith(SpringRunner.class)
public class MinioTest {
    @Autowired
    private FileStorageService fileStorageService;
    @Test
    public void test01() throws FileNotFoundException {
        FileInputStream fileInputStream=new FileInputStream("d:/list.html");
        String s = fileStorageService.uploadHtmlFile("", "list01.html", fileInputStream);
        System.out.println(s);

    }
}
