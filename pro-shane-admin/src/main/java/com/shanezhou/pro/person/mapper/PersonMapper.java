package com.shanezhou.pro.person.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanezhou.pro.person.entity.PersonEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* <p>
* 人员表 Mapper 接口
* </p>
*
* @author ZhouWX
* @since 2020-10-15
*/
@Mapper
public interface PersonMapper extends BaseMapper<PersonEntity> {

}
