package com.webs.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Bean
	public FilterRegistrationBean jwtFilterBean() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");
		registrationBean.addUrlPatterns("/rpc/*");
		registrationBean.addUrlPatterns("/admin/*");
		return registrationBean;
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class)
			.showBanner(false)
			.run(args);
	}
}