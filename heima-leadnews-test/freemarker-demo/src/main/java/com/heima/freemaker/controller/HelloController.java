package com.heima.freemaker.controller;

import com.heima.freemaker.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;


@Controller
@Slf4j
public class HelloController {

    @GetMapping("/basic")
    public String test(Model model) throws ParseException {


        Student student=new Student();
        student.setName("小米");
        student.setAge("20");
        student.setMoney(123f);
        student.setBirthday(setStringToDate("2015-12-30"));
        model.addAttribute("name","keysto");
        model.addAttribute("stu",student);
        return "01-basic";
    }

    @GetMapping("/list")
    public String test2(Model model) throws ParseException {
        Map<String,Student> map=new HashMap<>();


        Student student1=new Student();
        student1.setName("小米");
        student1.setAge("20");
        student1.setMoney(123f);
        student1.setBirthday(setStringToDate("2013-12-30"));

        Student student2=new Student();
        student2.setName("小强");
        student2.setAge("22");
        student2.setMoney(125f);
        student2.setBirthday(setStringToDate("2010-12-30"));
        Student student3=new Student();
        student3.setName("小红");
        student3.setAge("18");
        student3.setMoney(100f);
        student3.setBirthday(setStringToDate("2010-12-30"));
        List list=new ArrayList();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        model.addAttribute("stus",list);
        map.put("stu1",student1);
        map.put("stu2",student2);
        map.put("stu3",student3);
        model.addAttribute("stumap",map);
        return "02-list";
    }

    private Date setStringToDate(String str) throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date parse = dateFormat.parse(str);
        return parse;
    }
}
