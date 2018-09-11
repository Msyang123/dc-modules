package com.lhiot.dc.data.api;

import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.StorePosition;
import com.lhiot.dc.data.service.StorePositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
* Description:门店位置接口类
* @author yijun
* @date 2018/09/11
*/
@Api(description = "门店位置接口")
@Slf4j
@RestController
@RequestMapping("/storePosition")
public class StorePositionApi {

    private final StorePositionService storePositionService;

    @Autowired
    public StorePositionApi(StorePositionService storePositionService) {
        this.storePositionService = storePositionService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "添加门店位置")
    @ApiImplicitParam(paramType = "body", name = "storePosition", value = "要添加的门店位置", required = true, dataType = "StorePosition")
    public ResponseEntity<Integer> create(@RequestBody StorePosition storePosition) {
        log.debug("添加门店位置\t param:{}",storePosition);
        
        return ResponseEntity.ok(storePositionService.create(storePosition));
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "根据id更新门店位置")
    @ApiImplicitParam(paramType = "body", name = "storePosition", value = "要更新的门店位置", required = true, dataType = "StorePosition")
    public ResponseEntity<Integer> update(@PathVariable("id") Long id,@RequestBody StorePosition storePosition) {
        log.debug("根据id更新门店位置\t id:{} param:{}",id,storePosition);
        storePosition.setId(id);
        
        return ResponseEntity.ok(storePositionService.updateById(storePosition));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "根据ids删除门店位置")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "要删除门店位置的ids,逗号分割", required = true, dataType = "String")
    public ResponseEntity<Integer> deleteByIds(@PathVariable("ids") String ids) {
        log.debug("根据ids删除门店位置\t param:{}",ids);
        
        return ResponseEntity.ok(storePositionService.deleteByIds(ids));
    }
    
    @ApiOperation(value = "根据id查询门店位置", notes = "根据id查询门店位置")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键id", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public ResponseEntity<StorePosition> findStorePosition(@PathVariable("id") Long id) {

        return ResponseEntity.ok(storePositionService.selectById(id));
    }
    
    @GetMapping("/page/query")
    @ApiOperation(value = "查询门店位置分页列表")
    public ResponseEntity<PagerResultObject<StorePosition>> pageQuery(StorePosition storePosition){
        log.debug("查询门店位置分页列表\t param:{}",storePosition);
        
        return ResponseEntity.ok(storePositionService.pageList(storePosition));
    }
    
}
