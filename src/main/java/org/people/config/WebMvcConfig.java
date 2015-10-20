package org.people.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ImportResource({ "classpath:META-INF/db-hsqldb-config.xml",
		"classpath:META-INF/datasource-config.xml",
		"classpath:META-INF/application-config.xml" })
public class WebMvcConfig extends WebMvcConfigurerAdapter {
}