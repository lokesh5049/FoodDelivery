package com.sapphireims.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
/**
 * Spring  Configuration java file
 * 
 * 
 * @author lokesh.yadav
 * @since   2019-01-14
 *
 */
@SuppressWarnings("deprecation")
@EnableWebMvc
@ComponentScan(basePackages = "com.sapphireims")
@Import({HibernateConfig.class,SecurityConfig.class})
@Configuration
public class SpringConfigure extends WebMvcConfigurerAdapter {
   
	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setViewClass(JstlView.class);

		viewResolver.setPrefix("/WEB-INF/pages/");

		viewResolver.setSuffix(".jsp");

		return viewResolver;

	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

		configurer.enable();

	}
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry
	      .addResourceHandler("/resources/**")
	      .addResourceLocations("/resources/")
	      .setCachePeriod(3600)
	      .resourceChain(true)
	      .addResolver(new PathResourceResolver());
	    }

}
