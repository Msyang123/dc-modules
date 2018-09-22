package com.lhiot.dc.base.api;

import com.lhiot.dc.base.model.Store;
import com.lhiot.dc.base.model.type.ApplicationType;
import com.lhiot.dc.base.service.StoreService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zhangfeng created in 2018/9/22 8:57
 **/
@RestController
@Slf4j
@RequestMapping("/stores")
public class StoreApi {

    private StoreService storeService;

    public StoreApi(StoreService storeService) {
        this.storeService = storeService;
    }

    @ApiOperation(value = "根据id查询门店", notes = "根据id查询门店")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "主键id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "applicationType", value = "应用类型", required = false, dataType = "ApplicationType")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Store> findStore(@PathVariable("id") Long id, @RequestParam("applicationType") ApplicationType applicationType) {
        return ResponseEntity.ok(storeService.findById(id,applicationType));
    }

    @GetMapping("/by-code/{code}")
    @ApiOperation(value = "根据门店编码查询门店信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "code", value = "门店编码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "applicationType", value = "应用类型", required = false, dataType = "ApplicationType")
    })
    public ResponseEntity<Store> findStoreByCode(@PathVariable("code") String code,@RequestParam("applicationType") ApplicationType applicationType){
        log.debug("根据门店编码查询门店信息");
        return ResponseEntity.ok(storeService.findByCode(code,applicationType));
    }
}
