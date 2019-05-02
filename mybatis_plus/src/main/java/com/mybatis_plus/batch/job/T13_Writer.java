package com.mybatis_plus.batch.job;

import com.mybatis_plus.batch.pojo.Animal;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

import javax.sql.DataSource;


@Configuration
@EnableBatchProcessing
public class T13_Writer {


    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;


    @Bean
    public Job exJob() {
        return jobBuilderFactory.get("writerItem-2")
                .start(writeStep())
                .build();
    }

    private Step writeStep() {
        return stepBuilderFactory
                .get("writerStep")
                .<Animal, Animal>chunk(1000)
                .reader(reader())
                .writer(writer())
                .build();
    }

    private ItemWriter<? super Animal> writer() {
        ItemWriter<? super Animal> writer = jdbcWriter();
        return writer;
    }

    private ItemWriter<? super Animal> jdbcWriter() {
        //ItemWriter<Animal> animalItemWriter = new ItemWriter<Animal>() {
        //    @Override
        //    public void write(List list) throws Exception {
        //        list.stream().forEach(System.out::println);
        //    }
        //};
        //
        //return animalItemWriter;
        JdbcBatchItemWriter<Animal> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setDataSource(dataSource);

        jdbcBatchItemWriter.setSql("insert into animal(age,name) values" +
                "(:age,:name)");//:name  占位符防止sql注入
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Animal>());//将属性值与占位符进行替换
        return jdbcBatchItemWriter;

    }

    private ItemReader<? extends Animal> reader() {
        FlatFileItemReader<Animal> reader = new FlatFileItemReader<>();

        //数据源
        reader.setResource(new ClassPathResource("/batch/animal"));
        reader.setLinesToSkip(1);//setLinesToSkip;//跳过n行

        //解析数据
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();//根据某种分隔符来拆分
        tokenizer.setNames(new String[]{"age", "name"});
        //把解析的一行数据映射成实体类
        DefaultLineMapper<Animal> mapper = new DefaultLineMapper<>();
        mapper.setLineTokenizer(tokenizer);
        mapper.setFieldSetMapper(new FieldSetMapper<Animal>() {
            @Override
            public Animal mapFieldSet(FieldSet fieldSet) throws BindException {
                Animal person = new Animal();
                person.setName(fieldSet.readString("name"));
                person.setAge(fieldSet.readShort("age"));
                return person;
            }
        });
        mapper.afterPropertiesSet();//做检查？？？
        reader.setLineMapper(mapper);//设置行映射
        return reader;
    }


}
