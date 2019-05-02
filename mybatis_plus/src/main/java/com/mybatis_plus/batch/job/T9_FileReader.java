package com.mybatis_plus.batch.job;

import com.mybatis_plus.batch.pojo.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
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

import java.util.List;

@Configuration
@EnableBatchProcessing
public class T9_FileReader {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job fileJob() {

        return jobBuilderFactory.get("fileJob2")
                .start(fileStep())
                .build();
    }

    @Bean
    public Step fileStep() {
        return stepBuilderFactory.get("fileStep")
                .<Person, Person>chunk(500)
                .reader(reader())
                .writer(writer())
                .build();
    }

    public ItemWriter<? super Person> writer() {

        return new ItemWriter<Person>() {
            @Override
            public void write(List<? extends Person> list) throws Exception {
                System.out.println("------------------>进行写操作");
                list.stream().forEach(System.out::println);
            }
        };
    }

    /**
     * 类似与从db读取只是API不同
     *
     * @return
     */
    public ItemReader<? extends Person> reader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        //数据源
        reader.setResource(new ClassPathResource("/batch/personData.txt"));
        reader.setLinesToSkip(1);//setLinesToSkip;//跳过n行

        //解析数据
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"id", "name", "sex", "age", "birthday"});
        //把解析的一行数据映射成实体类
        DefaultLineMapper<Person> mapper = new DefaultLineMapper<>();
        mapper.setLineTokenizer(tokenizer);
        mapper.setFieldSetMapper(new FieldSetMapper<Person>() {
            @Override
            public Person mapFieldSet(FieldSet fieldSet) throws BindException {
                Person person = new Person();
                person.setId(fieldSet.readInt("id"));
                person.setName(fieldSet.readString("name"));
                person.setSex(fieldSet.readString("sex"));
                person.setAge(fieldSet.readShort("age"));
                person.setBirthday(fieldSet.readDate("birthday"));
                return person;
            }
        });
        mapper.afterPropertiesSet();//做检查？？？
        reader.setLineMapper(mapper);//设置行映射
        return reader;
    }

}
