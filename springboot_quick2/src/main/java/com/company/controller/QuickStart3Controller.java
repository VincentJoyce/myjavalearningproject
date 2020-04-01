package com.company.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuickStart3Controller {

    @Value("${person.name}")
    private String name;
    @Value("${person.age}")
    private String age;

    @RequestMapping("/quick3")
    @ResponseBody
    public String Quick() {
        return name + ": " + age;
    }
}
