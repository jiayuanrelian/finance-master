package com.simple.job.finance.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.job.finance.entity.TabTradeDate;
import com.simple.job.finance.mapper.TabTradeDateMapper;
import com.simple.job.finance.request.DataModelReq;
import com.simple.job.finance.request.ModelReq;
import com.simple.job.finance.request.ModelResp;
import com.simple.job.finance.service.ITradeDateService;
import com.simple.job.finance.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TradeDateServiceImpl implements ITradeDateService {

    @Resource
    private TabTradeDateMapper tradeDateMapper;
    @Override
    public void tradeDateAction() {
        String tradeDateStart = DateUtil.format(DateUtil.offsetDay(new Date(),-20), DatePattern.PURE_DATE_PATTERN);
        String tradeDateEnd = DateUtil.format(DateUtil.offsetDay(new Date(),5), DatePattern.PURE_DATE_PATTERN);
        DataModelReq dataModelReq = new DataModelReq();
        dataModelReq.setExchange("SSE");
        dataModelReq.setStart_date(tradeDateStart);
        dataModelReq.setEnd_date(tradeDateEnd);

        ModelReq modelReq = new ModelReq();
        modelReq.setToken(CommonUtils.TOKEN);
        modelReq.setApi_name("trade_cal");
        modelReq.setParams(dataModelReq);
        String result= HttpUtil.post(CommonUtils.URL, JSON.toJSONString(modelReq));
        ModelResp modelResp = JSONObject.parseObject(result, ModelResp.class);
        log.info("JSON:{}",JSONObject.toJSONString(modelResp));
        // not exit please insert
        if(modelResp.getCode().equals("0")){
            TabTradeDate tabTradeDateAdd = new TabTradeDate();
            List<String[]> items = modelResp.getData().getItems();
            for (int i = 0; i < items.size(); i++) {
                String[] item =  items.get(i);
                QueryWrapper<TabTradeDate> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("cal_date",item[1]);
                TabTradeDate tabSharesData = tradeDateMapper.selectOne(queryWrapper);
                if(null == tabSharesData){
                    tabTradeDateAdd.setId(null);
                    tabTradeDateAdd.setExchange("SSE");
                    tabTradeDateAdd.setCalDate(item[1]);
                    tabTradeDateAdd.setIsOpen(Integer.parseInt(item[2]));
                    tabTradeDateAdd.setCreatTime(DateUtil.offsetHour(DateUtil.parse(item[1],DatePattern.PURE_DATE_PATTERN),18));
                    tabTradeDateAdd.setModyfiTime(tabTradeDateAdd.getCreatTime());
                    tradeDateMapper.insert(tabTradeDateAdd);
                    log.debug(JSON.toJSONString(tabTradeDateAdd));
                }
            }
        }else {
            log.error("fetch data falil：{}" , result);
        }
    }

    @Override
    public List<TabTradeDate> queryUndoneTask(String dealFlag) {
        QueryWrapper<TabTradeDate> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isEmpty(dealFlag)){
            queryWrapper.eq("deal_flag","0");
        }else {
            queryWrapper.eq(dealFlag,"0");
        }
        queryWrapper.eq("is_open","1");
        queryWrapper.le("creat_time",new Date());
        queryWrapper.orderByAsc("cal_date");
        List<TabTradeDate> tabSharesData = tradeDateMapper.selectList(queryWrapper);
        return tabSharesData;
    }
}
