package com.simple.job.finance.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.job.finance.entity.TabShareSwitch;
import com.simple.job.finance.entity.TabTradeDate;
import com.simple.job.finance.mapper.TabShareSwitchMapper;
import com.simple.job.finance.mapper.TabTradeDateMapper;
import com.simple.job.finance.request.DataModelReq;
import com.simple.job.finance.request.ModelReq;
import com.simple.job.finance.request.ModelResp;
import com.simple.job.finance.service.IFetchSwitchHDailyService;
import com.simple.job.finance.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class FetchSwitchHDailyServiceImpl implements IFetchSwitchHDailyService {


    @Resource
    private TabShareSwitchMapper tabShareSwitchMapper;

    @Resource
    private TabTradeDateMapper tradeDateMapper;

    @Override
    public void fetchSwitchH(TabTradeDate tabTradeDate) {
        String tradeDate = tabTradeDate.getCalDate();
        DataModelReq dataModelReq = new DataModelReq();
        dataModelReq.setTrade_date(tradeDate);

        ModelReq modelReq = new ModelReq();
        modelReq.setToken(CommonUtils.TOKEN);
        modelReq.setApi_name("daily_basic");
        modelReq.setParams(dataModelReq);
        String result = HttpUtil.post(CommonUtils.URL, JSON.toJSONString(modelReq));
        ModelResp modelResp = JSONObject.parseObject(result, ModelResp.class);
        if (modelResp.getCode().equals("0")) {
            TabShareSwitch tabShareSwitch = new TabShareSwitch();
            List<String[]> items = modelResp.getData().getItems();
            for (int i = 0; i < items.size(); i++) {
                String[] item = items.get(i);
                QueryWrapper<TabShareSwitch> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("ts_code", item[0]);
                queryWrapper.eq("trade_date", tradeDate);
                TabShareSwitch tabShareSwitchExit = tabShareSwitchMapper.selectOne(queryWrapper);
                if (null == tabShareSwitchExit) {
                    tabShareSwitch.setId(null);
                    tabShareSwitch.setTsCode(item[0]);
                    tabShareSwitch.setTradeDate(item[1]);
                    tabShareSwitch.setClose(new BigDecimal(StringUtils.isEmpty(item[2]) ? "0" : item[2]));
                    tabShareSwitch.setTurnoverRate(new BigDecimal(StringUtils.isEmpty(item[3]) ? "0" : item[3]));
                    tabShareSwitch.setTurnoverRateF(new BigDecimal(StringUtils.isEmpty(item[4]) ? "0" : item[4]));
                    tabShareSwitch.setVolumeRatio(new BigDecimal(StringUtils.isEmpty(item[5]) ? "0" : item[5]));
                    tabShareSwitch.setPe(new BigDecimal(StringUtils.isEmpty(item[6]) ? "0" : item[6]));
                    tabShareSwitch.setPeTtm(new BigDecimal(StringUtils.isEmpty(item[7]) ? "0" : item[7]));
                    tabShareSwitch.setPb(new BigDecimal(StringUtils.isEmpty(item[8]) ? "0" : item[8]));
                    tabShareSwitch.setPs(new BigDecimal(StringUtils.isEmpty(item[9]) ? "0" : item[9]));
                    tabShareSwitch.setPsTtm(new BigDecimal(StringUtils.isEmpty(item[10]) ? "0" : item[10]));
                    tabShareSwitch.setDvRatio(new BigDecimal(StringUtils.isEmpty(item[11]) ? "0" : item[11]));
                    tabShareSwitch.setDvTtm(new BigDecimal(StringUtils.isEmpty(item[12]) ? "0" : item[12]));
                    tabShareSwitch.setTotalShare(new BigDecimal(StringUtils.isEmpty(item[13]) ? "0" : item[13]));
                    tabShareSwitch.setFloatShare(new BigDecimal(StringUtils.isEmpty(item[14]) ? "0" : item[14]));
                    tabShareSwitch.setFreeShare(new BigDecimal(StringUtils.isEmpty(item[15]) ? "0" : item[15]));
                    tabShareSwitch.setTotalMv(new BigDecimal(StringUtils.isEmpty(item[16]) ? "0" : item[16]));
                    tabShareSwitch.setCircMv(new BigDecimal(StringUtils.isEmpty(item[17]) ? "0" : item[17]));
                    tabShareSwitchMapper.insert(tabShareSwitch);
                } else {
                    log.info("fetch data fail");
                }
            }
            // update done task
            tabTradeDate.setDealFalgFirst(1);
            tabTradeDate.setModyfiTime(new Date());
            tradeDateMapper.updateById(tabTradeDate);
        }
    }
}
