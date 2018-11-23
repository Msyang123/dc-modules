package com.lhiot.dc.domain;

import com.lhiot.dc.domain.type.AvailableStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangfeng create in 9:11 2018/11/9
 */
@Data
public class ProductSpecification {
    private Long id;
    private Long productId;
    private String barcode;
    private String packagingUnit;
    private BigDecimal weight;
    private BigDecimal specificationQty;
    private Integer limitInventory;
    private InventorySpecification inventorySpecification;
    private AvailableStatus availableStatus;
    private Date createAt;
}
