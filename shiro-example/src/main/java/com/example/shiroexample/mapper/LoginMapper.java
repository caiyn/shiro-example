package com.example.shiroexample.mapper;

import com.example.shiroexample.pojo.po.PermissionPO;
import com.example.shiroexample.pojo.po.RolePO;
import com.example.shiroexample.pojo.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginMapper {

    UserPO getUserByName(String userName);
    List<RolePO> getRolesByUserName(String userName);
    List<PermissionPO> getPermissionsByRoleName(String roleName);
}
