package com.simple.job.finance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author finance
 * @since 2023-06-29
 */
@TableName("tab_shares_quota")
@Data
public class TabSharesQuota implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 股票代码
     */
    private String tsCode;

    /**
     * 交易日期
     */
    private String tradeDate;

    /**
     * 收盘价
     */
    private Float close;

    /**
     * 开盘价
     */
    private Float open;

    /**
     * 最高价
     */
    private Float high;

    /**
     * 最低价
     */
    private Float low;

    /**
     * 昨收价
     */
    private Float preClose;

    /**
     * 涨跌额
     */
    private Float changem;

    /**
     * 涨跌幅
     */
    private Float pctChange;

    /**
     * 成交量 （手）
     */
    private Float vol;

    /**
     * 成交额 （千元）
     */
    private Float amount;

    /**
     * 复权因子
     */
    private Float adjFactor;

    /**
     * 开盘价后复权
     */
    private Float openHfq;

    /**
     * 开盘价前复权
     */
    private Float openQfq;

    /**
     * 收盘价后复权
     */
    private Float closeHfq;

    /**
     * 收盘价前复权
     */
    private Float closeQfq;

    /**
     * 最高价后复权
     */
    private Float highHfq;

    /**
     * 最高价前复权
     */
    private Float highQfq;

    /**
     * 最低价后复权
     */
    private Float lowHfq;

    /**
     * 最低价前复权
     */
    private Float lowQfq;

    /**
     * 昨收价后复权
     */
    private Float preCloseHfq;

    /**
     * 昨收价前复权
     */
    private Float preCloseQfq;

    /**
     * MCAD_DIF (基于前复权价格计算，下同)
     */
    private Float macdDif;

    /**
     * MCAD_DEA
     */
    private Float macdDea;

    /**
     * MCAD
     */
    private Float macd;

    /**
     * KDJ_K
     */
    private Float kdjK;

    /**
     * KDJ_D
     */
    private Float kdjD;

    /**
     * KDJ_J
     */
    private Float kdjJ;

    /**
     * rsi_6
     */
    @TableField(value = "rsi_6")
    private Float rsi6;

    /**
     * rsi_12
     */
    @TableField(value = "rsi_12")
    private Float rsi12;

    /**
     * rsi_24
     */
    @TableField(value = "rsi_24")
    private Float rsi24;

    /**
     * boll_upper
     */
    private Float bollUpper;

    /**
     * boll_mid
     */
    private Float bollMid;

    /**
     * boll_lower
     */
    private Float bollLower;

    /**
     * cci
     */
    private Float cci;

}
