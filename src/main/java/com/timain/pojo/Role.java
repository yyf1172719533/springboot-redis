package com.timain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: yyf
 * @Date: 2021/12/20 23:34
 * @Version: 1.0
 */
@Data
@TableName("sys_role")
public class Role implements Serializable {

    private static final Long serialVersionUUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    private String roleName;

    private Integer roleSort;
}
