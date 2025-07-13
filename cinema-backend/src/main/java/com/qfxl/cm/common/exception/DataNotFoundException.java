package com.qfxl.cm.common.exception;

/**
 * 数据未找到异常
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
public class DataNotFoundException extends RuntimeException {

    static final long serialVersionUID = -7034897190745456227L;

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String message) {
        super(message);
    }

}
