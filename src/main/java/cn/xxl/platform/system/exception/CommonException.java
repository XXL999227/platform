package cn.xxl.platform.system.exception;

/**
 * 常见异常
 *
 * @author xxl
 * @since 2023/06/27
 */
public class CommonException extends RuntimeException{
    public CommonException() {
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    public CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
