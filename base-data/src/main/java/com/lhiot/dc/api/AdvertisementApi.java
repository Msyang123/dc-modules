package com.lhiot.dc.api;

import com.leon.microx.util.Maps;
import com.leon.microx.web.result.Pages;
import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.entity.Advertisement;
import com.lhiot.dc.model.AdvertisementParam;
import com.lhiot.dc.service.AdvertisementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * @author xiaojian  created in  2018/11/21 10:00
 */
@RestController
@Slf4j
@Api(description = "广告接口")
public class AdvertisementApi {

    private AdvertisementService advertisementService;

    public AdvertisementApi(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @ApiOperation("添加广告")
    @ApiImplicitParam(paramType = ApiParamType.BODY, name = "advertisement", value = "广告信息", dataType = "Advertisement", required = true)
    @PostMapping("/advertisements")
    public ResponseEntity create(@RequestBody Advertisement advertisement) {
        Long id = advertisementService.addAdvertisement(advertisement);
        return id > 0 ? ResponseEntity.created(URI.create("/advertisements/" + id)).body(Maps.of("id", id)) : ResponseEntity.badRequest().body("添加广告失败！");
    }


    @ApiOperation("修改广告")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "广告Id", dataType = "Long", required = true),
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "advertisement", value = "广告信息", dataType = "Advertisement", required = true)
    })
    @PutMapping("/advertisements/{id}")
    @ApiHideBodyProperty("id")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Advertisement advertisement) {
        advertisement.setId(id);
        return advertisementService.update(advertisement) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("修改信息失败！");
    }


    @ApiOperation(value = "根据Id查找广告", response = Advertisement.class)
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "广告Id", dataType = "Long", required = true)
    @GetMapping("/advertisements/{id}")
    public ResponseEntity single(@PathVariable("id") Long id) {
        Advertisement advertisement = advertisementService.findById(id);
        return ResponseEntity.ok().body(advertisement);
    }


    @ApiOperation("根据Id删除广告")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "ids", value = "多个广告Id以英文逗号分隔", dataType = "String", required = true)
    @DeleteMapping("/advertisements/{ids}")
    public ResponseEntity batchDelete(@PathVariable("ids") String ids) {
        return advertisementService.batchDeleteByIds(ids) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败！");
    }


    @ApiOperation(value = "根据条件分页查询广告信息列表", response = Advertisement.class, responseContainer = "Set")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "param", value = "查询条件", dataType = "AdvertisementParam")
    })
    @PostMapping("/advertisements/pages")
    public ResponseEntity search(@RequestBody AdvertisementParam param) {
        log.debug("查询广告信息列表\t param:{}", param);
        Pages<Advertisement> pages = advertisementService.findList(param);
        return ResponseEntity.ok(pages);
    }


}
