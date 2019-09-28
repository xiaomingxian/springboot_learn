package com.mybatis_plus.batch.job;

import com.mybatis_plus.pojo.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Configuration
@EnableBatchProcessing
public class T8_ItemReaderDb {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public Job jobDb() {
        return jobBuilderFactory.get("jobDb2").start(stepDb())
                .build();
    }

    @Bean
    public Step stepDb() {
        return stepBuilderFactory.get("stepDb")
                .<User, User>chunk(2)
                .reader(readerDb())
                .writer(list -> {
                    System.out.println("--------->读取数据库中查出的数据");
                    list.stream().forEach(System.out::println);
                })
                .build();
    }


    @Bean
    @StepScope
    public ItemReader<User> readerDb() {
        JdbcPagingItemReader<User> itemReader = new JdbcPagingItemReader<>();
        itemReader.setDataSource(dataSource);
        itemReader.setFetchSize(2);//每次读取两条
        //数据库中的数据映射到实体类
        itemReader.setRowMapper(new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                //resultSet数据库一条结果集，i当前行
                return new User(resultSet.getInt("uid"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));

            }
        });
        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
        provider.setSelectClause("*");
        provider.setFromClause("from user");
        //定义排序map
        HashMap<String, Order> map = new HashMap<>(1);
        map.put("uid", Order.DESCENDING);
        provider.setSortKeys(map);
        itemReader.setQueryProvider(provider);
        return itemReader;
    }

}
