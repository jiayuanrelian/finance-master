package com.simple.job.finance.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ModelResp implements Serializable {

    private String request_id;

    private String code;

    private String msg;

    private DataModelResp data;
}
