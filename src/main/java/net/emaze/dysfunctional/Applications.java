package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.consumers.ConsumeIntoCollection;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.Tapper;
import net.emaze.dysfunctional.dispatching.actions.Action;
import java.util.function.Function;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.TransformingIterator;

/**
 * transform, tap, each, map.
 *
 * @author rferranti
 */
public abstract class Applications {

    /**
     * Creates an iterator yielding the result of the transformation applied by
     * the delegate on the elements of the source iterable. This transformation
     * is evaluated lazily when the resulting iterator is consumed. E.g:
     * <code>
     * transform([1,2,3], toStringTransformer) -> ["1", "2", "3"]
     * </code>
     *
     * @param <R> the result iterator element type parameter
     * @param <E> the input iterable element type parameter
     * @param iterable the iterable where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return the transformed iterator
     */
    public static <R, E> Iterator<R> transform(Iterable<E> iterable, Function<E, R> delegate) {
        dbc.precondition(iterable != null, "cannot call transform with a null iterable");
        return new TransformingIterator<>(iterable.iterator(), delegate);
    }

    /**
     * Creates an iterator yielding the result of the transformation applied by
     * the delegate on the elements of the source iterator. This transformation
     * is evaluated lazily when the resulting iterator is consumed. E.g:
     * <code>
     * transform([1,2,3], toStringTransformer) -> ["1", "2", "3"]
     * </code>
     *
     * @param <R> the result iterator element type parameter
     * @param <E> the input iterator element type parameter
     * @param iterator the iterator where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return the transformed iterator
     */
    public static <R, E> Iterator<R> transform(Iterator<E> iterator, Function<E, R> delegate) {
        return new TransformingIterator<>(iterator, delegate);
    }

    /**
     * Creates an iterator yielding the result of the transformation applied by
     * the delegate on the elements of the source array. This transformation is
     * evaluated lazily when the resulting iterator is consumed. E.g:
     * <code>
     * transform([1,2,3], toStringTransformer) -> ["1", "2", "3"]
     * </code>
     *
     * @param <R> the result iterator element type parameter
     * @param <E> the input array element type parameter
     * @param array the array where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return the transformed iterator
     */
    public static <R, E> Iterator<R> transform(E[] array, Function<E, R> delegate) {
        return new TransformingIterator<>(new ArrayIterator<E>(array), delegate);
    }

    /**
     * Creates an iterator yielding each element of the source iterator after
     * applying the passed action. This application is evaluated lazily when the
     * resulting iterator is consumed. E.g:
     * <code>
     * tap([1,2,3], sideEffect) -> [1, 2, 3]
     * </code>
     *
     * @param <E> the element type parameter
     * @param iterator the iterator
     * @param action the action to be applied
     * @return an iterator that when consumed applies a side effect to every
     * element
     */
    public static <E> Iterator<E> tap(Iterator<E> iterator, Action<E> action) {
        return new TransformingIterator<E, E>(iterator, new Tapper<E>(action));
    }

    /**
     * Creates an iterator yielding each element of the source iterable after
     * applying the passed action. This application is evaluated lazily when the
     * resulting iterator is consumed. E.g:
     * <code>
     * tap([1,2,3], sideEffect) -> [1, 2, 3]
     * </code>
     *
     * @param <E> the element type parameter
     * @param iterable the iterable
     * @param action the action to be applied
     * @return an iterator that when consumed applies a side effect to every
     * element
     */
    public static <E> Iterator<E> tap(Iterable<E> iterable, Action<E> action) {
        dbc.precondition(iterable != null, "cannot tap on a null iterable");
        return new TransformingIterator<E, E>(iterable.iterator(), new Tapper<E>(action));
    }

    /**
     * Creates an iterator yielding each element of the source array after
     * applying the passed action. This application is evaluated lazily when the
     * resulting iterator is consumed. E.g:
     * <code>
     * tap([1,2,3], sideEffect) -> [1, 2, 3]
     * </code>
     *
     * @param <E> the element type parameter
     * @param array the array
     * @param action the action to be applied
     * @return an iterator that when consumed applies a side effect to every
     * element
     */
    public static <E> Iterator<E> tap(E[] array, Action<E> action) {
        return new TransformingIterator<E, E>(new ArrayIterator<E>(array), new Tapper<E>(action));
    }

