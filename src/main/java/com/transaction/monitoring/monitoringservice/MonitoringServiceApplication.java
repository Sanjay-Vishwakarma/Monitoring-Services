package com.transaction.monitoring.monitoringservice;

import com.transaction.monitoring.monitoringservice.registry.TMGatewayService;
import com.transaction.monitoring.monitoringservice.service.utils.Functions;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class MonitoringServiceApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MonitoringServiceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MonitoringServiceApplication.class, args);
	}

	@Bean
	public FactoryBean<?> getFactoryBean(){
		ServiceLocatorFactoryBean bean = new ServiceLocatorFactoryBean();
		bean.setServiceLocatorInterface(TMGatewayService.class);
		return bean;
	}
	
	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}

	@Bean
	public Functions functions(){return new Functions();}

}
