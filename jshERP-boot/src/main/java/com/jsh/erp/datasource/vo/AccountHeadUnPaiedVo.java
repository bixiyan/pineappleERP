package com.jsh.erp.datasource.vo;

import java.math.BigDecimal;

public class AccountHeadUnPaiedVo {
    private String organ;
    private BigDecimal total;

    private String billTime;

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(final String organ) {
        this.organ = organ;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(final BigDecimal total) {
        this.total = total;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(final String billTime) {
        this.billTime = billTime;
    }
}
