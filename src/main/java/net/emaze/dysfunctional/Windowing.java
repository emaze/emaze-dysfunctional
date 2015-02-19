package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.windows.CenteredWindowIterator;
import net.emaze.dysfunctional.windows.PreciseWindowIterator;
import net.emaze.dysfunctional.windows.TrailsIterator;

/**
 * window, centered.
 *
 * @author rferranti
 */
public abstract class Windowing {

    /**
     * Adapts an iterator to an iterator showing a sliding window of the
     * contained elements. e.g: iterator: [1,2,3] windowSize: 2 -> [[1,2],
     * [2,3]]
     *
     * @param <T> the iterator element type
     * @param windowSize the window size
     * @param iterator the iterator to be adapted
     * @return the window iterator
     */
    public static <T> Iterator<List<T>> window(int windowSize, Iterator<T> iterator) {
        final Provider<List<T>> factory = Compositions.compose(new Vary<ArrayList<T>, List<T>>(), new ArrayListFactory<T>());
        return new PreciseWindowIterator<List<T>, T>(iterator, windowSize, factory);
    }

    /**
     * Adapts an iterable to an iterator showing a sliding window of the
     * contained elements. e.g: iterable: [1,2,3] windowSize: 2 -> [[1,2],
     * [2,3]]
     *
     * @param <T> the iterator element type
     * @param windowSize the window size
     * @param iterable the iterable to be adapted
     * @return the window iterator
     */
    public static <T> Iterator<List<T>> window(int windowSize, Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot create a window iterator from a null iterable");
        final Provider<List<T>> factory = Compositions.compose(new Vary<ArrayList<T>, List<T>>(), new ArrayListFactory<T>());
        return new PreciseWindowIterator<List<T>, T>(iterable.iterator(), windowSize, factory);
    }

    /**
     * Adapts an iterator to an iterator showing a sliding window of the
     * contained elements. e.g: iterator: [1,2,3] windowSize: 2 -> [[1,2],
     * [2,3]]
     *
     * @param <W> the window type
     * @param <T> the iterator element type
     * @param windowSize the window size
     * @param iterator the iterable to be adapted
     * @param provider the provider providing the window collection
     * @return the window iterator
     */
    public static <W extends Collection<T>, T> Iterator<W> window(int windowSize, Iterator<T> iterator, Provider<W> provider) {
        return new PreciseWindowIterator<W, T>(iterator, windowSize, provider);
    }

    /**
     * Adapts an iterable to an iterator showing a sliding window of the
     * contained elements. e.g: iterable: [1,2,3] windowSize: 2 -> [[1,2],
     * [2,3]]
     *
     * @param <W> the window type
     * @param <T> the iterator element type
     * @param windowSize the window size
     * @param iterable the iterable to be adapted
     * @param provider the provider providing the window collection
     * @return the window iterator
     */
    public static <W extends Collection<T>, T> Iterator<W> window(int windowSize, Iterable<T> iterable, Provider<W> provider) {
        dbc.precondition(iterable != null, "cannot create a window iterator from a null iterable");
        return new PreciseWindowIterator<W, T>(iterable.iterator(), windowSize, provider);
    }

    /**
     * Adapts an iterator to an iterator showing a sliding centered window of
     * the contained elements. e.g: iterator: [1,2,3] windowSize: 3 ->
     * [[nothing, just(1), just(2)], [just(1), just(2), just(3)], [just(2),
     * just(3), nothing]]
     *
     * @param <T> the iterator element type
     * @param windowSize the window size (must be an odd positive integer)
     * @param iterator the iterator to be adapted
     * @return the window iterator
     */
    public static <T> Iterator<List<Maybe<T>>> centered(int windowSize, Iterator<T> iterator) {
        final Provider<List<Maybe<T>>> factory = Compositions.compose(new Vary<ArrayList<Maybe<T>>, List<Maybe<T>>>(), new ArrayListFactory<Maybe<T>>());
        return new CenteredWindowIterator<List<Maybe<T>>, T>(iterator, windowSize, factory);
    }

    /**
     * Adapts an iterable to an iterator showing a sliding centered window of
     * the contained elements. e.g: iterable: [1,2,3] windowSize: 3 ->
     * [[nothing, just(1), just(2)], [just(1), just(2), just(3)], [just(2),
     * just(3), nothing]]
     *
     * @param <T> the iterator element type
     * @param windowSize the window size (must be an odd positive integer)
     * @param iterable the iterable to be adapted
     * @return the window iterator
     */
    public static <T> Iterator<List<Maybe<T>>> centered(int windowSize, Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot create a centered window iterator from a null iterable");
        final Provider<List<Maybe<T>>> factory = Compositions.compose(new Vary<ArrayList<Maybe<T>>, List<Maybe<T>>>(), new ArrayListFactory<Maybe<T>>());
        return new CenteredWindowIterator<List<Maybe<T>>, T>(iterable.iterator(), windowSize, factory);
    }

