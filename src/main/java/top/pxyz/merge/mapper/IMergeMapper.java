package top.pxyz.merge.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Ijiran
 * @Package top.pxyz.merge.mapper
 * @date 2019-11-07 22:11
 */
@Repository
public interface IMergeMapper {

    List<Map<String,String>> findFiles();

}
