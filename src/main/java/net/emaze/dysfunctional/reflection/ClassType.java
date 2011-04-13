package net.emaze.dysfunctional.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * ripped from jmanner, please keep in sync
 * @author rferranti
 */
public class ClassType {

    private final Class<?> klass;

    public ClassType(Class<?> klass) {
        this.klass = klass;
    }

    public ClassType(String klassName) {
        try {
            this.klass = Class.forName(klassName);
        } catch (ClassNotFoundException ex) {
            throw new ReflectionException(ex);
        }
    }

    public MethodType getMethod(String methodName, Class<?>... paramTypes) {
        try {
            return new MethodType(klass.getMethod(methodName, paramTypes));
        } catch (NoSuchMethodException ex) {
            throw new ReflectionException(ex);
        }
    }

    public MethodType getMethodByName(String methodName) {
        dbc.precondition(methodName != null, "trying to fetch a method with a null methodName");
        final List<Method> matchingMethods = new ArrayList<Method>();
        for (Method m : klass.getMethods()) {
            if (m.getName().equals(methodName)) {
                matchingMethods.add(m);
            }
        }
        dbc.stateprecondition(!matchingMethods.isEmpty(), "method %s not found", methodName);
        dbc.stateprecondition(matchingMethods.size() == 1, "method is ambigouous (method params must be specified)");
        return new MethodType(matchingMethods.get(0));
    }

    public Class<?> getWrappedClass() {
        return klass;
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof ClassType) {
            final ClassType other = (ClassType) rhs;
            return this.klass.equals(other.klass);
        }
        if (rhs instanceof Class) {
            return this.klass.equals((Class<?>) rhs);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return klass.hashCode();
    }

    @Override
    public String toString() {
        return String.format("ClassType<%s>", klass.toString());
    }

    public ConstructorType getConstructor(Class<?>... paramTypes) {
        try {
            return new ConstructorType(this.klass.getConstructor(paramTypes));
        } catch (NoSuchMethodException ex) {
            throw new ReflectionException(ex);
        }
    }

    public ConstructorType[] getConstructors() {
        final Constructor<?>[] constructors = this.klass.getConstructors();
        final ConstructorType[] wrappers = new ConstructorType[constructors.length];
        for (int i = 0; i != constructors.length; ++i) {
            wrappers[i] = new ConstructorType(constructors[i]);
        }
        return wrappers;
    }

    public <T> T getAnnotationValue(Class<? extends Annotation> annotationClass, String annotationMethodName) {
        final Annotation annotation = klass.getAnnotation(annotationClass);
        dbc.precondition(annotation != null, "%s is not annotated with %s or %s has no RetentionPolicy.RUNTIME", klass, annotationClass, annotationClass);
        return (T) new ClassType(annotationClass).getMethod(annotationMethodName).invoke(annotation);

    }

    public <T> T getAnnotationValue(Class<? extends Annotation> annotationClass) {
        return this.<T>getAnnotationValue(annotationClass, "value");
    }

    public Object newInstance() {
        return getConstructor().newInstance();
    }

    public boolean isAnnotatedWith(String annotationClassName) {
        final ClassType annotationKlass = new ClassType(annotationClassName);
        final Annotation[] annotations = klass.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotationKlass.equals(annotation.annotationType())) {
                return true;
            }
        }
        return false;
    }
}
