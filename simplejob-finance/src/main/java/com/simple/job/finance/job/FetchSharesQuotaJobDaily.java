package com.simple.job.finance.job;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.simple.job.finance.entity.TabSharesData;
import com.simple.job.finance.service.IFetchSharesQuotaService;
import com.simple.job.finance.service.ITabTradeDateService;
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
    private ITabTradeDateService tabTradeDateService;

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("FetchSharesQuotaJobDaily start {}",DateUtil.format(new Date(), DatePattern.CHINESE_DATE_TIME_PATTERN));
        try {
            //查询所有的股票代码
            List<TabSharesData> tabSharesDataList = tabTradeDateService.queryAllShares();
            //逐个处理
            for (TabSharesData tabSharesData : tabSharesDataList) {
                log.info("开始处理...{}",tabSharesData.getName());
                fetchSharesQuotaService.fetchSharesQuota(tabSharesData.getTsCode());
                log.info("结束处理...{}",tabSharesData.getName());
            }
        }catch (Exception e){
            log.error("FetchSharesQuotaJobDaily has Exception:{}",e);
        }
        log.info("FetchSharesQuotaJobDaily end {}",DateUtil.format(new Date(), DatePattern.CHINESE_DATE_TIME_PATTERN));
    }
}
