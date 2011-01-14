package net.emaze.dysfunctional.reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class RecurringAnnotationFetcherTest {

    @Retention(RetentionPolicy.RUNTIME)
    public @interface AnAnnotation {
        String value() default "default_value";
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface AnotherAnnotation {
    }

    @AnAnnotation
    @Retention(RetentionPolicy.RUNTIME)
    public @interface AnAnnotatedAnnotation {
    }

    public static class NonAnnotatedBean {
    }

    @AnAnnotation("non_default_value")
    public static class AnnotatedBean {
    }

    @AnAnnotatedAnnotation
    public static class MetaAnnotatedBean {
    }

    @Test
    public void fetchingFromNonAnnotatedClassYieldsNull() {
        RecurringAnnotationFetcher f = new RecurringAnnotationFetcher();
        AnAnnotation got = f.fetch(NonAnnotatedBean.class, AnAnnotation.class);
        Assert.assertNull(got);
    }

    @Test
    public void fetchingFromClassAnnotatedWithAnotherAnnotationYieldsNull() {
        RecurringAnnotationFetcher f = new RecurringAnnotationFetcher();
        AnotherAnnotation got = f.fetch(MetaAnnotatedBean.class, AnotherAnnotation.class);
        Assert.assertNull(got);
    }

    @Test
    public void canFetchFromAnnotatedBean() {
        RecurringAnnotationFetcher f = new RecurringAnnotationFetcher();
        AnAnnotation got = f.fetch(AnnotatedBean.class, AnAnnotation.class);
        Assert.assertNotNull(got);
    }

    @Test
    public void canFetchFromMetaAnnotatedBean() {
        RecurringAnnotationFetcher f = new RecurringAnnotationFetcher();
        AnAnnotation got = f.fetch(MetaAnnotatedBean.class, AnAnnotation.class);
        Assert.assertNotNull(got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fetchingFromNullClassYieldsException() {
        RecurringAnnotationFetcher f = new RecurringAnnotationFetcher();
        f.fetch(null, AnAnnotation.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fetchingNullAnnotationYieldsException() {
        RecurringAnnotationFetcher f = new RecurringAnnotationFetcher();
        f.fetch(AnnotatedBean.class, null);
    }

    @Test
    public void canFetchDefault() {
        RecurringAnnotationFetcher f = new RecurringAnnotationFetcher();
        AnAnnotation got = f.fetchOrDefault(NonAnnotatedBean.class, AnAnnotation.class);
        Assert.assertNotNull(got);
    }
    
    @Test
    public void canFetchNonDefaultValue() {
        RecurringAnnotationFetcher f = new RecurringAnnotationFetcher();
        AnAnnotation got = f.fetchOrDefault(AnnotatedBean.class, AnAnnotation.class);
        Assert.assertNotNull("non_default_value", got.value());
    }

    @Test
    public void canGetDefaultValueFromDefaultAnnotation(){
        AnAnnotation got = RecurringAnnotationFetcher.DefaultAnnotation.create(AnAnnotation.class);
        Assert.assertEquals("default_value", got.value());
    }
}
