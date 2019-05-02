package com.mybatis_plus.batch.job;

import com.mybatis_plus.batch.pojo.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.validation.BindException;

@Configuration
@EnableBatchProcessing
public class T11_MulFileReader {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job mulFileJob() {

        return jobBuilderFactory.get("mulFileJob4")
                .start(mulFileStep())
                .build();
    }

    private Step mulFileStep() {
        return stepBuilderFactory.get("mulFileStep")
                .<Person, Person>chunk(500)
                .reader(reader())
                .writer(list -> {
                    System.out.println("------->批量写操作");
                    list.stream().forEach(System.out::println);
                })
                .build();
    }

    @Value("classpath:/batch/file*.txt")
    private Resource[] resources;

    private ItemReader<? extends Person> reader() {
        MultiResourceItemReader<Person> reader = new MultiResourceItemReader<>();

        reader.setResources(resources);
        reader.setDelegate(fileReader());
        return reader;
    }


    public ResourceAwareItemReaderItemStream<? extends Person> fileReader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        //数据源
        //reader.setResource(new ClassPathResource("/batch/file*.txt"));
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
