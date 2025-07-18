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
        this.setStatus(vo.getStatus());
        this.setCreateTime(vo.getCreateTime());
    }

    public void addTotal(OrderReportVo vo){
        BigDecimal total = vo.getTotalPrice();
        if (total == null)
            total = new BigDecimal(0);
        if (this.getTotalPrice() != null)
            this.setTotalPrice(this.getTotalPrice().add(total));
        else
            this.setTotalPrice(total);
    }

    public void setPaiedUnPaid(BigDecimal paied,BigDecimal unPaied){
        System.out.println("orgid======="+ this.getOrganId());
        System.out.println(paied);
        System.out.println(unPaied);
        this.paied = paied;
        this.unPaied = unPaied;
    }

    public Integer executionStatus(){
        if ("1".equals(this.getStatus()))
            return 2;
        else {
            if (paied.compareTo(BigDecimal.valueOf(0L)) == 1){
                return 1;
            }
            else
                return 0;
        }
    }

    public void resetStatus() {
        Integer executionStatus = this.executionStatus();
        if(executionStatus==0)
            this.setStatus("未施工");
        if(executionStatus==1)
            this.setStatus("施工中");
        if(executionStatus==2)
            this.setStatus("已施工");
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


