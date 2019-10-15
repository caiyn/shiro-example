package com.example.shiroexample.controller;

import com.example.shiroexample.conf.ShiroProperties;
import com.example.shiroexample.pojo.po.UserPO;
import com.example.shiroexample.service.LoginService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @GetMapping("/success")
    public String success(){
        UserPO userPO = loginService.getUserByName("caiyn");
        System.out.println(userPO.getPassword());
        return "success";
    }

    @RequiresRoles("admin") //需要admin角色的用户
    @RequiresPermissions("add")//需要有add权限的方可访问
    @RequestMapping("/index")
    public String index() {
        return "index!";
    }

    @RequestMapping("/index2")
    public String index2() {
        return "匿名访问!";
    }
}
