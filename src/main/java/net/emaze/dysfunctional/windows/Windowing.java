package net.emaze.dysfunctional.windows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.casts.Narrow;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.dispatching.Dispatching;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author rferranti
 */
public class Windowing {

    public <T> Iterator<List<T>> window(int windowSize, Iterator<T> iterator) {
        final Provider<List<T>> factory = Dispatching.compose(new Narrow<List<T>, ArrayList<T>>(), new ArrayListFactory<T>());
        return window(windowSize, iterator, factory);
    }

    public <T> Iterator<List<T>> window(int windowSize, Iterable<T> iterable) {
        final Provider<List<T>> factory = Dispatching.compose(new Narrow<List<T>, ArrayList<T>>(), new ArrayListFactory<T>());
        return window(windowSize, iterable, factory);
    }

    public <W extends Collection<T>, T> Iterator<W> window(int windowSize, Iterator<T> iterator, Provider<W> provider) {
        return new PreciseWindowIterator<W, T>(iterator, windowSize, provider);
    }

    public <W extends Collection<T>, T> Iterator<W> window(int windowSize, Iterable<T> iterable, Provider<W> provider) {
        return new PreciseWindowIterator<W, T>(iterable.iterator(), windowSize, provider);
    }

    public <T> Iterator<List<Maybe<T>>> centered(int windowSize, Iterator<T> iterator) {
        final Provider<List<Maybe<T>>> factory = Dispatching.compose(new Narrow<List<Maybe<T>>, ArrayList<Maybe<T>>>(), new ArrayListFactory<Maybe<T>>());
        return centered(windowSize, iterator, factory);
    }

    public <T> Iterator<List<Maybe<T>>> centered(int windowSize, Iterable<T> iterable) {
        final Provider<List<Maybe<T>>> factory = Dispatching.compose(new Narrow<List<Maybe<T>>, ArrayList<Maybe<T>>>(), new ArrayListFactory<Maybe<T>>());
        return centered(windowSize, iterable, factory);
    }

    public <W extends Collection<Maybe<T>>, T> Iterator<W> centered(int windowSize, Iterator<T> iterator, Provider<W> provider) {
        return new CenteredWindowIterator<W, T>(iterator, windowSize, provider);
    }

    public <W extends Collection<Maybe<T>>, T> Iterator<W> centered(int windowSize, Iterable<T> iterable, Provider<W> provider) {
        return new CenteredWindowIterator<W, T>(iterable.iterator(), windowSize, provider);
    }
}
