package com.lhiot.dc.data.api;

import com.google.common.collect.Lists;
import com.leon.microx.support.result.Multiple;
import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.ProductStandard;
import com.lhiot.dc.data.domain.Store;
import com.lhiot.dc.data.domain.out.SimpleProductStandard;
import com.lhiot.dc.data.service.ProducStandardService;
import com.lhiot.dc.data.service.ProductSaleService;
import com.lhiot.dc.data.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
* Description:商品规格接口类
* @author Limiaojun
* @date 2018/07/20
*/
@Api(description = "商品规格接口")
@Slf4j
@RestController
@RequestMapping("/products-standards")
public class ProducStandardApi {

    private final ProducStandardService producstandardService;
    private final ProductSaleService productSaleService;
    private final StoreService storeService;
    @Autowired
    public ProducStandardApi(ProducStandardService producstandardService,ProductSaleService productSaleService,StoreService storeService) {
        this.producstandardService = producstandardService;
        this.productSaleService=productSaleService;
        this.storeService=storeService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "添加商品规格")
    @ApiImplicitParam(paramType = "body", name = "producstandard", value = "要添加的商品规格", required = true, dataType = "Producstandard")
    public ResponseEntity<Integer> create(@RequestBody ProductStandard producstandard) {
        log.debug("添加商品规格\t param:{}",producstandard);
        
        return ResponseEntity.ok(producstandardService.create(producstandard));
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "根据id更新商品规格")
    @ApiImplicitParam(paramType = "body", name = "producstandard", value = "要更新的商品规格", required = true, dataType = "Producstandard")
    public ResponseEntity<Integer> update(@PathVariable("id") Long id, @RequestBody ProductStandard producstandard) {
        log.debug("根据id更新商品规格\t id:{} param:{}",id,producstandard);
        producstandard.setId(id);
        
        return ResponseEntity.ok(producstandardService.updateById(producstandard));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "根据ids删除商品规格")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "要删除商品规格的ids,逗号分割", required = true, dataType = "String")
    public ResponseEntity<Integer> deleteByIds(@PathVariable("ids") String ids) {
        log.debug("根据ids删除商品规格\t param:{}",ids);
        
        return ResponseEntity.ok(producstandardService.deleteByIds(ids));
    }
    
    @ApiOperation(value = "根据id查询商品规格", notes = "根据id查询商品规格")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键id", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public ResponseEntity<ProductStandard> findProducstandard(@PathVariable("id") Long id) {
        return ResponseEntity.ok(producstandardService.selectById(id));
    }
    
    @GetMapping("/page/query")
    @ApiOperation(value = "查询商品规格分页列表")
    public ResponseEntity<PagerResultObject<ProductStandard>> pageQuery(ProductStandard producstandard){
        log.debug("查询商品规格分页列表\t param:{}",producstandard);
        
        return ResponseEntity.ok(producstandardService.pageList(producstandard));
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "根据规格ids,或者barcode查找", notes = "根据规格ids,或者barcode查找")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "key", value = "ids或者barcode", required = true,
                    dataType = "String"),
            @ApiImplicitParam(paramType = "path", name = "value", value = "ids或者barcode的值", required = true,
                    dataType = "String") })
    @GetMapping("/find/{key}/{value}")
    public ResponseEntity<Multiple> findProductsStandardResults(@PathVariable String key,
                                                                   @PathVariable String value) {
        log.debug("根据规格ids,或者barcode查找\t param:{} {}", key,value);

        return ResponseEntity.ok(Multiple.of(producstandardService.findGoodsStandards(key, value)));
    }
    @GetMapping("/detail")
    @ApiOperation(value = "根据规格id查询商品规格-前端使用", notes = "根据规格id查询商品规格-前端使用")
    public ResponseEntity<?> findProducstandard(Long storeId, Long productStandId, String applyType) {
        ProductStandard productStandard = producstandardService.selectById(productStandId);
        if(null ==productStandard){
            return  ResponseEntity.badRequest().body("商品规格不存在");
        }
        List<ProductStandard> list = new ArrayList<ProductStandard>();
        list.add(productStandard);
        //Store store = storeService.selectById(storeId);
        //producstandardService.productInv(store.getStoreCode(),list);
        productSaleService.setSaleCount(list,applyType);
        return ResponseEntity.ok(productStandard);
    }
    @GetMapping("/batchDetail")
    @ApiOperation(value = "根据规格id查询商品规格-前端使用", notes = "根据规格id查询商品规格-前端使用")
    public ResponseEntity<?> findProducstandardBatch(Long storeId, String productStandIds) {
        List<ProductStandard> productStandardList = producstandardService.findGoodsStandards("ids",productStandIds);
        if(null ==productStandardList){
            return  ResponseEntity.badRequest().body("商品规格不存在");
        }
        //Store store = storeService.selectById(storeId);
        //producstandardService.productInv(store.getStoreCode(),productStandardList);
        List<SimpleProductStandard> result = Lists.newArrayList();
        for(ProductStandard productStandard:productStandardList){
            SimpleProductStandard simpleProductStandard = new SimpleProductStandard();
            com.leon.microx.util.BeanUtils.of(simpleProductStandard).populate(productStandard);
            result.add(simpleProductStandard);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    @ApiOperation(value = "商品搜索-前端使用", notes = "商品搜索-前端使用")
    public ResponseEntity<?> queryProductStand(String productName, Long storeId, String applyType) {
        ProductStandard productStandard = new ProductStandard();
        productStandard.setProductName(productName);
        productStandard.setRows(20L);
        PagerResultObject<ProductStandard> list= producstandardService.pageList(productStandard);
        Store store = storeService.selectById(storeId);
        //producstandardService.productInv(store.getStoreCode(),list.getRows());
        //productSaleService.setSaleCount(list.getRows(),applyType);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/order/sale-count")
    @ApiOperation(value = "统计销量-每小时一次")
    public ResponseEntity<?> saleCount(){
        try {
            productSaleService.recordSaleCount();
        }catch (Exception e){
            log.info("统计销量错误");
        }
        return ResponseEntity.ok("统计成功");
    }

    @PostMapping("/updatebatch")
    @ApiOperation(value = "批量更新")
    public ResponseEntity<?> updateBatch(@RequestBody List<ProductStandard> vo){
        log.debug("wxsamll-shop限时抢购活动批量更新规格活动价格");

        return ResponseEntity.ok(this.producstandardService.updateBatch(vo));
    }
}
