package top.pxyz.common;

/**
 * @author Ijiran
 * @Package top.pxyz.common
 * @date 2019-11-12 0:08
 */
public class PController {

    /**
     * 返回封装
     * @param message
     * @param object
     * @return
     */
    public Result result(String message,Object object){
        Result result = new Result();
        if(message!=null)result.setMessage(message);
        if(object!=null)result.setData(object);
        return result;
    }

}
