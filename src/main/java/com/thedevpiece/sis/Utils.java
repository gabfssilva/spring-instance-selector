package com.thedevpiece.sis;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
class Utils {
    public static Object unwrap(Object bean)  {
        if (AopUtils.isAopProxy(bean) && bean instanceof Advised) {
            Advised advised = (Advised) bean;
            try {
                bean = advised.getTargetSource().getTarget();
            } catch (Exception e) {
                throw new RuntimeException("Unable to unwrap the proxy from " + bean.getClass().getSimpleName() + " bean");
            }
        }

        return bean;
    }
}
