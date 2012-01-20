package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.casts.Narrow;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.Dispatching;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.windows.CenteredWindowIterator;
import net.emaze.dysfunctional.windows.PreciseWindowIterator;

/**
 *
 * @author rferranti
 */
public abstract class Windowing {

    public static <T> Iterator<List<T>> window(int windowSize, Iterator<T> iterator) {
        final Provider<List<T>> factory = Dispatching.compose(new Narrow<List<T>, ArrayList<T>>(), new ArrayListFactory<T>());
        return new PreciseWindowIterator<List<T>, T>(iterator, windowSize, factory);
    }

    public static <T> Iterator<List<T>> window(int windowSize, Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot create a window iterator from a null iterable");
        final Provider<List<T>> factory = Dispatching.compose(new Narrow<List<T>, ArrayList<T>>(), new ArrayListFactory<T>());
        return new PreciseWindowIterator<List<T>, T>(iterable.iterator(), windowSize, factory);
    }

    public static <W extends Collection<T>, T> Iterator<W> window(int windowSize, Iterator<T> iterator, Provider<W> provider) {
        return new PreciseWindowIterator<W, T>(iterator, windowSize, provider);
    }

    public static <W extends Collection<T>, T> Iterator<W> window(int windowSize, Iterable<T> iterable, Provider<W> provider) {
        dbc.precondition(iterable != null, "cannot create a window iterator from a null iterable");
        return new PreciseWindowIterator<W, T>(iterable.iterator(), windowSize, provider);
    }

    public static <T> Iterator<List<Maybe<T>>> centered(int windowSize, Iterator<T> iterator) {
        final Provider<List<Maybe<T>>> factory = Dispatching.compose(new Narrow<List<Maybe<T>>, ArrayList<Maybe<T>>>(), new ArrayListFactory<Maybe<T>>());
        return new CenteredWindowIterator<List<Maybe<T>>, T>(iterator, windowSize, factory);
    }

    public static <T> Iterator<List<Maybe<T>>> centered(int windowSize, Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot create a centered window iterator from a null iterable");
        final Provider<List<Maybe<T>>> factory = Dispatching.compose(new Narrow<List<Maybe<T>>, ArrayList<Maybe<T>>>(), new ArrayListFactory<Maybe<T>>());
        return new CenteredWindowIterator<List<Maybe<T>>, T>(iterable.iterator(), windowSize, factory);
    }

    public static <W extends Collection<Maybe<T>>, T> Iterator<W> centered(int windowSize, Iterator<T> iterator, Provider<W> provider) {
        return new CenteredWindowIterator<W, T>(iterator, windowSize, provider);
    }

    public static <W extends Collection<Maybe<T>>, T> Iterator<W> centered(int windowSize, Iterable<T> iterable, Provider<W> provider) {
        dbc.precondition(iterable != null, "cannot create a centered window iterator from a null iterable");
        return new CenteredWindowIterator<W, T>(iterable.iterator(), windowSize, provider);
    }
}
