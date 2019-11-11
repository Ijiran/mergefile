package top.pxyz.merge.service.impl;

import org.springframework.stereotype.Service;
import top.pxyz.merge.mapper.IMergeMapper;
import top.pxyz.merge.service.IMergeService;

import javax.annotation.Resource;

/**
 * @author Ijiran
 * @Package top.pxyz.merge.service.impl
 * @date 2019-11-07 22:12
 */
@Service
public class MergeServiceImpl implements IMergeService {

    @Resource
    private IMergeMapper mergeMapper;



}
