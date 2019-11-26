package top.pxyz.util;

import org.springframework.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ijiran
 * @Package top.pxyz.util
 * @date 2019-11-26 21:48
 */
public class ObjectUtils {

    private static Map<String, BeanCopier> beanCopierCache = new HashMap<String, BeanCopier>();

    /**
     * 对象属性拷贝，封装Cglib
     * @param source 源对象
     * @param target 目标对象
     * @author Ijiran
     */
    public static void cglibCopyProperties(Object source,Object target){
        String beanKey = generateKey(source.getClass(),target.getClass());
        BeanCopier copier = null;
        if (!beanCopierCache.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopierCache.put(beanKey, copier);
        }else {
            copier = beanCopierCache.get(beanKey);
        }
        copier.copy(source, target, null);
    }

    private static String generateKey(Class<?>class1,Class<?>class2){
        return class1.toString() + class2.toString();
    }

}
