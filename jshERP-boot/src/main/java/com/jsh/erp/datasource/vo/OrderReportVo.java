package com.jsh.erp.datasource.vo;

import com.jsh.erp.datasource.entities.DepotHead;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderReportVo extends DepotHead {
    private String ahStatus;
    private String organName;
    private BigDecimal ahTotal;
    private String designer;
}
