package com.timain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: yyf
 * @Date: 2021/12/20 23:34
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 4775722347162046572L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    private String roleName;

    private Integer roleSort;
}
