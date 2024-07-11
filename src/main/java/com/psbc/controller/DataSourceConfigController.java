package com.psbc.controller;

import com.psbc.entity.Do.DataSourceConfigDO;
import com.psbc.entity.Vo.DataSourceConfigRespVO;
import com.psbc.entity.Vo.DataSourceConfigSaveReqVO;
import com.psbc.entity.common.CommonResult;
import com.psbc.entity.common.GlobalErrorCodeConstants;
import com.psbc.service.DataSourceConfigService;
import com.psbc.util.BeanUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.List;

import static com.psbc.entity.common.CommonResult.error;
import static com.psbc.entity.common.CommonResult.success;

@Tag(name = "管理后台 - 数据源配置")
@RestController
@RequestMapping("/code/dataSourceConfig")
@Validated
public class DataSourceConfigController {

    @Resource
    private DataSourceConfigService dataSourceConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建数据源配置")
    public CommonResult<Long> createDataSourceConfig(@Valid @RequestBody DataSourceConfigSaveReqVO createReqVO) {
        Long insertId = -1L;
        try {
            insertId = dataSourceConfigService.createDataSourceConfig(createReqVO);
        } catch (Exception e) {
            return error(GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
        return success(insertId);
    }

    @PutMapping("/update")
    @Operation(summary = "更新数据源配置")
    public CommonResult<Boolean> updateDataSourceConfig(@Valid @RequestBody DataSourceConfigSaveReqVO updateReqVO) {
        dataSourceConfigService.updateDataSourceConfig(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除数据源配置")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteDataSourceConfig(@RequestParam("id") Long id) {
        dataSourceConfigService.deleteDataSourceConfig(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得数据源配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<DataSourceConfigRespVO> getDataSourceConfig(@RequestParam("id") Long id) {
        DataSourceConfigDO config = dataSourceConfigService.getDataSourceConfig(id);
        return success(BeanUtils.toBean(config, DataSourceConfigRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得数据源配置列表")
    public CommonResult<List<DataSourceConfigRespVO>> getDataSourceConfigList() {
        List<DataSourceConfigDO> list = dataSourceConfigService.getDataSourceConfigList();
        return success(BeanUtils.toBean(list, DataSourceConfigRespVO.class));
    }
}
