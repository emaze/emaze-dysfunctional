package net.emaze.dysfunctional.reflection;

import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ConstructorTypeTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingConstructorTypeWithNullCtorYieldsExcpetion() {
        new ConstructorType(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingNewInstanceWithNullInitArgsYieldsExcpetion() throws NoSuchMethodException {
        final ConstructorType ct = new ConstructorType(ConstructorTypeTest.class.getConstructor());
        ct.newInstance((Object[]) null);
    }

    @Test
    public void canCreateAnObject() throws NoSuchMethodException {
        final ConstructorType ct = new ConstructorType(ConstructorTypeTest.class.getConstructor());
        ct.newInstance();
    }

    @Test(expected = InvocationException.class)
    public void exceptionsDuringConstructionAreTransformedToInvocationException() throws NoSuchMethodException {
        final ConstructorType ct = new ConstructorType(ClassWithThrowingConstructor.class.getConstructor());
        ct.newInstance();
    }

    public static class ClassWithThrowingConstructor {

        public ClassWithThrowingConstructor() {
            throw new RuntimeException();
        }
    }

}
