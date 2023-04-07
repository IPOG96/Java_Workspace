package com.coding.Book_API.common;

import com.coding.Book_API.config.JwtInterceptor;
import com.coding.Book_API.dto.RequestMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class CustomWebConfig implements WebMvcConfigurer {

    @Autowired
    JwtInterceptor jwtInterceptor;


    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

        SortHandlerMethodArgumentResolver sortResolver = new SortHandlerMethodArgumentResolver();
        sortResolver.setSortParameter("orderBy");

        PageableHandlerMethodArgumentResolver pageResolver = new PageableHandlerMethodArgumentResolver(sortResolver);
        pageResolver.setPageParameterName("page-number");
        pageResolver.setSizeParameterName("page-size");
        pageResolver.setOneIndexedParameters(true);
        Pageable defaultPageable = PageRequest.of(0, 5, Sort.unsorted());
        pageResolver.setFallbackPageable(defaultPageable);
        resolvers.add(pageResolver);

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(jwtInterceptor);
    }


    @Bean
    // @Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
    @RequestScope
    public RequestMeta requestMeta() {
        return new RequestMeta();
    }

    @Bean
    public JwtInterceptor getJwtInterceptor() {
        return new JwtInterceptor(requestMeta());
    }
}
