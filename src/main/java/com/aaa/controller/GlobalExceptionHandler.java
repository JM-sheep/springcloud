package com.aaa.controller;

import com.aaa.core.ResultData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice //配置成一个普通的Controller，返回视图
@RestControllerAdvice //配置成Controller+ResponseBody组合 ，返回的json数据
public class GlobalExceptionHandler {

    //捕获指定类型的异常
    @ExceptionHandler(RuntimeException.class)
    public ResultData handler(RuntimeException ex) {
        return ResultData.error(ex.getMessage());
    }

/*    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public Object handler(ArrayIndexOutOfBoundsException ex) {

        return null;
    }*/
 }
