package com.example.shiroexample.service;

import com.example.shiroexample.pojo.po.PermissionPO;
import com.example.shiroexample.pojo.po.RolePO;
import com.example.shiroexample.pojo.po.UserPO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public UserPO getUserByName(String userName) {
        return null;
    }

    @Override
    public List<RolePO> getRolesByUserName(String userName) {
        return null;
    }

    @Override
    public List<PermissionPO> getPermissionsByRoleName(String roleName) {
        return null;
    }
}
