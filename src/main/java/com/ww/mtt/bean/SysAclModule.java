package com.ww.mtt.bean;

import lombok.Data;

import java.util.Date;
@Data
public class SysAclModule {
    private Integer id;

    private String name;

    private Integer parentId;

    private String level;

    private Integer seq;

    private Integer status;

    private String remark;

    private String operator;

    private Date operatorTime;

    private String operatorIp;

    private Date crtTime;
}