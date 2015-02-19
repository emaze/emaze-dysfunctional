package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class BinaryComposerTest {

    @Test
    public void canComposeDelegateWithBinaryDelegate() {
        final BinaryComposer<O, O, O, O> composer = new BinaryComposer<O, O, O, O>(new Function<O, O>() {
            @Override
            public O apply(O o) {
                return new O(o.toString() + " is composed");
            }
        }, new BiFunction<O, O, O>() {
            @Override
            public O apply(O former, O latter) {
                return former;
            }
        });
        final O got = composer.apply(O.ONE, O.IGNORED);
        Assert.assertEquals("ONE is composed", got.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void composingWithNullUnaryDelegateYieldsException() {
        new BinaryComposer<O, O, O, O>(null, new BiFunction<O, O, O>() {
            @Override
            public O apply(O former, O latter) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void composingWithNullBinaryDelegateYieldsException() {
        new BinaryComposer<O, O, O, O>(UnaryOperator.identity(), null);
    }
}
