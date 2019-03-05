package com.example.demo.service.impl;

import com.example.demo.dto.ProvinceInfo;
import com.example.demo.mapper.ProvinceInfoMapper;
import com.example.demo.service.ProvinceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceInfoServiceImpl implements ProvinceInfoService {

    @Autowired
    private ProvinceInfoMapper provinceInfoMapper;

    @Override
    public List<ProvinceInfo> getProvinceInfoList() {
        return provinceInfoMapper.getProvinceInfoList();
    }

    @Override
    public ProvinceInfo getProvinceInfoById(Integer id) {
        return provinceInfoMapper.getProvinceInfoById(id);
    }
}
