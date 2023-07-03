package com.simple.job.finance.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DataModelResp implements Serializable {

    private String[] fields;

    private List<String[]> items;
}
