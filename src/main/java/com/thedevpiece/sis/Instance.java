package com.thedevpiece.sis;

import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.thedevpiece.sis.Utils.unwrap;

public class Instance<T> {
    private List<T> beans;
    private ApplicationContext applicationContext;

    public Instance(Class<T> clazz, ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.beans = new LinkedList<T>();

        for (Map.Entry<String, T> entry : applicationContext.getBeansOfType(clazz).entrySet()) {
            this.beans.add(entry.getValue());
        }
    }

    public Instance(List<T> beans, ApplicationContext applicationContext) {
        this.beans = beans;
        this.applicationContext = applicationContext;
    }

    public Instance<T> select(Annotation annotation){
        List<T> selectedBeans = new LinkedList<T>();

        for (T bean : beans) {
            if(annotation.equals(unwrap(bean).getClass().getAnnotation(annotation.annotationType()))){
                selectedBeans.add(bean);
            }
        }

        return new Instance<T>(selectedBeans, applicationContext);
    }

    public T get(){
        if(beans.size() == 1){
            return beans.get(0);
        }

        if(beans.isEmpty()){
            return null;
        }

        throw new RuntimeException();
    }
}
