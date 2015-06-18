package net.emaze.dysfunctional.streams;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.*;

public interface Sequence<T> extends Stream<T>, SequenceConsumer<T>, SequenceOperation<T> {

    @Override
    boolean allMatch(Predicate<? super T> predicate);

    @Override
    boolean anyMatch(Predicate<? super T> predicate);

    @Override
    void close();

    @Override
    <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);

    @Override
    <R, A> R collect(Collector<? super T, A, R> collector);

    @Override
    long count();

    @Override
    Sequence<T> distinct();

    @Override
    Sequence<T> filter(Predicate<? super T> predicate);

    @Override
    Optional<T> findAny();

    @Override
    Optional<T> findFirst();

    @Override
    <R> Sequence<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);

    @Override
    DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper);

    @Override
    IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper);

    @Override
    LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper);

    @Override
    void forEach(Consumer<? super T> action);

    @Override
    void forEachOrdered(Consumer<? super T> action);

    @Override
    boolean isParallel();

    @Override
    Iterator<T> iterator();

    @Override
    Sequence<T> limit(long maxSize);

    @Override
    <R> Sequence<R> map(Function<? super T, ? extends R> mapper);

    @Override
    DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper);

    @Override
    IntStream mapToInt(ToIntFunction<? super T> mapper);

    @Override
    LongStream mapToLong(ToLongFunction<? super T> mapper);

    @Override
    Optional<T> max(Comparator<? super T> comparator);

    @Override
    Optional<T> min(Comparator<? super T> comparator);

    @Override
    boolean noneMatch(Predicate<? super T> predicate);

    @Override
    Sequence<T> onClose(Runnable closeHandler);

    @Override
    Sequence<T> parallel();

    @Override
    Sequence<T> peek(Consumer<? super T> action);

    @Override
    T reduce(T identity, BinaryOperator<T> accumulator);

    @Override
    Optional<T> reduce(BinaryOperator<T> accumulator);

    @Override
    <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);

    @Override
    Sequence<T> sequential();

    @Override
    Sequence<T> skip(long n);

    @Override
    Sequence<T> sorted();

    @Override
    Sequence<T> sorted(Comparator<? super T> comparator);

    @Override
    Spliterator<T> spliterator();

    @Override
    Object[] toArray();

    @Override
    <A> A[] toArray(IntFunction<A[]> generator);

    @Override
    Sequence<T> unordered();
}
