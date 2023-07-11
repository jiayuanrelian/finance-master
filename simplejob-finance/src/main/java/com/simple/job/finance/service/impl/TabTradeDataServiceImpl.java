package com.simple.job.finance.service.impl;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple.job.finance.entity.TabSharesData;
import com.simple.job.finance.entity.TabTradeDate;
import com.simple.job.finance.mapper.TabSharesDataMapper;
import com.simple.job.finance.mapper.TabTradeDateMapper;
import com.simple.job.finance.request.DataModelReq;
import com.simple.job.finance.request.ModelReq;
import com.simple.job.finance.request.ModelResp;
import com.simple.job.finance.service.ITabTradeDataService;
import com.simple.job.finance.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 交易日期 服务实现类
 * </p>
 *
 * @author finance
 * @since 2023-06-17
 */
@Service
@Slf4j
public class TabTradeDataServiceImpl extends ServiceImpl<TabTradeDateMapper, TabTradeDate> implements ITabTradeDataService {

    @Resource
    private TabSharesDataMapper tabSharesDataMapper;

    @Override
    public void stockBasic(String trandName) {
        DataModelReq dataModelReq = new DataModelReq();
        dataModelReq.setExchange(trandName);

        ModelReq modelReq = new ModelReq();
        modelReq.setToken(CommonUtils.TOKEN);
        modelReq.setApi_name("stock_basic");
        modelReq.setParams(dataModelReq);
        String result= HttpUtil.post(CommonUtils.URL, JSON.toJSONString(modelReq));
        ModelResp modelResp = JSONObject.parseObject(result, ModelResp.class);
        if(modelResp.getCode().equals("0")){
            TabSharesData tabSharesDataAdd = new TabSharesData();
            List<String[]> items = modelResp.getData().getItems();
            for (int i = 0; i < items.size(); i++) {
                String[] item =  items.get(i);
                QueryWrapper<TabSharesData> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("symbol",item[1]);
                TabSharesData tabSharesData = tabSharesDataMapper.selectOne(queryWrapper);
                if(null == tabSharesData){
                    tabSharesDataAdd.setId(null);
                    tabSharesDataAdd.setTsCode(item[0]);
                    tabSharesDataAdd.setSymbol(item[1]);
                    tabSharesDataAdd.setName(item[2]);
                    tabSharesDataAdd.setArea(item[3]);
                    tabSharesDataAdd.setIndustry(item[4]);
                    tabSharesDataAdd.setMarket(item[5]);
                    tabSharesDataAdd.setListDate(item[6]);
                    tabSharesDataMapper.insert(tabSharesDataAdd);
                    log.debug(JSON.toJSONString(tabSharesDataAdd));
                }
            }
        }else {
            log.error("获取信息失败："+ result);
        }
    }

    @Override
    public List<TabSharesData> queryAllShares() {
        QueryWrapper<TabSharesData> queryWrapper = new QueryWrapper<>();
        return tabSharesDataMapper.selectList(queryWrapper);
    }
}
