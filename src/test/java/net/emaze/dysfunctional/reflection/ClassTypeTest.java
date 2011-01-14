package net.emaze.dysfunctional.reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import net.emaze.dysfunctional.reflection.ClassTypeTest.AnAnnotation;
import net.emaze.dysfunctional.reflection.ClassTypeTest.AnAnnotationWithoutDefaultValue;
import net.emaze.dysfunctional.reflection.ClassTypeTest.AnAnnotationWithoutRuntimeRetention;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
@AnAnnotation
@AnAnnotationWithoutDefaultValue("a value")
@AnAnnotationWithoutRuntimeRetention("a value")
public class ClassTypeTest {

    @Test
    public void canFetchDefaultValue() {
        Assert.assertEquals("anAnnotationValue", new ClassType(ClassTypeTest.class).getAnnotationValue(AnAnnotation.class));
    }

    @Test
    public void canFetchSimpleAnnotation() {
        Assert.assertEquals("a value", new ClassType(ClassTypeTest.class).getAnnotationValue(AnAnnotationWithoutDefaultValue.class));
    }

    @Test
    public void equalsCanBeUsedWithClass() throws NoSuchMethodException {
        Class<?> c = ClassTypeTest.class;
        Assert.assertTrue(new ClassType(c).equals(c));
    }

    @Test
    public void twoClassTypesWithSameClassAreEquals() throws NoSuchMethodException {
        Class<?> c = ClassTypeTest.class;
        Assert.assertEquals(new ClassType(c), new ClassType(c));
    }

    @Test
    public void ClassTypeIsNotEqualToUnknownObjects() throws NoSuchMethodException {
        Class<?> c = ClassTypeTest.class;
        Assert.assertFalse(new ClassType(c).equals(null));
    }

    @Test
    public void sameHashcodeAsClass() throws NoSuchMethodException {
        Class<?> c = ClassTypeTest.class;
        Assert.assertEquals(c.hashCode(), new ClassType(c).hashCode());
    }

    @Test
    public void toStringContainsClassName() throws NoSuchMethodException {
        Class<?> c = ClassTypeTest.class;
        Assert.assertTrue(new ClassType(c).toString().contains(c.getSimpleName()));
    }

    @Test(expected = ReflectionException.class)
    public void creatingClassTypeWithUnexistentClassLeadsToException() throws NoSuchMethodException {
        new ClassType("IDoNotExist");
    }

    @Test(expected = ReflectionException.class)
    public void lookingForUnexistentMethodLeadsToException() throws NoSuchMethodException {
        new ClassType(ClassTypeTest.class).getMethod("iDoNotExist");
    }

    @Test(expected = ReflectionException.class)
    public void lookingForUnexistentConstructorLeadsToException() throws NoSuchMethodException {
        new ClassType(ClassTypeTest.class).getConstructor(String.class);
    }

    @Test
    public void canCreateFromClassName() throws NoSuchMethodException {
        ClassType k = new ClassType(ClassTypeTest.class.getName());
        Assert.assertEquals(ClassTypeTest.class, k.getWrappedClass());
    }

    @Test
    public void canAskIfClassIsAnnotated() {
        boolean a = new ClassType(ClassTypeTest.class).isAnnotatedWith("net.emaze.dysfunctional.reflection.ClassTypeTest$AnAnnotation");
        Assert.assertTrue(a);
    }

    @Test
    public void askingIfAnnotatedForNonAnnotatedClassYieldsFalse() {
        boolean a = new ClassType(ClassTypeTest.class).isAnnotatedWith("net.emaze.dysfunctional.reflection.ClassTypeTest$AnUnusedAnnotation");
        Assert.assertFalse(a);
    }

    @Test
    public void canCreateNewInstance(){
        Assert.assertNotNull(new ClassType(ClassTypeTest.class).newInstance());
    }

    @Retention(RetentionPolicy.RUNTIME)
    public static @interface AnAnnotation {

        String value() default "anAnnotationValue";
    }

    @Retention(RetentionPolicy.RUNTIME)
    public static @interface AnUnusedAnnotation {

        String value() default "anAnnotationValue";
    }

    @Retention(RetentionPolicy.RUNTIME)
    public static @interface AnAnnotationWithoutDefaultValue {

        String value();
    }

    public static @interface AnAnnotationWithoutRuntimeRetention {

        String value();
    }

    @Test(expected = IllegalArgumentException.class)
    public void fetchingAnAnnotationWithoutRententionPolicyRuntimeLeadsToIllegalArgumentException() {
        new ClassType(ClassTypeTest.class).getAnnotationValue(AnAnnotationWithoutRuntimeRetention.class);
    }
}
