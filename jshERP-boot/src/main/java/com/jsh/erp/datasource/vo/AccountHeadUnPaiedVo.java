package com.jsh.erp.datasource.vo;

import java.math.BigDecimal;

public class AccountHeadUnPaiedVo {
    private String organ;
    private BigDecimal total;

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
}
