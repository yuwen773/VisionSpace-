package com.yuwen.visionspace.model.vo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class StorageConfigVO implements Serializable {

    private Long id;

    private String platform;

    private String platformName;

    private String endpoint;

    private String region;

    private String bucket;

    /**
     * AccessKey，脱敏后返回
     */
    private String accessKey;

    /**
     * SecretKey，脱敏后返回
     */
    private String secretKey;

    private String domain;

    private String basePath;

    private Integer isActive;

    private Integer isDefault;

    private Integer orderNum;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
