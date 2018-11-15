package com.lhiot.dc.domain;

import com.lhiot.dc.domain.type.ApplicationType;
import com.lhiot.dc.domain.type.ShelfStatus;
import com.lhiot.dc.domain.type.ShelfType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangfeng create in 15:38 2018/11/8
 */
@Data
public class ProductShelf {
    private Long id;
    private String name;
    private Long specificationId;
    private BigDecimal shelfQty;
    private Integer price;
    private Integer originalPrice;
    private String image;
    private String productImage;
    private ShelfStatus shelfStatus;
    private ShelfType shelfType;
    private Date createAt;
    private String description;
    private Integer sorting;
    private ApplicationType applicationType;

}
