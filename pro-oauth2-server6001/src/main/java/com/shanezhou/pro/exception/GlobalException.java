package com.shanezhou.pro.exception;

import com.shanezhou.pro.entity.ResultVO;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

    @ExceptionHandler(NullPointerException.class)
    public ResultVO<Object> exception(NullPointerException e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTrace) {
            stringBuilder.append(stackTraceElement.toString() + " \n ");
        }
        String message = stringBuilder.toString();
        log.error("错误原因：", e);
        return new ResultVO(200, message);
    }

}
