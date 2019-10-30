package life.majiang.community2.exception;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-10-28 22:05
 **/

public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(String message) {
        this.message = message;
    }

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}

    