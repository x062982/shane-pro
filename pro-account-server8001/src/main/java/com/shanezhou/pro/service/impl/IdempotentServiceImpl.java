package com.shanezhou.pro.service.impl;

import com.shanezhou.pro.enums.ExceptionCodeEnum;
import com.shanezhou.pro.exception.APIException;
import com.shanezhou.pro.service.IIdempotentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author ShaneZhou
 * @since 2020/10/20 周二 11:14:45
 */
@Service
public class IdempotentServiceImpl implements IIdempotentService {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void createUrlToken(String urlToken) {
        redisTemplate.opsForValue().set(urlToken, urlToken);
    }

    @Override
    public void checkUrlToken(String urlToken) {

        if (StringUtils.isEmpty(urlToken)) {
            throw new APIException(ExceptionCodeEnum.NOT_VALIDATED);
        }
        if (!redisTemplate.hasKey(urlToken)) {
            throw new APIException(ExceptionCodeEnum.NO_REPEATED);
        }
        if (!redisTemplate.delete(urlToken)) {
            throw new APIException(ExceptionCodeEnum.OPERATE_FAILED);
        }
    }
}
