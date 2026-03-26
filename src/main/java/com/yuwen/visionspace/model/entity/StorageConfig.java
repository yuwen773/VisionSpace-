package com.yuwen.visionspace.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "storage_config")
public class StorageConfig implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 平台标识：minio / cos / oss / obs
     */
    private String platform;

    /**
     * 显示名称
     */
    private String platformName;

    /**
     * 请求地址
     */
    private String endpoint;

    /**
     * 区域
     */
    private String region;

    /**
     * Bucket名称
     */
    private String bucket;

    /**
     * AccessKey
     */
    private String accessKey;

    /**
     * SecretKey
     */
    private String secretKey;

    /**
     * 自定义域名
     */
    private String domain;

    /**
     * 存储基础路径
     */
    private String basePath;

    /**
     * 是否激活（1=激活，同一时刻仅一条为1）
     */
    private Integer isActive;

    /**
     * 是否默认平台
     */
    private Integer isDefault;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 状态：0-禁用 1-启用
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
