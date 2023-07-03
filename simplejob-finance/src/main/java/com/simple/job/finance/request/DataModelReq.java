package com.simple.job.finance.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataModelReq implements Serializable {

    private String start_date;

    private String end_date;

    private String exchange;

    private String ts_code;

    private String trade_date;
}
