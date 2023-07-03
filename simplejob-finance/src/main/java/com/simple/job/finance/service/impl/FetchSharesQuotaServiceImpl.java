package com.simple.job.finance.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.job.finance.entity.TabSharesQuota;
import com.simple.job.finance.mapper.TabSharesQuotaMapper;
import com.simple.job.finance.request.DataModelReq;
import com.simple.job.finance.request.ModelReq;
import com.simple.job.finance.request.ModelResp;
import com.simple.job.finance.service.IFetchSharesQuotaService;
import com.simple.job.finance.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FetchSharesQuotaServiceImpl implements IFetchSharesQuotaService {

    @Resource
    private TabSharesQuotaMapper tabSharesQuotaMapper;

    @Override
    public void fetchSharesQuota(String tsCode) {
        //查询每个股票的指标
        String tradeDate = DateUtil.format(DateUtil.offsetDay(new Date(),-1), DatePattern.PURE_DATE_PATTERN);
        DataModelReq dataModelReq = new DataModelReq();
        dataModelReq.setTs_code(tsCode);
        dataModelReq.setTrade_date(tradeDate);
        dataModelReq.setStart_date(tradeDate);
        dataModelReq.setEnd_date(tradeDate);

        ModelReq modelReq = new ModelReq();
        modelReq.setToken(CommonUtils.TOKEN);
        modelReq.setApi_name("stk_factor");
        modelReq.setParams(dataModelReq);
        String result= HttpUtil.post(CommonUtils.URL, JSON.toJSONString(modelReq));
        ModelResp modelResp = JSONObject.parseObject(result, ModelResp.class);
        if(modelResp.getCode().equals("0")){
            TabSharesQuota tabSharesQuota = new TabSharesQuota();
            List<String[]> items = modelResp.getData().getItems();
            for (int i = 0; i < items.size(); i++) {
                String[] item =  items.get(i);
                QueryWrapper<TabSharesQuota> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("ts_code",item[1]);
                queryWrapper.eq("trade_date",tradeDate);
                TabSharesQuota tabSharesQuotaExit = tabSharesQuotaMapper.selectOne(queryWrapper);
                if(null == tabSharesQuotaExit){
                    tabSharesQuota.setId(null);
                    tabSharesQuota.setTsCode(item[0]);
                    tabSharesQuota.setTradeDate(item[1]);
                    tabSharesQuota.setClose(Float.parseFloat(StringUtils.isEmpty(item[2])?"0":item[2]));
                    tabSharesQuota.setOpen(Float.parseFloat(StringUtils.isEmpty(item[3])?"0":item[3]));
                    tabSharesQuota.setHigh(Float.parseFloat(StringUtils.isEmpty(item[4])?"0":item[4]));
                    tabSharesQuota.setLow(Float.parseFloat(StringUtils.isEmpty(item[5])?"0":item[5]));
                    tabSharesQuota.setPreClose(Float.parseFloat(StringUtils.isEmpty(item[6])?"0":item[6]));
                    tabSharesQuota.setChangem(Float.parseFloat(StringUtils.isEmpty(item[7])?"0":item[7]));
                    tabSharesQuota.setPctChange(Float.parseFloat(StringUtils.isEmpty(item[8])?"0":item[8]));
                    tabSharesQuota.setVol(Float.parseFloat(StringUtils.isEmpty(item[9])?"0":item[9]));
                    tabSharesQuota.setAmount(Float.parseFloat(StringUtils.isEmpty(item[10])?"0":item[10]));
                    tabSharesQuota.setAdjFactor(Float.parseFloat(StringUtils.isEmpty(item[11])?"0":item[11]));
                    tabSharesQuota.setOpenHfq(Float.parseFloat(StringUtils.isEmpty(item[12])?"0":item[12]));
                    tabSharesQuota.setOpenQfq(Float.parseFloat(StringUtils.isEmpty(item[13])?"0":item[13]));
                    tabSharesQuota.setCloseHfq(Float.parseFloat(StringUtils.isEmpty(item[14])?"0":item[14]));
                    tabSharesQuota.setCloseQfq(Float.parseFloat(StringUtils.isEmpty(item[15])?"0":item[15]));
                    tabSharesQuota.setHighHfq(Float.parseFloat(StringUtils.isEmpty(item[16])?"0":item[16]));
                    tabSharesQuota.setHighQfq(Float.parseFloat(StringUtils.isEmpty(item[17])?"0":item[17]));
                    tabSharesQuota.setLowHfq(Float.parseFloat(StringUtils.isEmpty(item[18])?"0":item[18]));
                    tabSharesQuota.setLowQfq(Float.parseFloat(StringUtils.isEmpty(item[19])?"0":item[19]));
                    tabSharesQuota.setPreCloseHfq(Float.parseFloat(StringUtils.isEmpty(item[20])?"0":item[20]));
                    tabSharesQuota.setPreCloseQfq(Float.parseFloat(StringUtils.isEmpty(item[21])?"0":item[21]));
                    tabSharesQuota.setMacdDif(Float.parseFloat(StringUtils.isEmpty(item[22])?"0":item[22]));
                    tabSharesQuota.setMacdDea(Float.parseFloat(StringUtils.isEmpty(item[23])?"0":item[23]));
                    tabSharesQuota.setMacd(Float.parseFloat(StringUtils.isEmpty(item[24])?"0":item[24]));
                    tabSharesQuota.setKdjK(Float.parseFloat(StringUtils.isEmpty(item[25])?"0":item[25]));
                    tabSharesQuota.setKdjD(Float.parseFloat(StringUtils.isEmpty(item[26])?"0":item[26]));
                    tabSharesQuota.setKdjJ(Float.parseFloat(StringUtils.isEmpty(item[27])?"0":item[27]));

                    tabSharesQuota.setRsi6(Float.parseFloat(StringUtils.isEmpty(item[28])?"0":item[28]));
                    tabSharesQuota.setRsi12(Float.parseFloat(StringUtils.isEmpty(item[29])?"0":item[29]));
                    tabSharesQuota.setRsi24(Float.parseFloat(StringUtils.isEmpty(item[30])?"0":item[30]));
                    tabSharesQuota.setBollUpper(Float.parseFloat(StringUtils.isEmpty(item[31])?"0":item[31]));
                    tabSharesQuota.setBollMid(Float.parseFloat(StringUtils.isEmpty(item[32])?"0":item[32]));
                    tabSharesQuota.setBollLower(Float.parseFloat(StringUtils.isEmpty(item[33])?"0":item[33]));
                    tabSharesQuota.setCci(Float.parseFloat(StringUtils.isEmpty(item[34])?"0":item[34]));
                    tabSharesQuotaMapper.insert(tabSharesQuota);
                }
            }
        }else {
            log.error("获取信息失败："+ result);
        }
    }
}
