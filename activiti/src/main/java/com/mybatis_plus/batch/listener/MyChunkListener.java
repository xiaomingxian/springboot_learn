package com.mybatis_plus.batch.listener;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

public class MyChunkListener {

    @BeforeChunk
    public void before(ChunkContext chunkContext) {
        System.out.println("===============> @BeforeChunk：" + chunkContext.getStepContext());
    }

    @AfterChunk
    public void after(ChunkContext chunkContext) {
        System.out.println("===============> @AfterChunk：" + chunkContext.getStepContext());
    }

}
