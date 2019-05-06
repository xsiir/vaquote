package org.sienkiewicz.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.sienkiewicz.quote", "org.sienkiewicz.user"})
public class ApplicationConfiguration implements WebMvcConfigurer {
	
	
	
}
