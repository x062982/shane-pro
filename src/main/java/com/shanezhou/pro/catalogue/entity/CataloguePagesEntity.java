package com.shanezhou.pro.catalogue.entity;

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
* 目录资源表
* </p>
*
* @author ZhouWX
* @since 2021-02-02
*/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CHIPMUNK_CATALOGUE_PAGES")
@KeySequence(value = "SEQ_CATALOGUE_PAGES_ID")
public class CataloguePagesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 编号
    */
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    /**
    * 目录编号
    */
    @TableField(value = "CATALOGUE_ID", jdbcType = JdbcType.DOUBLE, updateStrategy = FieldStrategy.NOT_NULL)
    private Long catalogueId;

    /**
    * 资源标题
    */
    @TableField(value = "PAGE_TITLE", jdbcType = JdbcType.VARCHAR, updateStrategy = FieldStrategy.NOT_NULL)
    private String pageTitle;

    /**
    * 资源key
    */
    @TableField(value = "PAGE_KEY", jdbcType = JdbcType.VARCHAR, updateStrategy = FieldStrategy.NOT_NULL)
    private String pageKey;

    /**
    * 资源名称
    */
    @TableField(value = "PAGE_NAME", jdbcType = JdbcType.VARCHAR, updateStrategy = FieldStrategy.NOT_NULL)
    private String pageName;

    /**
    * 资源地址
    */
    @TableField(value = "PAGE_URL", jdbcType = JdbcType.VARCHAR, updateStrategy = FieldStrategy.NOT_NULL)
    private String pageUrl;

    /**
    * 图片地址
    */
    @TableField(value = "PIC_URL", jdbcType = JdbcType.VARCHAR, updateStrategy = FieldStrategy.NOT_NULL)
    private String picUrl;

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
    * 是否删除 是否删除，0：未删除；1：已删
除；
    */
    @TableField(value = "DELETE_FLAG", jdbcType = JdbcType.DOUBLE, updateStrategy = FieldStrategy.NOT_NULL)
    private Long deleteFlag;

    /**
    * 修改时间
    */
    @TableField(value = "UPDATED_DATE", jdbcType = JdbcType.DATE, updateStrategy = FieldStrategy.NOT_NULL)
    private LocalDateTime updatedDate;

}
