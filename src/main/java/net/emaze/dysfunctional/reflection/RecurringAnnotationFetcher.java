package net.emaze.dysfunctional.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Fetches an annotation recursively from a class or from class annotations
 * ripped from jmanner, please keep in sync
 * @author rferranti
 */
public class RecurringAnnotationFetcher {

    /**
     * searches for annotations or metaannotations in a class.
     * @param <T> the annotation type
     * @param clazz the class where the annotation is looked up
     * @param annotationClass the annotation class we are looking for
     * @return found annotation or null
     */
    public <T extends Annotation> T fetch(Class<?> clazz, Class<T> annotationClass) {
        dbc.precondition(clazz != null, "clazz cannot be null while fetching annotation with RecurringAnnotationFetcher");
        dbc.precondition(annotationClass != null, "annotationClass cannot be null  while fetching annotation with RecurringAnnotationFetcher");
        for (Annotation annotation : clazz.getAnnotations()) {
            final Class<? extends Annotation> currentAnnotationClass = annotation.getClass();
            if (annotationClass.isAssignableFrom(currentAnnotationClass)) {
                return (T) annotation;
            }
        }
        for (Annotation annotation : clazz.getAnnotations()) {
            final Annotation a = fetchFromAnnotation(annotation, annotationClass);
            if (a != null) {
                return (T) a;
            }
        }
        return null;
    }

    /**
     * searches for annotations or metaannotations in a class.
     * @param <T> the annotation type
     * @param clazz  the class where the annotation is looked up
     * @param annotationClass the annotation class we are looking for
     * @return found annotation, or an annotation with default values set
     */
    public <T extends Annotation> T fetchOrDefault(Class<?> clazz, Class<T> annotationClass) {
        T value = fetch(clazz, annotationClass);
        if (value != null) {
            return value;
        }
        return DefaultAnnotation.create(annotationClass);
    }

    private <T extends Annotation> T fetchFromAnnotation(Annotation haystack, Class<T> needle) {
        for (Annotation annotation : haystack.annotationType().getAnnotations()) {
            if (needle.isAssignableFrom(annotation.annotationType())) {
                return (T) annotation;
            }
        }
        return null;
    }

    public static class DefaultAnnotation implements InvocationHandler {

        public static <T extends Annotation> T create(Class<T> clazz) {
            return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new DefaultAnnotation());
        }

        @Override
        public Object invoke(Object proxy, Method m, Object[] params) {
            return m.getDefaultValue();
        }
    }
}
