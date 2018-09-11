package com.lhiot.dc.data.api;

import com.leon.microx.support.result.Multiple;
import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.common.util.CommonUtils;
import com.lhiot.dc.data.domain.LocationParam;
import com.lhiot.dc.data.domain.Store;
import com.lhiot.dc.data.domain.enums.ApplicationTypeEnum;
import com.lhiot.dc.data.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Description:门店接口类
* @author Limiaojun
* @date 2018/06/02
*/
@Api(description = "门店接口")
@Slf4j
@RestController
@RequestMapping("/stores")
@SuppressWarnings("rawtypes")
public class StoreApi {

    private final StoreService storeService;

    @Autowired
    public StoreApi(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    @ApiOperation(value = "添加门店")
    @ApiImplicitParam(paramType = "body", name = "store", value = "要添加的门店", required = true, dataType = "Store")
    public ResponseEntity<Integer> create(@RequestBody Store store) {
        log.debug("添加门店\t param:{}",store);

        return ResponseEntity.ok(storeService.create(store));
    }

    @PutMapping
    @ApiOperation(value = "根据id更新门店")
    @ApiImplicitParam(paramType = "body", name = "store", value = "要更新的门店", required = true, dataType = "Store")
    public ResponseEntity<Integer> update(@RequestBody Store store) {
        log.debug("根据id更新门店\t param:{}",store);

        return ResponseEntity.ok(storeService.updateById(store));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "根据ids删除门店")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "要删除门店的ids,逗号分割", required = true, dataType = "String")
    public ResponseEntity<Integer> deleteByIds(@PathVariable("ids") String ids) {
        log.debug("根据ids删除门店\t param:{}",ids);

        return ResponseEntity.ok(storeService.deleteByIds(ids));
    }

    @ApiOperation(value = "根据id查询门店", notes = "根据id查询门店")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "主键id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = "path", name = "applicationType", value = "应用类型", required = true, dataType = "ApplicationTypeEnum")
    })
    @GetMapping("/{id}/{applicationType}")
    public ResponseEntity<Store> findStore(@PathVariable("id") Long id,@PathVariable("applicationType") ApplicationTypeEnum applicationType) {
        return ResponseEntity.ok(storeService.selectById(id,applicationType));
    }

    @GetMapping("/by-code/{code}")
    @ApiOperation(value = "根据门店编码查询门店信息")
    public ResponseEntity<Store> findAllStores(@PathVariable("code") String code){
        log.debug("后台管理-获取所有门店信息");
        return ResponseEntity.ok(storeService.findByCode(code));
    }
    
    @PostMapping("/page/query")
    @ApiOperation(value = "查询门店分页列表")
    public ResponseEntity<PagerResultObject<Store>> pageQuery(Store store){
        log.debug("查询门店分页列表\t param:{}",store);

        return ResponseEntity.ok(storeService.pageList(store));
    }

    @GetMapping("position")
    @ApiOperation(value = "根据位置查询门店所有列表")
    public ResponseEntity<Multiple> findPosition(LocationParam param,ApplicationTypeEnum applicationType){
        log.debug("根据位置查询门店列表\t param:{}",param);

        return ResponseEntity.ok(Multiple.of(storeService.findStoresSortByDistance(param,applicationType)));
    }

    @GetMapping("position/lately")
    @ApiOperation(value = "根据位置返回最近的门店")
    public ResponseEntity<Store> findPositionLately(LocationParam param, ApplicationTypeEnum applicationTypeEnum){
        log.debug("根据位置返回最近的门店\t param:{}",param);
        List<Store> resultList = storeService.findStoresSortByDistance(param,applicationTypeEnum);

        return ResponseEntity.ok((Store)CommonUtils.getCollectionsFirst(resultList));
    }

    @ApiOperation(value = "根据用户位置查询附近(200km内)门店", notes = "定标位置查询附近(200km内)门店列表")
    @GetMapping(value = "/nearstores")
    public ResponseEntity<Multiple> findNearbyStores(LocationParam param,ApplicationTypeEnum applicationType){
        log.debug("根据用户位置查询附近(200km内)门店\t param:{}",param);
        List<Store> list = storeService.findUserNearbyStores(param.getLocationX(), param.getLocationY(), 200, 22,applicationType);
        return ResponseEntity.ok(Multiple.of(list));
    }

    @ApiOperation(value = "根据ids获取门店信息，传入位置信息按位置信息排序(由近到远)",response=Store.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "storeIds", value = "门店Id集合", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "locationX", value = "坐标位置（纬度）", required = false, dataType = "Double"),
            @ApiImplicitParam(paramType = "query", name = "locationY", value = "坐标位置（经度）", required = false, dataType = "Double"),

    })
    @GetMapping("/names")
    public ResponseEntity<Multiple> findStoreNames(@RequestParam String storeIds, LocationParam param){
        return ResponseEntity.ok(Multiple.of(storeService.findStoreList(storeIds,param)));
    }

    @GetMapping("manage/all")
    @ApiOperation(value = "后台管理-获取所有门店信息")
    public ResponseEntity<Multiple> findAllStores(){
        log.debug("后台管理-获取所有门店信息");
        //不查询门店位置信息
        return ResponseEntity.ok(Multiple.of(storeService.findAllStores(null)));
    }

}