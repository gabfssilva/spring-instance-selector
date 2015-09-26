package com.thedevpiece.sis;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class QualifierLiteral<T extends Annotation> implements Annotation {
    private Class<T> annotationType;
    private Method[] parameters;

    public QualifierLiteral() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        annotationType = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return annotationType;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Annotation) {
            Annotation that = (Annotation) other;
            if (this.annotationType().equals(that.annotationType())) {
                for (Method member : getMethods()) {
                    Object thisValue = invoke(member, this);
                    Object thatValue = invoke(member, that);
                    if (thisValue instanceof byte[] && thatValue instanceof byte[]) {
                        if (!Arrays.equals((byte[]) thisValue, (byte[]) thatValue)) return false;
                    } else if (thisValue instanceof short[] && thatValue instanceof short[]) {
                        if (!Arrays.equals((short[]) thisValue, (short[]) thatValue)) return false;
                    } else if (thisValue instanceof int[] && thatValue instanceof int[]) {
                        if (!Arrays.equals((int[]) thisValue, (int[]) thatValue)) return false;
                    } else if (thisValue instanceof long[] && thatValue instanceof long[]) {
                        if (!Arrays.equals((long[]) thisValue, (long[]) thatValue)) return false;
                    } else if (thisValue instanceof float[] && thatValue instanceof float[]) {
                        if (!Arrays.equals((float[]) thisValue, (float[]) thatValue)) return false;
                    } else if (thisValue instanceof double[] && thatValue instanceof double[]) {
                        if (!Arrays.equals((double[]) thisValue, (double[]) thatValue)) return false;
                    } else if (thisValue instanceof char[] && thatValue instanceof char[]) {
                        if (!Arrays.equals((char[]) thisValue, (char[]) thatValue)) return false;
                    } else if (thisValue instanceof boolean[] && thatValue instanceof boolean[]) {
                        if (!Arrays.equals((boolean[]) thisValue, (boolean[]) thatValue)) return false;
                    } else if (thisValue instanceof Object[] && thatValue instanceof Object[]) {
                        if (!Arrays.equals((Object[]) thisValue, (Object[]) thatValue)) return false;
                    } else {
                        if (!thisValue.equals(thatValue)) return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (Method member : getMethods()) {
            int memberNameHashCode = 127 * member.getName().hashCode();
            Object value = invoke(member, this);
            int memberValueHashCode;
            if (value instanceof boolean[]) {
                memberValueHashCode = Arrays.hashCode((boolean[]) value);
            } else if (value instanceof short[]) {
                memberValueHashCode = Arrays.hashCode((short[]) value);
            } else if (value instanceof int[]) {
                memberValueHashCode = Arrays.hashCode((int[]) value);
            } else if (value instanceof long[]) {
                memberValueHashCode = Arrays.hashCode((long[]) value);
            } else if (value instanceof float[]) {
                memberValueHashCode = Arrays.hashCode((float[]) value);
            } else if (value instanceof double[]) {
                memberValueHashCode = Arrays.hashCode((double[]) value);
            } else if (value instanceof byte[]) {
                memberValueHashCode = Arrays.hashCode((byte[]) value);
            } else if (value instanceof char[]) {
                memberValueHashCode = Arrays.hashCode((char[]) value);
            } else if (value instanceof Object[]) {
                memberValueHashCode = Arrays.hashCode((Object[]) value);
            } else {
                memberValueHashCode = value.hashCode();
            }
            hashCode += memberNameHashCode ^ memberValueHashCode;
        }
        return hashCode;
    }

    private static Object invoke(Method method, Object instance) {
        try {
            if (!method.isAccessible()) method.setAccessible(true);
            return method.invoke(instance);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error checking value of member method " + method.getName() + " on " + method.getDeclaringClass(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error checking value of member method " + method.getName() + " on " + method.getDeclaringClass(), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Error checking value of member method " + method.getName() + " on " + method.getDeclaringClass(), e);
        }
    }

    private Method[] getMethods() {
        if (parameters == null) {
            parameters = annotationType().getDeclaredMethods();
        }
        return parameters;
    }
}
