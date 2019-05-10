package com.mybatis_plus.batch.job;

import com.mybatis_plus.batch.pojo.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class T10_XmlReder {


    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job xmlJob() {
        return jobBuilderFactory.get("xmlJob4")
                .start(xmlStep())
                .build();
    }

    private Step xmlStep() {
        return stepBuilderFactory.get("xmlStep")
                .<Person, Person>chunk(100)
                .reader(xmlReader())
                .writer(writer())
                .build();
    }

    private ItemWriter<Person> writer() {

        return new ItemWriter<Person>() {
            @Override
            public void write(List<? extends Person> list) throws Exception {
                System.out.println("--------->进行写操作");
                //list.stream().forEach(System.out::println);
                System.out.println(list);
            }
        };
    }

    private ItemReader<? extends Person> xmlReader() {
        StaxEventItemReader<Person> reader = new StaxEventItemReader<>();
        //读取数据源
        reader.setResource(new ClassPathResource("/batch/PerSonData.xml"));

        //指定需要处理的跟标签
        reader.setFragmentRootElementName("Person");
        // 把xml转成对象
        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        //指明转换的类
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("Person", Person.class);
        try {
            xStreamMarshaller.setAliases(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        reader.setUnmarshaller(xStreamMarshaller);
        return reader;
    }

}
