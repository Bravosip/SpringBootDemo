package com.example.demo.log.dto;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

public class MDCData {
    /**
     * 流程ID
     */
    private String flowId;
    /**
     * 常量集合
     */
    private Map<String ,String> datas;

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Map<String, String> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, String> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        if (flowId == null && (CollectionUtils.isEmpty(datas))) {
            return null;
        }
        StringBuffer sb = new StringBuffer("{");
        if (StringUtils.hasText(flowId)){
            sb.append("F=").append(flowId);
        }
        sb.append("}");
        return sb.toString();
    }
}
