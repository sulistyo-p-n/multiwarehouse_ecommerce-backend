package com.multiwarehouse.app.authgateway.service.domain;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class BeanConfiguration {
    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }

//    @Bean
//    public CorsWebFilter corsFilter()
//    {
//        CorsConfiguration config = new CorsConfiguration();
////        config.setAllowCredentials( true );
//        config.setAllowedOrigins( List.of( "*" ) );
//        config.setAllowedMethods( List.of( "GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD" ) );
//        config.setAllowedHeaders( List.of( "origin", "content-type", "accept", "authorization", "cookie" ) );
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration( "/**", config );
//
//        return new CorsWebFilter( source );
//    }
}
