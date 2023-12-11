package com.simple.job.finance.job;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.simple.job.finance.entity.TabTradeDate;
import com.simple.job.finance.service.IFetchSharesQuotaService;
import com.simple.job.finance.service.ITradeDateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FetchSharesQuotaJobDaily implements SimpleJob {


    @Autowired
    private IFetchSharesQuotaService fetchSharesQuotaService;

    @Autowired
    private ITradeDateService tradeDateService;

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("FetchSharesQuotaJobDaily start {}",DateUtil.format(new Date(), DatePattern.CHINESE_DATE_TIME_PATTERN));
        try {
            //undone task
            List<TabTradeDate> tabTradeDates = tradeDateService.queryUndoneTask();
            if(CollectionUtil.isNotEmpty(tabTradeDates)){
                //逐个处理
                for (TabTradeDate tabTradeDate : tabTradeDates) {
                    log.info("开始处理...{}", JSON.toJSONString(tabTradeDate));
                    fetchSharesQuotaService.fetchSharesQuota(tabTradeDate);
                    log.info("结束处理...{}",JSON.toJSONString(tabTradeDate));
                }
            }else {
                log.info("FetchSharesQuotaJobDaily no task");
            }
        }catch (Exception e){
            log.error("FetchSharesQuotaJobDaily has Exception:{}",e);
        }
        log.info("FetchSharesQuotaJobDaily end {}",DateUtil.format(new Date(), DatePattern.CHINESE_DATE_TIME_PATTERN));
    }
}
