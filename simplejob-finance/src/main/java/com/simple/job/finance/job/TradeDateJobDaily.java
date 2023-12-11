package com.simple.job.finance.job;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.simple.job.finance.service.ITradeDateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Trade date daily
 */
@Service
@Slf4j
public class TradeDateJobDaily implements SimpleJob {

    @Resource
    private ITradeDateService tradeDateService;
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("TradeDateJobDaily start {}", DateUtil.format(new Date(), DatePattern.CHINESE_DATE_TIME_PATTERN));
        try {
            tradeDateService.tradeDateAction();
        }catch (Exception e){
            log.error("TradeDateJobDaily has Exception:{}",e);
        }
        log.info("TradeDateJobDaily end {}",DateUtil.format(new Date(), DatePattern.CHINESE_DATE_TIME_PATTERN));
    }
}
