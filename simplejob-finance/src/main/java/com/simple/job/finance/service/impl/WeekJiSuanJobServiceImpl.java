package com.simple.job.finance.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.job.finance.entity.TabShareWeekDeal;
import com.simple.job.finance.entity.TabSharesQuota;
import com.simple.job.finance.entity.TabTradeDate;
import com.simple.job.finance.mapper.TShareWeekDealMapper;
import com.simple.job.finance.mapper.TabSharesQuotaMapper;
import com.simple.job.finance.mapper.TabTradeDateMapper;
import com.simple.job.finance.service.IWeekJiSuanJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class WeekJiSuanJobServiceImpl implements IWeekJiSuanJobService {

    @Resource
    private TabSharesQuotaMapper tabSharesQuotaMapper;

    @Resource
    private TabTradeDateMapper tabTradeDateMapper;

    @Resource
    private TShareWeekDealMapper tShareWeekDealMapper;

    @Override
    public void weekJiSuanAction(TabTradeDate tabTradeDate) {
        //找到这周最新的时间
        String weekMonday = DateUtil.format(DateUtil.offsetDay(DateUtil.parse(tabTradeDate.getCalDate(),DatePattern.PURE_DATE_PATTERN) ,-5), DatePattern.PURE_DATE_PATTERN);
        QueryWrapper<TabTradeDate> tradeDateQueryWrapper = new QueryWrapper<>();
        tradeDateQueryWrapper.between("cal_date",weekMonday,tabTradeDate.getCalDate());
        tradeDateQueryWrapper.eq("is_open","1");
        tradeDateQueryWrapper.orderByAsc("cal_date");
        List<TabTradeDate> tabTradeDates = tabTradeDateMapper.selectList(tradeDateQueryWrapper);
        //找到这个最新的记录，计算，保存数据
        if(tabTradeDates.size() >1){
            TabTradeDate firstTrade = tabTradeDates.get(0);
            TabTradeDate lastTrade = tabTradeDates.get(tabTradeDates.size()-1);
            QueryWrapper<TabSharesQuota> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("trade_date",firstTrade.getCalDate());
            List<TabSharesQuota> tabSharesQuotaList = tabSharesQuotaMapper.selectList(queryWrapper);
            for (int i = 0; i < tabSharesQuotaList.size(); i++) {
                TabSharesQuota firstQuota = tabSharesQuotaList.get(i);
                QueryWrapper<TabSharesQuota> queryWrapperLast = new QueryWrapper<>();
                queryWrapperLast.eq("trade_date",lastTrade.getCalDate());
                queryWrapperLast.eq("ts_code",firstQuota.getTsCode());
                TabSharesQuota lastQuota = tabSharesQuotaMapper.selectList(queryWrapperLast).get(0);

                QueryWrapper<TabShareWeekDeal> weekDealQueryWrapper = new QueryWrapper<>();
                weekDealQueryWrapper.eq("ts_code",firstQuota.getTsCode());
                weekDealQueryWrapper.eq("date_start",firstTrade.getCalDate());
                weekDealQueryWrapper.eq("date_end",lastTrade.getCalDate());
                List<TabShareWeekDeal> tabShareWeekDeals = tShareWeekDealMapper.selectList(weekDealQueryWrapper);
                if(CollectionUtil.isEmpty(tabShareWeekDeals)){
                    TabShareWeekDeal tabShareWeekDeal = new TabShareWeekDeal();
                    tabShareWeekDeal.setMonthData(firstTrade.getCalDate().substring(0,6));
                    tabShareWeekDeal.setTsCode(firstQuota.getTsCode());
                    tabShareWeekDeal.setDateStart(firstTrade.getCalDate());
                    tabShareWeekDeal.setOpenP(firstQuota.getOpen());
                    BigDecimal openP = new BigDecimal(tabShareWeekDeal.getOpenP());
                    tabShareWeekDeal.setDateEnd(lastQuota.getTradeDate());
                    tabShareWeekDeal.setCloseP(lastQuota.getClose());
                    BigDecimal closeP = new BigDecimal(tabShareWeekDeal.getCloseP());
                    tabShareWeekDeal.setChangePem((((openP.subtract(closeP)).multiply(new BigDecimal(100))).divide(openP,BigDecimal.ROUND_CEILING)).floatValue());
                    tabShareWeekDeal.setCreateTime(new Date());
                    tabShareWeekDeal.setUpdateTime(new Date());
                    tShareWeekDealMapper.insert(tabShareWeekDeal);
                    log.info("统计代码：{}，从{}至{}",firstQuota.getTsCode(),firstTrade.getCalDate(),lastTrade.getCalDate());
                }
            }
        } else if (tabTradeDates.size()==1) {

        }
    }
}
