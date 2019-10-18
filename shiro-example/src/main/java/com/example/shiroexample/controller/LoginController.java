package com.example.shiroexample.controller;

import com.example.shiroexample.pojo.po.UserPO;
import com.example.shiroexample.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
//@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @GetMapping("/success")
    public String success(){
        UserPO userPO = loginService.getUserByName("caiyn");
        System.out.println(userPO.getPassword());
        return "success";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView toLogin() {
        return new ModelAndView("views/login");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(UserPO user,boolean rememberMe) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassword(),
                rememberMe
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return "没有权限";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "账号或密码错误！";
        }
        return "login success";
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

    @RequestMapping("/login.html")
    public String login() {
        return "登录页面!";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "退出登录!";
    }

}
