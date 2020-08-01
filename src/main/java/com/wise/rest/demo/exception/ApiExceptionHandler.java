package com.wise.rest.demo.exception;


import com.wise.rest.demo.oo.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Api 异常处理
 *
 * @author lingyuwang
 * @date 2020-08-01 11:29
 * @since 1.1.3.0
 */
@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    /**
     * 其它所有异常处理
     *
     * @param e
     * @return com.qiaoku.res.Response
     * @author lingyuwang
     * @date 2019/12/21 11:12
     */
    @ExceptionHandler(value = Exception.class)
    public Response exceptionHandler(Exception e) {
        log.error("error", e);

        StringJoiner message = new StringJoiner(", ");
        if (e instanceof BindException) {
            List<ObjectError> allErrors = ((BindException) e).getAllErrors();
            if (CollectionUtils.isNotEmpty(allErrors)) {
                for (ObjectError objectError : allErrors) {
                    if (objectError != null && objectError.getDefaultMessage() != null) {
                        message.add(objectError.getDefaultMessage());
                    }
                }
            }
            return Response.error(400, message.toString());
        }


        if (e instanceof MethodArgumentNotValidException) {
            List<ObjectError> allErrors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            if (CollectionUtils.isNotEmpty(allErrors)) {
                for (ObjectError objectError : allErrors) {
                    if (objectError != null && objectError.getDefaultMessage() != null) {
                        message.add(objectError.getDefaultMessage());
                    }
                }
            }

            return Response.error(400, message.toString());
        }

        // 接收类型异常
        if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException error = ((MethodArgumentTypeMismatchException) e);
            message.add(error.getMessage());
            return Response.error(400, message.toString());
        }

        if (e instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) e).getConstraintViolations();
            if (CollectionUtils.isNotEmpty(violations)) {
                violations.forEach(violation -> {
                    message.add(violation.getMessage());
                });
            }

            return Response.error(400, message.toString());
        }

        return Response.error(500, "服务异常");
    }

}
