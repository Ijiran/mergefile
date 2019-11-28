package top.pxyz.merge.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Ijiran
 * @Package top.pxyz.merge.service
 * @date 2019-11-07 22:11
 */
public interface IMergeService {

    List<Map<String, String>> findFiles();

    void mergeTest(InputStream source, InputStream target) throws Exception;

    void mergeTestByJsfw(InputStream source, InputStream target) throws Exception;
}
