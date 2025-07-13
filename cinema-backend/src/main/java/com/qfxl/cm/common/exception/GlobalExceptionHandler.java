package com.qfxl.cm.common.exception;

import com.qfxl.cm.common.constant.HttpStatus;
import com.qfxl.cm.common.response.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 配置异常处理器，管理全局异常
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    // 创建日志对象
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 处理验证不通过异常，将错误信息响应给前端
     *
     * @return 错误响应信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        //打印日志
        log.error(e.getMessage(), e);
        //获取该异常的结果
        BindingResult bindingResult = e.getBindingResult();
        //获取错误msg
        String msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
        return R.error(msg);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R sqlIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return R.error("插入或修改操作不合法");
    }

    @ExceptionHandler(DataNotFoundException.class)
    public R dataNotFoundExceptionHandler(DataNotFoundException e) {
        log.warn(e.getMessage());
        return R.error(e.getMessage());
    }

    @ExceptionHandler(NoSuchMethodException.class)
    public R noSuchMethodExceptionHandler(NoSuchMethodException e) {
        log.warn(e.getMessage());
        return R.error("抱歉，服务器内部出现了些问题");
    }

    @ExceptionHandler(IllegalAccessException.class)
    public R illegalAccessExceptionHandler(IllegalAccessException e) {
        log.warn(e.getMessage());
        return R.error("抱歉，服务器内部出现了些问题");
    }

    @ExceptionHandler(IOException.class)
    public R IOExceptionHandler(IOException e) {
        log.warn(e.getMessage());
        return R.error("文件信息错误，原因：" + e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public R authenticationExceptionHandler(AuthenticationException e) {
        log.warn(e.getMessage());
        return R.error(HttpStatus.BAD_REQUEST, e.getMessage());
    }

}
