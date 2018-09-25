package com.lhiot.dc.base.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 系统时间接口
 * @author: Limiaojun
 * @create: 2018-06-14 16:01
 **/
@Api(description = "系统时间接口")
@RestController
@RequestMapping("/system/time")
public class SystemTimeApi {

    @GetMapping("/current")
    @ApiOperation(value = "获取服务器系统时间")
    public ResponseEntity<String> getTime() {
        return ResponseEntity.ok(String.valueOf(System.currentTimeMillis()));
    }
}
