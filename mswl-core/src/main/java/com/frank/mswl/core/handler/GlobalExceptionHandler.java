package com.frank.mswl.core.handler;

import com.frank.mswl.core.response.SysErrResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 全局异常拦截.
 *
 * @author 张孝党 2019/08/26.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/06/26 张孝党 创建.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 系统异常处理.
     *
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String sysErrorHandler(Exception ex) {

        log.error("全局异常信息--------------->{}", ex.getMessage());

        StackTraceElement[] st = ex.getStackTrace();
        ;
        for (StackTraceElement stackTraceElement : st) {
            String exClass = stackTraceElement.getClassName();
            String method = stackTraceElement.getMethodName();
            int lineNumber = stackTraceElement.getLineNumber();
            log.error("[类:" + exClass + "]调用" + method + "时在第" + stackTraceElement.getLineNumber() + "行代码处发生异常!异常类型:" + ex.getClass().getName());
        }

        return new SysErrResponse(ex.getMessage()).toJsonString();
    }

}
