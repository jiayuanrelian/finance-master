package com.simple.job.finance.job;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.simple.job.finance.entity.TabTradeDate;
import com.simple.job.finance.service.IFetchSwitchHDailyService;
import com.simple.job.finance.service.ITradeDateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Fetch Switch every day
 */
@Service
@Slf4j
public class FetchSwitchHDailyJob implements SimpleJob {

    @Autowired
    private ITradeDateService tradeDateService;

    @Autowired
    private IFetchSwitchHDailyService fetchSwitchHDailyService;

    public static final String DEAL_FALG_FIRST = "deal_falg_first";

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("FetchSwitchHDailyJob start {}", DateUtil.format(new Date(), DatePattern.CHINESE_DATE_TIME_PATTERN));
        try {
            //undone task
            List<TabTradeDate> tabTradeDates = tradeDateService.queryUndoneTask(DEAL_FALG_FIRST);
            if(CollectionUtil.isNotEmpty(tabTradeDates)){
                //逐个处理
                for (TabTradeDate tabTradeDate : tabTradeDates) {
                    log.info("开始处理...{}", JSON.toJSONString(tabTradeDate));
                    fetchSwitchHDailyService.fetchSwitchH(tabTradeDate);
                    log.info("结束处理...{}",JSON.toJSONString(tabTradeDate));
                }
            }else {
                log.info("FetchSwitchHDailyJob no task");
            }
        }catch (Exception e){
            log.error("FetchSwitchHDailyJob has Exception:{}",e);
        }
        log.info("FetchSwitchHDailyJob end {}",DateUtil.format(new Date(), DatePattern.CHINESE_DATE_TIME_PATTERN));
    }
}
