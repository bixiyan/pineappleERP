package com.jsh.erp.datasource.vo;

import com.jsh.erp.datasource.entities.DepotHead;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

public class OrderReportVo2 extends DepotHead {
    private String ahStatus;
    private String organName;
    private BigDecimal paied = new BigDecimal(0);
    private BigDecimal unPaied = new BigDecimal(0);
    private String designer;

    public OrderReportVo2(){

    }

    public OrderReportVo2(OrderReportVo vo){
        this.setOrganId(vo.getOrganId());
        this.setOrganName(vo.getOrganName());
        this.setDesigner(vo.getDesigner());
        this.setTotalPrice(vo.getTotalPrice());
    }

    public void add(OrderReportVo vo){
        if(vo.getAhStatus() == null || "0".equals(vo.getAhStatus())){
            this.unPaied.add(vo.getAhTotal());
        }
        else{
            this.paied.add(vo.getAhTotal());
        }
    }

    public Long executionStatus(){
        if ("1".equals(this.getStatus()))
            return 2L;
        else {
            if (paied.compareTo(BigDecimal.valueOf(0L)) == 1){
                return 1L;
            }
            else
                return 0L;
        }
    }


    public String getAhStatus() {
        return ahStatus;
    }

    public void setAhStatus(final String ahStatus) {
        this.ahStatus = ahStatus;
    }

    public BigDecimal getPaied() {
        return paied;
    }

    public void setPaied(final BigDecimal paied) {
        this.paied = paied;
    }

    public BigDecimal getUnPaied() {
        return unPaied;
    }

    public void setUnPaied(final BigDecimal unPaied) {
        this.unPaied = unPaied;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(final String designer) {
        this.designer = designer;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(final String organName) {
        this.organName = organName;
    }
}


