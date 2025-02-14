package com.jsh.erp.datasource.vo;

import com.jsh.erp.datasource.entities.Supplier;

public class SupplierVo extends Supplier {
    private Long organId;

    public Long getOrganId() {
        return organId;
    }

    public void setOrganId(final Long organId) {
        this.organId = organId;
    }
}
