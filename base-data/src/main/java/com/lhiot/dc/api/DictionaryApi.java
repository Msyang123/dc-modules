package com.lhiot.dc.api;

import com.leon.microx.web.result.Pages;
import com.leon.microx.web.result.Tips;
import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.domain.Dictionary;
import com.lhiot.dc.domain.DictionaryEntry;
import com.lhiot.dc.domain.SearchParameter;
import com.lhiot.dc.mapper.DictionaryMapper;
import com.lhiot.dc.service.DictionaryService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * @author Leon (234239150@qq.com) created in 11:22 18.10.15
 */
@Api("数据字典")
@Slf4j
@RestController
@RequestMapping("/dictionaries")
public class DictionaryApi {

    private final DictionaryService service;
    private final DictionaryMapper dictionaryMapper;

    @Autowired
    public DictionaryApi(DictionaryService service, DictionaryMapper dictionaryMapper) {
        this.service = service;
        this.dictionaryMapper = dictionaryMapper;
    }

    @PostMapping("/")
    @ApiOperation("添加一个字典")
    @ApiHideBodyProperty({"id", "children", "entries"})
    public ResponseEntity add(@RequestBody Dictionary dictionary) {
        Tips tips = service.add(dictionary);
        if (tips.err()) {
            return ResponseEntity.badRequest().body(tips.getMessage());
        }
        return ResponseEntity.created(URI.create("/dictionaries/" + dictionary.getCode())).build();
    }

    @Transactional
    @ApiOperation("删除字典（级联删除字典下所有子项）")
    @DeleteMapping("/{code}")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "code", value = "字典code", required = true, dataType = "String")
    public ResponseEntity remove(@PathVariable("code") String code) {
        service.remove(code);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{code}")
    @ApiOperation("修改字典信息")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "code", value = "字典code", required = true, dataType = "String")
    @ApiHideBodyProperty({"id", "code", "children", "entries"})
    public ResponseEntity update(@PathVariable("code") String code, @RequestBody Dictionary dictionary) {
        dictionary.setCode(code);
        dictionaryMapper.update(dictionary);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{code}")
    @ApiOperation(value = "查询一个字典数据", response = Dictionary.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "code", value = "字典code", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = ApiParamType.QUERY, name = "includeEntries", value = "是否加载字典子项", dataType = "includeEntries")
    })
    public ResponseEntity dictionary(@PathVariable("code") String code, @RequestParam(value = "includeEntries", required = false) boolean includeEntries) {
        return ResponseEntity.ok().body(service.findByCode(code, includeEntries));
    }

    @ApiOperation("分页查询")
    @PostMapping("/pages")
    @ApiHideBodyProperty("entryCode")
    @ApiResponses({
            @ApiResponse(code = 0, message = "字典数据", response = Dictionary.class, responseContainer = "Set"),
            @ApiResponse(code = 200, message = "字典分页", response = Pages.class)
    })
    public ResponseEntity dictionaries(@RequestBody SearchParameter search) {
        return ResponseEntity.ok().body(service.pages(search));
    }

    //=====================================================================================================================================
    @ApiOperation("给字典添加一个子项")
    @PostMapping("/{dictCode}/entries")
    @ApiHideBodyProperty("id")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "dictCode", value = "字典code", required = true, dataType = "String")
    public ResponseEntity addEntry(@PathVariable("dictCode") String dictCode, @RequestBody DictionaryEntry entry) {
        Tips tips = service.addEntry(dictCode, entry);
        if (tips.err()) {
            return ResponseEntity.badRequest().body(tips.getMessage());
        }
        return ResponseEntity.created(URI.create("/dictionaries/"+dictCode)).body(tips);
    }

    @Transactional
    @ApiOperation("删除字典子项（如果子项code为空，则删除此字典下所有子项）")
    @DeleteMapping("/{dictCode}/entries")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "dictCode", value = "字典code", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = ApiParamType.QUERY, name = "code", value = "字典子项code", dataType = "String")
    })
    public ResponseEntity removeEntry(@PathVariable("dictCode") String dictCode, @RequestParam(value = "code", required = false) String code) {
        service.deleteEntry(dictCode, code);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("修改字典子项信息")
    @PutMapping("/{dictCode}/entries/{code}")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "dictCode", value = "字典code", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "code", value = "字典子项code", required = true, dataType = "String")
    })
    @ApiHideBodyProperty({"id", "code", "dictCode"})
    public ResponseEntity updateEntry(@PathVariable("dictCode") String dictCode, @PathVariable("code") String code, @RequestBody DictionaryEntry entry) {
        service.updateEntry(dictCode, code, entry);
        return ResponseEntity.ok().build();
    }
}
