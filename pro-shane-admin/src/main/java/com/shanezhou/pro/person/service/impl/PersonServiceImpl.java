package com.shanezhou.pro.person.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanezhou.pro.person.entity.PersonEntity;
import com.shanezhou.pro.person.mapper.PersonMapper;
import com.shanezhou.pro.person.service.IPersonService;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 人员表 服务实现类
 * </p>
 *
 * @author ZhouWX
 * @since 2020-10-15
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, PersonEntity> implements IPersonService {

}
