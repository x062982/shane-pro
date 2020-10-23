package com.shanezhou.pro.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;

/**
* <p>
* 角色表
* </p>
*
* @author ZhouWX
* @since 2020-09-30
*/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PRACTICE_ROLE")
@KeySequence(value = "SEQ_PRACTICE_ROLE")
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

}
