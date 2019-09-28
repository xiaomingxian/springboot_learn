package com.xxm.mbp.common;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MpCodeGen {
    public static void main(String[] args) {

        String projectPath = "/Users/xxm/develop/workspace/boot_project/mp_learn";


        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(new GlobalConfig()
                .setAuthor("xiaoming.xian")
                .setActiveRecord(true)
                .setFileOverride(true)
                .setOutputDir(projectPath + "/src/main/java")
                .setBaseColumnList(true)
                .setEnableCache(false)
                .setBaseResultMap(true)
                .setOpen(false)
                .setXmlName("%sMapper")
                .setMapperName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
        );
        mpg.setStrategy(new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                //.setTablePrefix("t_")
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setInclude(new String[]{"person", "role", "role_per", "user", "user_role"})
        );
        mpg.setDataSource(new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://localhost:3306/springboot_learn?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai")
                .setUsername("root")
                .setPassword("abc")
                .setDriverName("com.mysql.cj.jdbc.Driver")
        );
        mpg.setPackageInfo(new PackageConfig()
                .setParent("com.xxm.mbp")
                .setMapper("dao")
                .setXml("mapper")
                .setEntity("pojo")
                .setService("service")
                .setServiceImpl("service.impl")
                .setController("controller")
        );
        mpg.execute();
    }
}