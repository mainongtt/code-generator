package com.psbc.service.impl;

import com.psbc.entity.Do.CodegenTableDO;
import com.psbc.mapper.CodegenTableMapper;
import com.psbc.service.CodegenService;

import javax.annotation.Resource;
import java.util.List;

public class CodegenServiceImpl implements CodegenService {

    @Resource
    private CodegenTableMapper codegenTableMapper;

    @Override
    public List<CodegenTableDO> getCodegenTableList(Long dataSourceConfigId) {
        return codegenTableMapper.selectListByDataSourceConfigId(dataSourceConfigId);
    }
}
