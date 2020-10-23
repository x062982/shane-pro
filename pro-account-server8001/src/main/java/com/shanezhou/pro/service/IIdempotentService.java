package com.shanezhou.pro.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ShaneZhou
 * @since 2020/10/20 周二 10:38:09
 */
public interface IIdempotentService {

    void createUrlToken(String urlToken);

    void checkUrlToken(String urlToken);
}
