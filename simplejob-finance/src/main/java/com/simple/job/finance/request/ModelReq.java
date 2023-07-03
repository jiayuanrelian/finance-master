package com.simple.job.finance.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ModelReq implements Serializable {

    private String token;

    private String api_name;

    private DataModelReq params;


}
