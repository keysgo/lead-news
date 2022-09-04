package com.heima.freemarker.test;

import com.heima.FreeApplication;
import com.heima.freemaker.entity.Student;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = FreeApplication.class)
@RunWith(SpringRunner.class)
public class FreemarkerTest {
    @Autowired
    Configuration configuration;


    @Test
    public void test01() throws IOException, TemplateException {
        Template template = configuration.getTemplate("02-list.ftl");

        template.process(getData(),new FileWriter("d:/list.html"));
    }

    private Map getData(){
        Map<String,Object> map=new HashMap<>();
        Student stu1=new Student();
        stu1.setName("小马");
        stu1.setAge("19");
        stu1.setMoney(200f);

        Student stu2=new Student();
        stu2.setName("小红");
        stu2.setAge("16");
        stu2.setMoney(1000f);
        List<Student> list=new ArrayList<>();
        list.add(stu1);
        list.add(stu2);
        Map<String,Student> stuMap=new HashMap<>();
        stuMap.put("stu1",stu1);
        stuMap.put("stu2",stu2);
        map.put("stus",list);
        map.put("stuMap",stuMap);
        return map;
    }

}
