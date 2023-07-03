package com.simple.job.finance.service;

import com.simple.job.finance.entity.TabSharesData;
import com.simple.job.finance.entity.TabTradeDate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 交易日期 服务类
 * </p>
 *
 * @author finance
 * @since 2023-06-17
 */
public interface ITabTradeDateService extends IService<TabTradeDate> {

    void stockBasic(String trandName);

    List<TabSharesData> queryAllShares();
}
