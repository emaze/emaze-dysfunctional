package net.emaze.dysfunctional.windows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.Compositions;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.Consumers;
import java.util.function.Supplier;
import net.emaze.dysfunctional.Iterations;
import java.util.Optional;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class CenteredWindowIteratorTest {

    private static Supplier<List<Optional<O>>> LIST_FACTORY = Compositions.compose(new Vary<ArrayList<Optional<O>>, List<Optional<O>>>(), new ArrayListFactory<Optional<O>>());

    @Test(expected = IllegalArgumentException.class)
    public void creatingCenteredWindowIteratorWithNullIteratorYieldsException() {
        new CenteredWindowIterator<List<Optional<O>>, O>(null, 1, LIST_FACTORY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingCenteredWindowIteratorWithNonPositiveWindowSizeYieldsException() {
        final Iterator<O> iter = Iterations.iterator();
        new CenteredWindowIterator<List<Optional<O>>, O>(iter, 0, LIST_FACTORY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingCenteredWindowIteratorWithEvenWindowSizeYieldsException() {
        final Iterator<O> iter = Iterations.iterator();
        new CenteredWindowIterator<List<Optional<O>>, O>(iter, 4, LIST_FACTORY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingCenteredWindowIteratorWithNullProviderYieldsException() {
        final Iterator<O> iter = Iterations.iterator();
        new CenteredWindowIterator<List<Optional<O>>, O>(iter, 3, null);
    }

    @Test(expected = NoSuchElementException.class)
    public void consumingEmptyIteratorYieldsException() {
        final Iterator<O> iter = Iterations.iterator();
        new CenteredWindowIterator<List<Optional<O>>, O>(iter, 3, LIST_FACTORY).next();
    }

    @Test
    public void windowsAreInOrder() {
        final Iterator<O> iter = Iterations.iterator(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        final CenteredWindowIterator<List<Optional<O>>, O> win = new CenteredWindowIterator<List<Optional<O>>, O>(iter, 3, LIST_FACTORY);
        final List<List<Optional<O>>> got = Consumers.all(win);
        final List<List<Optional<O>>> expected = new ArrayList<List<Optional<O>>>();
        expected.add(Arrays.asList(Optional.<O>empty(), Optional.of(O.ONE), Optional.of(O.ANOTHER)));
        expected.add(Arrays.asList(Optional.of(O.ONE), Optional.of(O.ANOTHER), Optional.of(O.YET_ANOTHER)));
        expected.add(Arrays.asList(Optional.of(O.ANOTHER), Optional.of(O.YET_ANOTHER), Optional.<O>empty()));
        Assert.assertEquals(expected, got);
    }

    @Test
    public void singletonIteratorYieldOneElementInWindow() {
        final Iterator<O> iter = Iterations.iterator(O.ONE);
        final CenteredWindowIterator<List<Optional<O>>, O> win = new CenteredWindowIterator<List<Optional<O>>, O>(iter, 3, LIST_FACTORY);
        final List<List<Optional<O>>> got = Consumers.all(win);
        final List<List<Optional<O>>> expected = new ArrayList<List<Optional<O>>>();
        expected.add(Arrays.asList(Optional.<O>empty(), Optional.of(O.ONE), Optional.<O>empty()));
        Assert.assertEquals(expected, got);

    }
}
