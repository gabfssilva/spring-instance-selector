package com.thedevpiece.sis;

import org.springframework.stereotype.Component;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@Component
public class ServiceSample {
    @Factory
    private Instance<Service> services;

    public String getName(String type){
        return services.select(new ServiceTypeQualifier(type)).get().getName();
    }
}
