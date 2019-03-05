package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProvinceInfo {
    private Integer id;
    private String provinceNo;
    private String provinceName;
    private Date createDate;
}
