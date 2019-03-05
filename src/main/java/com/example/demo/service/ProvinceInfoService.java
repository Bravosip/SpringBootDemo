package com.example.demo.service;

import com.example.demo.dto.ProvinceInfo;

import java.util.List;

public interface ProvinceInfoService {

    public List<ProvinceInfo> getProvinceInfoList();

    public ProvinceInfo getProvinceInfoById(Integer id);
}
