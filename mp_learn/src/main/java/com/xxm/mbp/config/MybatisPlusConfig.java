package com.xxm.mbp.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

//Spring boot方式
@EnableTransactionManagement
@Configuration
@MapperScan("com.xxm.mbp.*.mapper*")
public class MybatisPlusConfig {

    public static ThreadLocal<String> TABLE_NAME = new ThreadLocal<String>();




    //@ConfigurationProperties("mybatis-plus")


    /**
     * 分页插件
     *
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // paginationInterceptor.setLimit(你的最大单页限制数量，默认 500 条，小于 0 如 -1 不受限制);
        ConcurrentHashMap
                 s;


        /*
         * 【测试多租户】 SQL 解析处理拦截器<br>
         * 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
         */
        List<ISqlParser> sqlParserList = new ArrayList<>();
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            //@Override
            //public Expression getTenantId(boolean where) {
            //    // 该 where 条件 3.2.0 版本开始添加的，用于分区是否为在 where 条件中使用
            //    // 此判断用于支持返回多个租户 ID 场景，具体使用查看示例工程
            //    return new LongValue(1L);
            //}

            @Override
            public Expression getTenantId() {
                //设置当前租户id，实际情况你可以从cookie、或者缓存中拿都行
                return new LongValue(1L);
            }

            @Override
            public String getTenantIdColumn() {
                return "tenant_id";
            }

            @Override
            public boolean doTableFilter(String tableName) {
                // 这里可以判断是否过滤表---为true表示不经过租户过滤
                //if ("person".equals(tableName)) {
                //    return true;
                //}
                return false;
            }
        });
        sqlParserList.add(tenantSqlParser);

        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
                SqlCommandType sqlCommandType = ms.getSqlCommandType();

                //插入与更新时过滤 租户字段 [不这样会生成的sql会有两个租户字段(原因未知)]
                if (("INSERT").equals(sqlCommandType.toString()) || "UPDATE".equals(sqlCommandType.toString())) {
                    System.out.println("----->过滤插入与更新方法");
                    return true;
                }
                // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
                if ("com.xxm.mbp.dao.PersonMapper.selectList".equals(ms.getId())) {
                    return true;
                }
                return false;
            }
        });

        //-------------------------动态表名--------------------------------
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        HashMap<String, ITableNameHandler> map = new HashMap<>();
        map.put("person", new ITableNameHandler() {
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                return TABLE_NAME.get();
            }
        });

        dynamicTableNameParser.setTableNameHandlerMap(map);
        sqlParserList.add(dynamicTableNameParser);

        //----------------------------------------------------------
        paginationInterceptor.setSqlParserList(sqlParserList);

        return paginationInterceptor;
    }

    /**
     * 逻辑删除  高版本不需要
     */
    //@Bean
    //public ISqlInjector iSqlInjector(){
    //}

    /**
     * 乐观所插件
     * 支持的数据类型只有:int,Integer,long,Long,Date,Timestamp,LocalDateTime
     * 整数类型下 newVersion = oldVersion + 1
     * newVersion 会回写到 entity 中
     * 仅支持 updateById(id) 与 update(entity, wrapper) 方法
     * 在 update(entity, wrapper) 方法下, wrapper 不能复用!!!
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }


    /**
     * 性能监控
     *
     * @return
     */
    @Bean
    @Profile({"test", "dev"})//-Dspring.profiles.active=dev
    public PerformanceInterceptor performanceMonitorInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();

        performanceInterceptor.setFormat(true);
        //performanceInterceptor.setMaxTime(5L);//慢sql

        return performanceInterceptor;
    }


}
