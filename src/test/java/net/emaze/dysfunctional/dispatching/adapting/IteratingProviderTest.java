package net.emaze.dysfunctional.dispatching.adapting;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.Iterations;
import java.util.function.Supplier;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IteratingProviderTest {

    @Test
    public void callingProviderYieldsIteratorElementsInOrder() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER);
        final Supplier<Maybe<O>> provider = new IteratingProvider<O>(iterator);

        final List<Maybe<O>> expected = Arrays.asList(Maybe.just(O.ONE), Maybe.just(O.ANOTHER), Maybe.<O>nothing());
        Assert.assertEquals(expected, Arrays.asList(provider.get(), provider.get(), provider.get()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullIteratorYieldsException() {
        new IteratingProvider<Integer>(null);
    }
}