package com.thedevpiece.sis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@Configuration
@ComponentScan("com.thedevpiece.sis")
public class ConfigurationTest {
    @Bean
    public static FactoryBeanPostProcessor factoryBeanPostProcessor(ApplicationContext applicationContext){
        return new FactoryBeanPostProcessor(applicationContext);
    }
}
