package com.example.shiroexample.service.impl;

import com.example.shiroexample.mapper.LoginMapper;
import com.example.shiroexample.pojo.po.PermissionPO;
import com.example.shiroexample.pojo.po.RolePO;
import com.example.shiroexample.pojo.po.UserPO;
import com.example.shiroexample.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public UserPO getUserByName(String userName) {
        return loginMapper.getUserByName(userName);
    }

    @Override
    public List<RolePO> getRolesByUserName(String userName) {
        return loginMapper.getRolesByUserName(userName);
    }

    @Override
    public List<PermissionPO> getPermissionsByRoleName(String roleName) {
        return loginMapper.getPermissionsByRoleName(roleName);
    }
}
