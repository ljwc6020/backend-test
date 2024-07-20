package kr.co.polycube.backendtest.config;

import kr.co.polycube.backendtest.filter.URLFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<URLFilter> urlFilter() {
        FilterRegistrationBean<URLFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new URLFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
