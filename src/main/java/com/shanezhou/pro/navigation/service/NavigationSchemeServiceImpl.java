package com.shanezhou.pro.navigation.service;

import com.shanezhou.pro.navigation.entity.NavigationSchemeEntity;
import com.shanezhou.pro.navigation.mapper.NavigationSchemeMapper;
import com.shanezhou.pro.navigation.service.INavigationSchemeService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 导航方案表 服务实现类
 * </p>
 *
 * @author ZhouWX
 * @since 2021-02-02
 */
@Service
public class NavigationSchemeServiceImpl implements INavigationSchemeService {

    @Autowire
    private NavigationSchemeMapper mapper;

    @Override
    public Long saveScheme(NavigationSchemeEntity entity) {
        long id = Long.valueOf(mapper.insert(entity));
        if (id > 0) {
            id = entity.getId();
        }
        return id;
    }

    @Override
    public Integer updSchemeById(NavigationSchemeEntity entity) {
        return mapper.updateById(entity);
    }

    @Override
    public Integer delSchemeById(Long id) {
        return mapper.deleteById(id);
    }

    @Override
    public NavigationSchemeEntity getSchemeById(Long id) {
       return mapper.selectById(id);
    }

    @Override
    public List<NavigationSchemeEntity> getSchemes() {
        return mapper.selectList(null);
    }

}

