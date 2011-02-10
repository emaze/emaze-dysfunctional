package net.emaze.dysfunctional.consumers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.adapting.ArrayIterator;
import net.emaze.dysfunctional.collections.CollectionProvider;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Provider;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.options.MaybeIterator;

/**
 *
 * @author rferranti
 */
public abstract class Consumers {

    /**
     * yields the first element in the iterator
     * @param <E>
     * @param iterator
     * @throws IllegalArgumentException if the iterator is empty
     * @return the first element
     */
    public static <E> E first(Iterator<E> iterator) {
        return new GiveUpConsumer<E>().consume(iterator);
    }

    /**
     * yields the first element of the iterable's iterator
     * @param <E>
     * @param iterable
     * @throws IllegalArgumentException if the iterator is empty
     * @return the first element
     */
    public static <E> E first(Iterable<E> iterable) {
        return first(iterable.iterator());
    }

    /**
     * yields the first element of the array
     * @param <E>
     * @param array
     * @throws IllegalArgumentException if the iterator is empty
     * @return the first element
     */
    public static <E> E first(E[] array) {
        return first(new ArrayIterator<E>(array));
    }

    /**
     * yields the only element
     * @param <E> the element type
     * @param iterator
     * @throws IllegalArgumentException if the iterator is empty
     * @throws IllegalStateException if the iterator contains more than one element
     * @return the only element
     */
    public static <E> E one(Iterator<E> iterator) {
        final E element = first(iterator);
        dbc.stateprecondition(!iterator.hasNext(), "Expected only one value in iterator");
        return element;
    }

    /**
     * yields the only element
     * @param <E> the element type
     * @param iterable
     * @throws IllegalArgumentException if the iterator is empty
     * @throws IllegalStateException if the iterator contains more than one element
     * @return the only element
     */
    public static <E> E one(Iterable<E> iterable) {
        return one(iterable.iterator());
    }

    /**
     * yields the only element
     * @param <E> the element type
     * @param array
     * @throws IllegalArgumentException if the iterator is empty
     * @throws IllegalStateException if the iterator contains more than one element
     * @return the only element
     */
    public static <E> E one(E[] array) {
        return one(new ArrayIterator<E>(array));
    }

    /**
     * yields all elements of the iterator (in the provided collection).
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param iterator the iterator that will be consumed
     * @param collection the collection where the iterator is consumed
     * @return the collection filled with iterator values
     */
    public static <R extends Collection<E>, E> R all(Iterator<E> iterator, R collection) {
        return new EagerConsumer<R, E>(new CollectionProvider<R, E>(collection)).consume(iterator);
    }

    /**
     * yields all elements of the iterator (in the provided collection).
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param iterable the iterable that will be consumed
     * @param collection the collection where the iterator is consumed
     * @return the collection filled with iterator values
     */
    public static <R extends Collection<E>, E> R all(Iterable<E> iterable, R collection) {
        return Consumers.all(iterable.iterator(), collection);
    }

    /**
     * yields all elements of the array (in the provided collection).
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param array the array that will be consumed
     * @param collection the collection where the iterator is consumed
     * @return the collection filled with iterator values
     */
    public static <R extends Collection<E>, E> R all(E[] array, R collection) {
        return Consumers.all(new ArrayIterator<E>(array), collection);
    }

    /**
     * yields all elements of the iterator (in a collection created by the factory).
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param iterator the iterator that will be consumed
     * @param provider the factory used to provide the returned collection
     * @return a collection filled with iterator values
     */
    public static <E, R extends Collection<E>> R all(Iterator<E> iterator, Provider<R> provider) {
        return new EagerConsumer<R, E>(provider).consume(iterator);
    }

    /**
     * yields all elements of the iterator (in a collection created by the factory).
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param iterable the iterable that will be consumed
     * @param provider the factory used to provide the returned collection
     * @return a collection filled with iterator values
     */
    public static <E, R extends Collection<E>> R all(Iterable<E> iterable, Provider<R> provider) {
        return Consumers.all(iterable.iterator(), provider);
    }

    /**
     * yields all elements of the iterator (in a collection created by the factory).
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param array the array that will be consumed
     * @param provider the factory used to provide the returned collection
     * @return a collection filled with iterator values
     */
    public static <R extends Collection<E>, E> R all(E[] array, Provider<R> provider) {
        return Consumers.all(new ArrayIterator<E>(array), provider);
    }

    /**
     * yields all elements of the iterator (in a list).
     * @param <E> the iterator element type
     * @param iterator the iterator that will be consumed
     * @return a list filled with iterator values
     */
    public static <E> List<E> all(Iterator<E> iterator) {
        return Consumers.all(iterator, new ArrayList<E>());
    }

