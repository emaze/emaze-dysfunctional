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

}