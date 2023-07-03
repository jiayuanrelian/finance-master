package com.simple.job.finance.job;

import com.simple.job.finance.service.ITabTradeDateService;
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
    private ITabTradeDateService tabTradeDateService;

    @Override
    public void execute(ShardingContext shardingContext) {
//        log.info("Hello World MySimpleJob" + new Date());
//        String[] trandNames = TRADE_NAME.split(",");
//        for (int i = 0; i < trandNames.length; i++) {
//            tabTradeDateService.stockBasic(trandNames[i]);
//        }
//        System.out.println("Hello World MySimpleJob" + new Date());
    }
}
