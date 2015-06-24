package net.emaze.dysfunctional;

import junit.framework.Assert;
import java.util.function.Function;
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
    public void canUpcastUsingNarrow() {
        final B b = new B();
        final A a = Casts.narrow(b);
        Assert.assertNotNull(a);
    }

    @Test
    public void canDowncastUsingWiden() {
        final A a = new B();
        final B b = Casts.widen(a);
        Assert.assertNotNull(b);
    }

    @Test
    public void canCastUsingVary() {
        final B b = new B();
        final A a = Casts.vary(b);
        Assert.assertNotNull(a);
    }

    @Test
    public void canGetWidener() {
        final A a = new B();
        final Function<A, B> widener = Casts.widener();
        Assert.assertNotNull(widener.apply(a));
    }

    @Test
    public void canGetNarrower() {
        final B b = new B();
        final Function<B, A> narrower = Casts.narrower();
        final A got = narrower.apply(b);
        Assert.assertNotNull(got);
    }

    @Test
    public void canGetVariator() {
        final B b = new B();
        final Function<B, A> variator = Casts.variator();
        final A got = variator.apply(b);
        Assert.assertNotNull(got);
    }

    @Test
    public void facadeIsNotFinal() {
        new Casts() {
        };
    }
}
