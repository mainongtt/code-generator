package com.psbc.service.impl;

import com.psbc.entity.Do.DataSourceConfigDO;
import com.psbc.entity.Vo.DataSourceConfigSaveReqVO;
import com.psbc.mapper.DataSourceConfigMapper;
import com.psbc.service.DataSourceConfigService;
import com.psbc.util.BeanUtils;
import com.psbc.util.JdbcUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;


@Service
@Validated
@Slf4j
public class DataSourceConfigServiceImpl implements DataSourceConfigService {
    @Resource
    private DataSourceConfigMapper dataSourceConfigMapper;

    @Override
    public Long createDataSourceConfig(DataSourceConfigSaveReqVO createReqVO) throws Exception {
        DataSourceConfigDO config = BeanUtils.toBean(createReqVO, DataSourceConfigDO.class);
        validateConnectionOK(config);
        try {
            // 插入
            dataSourceConfigMapper.insert(config);
        } catch (Exception e) {
            log.error("错误信息", e.toString());
            throw new Exception(e.getMessage());
        }
        // 返回
        return config.getId();
    }

    private void validateConnectionOK(DataSourceConfigDO config) {
        boolean success = JdbcUtils.isConnectionOK(config.getUrl(), config.getUsername(), config.getPassword());
        if (!success) {
            throw new RuntimeException("数据库配置错");
        }
    }

    @Override
    public void updateDataSourceConfig(DataSourceConfigSaveReqVO updateReqVO) {
        // 校验存在
        validateDataSourceConfigExists(updateReqVO.getId());
        DataSourceConfigDO updateObj = BeanUtils.toBean(updateReqVO, DataSourceConfigDO.class);
        validateConnectionOK(updateObj);

        // 更新
        dataSourceConfigMapper.updateById(updateObj);
    }

    private void validateDataSourceConfigExists(Long id) {
        if (dataSourceConfigMapper.selectById(id) == null) {
            throw new RuntimeException("数据库配置不存在");
        }
    }

    @Override
    public void deleteDataSourceConfig(Long id) {
        // 校验存在
        validateDataSourceConfigExists(id);
        // 删除
        dataSourceConfigMapper.deleteById(id);
    }

    @Override
    public DataSourceConfigDO getDataSourceConfig(Long id) {
        // 从 DB 中读取
        return dataSourceConfigMapper.selectById(id);
    }

    @Override
    public List<DataSourceConfigDO> getDataSourceConfigList() {
        List<DataSourceConfigDO> result = dataSourceConfigMapper.selectList();
        return result;
    }
}
