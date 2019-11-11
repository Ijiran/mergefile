package top.pxyz.common;

/**
 * @author Ijiran
 * @Package
 * @date
 */
public class Result {

    public Result(){}

    public Result(String message, Object data){
        this.message = message;
        this.data = data;
    }

    private String message;

    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
