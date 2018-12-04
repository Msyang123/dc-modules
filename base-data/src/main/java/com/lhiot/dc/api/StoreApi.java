package com.lhiot.dc.api;

import com.leon.microx.util.Calculator;
import com.leon.microx.util.Position;
import com.leon.microx.web.result.Pages;
import com.leon.microx.web.result.Tips;
import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.dictionary.DictionaryClient;
import com.lhiot.dc.entity.Store;
import com.lhiot.dc.entity.type.CoordinateType;
import com.lhiot.dc.model.StoreSearchParam;
import com.lhiot.dc.mapper.StoreMapper;
import com.lhiot.dc.service.StoreService;
import com.lhiot.dc.util.DictionaryCodes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author zhangfeng created in 2018/9/22 8:57
 **/
@RestController
@Slf4j
@Api("门店接口")
public class StoreApi {

    private StoreService storeService;
    private StoreMapper storeMapper;

    public StoreApi(StoreService storeService, StoreMapper storeMapper) {
        this.storeService = storeService;
        this.storeMapper = storeMapper;
    }

    @ApiOperation(value = "根据id查询门店", notes = "根据id查询门店")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "主键id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = ApiParamType.QUERY, name = "applicationType", value = "应用类型", dataType = "String")
    })
    @GetMapping("/stores/{id}")
    public ResponseEntity findStore(@PathVariable("id") Long id, @RequestParam("applicationType") String applicationType) {

        Store store = storeMapper.selectById(id);
        if (Objects.isNull(store) || store.getApplicationType().contains(applicationType)) {
            return ResponseEntity.badRequest().body("门店不存在");
        }
        return ResponseEntity.ok(store);
    }

    @GetMapping("/stores/code/{code}")
    @ApiOperation(value = "根据门店编码查询门店信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "code", value = "门店编码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = ApiParamType.QUERY, name = "applicationType", value = "应用类型", dataType = "String")
    })
    public ResponseEntity findStoreByCode(@PathVariable("code") String code,@RequestParam("applicationType") String applicationType) {
        log.debug("根据门店编码查询门店信息");
        Store store = storeMapper.selectByCode(code);
        if (Objects.isNull(store) || !store.getApplicationType().contains(applicationType)) {
            return ResponseEntity.badRequest().body("门店不存在");
        }
        return ResponseEntity.ok(store);
    }


    @PostMapping("/stores")
    @ApiOperation(value = "添加门店")
    @ApiImplicitParam(paramType = ApiParamType.BODY, name = "store", value = "要添加的门店", required = true, dataType = "Store")
    public ResponseEntity create(@RequestBody Store store) {
        Position.GCJ02 amap;
        switch (store.getCoordinateType()) {
            case BD09:
                Position.BD09 bd09 = Position.baidu(store.getLongitude().doubleValue(), store.getLatitude().doubleValue());
                amap = Position.GCJ02.of(bd09);
                store.setLatitude(BigDecimal.valueOf(amap.getLatitude()));
                store.setLongitude(BigDecimal.valueOf(amap.getLongitude()));
                break;
            case WGS84:
                Position.WGS84 wgs84 = Position.gps(store.getLongitude().doubleValue(), store.getLatitude().doubleValue());
                amap = Position.GCJ02.of(wgs84);
                store.setLatitude(BigDecimal.valueOf(amap.getLatitude()));
                store.setLongitude(BigDecimal.valueOf(amap.getLongitude()));
                break;
            default:
                break;
        }
        log.debug("添加门店\t param:{}", store);
        return ResponseEntity.ok(storeMapper.insert(store));
    }

    @PutMapping("/stores/{id}")
    @ApiOperation(value = "根据id更新门店")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "要更新的门店id", required = true, dataType = "Long")
    @ApiHideBodyProperty("id")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Store store) {
        log.debug("根据id更新门店\t param:{}", store);
        store.setId(id);
        return ResponseEntity.ok(storeMapper.update(store));
    }

    @DeleteMapping("/stores/{id}")
    @ApiOperation(value = "根据id删除门店")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "要删除门店的id", required = true, dataType = "Long")
    public ResponseEntity<Integer> deleteById(@PathVariable("id") Long id) {
        log.debug("根据ids删除门店\t param:{}", id);
        return ResponseEntity.ok(storeMapper.deleteById(id));
    }

    @PostMapping("/stores/search")
    @ApiOperation("根据位置查询门店所有列表根据距离排序")
    @ApiImplicitParam(paramType = ApiParamType.BODY, name = "param", value = "查询条件", dataType = "StoreSearchParam", required = true)
    public ResponseEntity search(@RequestBody StoreSearchParam param) {
        log.debug("根据位置查询门店列表\t param:{}", param);
        List<Store> storeList = storeService.findList(param);
        Pages<Store> pages = storeService.filtrate(storeList, param);
        return ResponseEntity.ok(pages);
    }
}