    /**
     * Adapts an iterator to an iterator showing a sliding centered window of
     * the contained elements. e.g: iterator: [1,2,3] windowSize: 3 ->
     * [[nothing, just(1), just(2)], [just(1), just(2), just(3)], [just(2),
     * just(3), nothing]]
     *
     * @param <W> the window type
     * @param <T> the iterator element type
     * @param windowSize the window size (must be an odd positive integer)
     * @param iterator the iterator to be adapted
     * @param provider the provider providing the window collection
     * @return the window iterator
     */
    public static <W extends Collection<Maybe<T>>, T> Iterator<W> centered(int windowSize, Iterator<T> iterator, Provider<W> provider) {
        return new CenteredWindowIterator<W, T>(iterator, windowSize, provider);
    }

    /**
     * Adapts an iterable to an iterator showing a sliding centered window of
     * the contained elements. e.g: iterable: [1,2,3] windowSize: 3 ->
     * [[nothing, just(1), just(2)], [just(1), just(2), just(3)], [just(2),
     * just(3), nothing]]
     *
     * @param <W> the window type
     * @param <T> the iterator element type
     * @param windowSize the window size (must be an odd positive integer)
     * @param iterable the iterable to be adapted
     * @param provider the provider providing the window collection
     * @return the window iterator
     */
    public static <W extends Collection<Maybe<T>>, T> Iterator<W> centered(int windowSize, Iterable<T> iterable, Provider<W> provider) {
        dbc.precondition(iterable != null, "cannot create a centered window iterator from a null iterable");
        return new CenteredWindowIterator<W, T>(iterable.iterator(), windowSize, provider);
    }

    /**
     * Adapts an iterator to an iterator showing predecessors of the contained
     * elements. This iterator always yields an alias to the same queue, beware
     * of aliasing problems. e.g:
     * <code>
     * iterator: [1,2,3,4], trailSize: 3 ->
     * [[Nothing, Nothing, Just 1],[Nothing Just 1, Just 2], [Just 1, Just 2, Just 3], [Just 2, Just 3, Just 4]]
     * </code>
     *
     * @param <T> the adapted iterator element type
     * @param trailSize the trail size
     * @param iterator the iterator to be adapted
     * @return
     */
    public static <T> Iterator<Queue<Maybe<T>>> trails(int trailSize, Iterator<T> iterator) {
        return new TrailsIterator<>(iterator, trailSize, new Identity<>());
    }

    /**
     * Adapts an iterator to an iterator showing predecessors of the contained
     * elements. Copy semantics of the internal queue yielded is controlled by
     * the copy delegate. e.g:
     * <code>
     * iterator: [1,2,3,4], trailSize: 3 ->
     * [[Nothing, Nothing, Just 1],[Nothing Just 1, Just 2], [Just 1, Just 2, Just 3], [Just 2, Just 3, Just 4]]
     * </code>
     *
     * @param <T> the adapted iterator element type
     * @param <W> the window type
     * @param trailSize the trail size
     * @param iterator the iterator to be adapted
     * @param copy copy semantics for the internal queue
     * @return the adapted iterator
     */
    public static <T, W extends Collection<?>> Iterator<W> trails(int trailSize, Iterator<T> iterator, Function<Queue<Maybe<T>>, W> copy) {
        return new TrailsIterator<>(iterator, trailSize, copy);
    }

    /**
     * Adapts an iterator to an iterator showing predecessors of the contained
     * elements. This iterator always yields an alias to the same queue, beware
     * of aliasing problems. e.g:
     * <code>
     * iterable: [1,2,3,4], trailSize: 3 ->
     * [[Nothing, Nothing, Just 1],[Nothing Just 1, Just 2], [Just 1, Just 2, Just 3], [Just 2, Just 3, Just 4]]
     * </code>
     *
     * @param <T> the adapted iterator element type
     * @param trailSize the trail size
     * @param iterable the iterable to be adapted
     * @return the adapted iterator
     */
    public static <T> Iterator<Queue<Maybe<T>>> trails(int trailSize, Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot create a trails iterator from a null iterable");
        return new TrailsIterator<>(iterable.iterator(), trailSize, new Identity<>());
    }

    /**
     * Adapts an iterator to an iterator showing predecessors of the contained
     * elements. Copy semantics of the internal queue yielded is controlled by
     * the copy delegate. e.g:
     * <code>
     * iterable: [1,2,3,4], trailSize: 3 ->
     * [[Nothing, Nothing, Just 1],[Nothing Just 1, Just 2], [Just 1, Just 2, Just 3], [Just 2, Just 3, Just 4]]
     * </code>
     *
     * @param <T> the adapted iterator element type
     * @param <W> the window type
     * @param trailSize the trail size
     * @param iterable the iterable to be adapted
     * @param copy copy semantics for the internal queue
     * @return the adapted iterator
     */
    public static <T, W extends Collection<?>> Iterator<W> trails(int trailSize, Iterable<T> iterable, Function<Queue<Maybe<T>>, W> copy) {
        dbc.precondition(iterable != null, "cannot create a trails iterator from a null iterable");
        return new TrailsIterator<>(iterable.iterator(), trailSize, copy);
    }
}
