package com.uit.tms.TaskManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  /**
   * Enables support for HTTP method conversion using the _method parameter.
   * This allows HTML forms to simulate DELETE, PUT, and PATCH requests
   * by including a hidden input field with name _method and the desired HTTP
   * method.
   */
  @Bean
  public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
    return new HiddenHttpMethodFilter();
  }
}