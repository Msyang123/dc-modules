package com.lhiot.dc.base.api;

import com.leon.microx.support.result.Multiple;
import com.lhiot.dc.base.common.LocationParam;
import com.lhiot.dc.base.common.PagerResultObject;
import com.lhiot.dc.base.common.util.CommonUtils;
import com.lhiot.dc.base.model.Store;
import com.lhiot.dc.base.model.type.ApplicationType;
import com.lhiot.dc.base.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zhangfeng created in 2018/9/22 8:57
 **/
@RestController
@Slf4j
@RequestMapping("/stores")
@Api(description = "门店接口")
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
    public ResponseEntity<Store> findStore(@PathVariable("id") Long id, @RequestParam(value = "applicationType",required = false) ApplicationType applicationType) {
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


    @PostMapping("/page/query")
    @ApiOperation(value = "查询门店分页列表")
    public ResponseEntity<PagerResultObject<Store>> pageQuery(Store store){
        log.debug("查询门店分页列表\t param:{}",store);

        return ResponseEntity.ok(storeService.pageList(store));
    }

    @GetMapping("position")
    @ApiOperation(value = "根据位置查询门店所有列表")
    public ResponseEntity<Multiple> findPosition(LocationParam param, ApplicationType applicationType){
        log.debug("根据位置查询门店列表\t param:{}",param);

        return ResponseEntity.ok(Multiple.of(storeService.findStoresSortByDistance(param,applicationType)));
    }

    @GetMapping("position/lately")
    @ApiOperation(value = "根据位置返回最近的门店")
    public ResponseEntity<Store> findPositionLately(LocationParam param, ApplicationType applicationType){
        log.debug("根据位置返回最近的门店\t param:{}",param);
        List<Store> resultList = storeService.findStoresSortByDistance(param,applicationType);

        return ResponseEntity.ok((Store) CommonUtils.getCollectionsFirst(resultList));
    }

    @ApiOperation(value = "根据用户位置查询附近(200km内)门店", notes = "定标位置查询附近(200km内)门店列表")
    @GetMapping(value = "/nearstores")
    public ResponseEntity<Multiple> findNearbyStores(LocationParam param,ApplicationType applicationType){
        log.debug("根据用户位置查询附近(200km内)门店\t param:{}",param);
        List<Store> list = storeService.findUserNearbyStores(param.getLat(), param.getLng(), 200, 22,applicationType);
        return ResponseEntity.ok(Multiple.of(list));
    }

    @ApiOperation(value = "根据ids获取门店信息，传入位置信息按位置信息排序(由近到远)",response=Store.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "storeIds", value = "门店Id集合", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "lat", value = "坐标位置（纬度）", required = false, dataType = "Double"),
            @ApiImplicitParam(paramType = "query", name = "lng", value = "坐标位置（经度）", required = false, dataType = "Double"),

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
