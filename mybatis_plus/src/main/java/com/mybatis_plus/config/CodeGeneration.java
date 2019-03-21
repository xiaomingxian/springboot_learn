package com.mybatis_plus.config;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 *
 * @ClassName: CodeGeneration
 * @Description: 代码生成器
 * @author cheng
 * @date 2018年1月25日 下午2:55:14
 */
public class CodeGeneration {

    /**
     *
     * @Title: main
     * @Description: 生成
     * @param args
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        String windir="C:\\Users\\Administrator\\Desktop\\springboot_learn\\mybatis_plus\\src\\main\\java";
        String macdir = "/Users/xxm/develop/workspace/springboot_learn/springboot_learn/mybatis_plus/src/main/java";
        String dir = windir;
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(dir);
        gc.setFileOverride(true);
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false，true:service无需继承ServiceImpl--会生成一系列方法
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("xxm");// 作者

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("abc");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/springboot_learn?serverTimezone=GMT%2B8");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[] { "" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略

        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);
        String[] tables = {
                "user",
                "role",
                "user_role",
                "permission",
                "role_per",
        };

        strategy.setInclude(tables); // 需要生成的表



        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.mybatis_plus");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("dao");
        pc.setEntity("pojo");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);


        // 执行生成
        mpg.execute();

    }

}