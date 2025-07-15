//package com.financas.gestaofinanceira.domain.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
//import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
//import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class HideJsonObject {
//
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer hideJsonObject() {
//       return builder -> {
//           SimpleFilterProvider filterProvider = new SimpleFilterProvider()
//                   .addFilter("PasswordFilter",
//                           SimpleBeanPropertyFilter.serializeAllExcept("password"));
//           builder.filters(filterProvider);
//       };
//    }
//}
