package com.simple.job.finance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 交易日期
 * </p>
 *
 * @author finance
 * @since 2023-06-17
 */
@TableName("tab_trade_date")
@Data
public class TabTradeDate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 交易所 SSE上交所 SZSE深交所
     */
    private String exchange;

    /**
     * 日历日期
     */
    private String calDate;

    /**
     * 是否交易 0休市 1交易
     */
    private Integer isOpen;

    /**
     * 上一个交易日
     */
    private String pretradeDate;

    /**
     * 是否处理 0 未处理 1 已处理
     */
    private String dealFlag;


    private Integer dealFalgFirst;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 修改时间
     */
    private Date modyfiTime;


}
