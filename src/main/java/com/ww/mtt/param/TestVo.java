package com.ww.mtt.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class TestVo {

    @NotNull
    private Integer a;
    @NotBlank
    private String msg;

}
