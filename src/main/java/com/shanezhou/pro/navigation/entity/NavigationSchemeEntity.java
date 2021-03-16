package com.shanezhou.pro.navigation.entity;

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
* 导航方案表
* </p>
*
* @author ZhouWX
* @since 2021-02-02
*/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CHIPMUNK_NAVIGATION_SCHEME")
@KeySequence(value = "SEQ_NAVIGATION_SCHEME_ID")
public class NavigationSchemeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 编号
    */
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    /**
    * 方案名称
    */
    @TableField(value = "SCHEME_NAME", jdbcType = JdbcType.VARCHAR, updateStrategy = FieldStrategy.NOT_NULL)
    private String schemeName;

    /**
    * 组织编号
    */
    @TableField(value = "ORGANIZATION_ID", jdbcType = JdbcType.DOUBLE, updateStrategy = FieldStrategy.NOT_NULL)
    private Long organizationId;

    /**
    * 备注
    */
    @TableField(value = "REMARK", jdbcType = JdbcType.VARCHAR, updateStrategy = FieldStrategy.NOT_NULL)
    private String remark;

    /**
    * 状态 1：启用；0：停用；
    */
    @TableField(value = "STATUS", jdbcType = JdbcType.DOUBLE, updateStrategy = FieldStrategy.NOT_NULL)
    private Long status;

    /**
    * 创建人id
    */
    @TableField(value = "CREATED_ACCOUNT_ID", jdbcType = JdbcType.DOUBLE, updateStrategy = FieldStrategy.NOT_NULL)
    private Long createdAccountId;

    /**
    * 修改人id
    */
    @TableField(value = "UPDATED_ACCOUNT_ID", jdbcType = JdbcType.DOUBLE, updateStrategy = FieldStrategy.NOT_NULL)
    private Long updatedAccountId;

    /**
    * 创建时间
    */
    @TableField(value = "CREATED_DATE", jdbcType = JdbcType.DATE, updateStrategy = FieldStrategy.NOT_NULL)
    private LocalDateTime createdDate;

    /**
    * 修改时间
    */
    @TableField(value = "UPDATED_DATE", jdbcType = JdbcType.DATE, updateStrategy = FieldStrategy.NOT_NULL)
    private LocalDateTime updatedDate;

    /**
    * 是否删除 是否删除，0：未删除；1：已
删除；
    */
    @TableField(value = "DELETE_FLAG", jdbcType = JdbcType.DOUBLE, updateStrategy = FieldStrategy.NOT_NULL)
    private Long deleteFlag;

}
