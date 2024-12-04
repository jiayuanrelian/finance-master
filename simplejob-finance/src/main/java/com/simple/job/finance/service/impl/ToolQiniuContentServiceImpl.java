package com.simple.job.finance.service.impl;

import com.simple.job.finance.service.ToolQiniuContentService;
import com.simple.job.finance.mapper.ToolQiniuContentMapper;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
/**
* @description 服务实现
* @author JL
* @date 2023-12-14
**/
@Slf4j
@Service
public class ToolQiniuContentServiceImpl implements ToolQiniuContentService {

    @Resource
    private ToolQiniuContentMapper toolQiniuContentMapper;

}