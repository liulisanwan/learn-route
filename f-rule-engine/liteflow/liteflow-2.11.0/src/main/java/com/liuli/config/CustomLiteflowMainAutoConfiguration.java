package com.liuli.config;

import com.yomahub.liteflow.property.LiteflowConfig;
import com.yomahub.liteflow.spi.spring.SpringAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 主要的业务装配器 在这个装配器里装配了执行器，执行器初始化类，监控器
 * 这个装配前置条件是需要LiteflowConfig，LiteflowPropertyAutoConfiguration以及SpringAware
 *
 * @author Bryan.Zhang
 */
@Configuration
@Import(SpringAware.class)
public class CustomLiteflowMainAutoConfiguration {

	// 实例化FlowExecutor
	// 多加一个SpringAware的意义是，确保在执行这个的时候，SpringAware这个bean已经被初始化
	@Bean
	public CustomFlowExecutor customFlowExecutor(LiteflowConfig liteflowConfig, SpringAware springAware) {
		CustomFlowExecutor flowExecutor = new CustomFlowExecutor();
		flowExecutor.setLiteflowConfig(liteflowConfig);
		return flowExecutor;
	}

	// FlowExecutor的初始化工作，和实例化分开来
	@Bean
	@ConditionalOnProperty(prefix = "liteflow", name = "parse-on-start", havingValue = "true")
	public CustomLiteflowExecutorInit customLiteflowExecutorInit(CustomFlowExecutor flowExecutor) {
		return new CustomLiteflowExecutorInit(flowExecutor);
	}


}
