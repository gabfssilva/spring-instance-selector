package com.thedevpiece.sis;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ServiceType(name = "service_b")
public class ServiceB implements Service {
    @Override
    public String getName() {
        return "service_b";
    }
}
