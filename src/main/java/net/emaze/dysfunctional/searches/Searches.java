package net.emaze.dysfunctional.searches;

import java.util.ArrayList;
import java.util.Iterator;
import net.emaze.dysfunctional.adapting.ArrayIterator;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.delegates.Predicate;
import net.emaze.dysfunctional.filtering.Filtering;
import net.emaze.dysfunctional.options.Maybe;

public abstract class Searches {

    public static <E> Maybe<E> search(Iterator<E> iterator, Predicate<E> predicate) {
        return Consumers.maybeFirst(Filtering.filter(iterator, predicate));
    }

    public static <E> Maybe<E> searchOne(Iterator<E> iterator, Predicate<E> predicate) {
        return Consumers.maybeOne(Filtering.filter(iterator, predicate));
    }

    public static <E> E find(Iterator<E> iterator, Predicate<E> predicate) {
        return Consumers.first(Filtering.filter(iterator, predicate));
    }

    public static <E> E findOne(Iterator<E> iterator, Predicate<E> predicate) {
        return Consumers.one(Filtering.filter(iterator, predicate));
    }

    public static <E> Maybe<E> search(Iterable<E> iterable, Predicate<E> predicate) {
        return search(iterable.iterator(), predicate);
    }

    public static <E> Maybe<E> searchOne(Iterable<E> iterable, Predicate<E> predicate) {
        return searchOne(iterable.iterator(), predicate);
    }

    public static <E> E find(Iterable<E> iterable, Predicate<E> predicate) {
        return find(iterable.iterator(), predicate);
    }

    public static <E> E findOne(Iterable<E> iterable, Predicate<E> predicate) {
        return findOne(iterable.iterator(), predicate);
    }

    public static <E> Maybe<E> search(E[] array, Predicate<E> predicate) {
        return search(new ArrayIterator<E>(array), predicate);
    }

    public static <E> Maybe<E> searchOne(E[] array, Predicate<E> predicate) {
        return searchOne(new ArrayIterator<E>(array), predicate);
    }

    public static <E> E find(E[] array, Predicate<E> predicate) {
        return find(new ArrayIterator<E>(array), predicate);
    }

    public static <E> E findOne(E[] array, Predicate<E> predicate) {
        return findOne(new ArrayIterator<E>(array), predicate);
    }
}
