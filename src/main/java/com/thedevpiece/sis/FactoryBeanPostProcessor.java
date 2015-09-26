package com.thedevpiece.sis;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import static com.thedevpiece.sis.Utils.unwrap;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class FactoryBeanPostProcessor implements BeanPostProcessor {
    private ApplicationContext applicationContext;

    public FactoryBeanPostProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object b, String beanName) throws BeansException {
        final Object bean = unwrap(b);

        for (Field field : bean.getClass().getDeclaredFields()) {
            if(!field.isAnnotationPresent(Factory.class)){
                continue;
            }

            if(!Instance.class.equals(field.getType())){
                continue;
            }

            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();

            field.setAccessible(true);
            try {
                field.set(bean, new Instance<Object>((Class<Object>) parameterizedType.getActualTypeArguments()[0], applicationContext));
            } catch (IllegalAccessException e) {
                throw new BeansException("Could not inject a factory in " + beanName + " bean", e) {};
            }
        }

        return b;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
