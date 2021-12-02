package edu.baylor.cs.se.hibernate.config;

import edu.baylor.cs.se.hibernate.model.Team;
import edu.baylor.cs.se.hibernate.model.UserResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JmsConfiguration {

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");
        Map<String, Class<?>> typeIdMappings = new HashMap();
        typeIdMappings.put("users", UserResponse.class);
        messageConverter.setTypeIdMappings(typeIdMappings);
        return messageConverter;
    }
}
