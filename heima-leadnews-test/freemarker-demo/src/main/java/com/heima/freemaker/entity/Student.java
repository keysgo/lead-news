package com.heima.freemaker.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private String name;
    private String age;
    private Date birthday;
    private Float money;
}
