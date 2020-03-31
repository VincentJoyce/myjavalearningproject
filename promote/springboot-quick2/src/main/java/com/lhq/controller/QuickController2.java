package com.lhq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuickController2 {

    @RequestMapping("/quick2")
    @ResponseBody
    public String quick() {
        return "springboot 访问成功, 哒哒！";
    }
}
