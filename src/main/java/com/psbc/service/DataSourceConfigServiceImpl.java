package com.psbc.service;

import com.psbc.entity.Do.DataSourceConfigDO;
import com.psbc.entity.Vo.DataSourceConfigSaveReqVO;
import com.psbc.mapper.DataSourceConfigMapper;
import com.psbc.util.BeanUtils;
import com.psbc.util.JdbcUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;


@Service
@Validated
public class DataSourceConfigServiceImpl implements DataSourceConfigService {
    @Resource
    private DataSourceConfigMapper dataSourceConfigMapper;

    @Override
    public Long createDataSourceConfig(DataSourceConfigSaveReqVO createReqVO) {
        DataSourceConfigDO config = BeanUtils.toBean(createReqVO, DataSourceConfigDO.class);
        validateConnectionOK(config);

        // 插入
        dataSourceConfigMapper.insert(config);
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

    }

    @Override
    public void deleteDataSourceConfig(Long id) {

    }

    @Override
    public DataSourceConfigDO getDataSourceConfig(Long id) {
        return null;
    }

    @Override
    public List<DataSourceConfigDO> getDataSourceConfigList() {
        return null;
    }
}
