package com.simple.job.finance.job;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.simple.job.finance.service.ITabTradeDataService;
import com.simple.job.finance.utils.SharesTradeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class FetchSharesListJobDaily implements SimpleJob {

    private String TRADE_NAME = "SSE,SZSE";
    @Resource
    private ITabTradeDataService tabTradeDateService;

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("FetchSharesListJobDaily start {}", DateUtil.format(new Date(), DatePattern.CHINESE_DATE_TIME_PATTERN));
        try {
            //当天是不是交易日
            boolean tradeOrnot = SharesTradeUtils.checkCurrentDayTradeOrnot();
            if(tradeOrnot){
                String[] trandNames = TRADE_NAME.split(",");
                for (int i = 0; i < trandNames.length; i++) {
                    tabTradeDateService.stockBasic(trandNames[i]);
                }
            }else {
                log.info("FetchSharesListJobDaily 今天非交易日");
            }
        }catch (Exception e){
            log.error("FetchSharesListJobDaily has Exception:{}",e);
        }
        log.info("FetchSharesListJobDaily end {}",DateUtil.format(new Date(), DatePattern.CHINESE_DATE_TIME_PATTERN));

    }
}
