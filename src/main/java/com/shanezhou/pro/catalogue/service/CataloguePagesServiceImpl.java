package com.shanezhou.pro.catalogue.service;

import com.shanezhou.pro.catalogue.entity.CataloguePagesEntity;
import com.shanezhou.pro.catalogue.mapper.CataloguePagesMapper;
import com.shanezhou.pro.catalogue.service.ICataloguePagesService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 目录资源表 服务实现类
 * </p>
 *
 * @author ZhouWX
 * @since 2021-02-02
 */
@Service
public class CataloguePagesServiceImpl implements ICataloguePagesService {

    @Autowire
    private CataloguePagesMapper mapper;

    @Override
    public Long savePages(CataloguePagesEntity entity) {
        long id = Long.valueOf(mapper.insert(entity));
        if (id > 0) {
            id = entity.getId();
        }
        return id;
    }

    @Override
    public Integer updPagesById(CataloguePagesEntity entity) {
        return mapper.updateById(entity);
    }

    @Override
    public Integer delPagesById(Long id) {
        return mapper.deleteById(id);
    }

    @Override
    public CataloguePagesEntity getPagesById(Long id) {
       return mapper.selectById(id);
    }

    @Override
    public List<CataloguePagesEntity> getPagess() {
        return mapper.selectList(null);
    }

}

