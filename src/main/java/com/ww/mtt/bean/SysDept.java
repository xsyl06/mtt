package com.ww.mtt.bean;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysDept {
    private Integer id;

    private String name;

    private Integer parentId;

    private String level;

    private Integer seq;

    private String remark;

    private String operator;

    private Date operatorTime;

    private String operatorIp;

    private Date crtTime;
}