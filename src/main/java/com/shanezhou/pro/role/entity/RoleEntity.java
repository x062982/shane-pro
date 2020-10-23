package com.shanezhou.pro.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.baomidou.mybatisplus.annotation.KeySequence;

import org.apache.ibatis.type.JdbcType;

/**
* <p>
* 角色表
* </p>
*
* @author ZhouWX
* @since 2020-10-15
*/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PRACTICE_ROLE")
@KeySequence(value = "SEQ_ROLE_ID")
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 编号
    */
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    /**
    * 角色代码
    */
    @TableField(value = "ROLE_CODE", jdbcType = JdbcType.VARCHAR, updateStrategy = FieldStrategy.NOT_NULL)
    private String roleCode;

    /**
    * 角色名
    */
    @TableField(value = "ROLE_NAME", jdbcType = JdbcType.VARCHAR, updateStrategy = FieldStrategy.NOT_NULL)
    private String roleName;

    /**
    * 状态 1：启用；0：停用
    */
    @TableField(value = "STATUS", jdbcType = JdbcType.DOUBLE, updateStrategy = FieldStrategy.NOT_NULL)
    private Integer status;

}
