package com.shanezhou.pro.account.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
* 账户表
* </p>
*
* @author ZhouWX
* @since 2020-10-15
*/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PRACTICE_ACCOUNT")
@KeySequence(value = "SEQ_ACCOUNT_ID")
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
    * 用户编号
    */
    @TableField(value = "PERSON_ID", jdbcType = JdbcType.DOUBLE, updateStrategy = FieldStrategy.NOT_NULL)
    private Long personId;

    /**
    * 组织编号
    */
    @TableField(value = "DEPT_ID", jdbcType = JdbcType.DOUBLE, updateStrategy = FieldStrategy.NOT_NULL)
    private Long deptId;

    /**
    * 创建时间
    */
    @TableField(value = "CREATE_TIME", jdbcType = JdbcType.DATE, updateStrategy = FieldStrategy.NOT_NULL)
    private LocalDateTime createTime;

    /**
    * 登录时间
    */
    @TableField(value = "LOGIN_TIME", jdbcType = JdbcType.DATE, updateStrategy = FieldStrategy.NOT_NULL)
    private LocalDateTime loginTime;

    /**
    * 上次登录时间
    */
    @TableField(value = "LAST_LOGIN_TIME", jdbcType = JdbcType.DATE, updateStrategy = FieldStrategy.NOT_NULL)
    private LocalDateTime lastLoginTime;

    /**
    * 状态
    */
    @TableField(value = "STATUS", jdbcType = JdbcType.VARCHAR, updateStrategy = FieldStrategy.NOT_NULL)
    private String status;

    /**
    * 登录ip
    */
    @TableField(value = "LOGIN_IP", jdbcType = JdbcType.VARCHAR, updateStrategy = FieldStrategy.NOT_NULL)
    private String loginIp;

}
