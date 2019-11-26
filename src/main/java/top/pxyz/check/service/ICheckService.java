package top.pxyz.check.service;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文档效验Service
 * @author Ijiran
 * @Package top.pxyz.check.service
 * @date 2019-11-07 21:44
 */
public interface ICheckService {

    /**
     * doc文档测试
     * @param is
     */
    void checkDocTest(InputStream is);

    /**
     * docx文档测试
     * @param is
     */
    void checkDocxTest(InputStream is) throws Exception;

}
