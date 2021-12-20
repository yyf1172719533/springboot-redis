package com.timain.controller;

import com.timain.pojo.Role;
import com.timain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: yyf
 * @Date: 2021/12/20 23:47
 * @Version: 1.0
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("findAll")
    public String findAll() {
        List<Role> roleList = this.roleService.findList();
        return "ok";
    }
}
