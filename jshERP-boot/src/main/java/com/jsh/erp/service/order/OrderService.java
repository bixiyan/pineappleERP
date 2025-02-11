package com.jsh.erp.service.order;

import com.jsh.erp.datasource.mappers.AccountHeadMapperEx;
import com.jsh.erp.datasource.mappers.DepotHeadMapper;
import com.jsh.erp.datasource.mappers.DepotHeadMapperEx;
import com.jsh.erp.datasource.vo.OrderReportVo;
import com.jsh.erp.datasource.vo.OrderReportVo2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Resource
    private DepotHeadMapper depotHeadMapper;
    @Resource
    private DepotHeadMapperEx depotHeadMapperEx;
    @Resource
    private AccountHeadMapperEx accountHeadMapperEx;

    public List<OrderReportVo2> select(String beginTime, String endTime, String designer, Long organId, Long status){
        List<OrderReportVo2> result = new ArrayList<>();
        String dhStatus = null;
        Map<Long,OrderReportVo2> calMap = new HashMap<>();
        if(status != null) {
            // 未施工,施工中
            if (status == 0 || status == 1) {
                dhStatus = "0";
            }
            // 已施工
            if (status == 2) {
                dhStatus = "1";
            }
        }
        List<OrderReportVo> data = depotHeadMapperEx.orderReport(dhStatus,beginTime,endTime,designer,organId);
        for(OrderReportVo vo : data){
            OrderReportVo2 vo2 =  calMap.get(vo.getOrganId());
            if (vo2 == null) {
                vo2 = new OrderReportVo2(vo);
                calMap.put(vo.getOrganId(),vo2);
            }
            else {
                vo2.add(vo);
            }
        }
        for (OrderReportVo2 value : calMap.values()){
            if(status == null || value.executionStatus().equals(status)){
                result.add(value);
            }
        }
        return result;
    }
}
