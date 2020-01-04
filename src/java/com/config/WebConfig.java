// /*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.ResourceBundleMessageSource;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// *
// * @author Supersoft
// */
//@EnableWebMvc
//@Configuration
//@ComponentScan({"com.config"})
//public class WebConfig extends WebMvcConfigurerAdapter{
//    
//    
//    @Bean
//    public ResourceBundleMessageSource messageSource(){
//    ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
//    resourceBundleMessageSource.setBasenames(new String[] {"validation"});
//    return resourceBundleMessageSource;
//    }
//    
//    
//    @Bean(name = "multipartResolver")
//public CommonsMultipartResolver multipartResolver() {
//    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//    multipartResolver.setMaxUploadSize(100000);
//    return multipartResolver;
//}
//    
//    
//    
//}
