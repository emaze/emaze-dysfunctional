package net.emaze.dysfunctional;

import net.emaze.dysfunctional.Windowing;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.casts.Narrow;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.Dispatching;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class WindowingTest {

    private final Provider<List<O>> LIST_FACTORY = Dispatching.compose(new Narrow<List<O>, ArrayList<O>>(), new ArrayListFactory<O>());
    private final Provider<List<Maybe<O>>> LIST_MAYBE_FACTORY = Dispatching.compose(new Narrow<List<Maybe<O>>, ArrayList<Maybe<O>>>(), new ArrayListFactory<Maybe<O>>());

    @Test
    public void canCreateWindowFromIterator() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertNotNull(Windowing.window(3, iterator));
    }

    @Test
    public void canCreateWindowFromIteratorUsingProvider() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertNotNull(Windowing.window(3, iterator, LIST_FACTORY));
    }

    @Test
    public void canCreateCenteredWindowFromIterator() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertNotNull(Windowing.centered(3, iterator));
    }

    @Test
    public void canCreateCenteredWindowFromIteratorUsingProvider() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Windowing.centered(3, iterator, LIST_MAYBE_FACTORY);
    }

    @Test
    public void canCreateWindowFromIterable() {
        final Iterable<O> iterable = Iterations.iterable(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertNotNull(Windowing.window(3, iterable));
    }

    @Test
    public void canCreateWindowFromIterableUsingProvider() {
        final Iterable<O> iterable = Iterations.iterable(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertNotNull(Windowing.window(3, iterable, LIST_FACTORY));
    }

    @Test
    public void canCreateCenteredWindowFromIterable() {
        final Iterable<O> iterable = Iterations.iterable(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertNotNull(Windowing.centered(3, iterable));
    }

    @Test
    public void canCreateCenteredWindowFromIterableUsingProvider() {
        final Iterable<O> iterable = Iterations.iterable(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Windowing.centered(3, iterable, LIST_MAYBE_FACTORY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWindowWithNullIterableYieldsException() {
        final Iterable<O> iterable = null;
        Windowing.window(3, iterable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWindowUsingProviderWithNullIterableYieldsException() {
        final Iterable<O> iterable = null;
        Windowing.window(3, iterable, LIST_FACTORY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingCenteredWindowWithNullIterableYieldsException() {
        final Iterable<O> iterable = null;
        Windowing.centered(3, iterable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingCenteredWindowUsingProviderWithNullIterableYieldsException() {
        final Iterable<O> iterable = null;
        Windowing.centered(3, iterable, LIST_MAYBE_FACTORY);
    }

    @Test
    public void facadeIsNotFinal() {
        new Windowing() {
        };
    }
}
