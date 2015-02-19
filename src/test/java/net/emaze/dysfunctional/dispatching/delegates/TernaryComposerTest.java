package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class TernaryComposerTest {

    @Test
    public void canComposeDelegateWithTernaryDelegate() {
        final TernaryComposer<O, O, O, O, O> composer = new TernaryComposer<O, O, O, O, O>(new Function<O, O>() {
            @Override
            public O apply(O o) {
                return new O(o.toString() + " is composed");
            }
        }, new TernaryDelegate<O, O, O, O>() {
            @Override
            public O perform(O first, O second, O third) {
                return first;
            }
        });
        final O got = composer.perform(O.ONE, O.IGNORED, O.IGNORED);
        Assert.assertEquals("ONE is composed", got.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void composingWithNullUnaryDelegateYieldsException() {
        new TernaryComposer<O, O, O, O, O>(null, new TernaryDelegate<O, O, O, O>() {
            @Override
            public O perform(O first, O second, O third) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void composingWithNullTernaryDelegateYieldsException() {
        new TernaryComposer<O, O, O, O, O>(UnaryOperator.identity(), null);
    }
}
