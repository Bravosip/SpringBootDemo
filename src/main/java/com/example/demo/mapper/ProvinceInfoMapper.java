package com.example.demo.mapper;

import com.example.demo.dto.ProvinceInfo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProvinceInfoMapper {

    @Results(id="provinceInfo",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "provinceNo",column = "province_no"),
            @Result(property = "provinceName",column = "province_name"),
            @Result(property = "createDate",column = "create_date")
    })
    @Select("SELECT * FROM PROVINCE_INFO WHERE id = #{id}")
    public ProvinceInfo getProvinceInfoById(Integer id);

    @ResultMap(value = "provinceInfo")
    @Select("SELECT * FROM PROVINCE_INFO")
    public List<ProvinceInfo> getProvinceInfoList();

}
