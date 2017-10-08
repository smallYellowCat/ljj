package com.hzdz.ls;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 给所有的请求添加cors
 */
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        // 对响应头进行CORS授权
        MyCorsRegistration corsRegistration = new MyCorsRegistration("/**");
        corsRegistration.allowedOrigins(CrossOrigin.DEFAULT_ORIGINS)
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.PUT.name())
                .allowedHeaders(CrossOrigin.DEFAULT_ALLOWED_HEADERS)
                .exposedHeaders(HttpHeaders.SET_COOKIE)
                .allowCredentials(CrossOrigin.DEFAULT_ALLOW_CREDENTIALS)
                .maxAge(CrossOrigin.DEFAULT_MAX_AGE);

        // 注册CORS过滤器
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", corsRegistration.getCorsConfiguration());
        CorsFilter corsFilter = new CorsFilter(configurationSource);
        return new FilterRegistrationBean(corsFilter);
    }


}
