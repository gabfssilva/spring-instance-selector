package com.thedevpiece.sis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {ConfigurationTest.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class InstanceTest {
    @Autowired
    private ServiceSample service;

    @Test
    public void test(){
        String name = service.getName("service_a");
        assertThat(name, equalTo("service_a"));
    }
}