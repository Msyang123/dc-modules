package com.lhiot.dc.base.api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangfeng created in 2018/9/20 15:08
 **/
@RestController
@Slf4j
@RequestMapping("/product/specification")
public class ProductSpecificationApi {

    @ApiOperation("根据规格Id查询商品信息")
    @ApiImplicitParam(paramType = "path",name = "id",value = "规格Id",dataType = "Long",required = true)
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id){

        return null;
    }
}