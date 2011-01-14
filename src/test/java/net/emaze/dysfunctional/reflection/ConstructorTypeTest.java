package net.emaze.dysfunctional.reflection;

import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ConstructorTypeTest {

    @Test(expected=IllegalArgumentException.class)
    public void creatingConstructorTypeWithNullCtorYieldsExcpetion() {
        new ConstructorType(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void creatingNewInstanceWithNullInitArgsYieldsExcpetion() throws NoSuchMethodException {
        ConstructorType ct = new ConstructorType(ConstructorTypeTest.class.getConstructor());
        ct.newInstance((Object[])null);
    }

}