package net.emaze.dysfunctional.sequences;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;

public abstract class Collecting {

    /**
     * Returns a {@code Collector} that accumulates and flats the input elements into a
     * new {@code List}. There are no guarantees on the type, mutability, serializability,
     * or thread-safety of the {@code List} returned; if more control over the returned
     * {@code List} is required, use {@link #flat(Supplier)}.
     *
     * @param <T> the type of the input elements (it must be {@code Iterable})
     * @return a {@code Collector} which collects and flats all the input elements into a
     * {@code List}, in encounter order
     */
    @SuppressWarnings("unchecked")
    public static <T extends Iterable<E>, E> Collector<T, ?, T> flat() {
        return (Collector<T, ?, T>) flat(ArrayList::new);
    }

    /**
     * Returns a {@code Collector} that accumulates and flats the input elements into a
     * new {@code Collection}, in encounter order. The {@code Collection} is created by
     * the provided supplier.
     *
     * @param <T> the type of the input elements (it must be {@code Iterable})
     * @param <R> the type of the resulting {@code Collection}
     * @param supplier a {@code Supplier} which returns a new, empty {@code Collection} of
     * the appropriate type
     * @return a {@code Collector} which collects and flats all the input elements into a
     * {@code Collection}, in encounter order
     */
    @SuppressWarnings("unchecked")
    public static <T extends Iterable<E>, R extends Collection<E>, E> Collector<T, ?, R> flat(Supplier<R> supplier) {
        return (Collector<T, ?, R>) Collector.of(supplier, R::addAll, (left, right) -> {
            left.addAll(right);
            return left;
        });
    }

    /**
     * Returns a {@code Collector} that accumulates in reverse order the items into a new {@code Deque}.
     *
     * @param <T> the type of the input elements
     * @return the resulting {@code Collector}
     */
    public static <T> Collector<T, ?, Deque<T>> reverse() {
        return Collector.of(LinkedList::new, Deque::addFirst, (left, right) -> {
            right.addAll(left);
            return right;
        });
    }
}
