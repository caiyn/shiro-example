package com.example.shiroexample.controller;

import com.example.shiroexample.conf.ShiroProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ShiroProperties shiroProperties;

    @GetMapping("/success")
    public String success(){
        Set<Map.Entry<String, String>> entries = shiroProperties.getFilterChainDefinitionMap().entrySet();
        System.out.println(entries);
        return "success";
    }
}
