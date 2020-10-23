package com.shanezhou.pro.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shanezhou.pro.exception.APIException;
import com.shanezhou.pro.entity.ResultVO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author ShaneZhou
 * @since 2020/9/25 周五
 */
// 注解用于指定扫描包
@RestControllerAdvice(basePackages = {"com.shanezhou.pro.*.controller"})
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果返回的类型就是ResultVO 则直接返回false
        return !methodParameter.getGenericParameterType().equals(ResultVO.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 如果是String类型，则不能直接包装，需要先处理
        if (methodParameter.getGenericParameterType().equals(String.class)) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.writeValueAsString(new ResultVO<>(o));
            } catch (JsonProcessingException e) {
                throw new APIException("返回String类型错误");
            }
        }
        return new ResultVO<>(o);
    }
}
