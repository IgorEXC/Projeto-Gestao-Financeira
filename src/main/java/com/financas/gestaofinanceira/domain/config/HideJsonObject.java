package com.financas.gestaofinanceira.domain.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HideJsonObject {

    @Bean
    public ObjectMapper hideJsonObject() {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleFilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("PasswordFilter",
                        SimpleBeanPropertyFilter.serializeAllExcept("password"));
        objectMapper.setFilterProvider(filterProvider);
        return objectMapper;
    }
}
