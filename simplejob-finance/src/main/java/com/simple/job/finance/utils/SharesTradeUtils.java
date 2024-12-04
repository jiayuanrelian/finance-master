package com.simple.job.finance.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.simple.job.finance.request.DataModelReq;
import com.simple.job.finance.request.ModelReq;
import com.simple.job.finance.request.ModelResp;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class SharesTradeUtils {

    public static boolean checkCurrentDayTradeOrnot(){
        String tradeDate = DateUtil.format(DateUtil.offsetDay(new Date(),-1), DatePattern.PURE_DATE_PATTERN);
        DataModelReq dataModelReq = new DataModelReq();
        dataModelReq.setExchange("SSE");
        dataModelReq.setStart_date(tradeDate);
        dataModelReq.setEnd_date(tradeDate);
        dataModelReq.setTrade_date(tradeDate);

        ModelReq modelReq = new ModelReq();
        modelReq.setToken(CommonUtils.TOKEN);
        modelReq.setApi_name("trade_cal");
        modelReq.setParams(dataModelReq);
        String result= HttpUtil.post(CommonUtils.URL, JSON.toJSONString(modelReq));
        ModelResp modelResp = JSONObject.parseObject(result, ModelResp.class);
        log.info("JSON:{}",JSONObject.toJSONString(modelResp));
        if(modelResp.getCode().equals("0")&&(modelResp.getData().getItems().get(0)[2].equals("1"))){//开市
            return true;
        }
        return false;
    }

//    public static void main(String[] args) {
//        boolean flag = SharesTradeUtils.checkCurrentDayTradeOrnot();
//    }
}
