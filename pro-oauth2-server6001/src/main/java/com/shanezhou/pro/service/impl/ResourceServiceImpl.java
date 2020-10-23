package com.shanezhou.pro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 资源与角色匹配关系管理业务类
 * @author ShaneZhou
 * @since 2020/10/12 周一
 */
@Service
public class ResourceServiceImpl {

    private Map<String, List<String>> rolesMap;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void initData() {
        rolesMap = new TreeMap<>();
        List<String> roleList = new ArrayList<>();
        Collections.addAll(roleList, "ADMIN");
        rolesMap.put("/oauth2-api/test", roleList);
        rolesMap.put("/oauth2-api/token", roleList);
        List<String> roleList1 = new ArrayList<>();
        Collections.addAll(roleList1, "ADMIN", "TEST");
        rolesMap.put("/oauth2-api/user/currentUser", roleList1);
        if (redisTemplate.hasKey("AUTH:RESOURCE_ROLES_MAP")) {
            redisTemplate.delete("AUTH:RESOURCE_ROLES_MAP");
        }
        redisTemplate.opsForHash().putAll("AUTH:RESOURCE_ROLES_MAP", rolesMap);
    }
}
