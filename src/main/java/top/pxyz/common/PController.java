package top.pxyz.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

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

    /**
     * list转json
     * @param list
     * @return
     */
    public String toJson(List<?> list){
        return JSON.toJSONString(list);
    }

    /**
     * map转json
     * @param map
     * @return
     */
    public String toJson(Map<?,?> map){
        return JSON.toJSONString(map);
    }

}
