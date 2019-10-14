package com.example.shiroexample.service;

import com.example.shiroexample.pojo.po.PermissionPO;
import com.example.shiroexample.pojo.po.RolePO;
import com.example.shiroexample.pojo.po.UserPO;

import java.util.List;

public interface LoginService {

    UserPO getUserByName(String userName);
    List<RolePO> getRolesByUserName(String userName);
    List<PermissionPO> getPermissionsByRoleName(String roleName);
}
