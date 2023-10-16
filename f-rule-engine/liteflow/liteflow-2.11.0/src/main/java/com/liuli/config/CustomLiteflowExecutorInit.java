package com.liuli.config;

import org.springframework.beans.factory.InitializingBean;

public class CustomLiteflowExecutorInit implements InitializingBean {

	private final CustomFlowExecutor customFlowExecutor;

	public CustomLiteflowExecutorInit(CustomFlowExecutor flowExecutor) {
		this.customFlowExecutor = flowExecutor;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		customFlowExecutor.init(true);
	}

}