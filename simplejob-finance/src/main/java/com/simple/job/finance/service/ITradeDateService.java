package com.simple.job.finance.service;

import com.simple.job.finance.entity.TabTradeDate;

import java.util.List;

public interface ITradeDateService {
    void tradeDateAction();

    List<TabTradeDate> queryUndoneTask();
}
