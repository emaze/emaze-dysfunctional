package net.emaze.dysfunctional.dispatching.delegates;

import junit.framework.Assert;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;

public class BinaryComposerTest {

    @Test
    public void canComposeDelegateWithBinaryDelegate() {
        final BinaryComposer<O, O, O, O> composer = new BinaryComposer<O, O, O, O>(new Delegate<O, O>() {
            @Override
            public O perform(O o) {
                return new O(o.toString() + " is composed");
            }
        }, new BinaryDelegate<O, O, O>() {
            @Override
            public O perform(O former, O latter) {
                return former;
            }
        });
        final O got = composer.perform(O.ONE, O.IGNORED);
        Assert.assertEquals("ONE is composed", got.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void composingWithNullUnaryDelegateYieldsException() {
        new BinaryComposer<O, O, O, O>(null, new BinaryDelegate<O, O, O>() {
            @Override
            public O perform(O former, O latter) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void composingWithNullBinaryDelegateYieldsException() {
        new BinaryComposer<O, O, O, O>(new Identity<O>(), null);
    }
}
