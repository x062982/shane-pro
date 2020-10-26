package com.shanezhou.pro.interceptor;

import com.shanezhou.pro.annotation.ApiIdempotent;
import com.shanezhou.pro.service.IIdempotentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * @author ShaneZhou
 * @since 2020/10/20 周二 10:30:59
 */
@Component
public class ApiIdempotentInterceptor implements HandlerInterceptor {

    public static final String URL_TOKEN = "Url-Token";

    @Autowired
    private IIdempotentService idempotentService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        ApiIdempotent annotation = method.getAnnotation(ApiIdempotent.class);
        if (annotation != null) {
            ApiIdempotent.ApiIdempotentEnum type = annotation.type();
            if (type == ApiIdempotent.ApiIdempotentEnum.CHECK) {
                String urlToken = request.getHeader(URL_TOKEN);
                check(urlToken);

            } else if (ApiIdempotent.ApiIdempotentEnum.CREATE == type) {
                String urlToken = create(request);
                idempotentService.createUrlToken(urlToken);
                response.setHeader(URL_TOKEN, urlToken);
            }
        }
        return true;
    }

    private void check(String urlToken) {
        idempotentService.checkUrlToken(urlToken);
    }

    private String create(HttpServletRequest request) {
        String uri = request.getHeader("Authorization") + "?" + System.currentTimeMillis();
        return UUID.nameUUIDFromBytes(uri.getBytes()).toString().replaceAll("-", "").toLowerCase();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
