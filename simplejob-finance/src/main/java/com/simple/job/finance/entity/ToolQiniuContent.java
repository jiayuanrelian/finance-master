package com.simple.job.finance.entity;

import lombok.Data;
import java.sql.Timestamp;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

/**
* @description /
* @author JL
* @date 2023-12-14
**/
@Data
@TableName("tool_qiniu_content")
public class ToolQiniuContent implements Serializable {

        /**
        *  ID
        **/
        @TableId(value = "content_id", type = IdType.AUTO)
        private Long contentId;

        /**
        *  Bucket 识别符
        **/
        @TableField(value = "bucket")
        private String bucket;

        /**
        *  文件名称
        **/
        @TableField(value = "name")
        private String name;

        /**
        *  文件大小
        **/
        @TableField(value = "size")
        private String size;

        /**
        *  文件类型：私有或公开
        **/
        @TableField(value = "type")
        private String type;

        /**
        *  文件url
        **/
        @TableField(value = "url")
        private String url;

        /**
        *  文件后缀
        **/
        @TableField(value = "suffix")
        private String suffix;

        /**
        *  上传或同步的时间
        **/
        @TableField(value = "update_time")
        private Timestamp updateTime;

}
