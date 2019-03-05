package com.example.demo.controller;

import com.example.demo.dto.JsonResult;
import com.example.demo.dto.ProvinceInfo;
import com.example.demo.service.ProvinceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProvinceInfoController {
    @Autowired
    private ProvinceInfoService provinceInfoService;

    @RequestMapping(value = "infoList", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getProvinceInfoList(){
        JsonResult r = new JsonResult();
        List<ProvinceInfo> provinceInfoList = provinceInfoService.getProvinceInfoList();
        r.setResult(provinceInfoList);
        r.setStatus("OK");
        return ResponseEntity.ok(r);
    }
}