    /**
     * yields all elements of the iterable's iterator (in a list).
     * @param <E>
     * @param iterable
     * @return
     */
    public static <E> List<E> all(Iterable<E> iterable) {
        return Consumers.all(iterable, new ArrayList<E>());
    }

    /**
     * yields all element of the array in a list
     * @param <E>
     * @param array
     * @return
     */
    public static <E> List<E> all(E[] array) {
        return Consumers.all(array, new ArrayList<E>());
    }

    /**
     * yields last elemenet of the iterator
     * @param <E>
     * @param iterator
     * @return
     */
    public static <E> E last(Iterator<E> iterator) {
        return new StubbornConsumer<E>().consume(iterator);
    }

    /**
     * yields last element of the iterable's iterator
     * @param <E>
     * @param iterable
     * @return
     */
    public static <E> E last(Iterable<E> iterable) {
        return last(iterable.iterator());
    }

    /**
     * yields last element of the array
     * @param <E>
     * @param array
     * @return
     */
    public static <E> E last(E[] array) {
        return last(new ArrayIterator<E>(array));
    }

    /**
     * consumes the input iterator to the output iterator
     * @param <E>
     * @param iterator
     * @param outputIterator
     */
    public static <E> void pipe(Iterator<E> iterator, OutputIterator<E> outputIterator) {
        new PipingConsumer<E>(outputIterator).consume(iterator);
    }

    /**
     * consumes the iterable's input iterator to the output iterator
     * @param <E>
     * @param iterable
     * @param outputIterator
     */
    public static <E> void pipe(Iterable<E> iterable, OutputIterator<E> outputIterator) {
        pipe(iterable.iterator(), outputIterator);
    }

    /**
     * consumes the array to the output iterator
     * @param <E>
     * @param array
     * @param outputIterator 
     */
    public static <E> void pipe(E[] array, OutputIterator<E> outputIterator) {
        pipe(new ArrayIterator<E>(array), outputIterator);
    }

    /**
     * yields the first element if found, nothing otherwise.
     * @param <E>
     * @param iterator
     * @return just the first element or nothing
     */
    public static <E> Maybe<E> maybeFirst(Iterator<E> iterator) {
        return new MaybeIterator<E>(iterator).next();
    }

    /**
     * yields the first element if found, nothing otherwise.
     * @param <E>
     * @param iterable
     * @return
     */
    public static <E> Maybe<E> maybeFirst(Iterable<E> iterable) {
        return maybeFirst(iterable.iterator());
    }

    /**
     * yields the first element if found, nothing otherwise.
     * @param <E>
     * @param array
     * @return
     */
    public static <E> Maybe<E> maybeFirst(E[] array) {
        return maybeFirst(new ArrayIterator<E>(array));
    }

    /**
     * yields the only element if found, nothing otherwise.
     * @param <E>
     * @param iterator
     * @throws IllegalStateException if the iterator contains more than one element
     * @return
     */
    public static <E> Maybe<E> maybeOne(Iterator<E> iterator) {
        Iterator<Maybe<E>> maybeIter = new MaybeIterator<E>(iterator);
        final Maybe<E> element = maybeIter.next();
        dbc.stateprecondition(!maybeIter.hasNext(), "Expected only one value in iterator");
        return element;
    }

    /**
     * yields the only element if found, nothing otherwise.
     * @param <E>
     * @param iterable
     * @throws IllegalStateException if the iterator contains more than one element
     * @return
     */
    public static <E> Maybe<E> maybeOne(Iterable<E> iterable) {
        return maybeOne(iterable.iterator());
    }

    /**
     * yields the only element if found, nothing otherwise.
     * @param <E>
     * @param array
     * @throws IllegalStateException if the iterator contains more than one element
     * @return
     */
    public static <E> Maybe<E> maybeOne(E[] array) {
        return maybeOne(new ArrayIterator<E>(array));
    }

    /**
     * 
     * @param <T>
     * @param iterator
     * @return
     */
    public static <T> Maybe<T> maybeLast(Iterator<T> iterator) {
        dbc.precondition(iterator != null, "cannot call maybeLast with a null iterator");
        if (iterator.hasNext()) {
            return Maybe.just(Consumers.last(iterator));
        }
        return Maybe.nothing();
    }

    /**
     *
     * @param <T>
     * @param iterable
     * @return
     */
    public static <T> Maybe<T> maybeLast(Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot call maybeLast with a null iterable");
        return maybeLast(iterable.iterator());
    }

    /**
     *
     * @param <T>
     * @param array
     * @return
     */
    public static <T> Maybe<T> maybeLast(T[] array) {
        return maybeLast(new ArrayIterator<T>(array));
    }
}
