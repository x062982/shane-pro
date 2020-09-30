package com.shanezhou.pro.config;

import com.shanezhou.pro.exception.APIException;
import com.shanezhou.pro.enums.ExceptionCodeEnum;
import com.shanezhou.pro.entity.ResultVO;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author ShaneZhou
 * @since 2020/9/24 周四
 */
@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResultVO<>(ExceptionCodeEnum.VALIDATE_FAILED, objectError.getDefaultMessage());
    }

    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e) {
        return new ResultVO<>(ExceptionCodeEnum.FAILED, e.getMsg());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVO<String> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        return new ResultVO<>(ExceptionCodeEnum.VALIDATE_FAILED, e.getConstraintViolations().iterator().next().getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultVO<String> RuntimeExceptionHandler(RuntimeException e) {
        return new ResultVO<>(ExceptionCodeEnum.ERROR, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO<String> ExceptionHandler(Exception e) {
        return new ResultVO<>(ExceptionCodeEnum.ERROR, e.getLocalizedMessage());
    }
}
