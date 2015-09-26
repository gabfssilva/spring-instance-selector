package com.thedevpiece.sis;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ServiceType(name = "service_a")
public class ServiceA implements Service {
    @Override
    public String getName() {
        return "service_a";
    }
}
