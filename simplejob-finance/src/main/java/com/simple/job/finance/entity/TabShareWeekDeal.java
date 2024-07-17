package com.simple.job.finance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @description /
* @author jl
* @date 2024-07-16
**/
@Data
@TableName("tab_share_week_deal")
public class TabShareWeekDeal implements Serializable {

        /**
        *  number
        **/
        @TableId(value = "id", type = IdType.AUTO)
        private Long id;

        /**
        *  year month
        **/
        @TableField(value = "month_data")
        private String monthData;

        /**
        *  order num
        **/
        @TableField(value = "order_num")
        private String orderNum;

        /**
        *  ts  code num
        **/
        @TableField(value = "ts_code")
        private String tsCode;

        /**
        *  date _start
        **/
        @TableField(value = "date_start")
        private String dateStart;

        /**
        *  date start week
        **/
        @TableField(value = "date_start_week")
        private String dateStartWeek;

        /**
        *  open p
        **/
        @TableField(value = "open_p")
        private Float openP;

        /**
        *  date end
        **/
        @TableField(value = "date_end")
        private String dateEnd;

        /**
        *  date end week
        **/
        @TableField(value = "date_end_week")
        private String dateEndWeek;

        /**
        *  close p
        **/
        @TableField(value = "close_p")
        private Float closeP;

        /**
        *  change pem
        **/
        @TableField(value = "change_pem")
        private Float changePem;

        /**
        *  create tiem
        **/
        @TableField(value = "create_time")
        private Date createTime;

        /**
        *  update time
        **/
        @TableField(value = "update_time")
        private Date updateTime;

}
