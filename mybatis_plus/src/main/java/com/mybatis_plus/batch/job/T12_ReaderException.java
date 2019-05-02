//package com.mybatis_plus.batch.job;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.*;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
//@Configuration
//@EnableBatchProcessing
//public class T12_ReaderException {
//    /**
//     * reader
//     * ItemStream
//     * step执行之前--
//     * void open(ExecutionContext var1) throws ItemStreamException;
//     * 每执行完一批chunk
//     * void update(ExecutionContext var1) throws ItemStreamException;
//     * step执行之后--
//     * void close() throws ItemStreamException;
//     */
//
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//
//    @Bean
//    public Job exJob() {
//        return jobBuilderFactory.get("exJob2")
//                .start(exStep())
//                .build();
//    }
//
//    @Bean
//    public Step exStep() {
//        return stepBuilderFactory.get("exStep")
//                .<String,String>chunk(3)
//                .reader(reader())
//                .writer(list -> list.stream().forEach(System.out::println))
//                .build();
//    }
//
//    @Bean
//    public ItemReader reader() {
//        return new ExReader();
//    }
//
//
//    class ExReader implements ItemStreamReader {
//        @Override
//        public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//            return new ListItemReader<>(Arrays.asList("java", "go", "python", "c", "c++", "c#"));
//        }
//
//        @Override
//        public void open(ExecutionContext executionContext) throws ItemStreamException {
//            System.out.println("----------->open");
//        }
//
//        @Override
//        public void update(ExecutionContext executionContext) throws ItemStreamException {
//            System.out.println("----------->update");
//            //executionContext.put("ex", "捕获到异常".getBytes());
//        }
//
//        @Override
//        public void close() throws ItemStreamException {
//            System.out.println("----------->close");
//
//        }
//    }
//
//}
