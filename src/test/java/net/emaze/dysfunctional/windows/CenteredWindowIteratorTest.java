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
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class CenteredWindowIteratorTest {

    private static Provider<List<Maybe<O>>> LIST_FACTORY = Compositions.compose(new Vary<List<Maybe<O>>, ArrayList<Maybe<O>>>(), new ArrayListFactory<Maybe<O>>());

    @Test(expected = IllegalArgumentException.class)
    public void creatingCenteredWindowIteratorWithNullIteratorYieldsException() {
        new CenteredWindowIterator<List<Maybe<O>>, O>(null, 1, LIST_FACTORY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingCenteredWindowIteratorWithNonPositiveWindowSizeYieldsException() {
        final Iterator<O> iter = Iterations.iterator();
        new CenteredWindowIterator<List<Maybe<O>>, O>(iter, 0, LIST_FACTORY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingCenteredWindowIteratorWithEvenWindowSizeYieldsException() {
        final Iterator<O> iter = Iterations.iterator();
        new CenteredWindowIterator<List<Maybe<O>>, O>(iter, 4, LIST_FACTORY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingCenteredWindowIteratorWithNullProviderYieldsException() {
        final Iterator<O> iter = Iterations.iterator();
        new CenteredWindowIterator<List<Maybe<O>>, O>(iter, 3, null);
    }

    @Test(expected = NoSuchElementException.class)
    public void consumingEmptyIteratorYieldsException() {
        final Iterator<O> iter = Iterations.iterator();
        new CenteredWindowIterator<List<Maybe<O>>, O>(iter, 3, LIST_FACTORY).next();
    }

    @Test
    public void windowsAreInOrder() {
        final Iterator<O> iter = Iterations.iterator(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        final CenteredWindowIterator<List<Maybe<O>>, O> win = new CenteredWindowIterator<List<Maybe<O>>, O>(iter, 3, LIST_FACTORY);
        final List<List<Maybe<O>>> got = Consumers.all(win);
        final List<List<Maybe<O>>> expected = new ArrayList<List<Maybe<O>>>();
        expected.add(Arrays.asList(Maybe.<O>nothing(), Maybe.just(O.ONE), Maybe.just(O.ANOTHER)));
        expected.add(Arrays.asList(Maybe.just(O.ONE), Maybe.just(O.ANOTHER), Maybe.just(O.YET_ANOTHER)));
        expected.add(Arrays.asList(Maybe.just(O.ANOTHER), Maybe.just(O.YET_ANOTHER), Maybe.<O>nothing()));
        Assert.assertEquals(expected, got);
    }

    @Test
    public void singletonIteratorYieldOneElementInWindow() {
        final Iterator<O> iter = Iterations.iterator(O.ONE);
        final CenteredWindowIterator<List<Maybe<O>>, O> win = new CenteredWindowIterator<List<Maybe<O>>, O>(iter, 3, LIST_FACTORY);
        final List<List<Maybe<O>>> got = Consumers.all(win);
        final List<List<Maybe<O>>> expected = new ArrayList<List<Maybe<O>>>();
        expected.add(Arrays.asList(Maybe.<O>nothing(), Maybe.just(O.ONE), Maybe.<O>nothing()));
        Assert.assertEquals(expected, got);

    }
}
