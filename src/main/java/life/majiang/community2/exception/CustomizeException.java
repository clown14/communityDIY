package life.majiang.community2.exception;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-10-28 22:05
 **/

public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizeException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}

