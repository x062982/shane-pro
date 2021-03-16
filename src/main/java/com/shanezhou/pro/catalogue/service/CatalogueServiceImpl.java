package com.shanezhou.pro.catalogue.service;

import com.shanezhou.pro.catalogue.entity.CatalogueEntity;
import com.shanezhou.pro.catalogue.mapper.CatalogueMapper;
import com.shanezhou.pro.catalogue.service.ICatalogueService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 目录表 服务实现类
 * </p>
 *
 * @author ZhouWX
 * @since 2021-02-02
 */
@Service
public class CatalogueServiceImpl implements ICatalogueService {

    @Autowire
    private CatalogueMapper mapper;

    @Override
    public Long saveCatalogue(CatalogueEntity entity) {
        long id = Long.valueOf(mapper.insert(entity));
        if (id > 0) {
            id = entity.getId();
        }
        return id;
    }

    @Override
    public Integer updCatalogueById(CatalogueEntity entity) {
        return mapper.updateById(entity);
    }

    @Override
    public Integer delCatalogueById(Long id) {
        return mapper.deleteById(id);
    }

    @Override
    public CatalogueEntity getCatalogueById(Long id) {
       return mapper.selectById(id);
    }

    @Override
    public List<CatalogueEntity> getCatalogues() {
        return mapper.selectList(null);
    }

}

