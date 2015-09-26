package com.thedevpiece.sis;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class ServiceTypeQualifier extends QualifierLiteral<ServiceType> implements ServiceType {
    private String name;

    public ServiceTypeQualifier(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
