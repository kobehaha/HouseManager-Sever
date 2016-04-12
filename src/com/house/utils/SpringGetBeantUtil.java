package com.house.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringGetBeantUtil implements ApplicationContextAware {

	private ApplicationContext context;// 通过这个代理获取spring context对象

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;

	}

	public ApplicationContext getContext() {
		return context;
	}

	public Object getBean(Class<Object> class1) {
		Object object = context.getBean(class1);
		return object;

	}

}