package net.emaze.dysfunctional.dispatching.delegates;

import junit.framework.Assert;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;

public class TernaryComposerTest {

    @Test
    public void canComposeDelegateWithTernaryDelegate() {
        final TernaryComposer<O, O, O, O, O> composer = new TernaryComposer<O, O, O, O, O>(new Delegate<O, O>() {
            @Override
            public O perform(O o) {
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
        new TernaryComposer<O, O, O, O, O>(new Identity<O>(), null);
    }
}
