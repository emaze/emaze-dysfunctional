package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class WindowingTest {

    private final Supplier<List<O>> LIST_FACTORY = Compositions.compose(new Vary<ArrayList<O>, List<O>>(), new ArrayListFactory<O>());
    private final Supplier<List<Optional<O>>> LIST_MAYBE_FACTORY = Compositions.compose(new Vary<ArrayList<Optional<O>>, List<Optional<O>>>(), new ArrayListFactory<Optional<O>>());

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
    public void canCreateTrailsFromIterator() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertNotNull(Windowing.trails(3, iterator));
    }

    @Test
    public void canCreateTrailsFromIteratorUsingDelegate() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Windowing.trails(3, iterator, Function.identity());
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

    @Test
    public void canCreateTrailsFromIterable() {
        final Iterable<O> iterable = Iterations.iterable(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertNotNull(Windowing.trails(3, iterable));
    }

    @Test
    public void canCreateTrailsFromIterableUsingDelegate() {
        final Iterable<O> iterable = Iterations.iterable(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Windowing.trails(3, iterable, Function.identity());
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

    @Test(expected = IllegalArgumentException.class)
    public void creatingTrailsWithNullIterableYieldsException() {
        final Iterable<O> iterable = null;
        Windowing.trails(3, iterable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingTrailsUsingDelegateWithNullIterableYieldsException() {
        final Iterable<O> iterable = null;
        Windowing.trails(3, iterable, Function.identity());
    }

    @Test
    public void facadeIsNotFinal() {
        new Windowing() {
        };
    }
}
