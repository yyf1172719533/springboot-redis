package com.timain.service;

import com.timain.pojo.Role;

import java.util.List;

/**
 * @Author: yyf
 * @Date: 2021/12/20 23:45
 * @Version: 1.0
 */
public interface RoleService {

    /**
     * 查询所有角色信息
     * @return
     */
    List<Role> findList();
}
