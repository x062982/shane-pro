package com.shanezhou.pro.person.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.baomidou.mybatisplus.annotation.KeySequence;

import org.apache.ibatis.type.JdbcType;

/**
* <p>
* 人员表
* </p>
*
* @author ZhouWX
* @since 2020-10-15
*/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PRACTICE_PERSON")
@KeySequence(value = "SEQ_PERSON_ID")
public class PersonEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 编号
    */
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    /**
    * 姓名
    */
    @TableField(value = "NAME", jdbcType = JdbcType.VARCHAR, updateStrategy = FieldStrategy.NOT_NULL)
    private String name;

    /**
    * 年龄
    */
    @TableField(value = "AGE", jdbcType = JdbcType.DOUBLE, updateStrategy = FieldStrategy.NOT_NULL)
    private Integer age;

    /**
    * 性别 1：男；0：女
    */
    @TableField(value = "GENDER", jdbcType = JdbcType.DOUBLE, updateStrategy = FieldStrategy.NOT_NULL)
    private Integer gender;

    /**
    * 手机号码
    */
    @TableField(value = "MOBILE", jdbcType = JdbcType.DOUBLE, updateStrategy = FieldStrategy.NOT_NULL)
    private Long mobile;

    /**
    * 邮箱地址
    */
    @TableField(value = "EMAIL", jdbcType = JdbcType.VARCHAR, updateStrategy = FieldStrategy.NOT_NULL)
    private String email;

    /**
    * 创建时间
    */
    @TableField(value = "CREATE_TIME", jdbcType = JdbcType.DATE, updateStrategy = FieldStrategy.NOT_NULL)
    private LocalDateTime createTime;

    /**
    * 状态 1：启用；0：停用
    */
    @TableField(value = "STATUS", jdbcType = JdbcType.DOUBLE, updateStrategy = FieldStrategy.NOT_NULL)
    private Integer status;

}
