/*
*  Copyright 2019-2023 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package com.simple.job.finance.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @description /
* @author JL
* @date 2023-12-13
**/
@Data
@TableName("tab_share_switch")
public class TabShareSwitch implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String tsCode;

    private String tradeDate;

    private BigDecimal close;

    private BigDecimal turnoverRate;

    private BigDecimal turnoverRateF;

    private BigDecimal volumeRatio;

    private BigDecimal pe;

    private BigDecimal peTtm;

    private BigDecimal pb;

    private BigDecimal ps;

    private BigDecimal psTtm;

    private BigDecimal dvRatio;

    private BigDecimal dvTtm;

    private BigDecimal totalShare;

    private BigDecimal floatShare;

    private BigDecimal freeShare;

    private BigDecimal totalMv;

    private BigDecimal circMv;

    public void copy(TabShareSwitch source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
