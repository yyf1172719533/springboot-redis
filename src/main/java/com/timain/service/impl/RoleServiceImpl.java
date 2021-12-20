package com.timain.service.impl;

import com.timain.annotation.RedisCache;
import com.timain.mapper.RoleMapper;
import com.timain.pojo.Role;
import com.timain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yyf
 * @Date: 2021/12/20 23:45
 * @Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询所有角色信息
     *
     * @return
     */
    @RedisCache(expired = 60)
    @Override
    public List<Role> findList() {
        return this.roleMapper.selectList(null);
    }
}
