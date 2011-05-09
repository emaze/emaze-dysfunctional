package net.emaze.dysfunctional.casts;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class CastsTest {

    public static class A {
    }

    public static class B extends A {
    }

    @Test
    public void canUpcast() {
        A a = new B();
        B b = Casts.widen(a);
        Assert.assertNotNull(b);
    }

    @Test
    public void facadeIsNotFinal() {
        new Casts() {
        };
    }
}
