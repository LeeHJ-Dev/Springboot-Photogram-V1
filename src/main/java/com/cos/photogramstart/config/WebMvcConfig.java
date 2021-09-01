package com.cos.photogramstart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * Web 설정파일
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // Path. /USers/lhj/study/Springboot-Photogram-V1/upload/
    @Value(value = "${file.path}")
    private String updateFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry
                .addResourceHandler("/upload/**")       // jsp 페이지에서 /update/** 주소인경우 적용
                .addResourceLocations("file:///" + updateFolder)
                .setCachePeriod(60*10*6) //1시간 캐싱
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
