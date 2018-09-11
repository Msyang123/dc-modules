package com.lhiot.dc.data.api;

import com.leon.microx.support.result.Multiple;
import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.Assortment;
import com.lhiot.dc.data.service.AssortmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
* Description:商品套餐接口类
* @author yijun
* @date 2018/07/24
*/
@Api(description = "商品套餐接口")
@Slf4j
@RestController
@RequestMapping("/assortment")
public class AssortmentApi {

    private final AssortmentService assortmentService;

    @Autowired
    public AssortmentApi(AssortmentService assortmentService) {
        this.assortmentService = assortmentService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "添加商品套餐")
    @ApiImplicitParam(paramType = "body", name = "assortment", value = "要添加的商品套餐", required = true, dataType = "Assortment")
    public ResponseEntity<Assortment> create(@RequestBody Assortment assortment) {
        log.debug("添加商品套餐\t param:{}",assortment);
        
        return ResponseEntity.ok(assortmentService.create(assortment));
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "根据id更新商品套餐")
    @ApiImplicitParam(paramType = "body", name = "assortment", value = "要更新的商品套餐", required = true, dataType = "Assortment")
    public ResponseEntity<Integer> update(@PathVariable("id") Long id, @RequestBody Assortment assortment) {
        log.debug("根据id更新商品套餐\t id:{} param:{}",id,assortment);
        assortment.setId(id);
        
        return ResponseEntity.ok(assortmentService.updateById(assortment));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "根据ids删除商品套餐")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "要删除商品套餐的ids,逗号分割", required = true, dataType = "String")
    public ResponseEntity<Integer> deleteByIds(@PathVariable("ids") String ids) {
        log.debug("根据ids删除商品套餐\t param:{}",ids);
        
        return ResponseEntity.ok(assortmentService.deleteByIds(ids));
    }
    
    @ApiOperation(value = "根据id查询商品套餐", notes = "根据id查询商品套餐")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "主键id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "flag", value = "是否查询套餐商品", dataType = "String")
         }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Assortment> findAssortment(@PathVariable(required=true,value="id") @NotNull Long id,
                                                     @RequestParam(required=false,value="flag",defaultValue="no") String flag) {
        return ResponseEntity.ok(assortmentService.selectById(id,flag));
    }

    @ApiOperation(value = "根据关键字查询商品套餐", notes = "根据关键字查询商品套餐")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "keywords", value = "关键词", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "page", value = "页数", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "rows", value = "每页展示数量", required = true, dataType = "Integer")
      }
    )
    @GetMapping("/search")
    public ResponseEntity findAssortmentByKeywords(@RequestParam("keywords") String keywords,
                                                   @RequestParam(value="page",defaultValue="1") int page,
                                                   @RequestParam(value="rows",defaultValue="6") int rows) {
        return ResponseEntity.ok(Multiple.of(assortmentService.findAssortmentByKeywords(keywords,page,rows)));
    }

    
    @GetMapping("/page/query")
    @ApiOperation(value = "查询商品套餐分页列表")
    public ResponseEntity<PagerResultObject<Assortment>> pageQuery(Assortment assortment){
        log.debug("查询商品套餐分页列表\t param:{}",assortment);
        
        return ResponseEntity.ok(assortmentService.pageList(assortment));
    }

    @SuppressWarnings("unchecked")
	@GetMapping("/list/{ids}")
    @ApiOperation(value = "根据ids查找套餐或者套餐及商品列表,供订单服务feign调用")
    public ResponseEntity<Multiple<Assortment>> findAssortments(@PathVariable(required=true,value="ids") @NotNull String ids,
                                                                   @RequestParam(required=false,value="flag",defaultValue="no") String flag) {
        log.debug("根据ids查找套餐或者套餐及商品列表", ids);
        return ResponseEntity.ok(Multiple.of(assortmentService.findAssortments(ids, flag)));
    }

    @GetMapping("/page/assortments/{ids}")
    @ApiOperation(value = "查询商品套餐分页列表")
    public ResponseEntity<PagerResultObject<Assortment>> pageAssortments(@PathVariable(required=true,value="ids") @NotNull String ids){

        return ResponseEntity.ok(assortmentService.pageAssortmentList(ids));
    }



}
