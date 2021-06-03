package com.example.demo.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.batch.tasklet.CreateApiDataTasklet;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

	private final JobBuilderFactory jobBuilderFactory;

	private final StepBuilderFactory stepBuilderFactory;

	private final CreateApiDataTasklet createApiDataTasklet;

	public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			CreateApiDataTasklet createApiDataTasklet) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.createApiDataTasklet = createApiDataTasklet;
	}

	@Bean
	public Job helloWorldJob(Step createApiDataStep) {
		return jobBuilderFactory.get("createApiDataJob") // Job名を指定
				.flow(createApiDataStep) // 実行するStepを指定
				.end().build();
	}

	@Bean
	public Step helloWorldStep() {
		return stepBuilderFactory.get("createApiDataStep") // Step名を指定
				.tasklet(createApiDataTasklet) // 実行するTaskletを指定
				.build();
	}
}