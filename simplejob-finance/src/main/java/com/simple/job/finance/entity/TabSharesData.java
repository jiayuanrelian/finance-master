package com.simple.job.finance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 上市股票信息
 * </p>
 *
 * @author finance
 * @since 2023-06-29
 */
@TableName("tab_shares_data")
@Data
public class TabSharesData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * TS代码
     */
    private String tsCode;

    /**
     * 股票代码
     */
    private String symbol;

    /**
     * 股票名称
     */
    private String name;

    /**
     * 地域
     */
    private String area;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 股票全称
     */
    private String fullname;

    /**
     * 英文全称
     */
    private String enname;

    /**
     * 拼音缩写
     */
    private String cnspell;

    /**
     * 市场类型（主板/创业板/科创板/CDR）
     */
    private String market;

    /**
     * 交易所代码
     */
    private String exchange;

    /**
     * 交易货币
     */
    private String currType;

    /**
     * N 上市状态 L上市 D退市 P暂停上市
     */
    private String listStatus;

    /**
     * 上市日期
     */
    private String listDate;

    /**
     * 退市日期
     */
    private String delistDate;

    /**
     * N 是否沪深港通标的，N否 H沪股通 S深股通
     */
    private String isHs;

}
