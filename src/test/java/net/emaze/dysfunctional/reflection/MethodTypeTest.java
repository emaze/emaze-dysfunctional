package net.emaze.dysfunctional.reflection;

import java.lang.reflect.Method;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class MethodTypeTest {

    @Test
    public void canInvokeMethod() throws NoSuchMethodException {
        new MethodType(MethodTypeTest.class.getMethod("dummyMethod")).invoke(this);
    }

    @Test(expected = InvocationException.class)
    public void exceptionInMethodLeadsToInvocationException() throws NoSuchMethodException {
        new MethodType(MethodTypeTest.class.getMethod("throwingMethod")).invoke(this);
    }

    @Test
    public void equalsCanBeUsedWithMethod() throws NoSuchMethodException {
        MethodType m = new MethodType(MethodTypeTest.class.getMethod("dummyMethod"));
        Assert.assertTrue(m.equals(m.getWrappedMethod()));
    }
    
    @Test
    public void notEqualsToUnknownObjects() throws NoSuchMethodException {
        Method m = MethodTypeTest.class.getMethod("dummyMethod");
        Assert.assertFalse(new MethodType(m).equals(null));
    }

    @Test
    public void sameHashcodeAsMethod() throws NoSuchMethodException {
        Method m = MethodTypeTest.class.getMethod("dummyMethod");
        Assert.assertEquals(m.hashCode(), new MethodType(m).hashCode());
    }

    @Test
    public void toStringContainsDeclaredMethodName() throws NoSuchMethodException {
        Method m = MethodTypeTest.class.getMethod("dummyMethod");
        Assert.assertTrue(new MethodType(m).toString().contains("dummyMethod"));
    }

    @Test
    public void twoMethodsTypesRepresentingTheSameMethodAreEquals() throws NoSuchMethodException {
        Method m = MethodTypeTest.class.getMethod("dummyMethod");
        Assert.assertEquals(new MethodType(m), new MethodType(m));
    }

    public void dummyMethod() {
    }

    public void throwingMethod() {
        throw new IllegalStateException();
    }
}
