package com.ww.mtt.bean;

import lombok.Data;

import java.util.Date;
@Data
public class SysUser {
    private Integer id;

    private String userName;

    private String telephone;

    private String password;

    private String mail;

    private String remark;

    private Integer deptId;

    private Integer status;

    private String operator;

    private Date operatorTime;

    private String operatorIp;

    private Date crtTime;

}