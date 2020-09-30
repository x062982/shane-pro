package com.shanezhou.pro.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;


/**
* <p>
* 账户表
* </p>
*
* @author ZhouWX
* @since 2020-09-30
*/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PRACTICE_ACCOUNT")
@KeySequence(value = "SEQ_PRACTICE_ACCOUNT")
public class AccountEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 编号
    */
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    /**
    * 用户名
    */
    @TableField(value = "USERNAME", jdbcType = JdbcType.VARCHAR, updateStrategy = FieldStrategy.NOT_NULL)
    private String username;

    /**
    * 密码
    */
    @TableField(value = "PASSWORD", jdbcType = JdbcType.VARCHAR, updateStrategy = FieldStrategy.NOT_NULL)
    private String password;

    /**
    * 角色
    */
    @TableField(value = "ROLE_ID", jdbcType = JdbcType.DOUBLE, updateStrategy = FieldStrategy.NOT_NULL)
    private Long roleId;

}
