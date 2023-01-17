package io.ab.shop.utils.exception;

import io.ab.shop.utils.constants.HttpCode;
import io.ab.shop.utils.resp.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class RestCtrlExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestCtrlExceptionHandler.class);

    @ExceptionHandler
    public Result<String> handleResult(Exception exception){
        return new Result<String>(HttpCode.FAILURE, "失败", exception.getMessage());
    }

}
