package com.scott.trace.config;



import com.scott.trace.filter.TraceMDCFilter;
import com.scott.trace.interceptor.FeignLog4MdcInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "trace.enabled", havingValue = "true", matchIfMissing = false)
public class TraceAutoConfiguration {
    @Bean
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @ConditionalOnMissingBean
    public FilterRegistrationBean<TraceMDCFilter> traceMDCFilter() {
        FilterRegistrationBean<TraceMDCFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TraceMDCFilter());
        registrationBean.setOrder(Integer.MIN_VALUE); // Set a high priority (low order value) for the filter
        return registrationBean;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({FeignClient.class})
    public FeignConfiguration feignConfiguration() {
        return new FeignConfiguration();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({FeignClient.class})
    public FeignLog4MdcInterceptor feignLog4MdcInterceptor() {
        return new FeignLog4MdcInterceptor();
    }
}
