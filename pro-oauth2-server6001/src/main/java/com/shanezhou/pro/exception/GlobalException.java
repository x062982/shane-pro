package com.shanezhou.pro.exception;

import com.shanezhou.pro.entity.ResultVO;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ShaneZhou
 * @since 2020/10/26 周一 19:34:10
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(OAuth2Exception.class)
    @ResponseBody
    public ResultVO<Object> oAuth2Exception(OAuth2Exception e) {
        return new ResultVO(e.getHttpErrorCode(), e.getOAuth2ErrorCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO<Object> exception(Exception e) {
        return new ResultVO(200, e.getMessage());
    }

}