    /**
     * Applies a side effect on each elements of the source iterable. This
     * application is evaluated eagerly.
     * <code>
     * > each([1,2], println) -> void
     *   1
     *   2
     * >
     * </code>
     *
     * @param <E> the iterable element type parameter
     * @param iterable the iterable where elements are fetched from
     * @param action the action applied to every element fetched from the
     * iterable
     */
    public static <E> void each(Iterable<E> iterable, Action<E> action) {
        dbc.precondition(iterable != null, "cannot call each with a null iterable");
        dbc.precondition(action != null, "cannot call each with a null action");
        for (E element : iterable) {
            action.perform(element);
        }
    }

    /**
     * Applies a side effect on each elements of the source iterator. This
     * application is evaluated eagerly.
     * <code>
     * > each([1,2], println) -> void
     *   1
     *   2
     * >
     * </code>
     *
     * @param <E> the iterator element type parameter
     * @param iterator the iterator where elements are fetched from
     * @param action the action applied to every element fetched from the
     * iterator
     */
    public static <E> void each(Iterator<E> iterator, Action<E> action) {
        dbc.precondition(iterator != null, "cannot call each with a null iterator");
        dbc.precondition(action != null, "cannot call each with a null action");
        while (iterator.hasNext()) {
            action.perform(iterator.next());
        }
    }

    /**
     * Applies a side effect on each elements of the source array. This
     * application is evaluated eagerly.
     * <code>
     * > each([1,2], println) -> void
     *   1
     *   2
     * >
     * </code>
     *
     * @param <E> the array element type parameter
     * @param array the array where elements are fetched from
     * @param action the action applied to every element fetched from the array
     */
    public static <E> void each(E[] array, Action<E> action) {
        dbc.precondition(array != null, "cannot call each with a null array");
        dbc.precondition(action != null, "cannot call each with a null action");
        for (int i = 0; i != array.length; ++i) {
            action.perform(array[i]);
        }
    }

    /**
     * Creates a List yielding the result of the transformation applied by the
     * delegate on the elements of the source iterable. This transformation is
     * evaluated eagerly. E.g:
     * <code>
     * map([1,2,3], toStringTransformer) -> ["1", "2", "3"]
     * </code>
     *
     * @param <R> the result iterator element type parameter
     * @param <E> the input array element type parameter
     * @param iterable the iterable where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return a List containing the transformed elements
     */
    public static <R, E> List<R> map(Iterable<E> iterable, Function<E, R> delegate) {
        dbc.precondition(iterable != null, "cannot map from a null iterable");
        final TransformingIterator<E, R> transformed = new TransformingIterator<>(iterable.iterator(), delegate);
        final Function<Iterator<R>, ArrayList<R>> consumer = new ConsumeIntoCollection<>(new ArrayListFactory<R>());
        return consumer.apply(transformed);
    }

    /**
     * Creates a List yielding the result of the transformation applied by the
     * delegate on the elements of the source iterator. This transformation is
     * evaluated eagerly. E.g:
     * <code>
     * map([1,2,3], toStringTransformer) -> ["1", "2", "3"]
     * </code>
     *
     * @param <R> the result iterator element type parameter
     * @param <E> the input array element type parameter
     * @param iterator the iterator where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return a List containing the transformed elements
     */
    public static <R, E> List<R> map(Iterator<E> iterator, Function<E, R> delegate) {
        final TransformingIterator<E, R> transformed = new TransformingIterator<>(iterator, delegate);
        final Function<Iterator<R>, ArrayList<R>> consumer = new ConsumeIntoCollection<>(new ArrayListFactory<R>());
        return consumer.apply(transformed);
    }

    /**
     * Creates a List yielding the result of the transformation applied by the
     * delegate on the elements of the source array. This transformation is
     * evaluated eagerly. E.g:
     * <code>
     * map([1,2,3], toStringTransformer) -> ["1", "2", "3"]
     * </code>
     *
     * @param <R> the result iterator element type parameter
     * @param <E> the input array element type parameter
     * @param array the array where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return a List containing the transformed elements
     */
    public static <R, E> List<R> map(E[] array, Function<E, R> delegate) {
        final TransformingIterator<E, R> transformed = new TransformingIterator<>(new ArrayIterator<E>(array), delegate);
        final Function<Iterator<R>, ArrayList<R>> consumer = new ConsumeIntoCollection<>(new ArrayListFactory<R>());
        return consumer.apply(transformed);
    }
}
