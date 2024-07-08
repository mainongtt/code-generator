package com.psbc.controller;

import com.psbc.entity.Vo.DataSourceConfigSaveReqVO;
import com.psbc.entity.common.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "管理后台 - 数据源配置")
@RestController
@RequestMapping("/infra/data-source-config")
@Validated
public class DataSourceConfigController {

    @PostMapping("/create")
    @Operation(summary = "创建数据源配置")
    public CommonResult<Long> createDataSourceConfig(@Valid @RequestBody DataSourceConfigSaveReqVO createReqVO) {
//        return success(dataSourceConfigService.createDataSourceConfig(createReqVO));
        // todo
        return null;
    }
}
