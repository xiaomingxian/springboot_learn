package com.xxm.mbp.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class MethodCreate extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {

        String sql = "select count(*) from " + tableInfo.getTableName();

        String method = "selectCountTotal";

        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);

        //return addSelectMappedStatementForTable(mapperClass, method, sqlSource, tableInfo);
        //返回值
        return addSelectMappedStatementForOther(mapperClass, method, sqlSource, Integer.class);
    }
}
