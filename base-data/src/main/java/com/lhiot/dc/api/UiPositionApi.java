package com.lhiot.dc.api;

import com.leon.microx.util.Maps;
import com.leon.microx.web.result.Pages;
import com.leon.microx.web.result.Tips;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.entity.UiPosition;
import com.lhiot.dc.model.UiPositionParam;
import com.lhiot.dc.service.UiPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * @author xiaojian  created in  2018/11/20 15:51
 */
@RestController
@Slf4j
@Api(description = "UI位置接口")
public class UiPositionApi {

    private UiPositionService positionService;

    public UiPositionApi(UiPositionService positionService) {
        this.positionService = positionService;
    }

    @ApiOperation("添加位置")
    @ApiImplicitParam(paramType = ApiParamType.BODY, name = "uiPosition", value = "位置信息", dataType = "UiPosition", required = true)
    @PostMapping("/ui-positions")
    public ResponseEntity create(@RequestBody UiPosition uiPosition) {
        Tips tips = positionService.addUiPosition(uiPosition);
        if (tips.err()) {
            return ResponseEntity.badRequest().body(tips.getMessage());
        }
        Long id = Long.valueOf(tips.getMessage());
        return id > 0 ? ResponseEntity.created(URI.create("/ui-positions/" + id)).body(Maps.of("id", id)) : ResponseEntity.badRequest().body("添加位置失败！");
    }


    @ApiOperation("修改位置")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "位置Id", dataType = "Long", required = true),
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "uiPosition", value = "位置信息", dataType = "UiPosition", required = true)
    })
    @PutMapping("/ui-positions/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody UiPosition uiPosition) {
        uiPosition.setId(id);

        Tips tips = positionService.update(uiPosition);
        if (tips.err()) {
            return ResponseEntity.badRequest().body(tips.getMessage());
        }
        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "根据Id查找位置", response = UiPosition.class)
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "位置Id", dataType = "Long", required = true)
    @GetMapping("/ui-positions/{id}")
    public ResponseEntity single(@PathVariable("id") Long positionId) {
        UiPosition uiPosition = positionService.findById(positionId);
        return ResponseEntity.ok().body(uiPosition);
    }


    @ApiOperation("根据Id删除位置")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "ids", value = "多个位置Id以英文逗号分隔", dataType = "String", required = true)
    @DeleteMapping("/ui-positions/{ids}")
    public ResponseEntity batchDelete(@PathVariable("ids") String ids) {
        return positionService.batchDeleteByIds(ids) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败！");
    }


    @ApiOperation(value = "根据条件分页查询位置信息列表", response = UiPosition.class, responseContainer = "Set")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "param", value = "查询条件", dataType = "UiPositionParam")
    })
    @PostMapping("/ui-positions/pages")
    public ResponseEntity search(@RequestBody UiPositionParam param) {
        log.debug("查询位置信息列表\t param:{}", param);
        Pages<UiPosition> pages = positionService.findList(param);
        return ResponseEntity.ok(pages);
    }


}
