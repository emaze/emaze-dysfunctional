package net.emaze.dysfunctional.sequences;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.*;
import java.util.stream.*;
import net.emaze.dysfunctional.consumers.FirstElement;
import net.emaze.dysfunctional.consumers.LastElement;
import net.emaze.dysfunctional.consumers.MaybeLastElement;
import net.emaze.dysfunctional.consumers.MaybeOneElement;
import net.emaze.dysfunctional.consumers.OneElement;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.filtering.AtIndex;
import net.emaze.dysfunctional.filtering.AtMostMemoryIterator;
import net.emaze.dysfunctional.filtering.DropWhile;
import net.emaze.dysfunctional.filtering.MemoryIterator;
import net.emaze.dysfunctional.filtering.Nth;
import net.emaze.dysfunctional.filtering.TakeUpToIterator;
import net.emaze.dysfunctional.filtering.TakeWhileIterator;
import net.emaze.dysfunctional.filtering.UntilCount;

public interface Sequence<T> extends Stream<T> {

    static <T> Sequence<T> from(Iterator<T> iterator) {
        dbc.precondition(iterator != null, "Cannot create a sequence from a null iterator");
        final Spliterator<T> splitIterator = Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED);
        return new DefaultSequence<>(StreamSupport.stream(splitIterator, false));
    }

    static <T> Sequence<T> from(Stream<T> stream) {
        dbc.precondition(stream != null, "Cannot create a sequence from a null stream");
        return stream instanceof Sequence
                ? (Sequence<T>) stream
                : new DefaultSequence<>(stream);
    }

    default List<T> toList() {
        return collect(Collectors.toList());
    }

    default Set<T> toSet() {
        return collect(Collectors.toSet());
    }

    default <K, U> Map<K, U> toMap(
            Function<? super T, ? extends K> keyMapper,
            Function<? super T, ? extends U> valueMapper) {
        dbc.precondition(keyMapper != null, "Cannot use a null key mapper function");
        dbc.precondition(valueMapper != null, "Cannot use a null value mapper function");
        return collect(Collectors.toMap(keyMapper, valueMapper));
    }

    default <K> Sequence<T> distinctBy(Function<? super T, ? extends K> propertyMapper) {
        dbc.precondition(propertyMapper != null, "Cannot use a null property mapper function");
        final Set<K> seen = new HashSet<>();
        return filter(t -> seen.add(propertyMapper.apply(t)));
    }

    default T first() {
        return new FirstElement<T>().apply(iterator());
    }

    default Optional<T> maybeFirst() {
        return findFirst();
    }

    default T one() {
        return new OneElement<T>().apply(iterator());
    }

    default Optional<T> maybeOne() {
        return new MaybeOneElement<T>().apply(iterator());
    }

    default T last() {
        return new LastElement<T>().apply(iterator());
    }

    default Optional<T> maybeLast() {
        return new MaybeLastElement<T>().apply(iterator());
    }

    default T nth(long count) {
        final Sequence<T> filtered = filter(new Nth<>(count));
        return new FirstElement<T>().apply(filtered.iterator());
    }

    default Optional<T> maybeNth(long count) {
        return filter(new Nth<>(count)).findFirst();
    }

    default T at(long index) {
        final Sequence<T> filtered = filter(new AtIndex<>(index));
        return new FirstElement<T>().apply(filtered.iterator());
    }

    default Optional<T> maybeAt(long index) {
        return filter(new AtIndex<>(index)).findFirst();
    }

    default Sequence<T> take(int howMany) {
        final Iterator<T> iterator = new TakeUpToIterator<>(iterator(), howMany);
        return Sequence.from(iterator);
    }

    default Sequence<T> takeLast(int howMany) {
        final Iterator<T> iterator = new MemoryIterator<>(iterator(), howMany);
        return Sequence.from(iterator);
    }

    default Sequence<T> takeAtMostLast(int howMany) {
        final Iterator<T> iterator = new AtMostMemoryIterator<>(iterator(), howMany);
        return Sequence.from(iterator);
    }

    default Sequence<T> takeWhile(Predicate<T> predicate) {
        final Iterator<T> iterator = new TakeWhileIterator<>(iterator(), predicate);
        return Sequence.from(iterator);
    }

    default Sequence<T> drop(long howMany) {
        return filter(new DropWhile<>(new UntilCount<>(howMany)));
    }

    default Sequence<T> dropWhile(Predicate<T> predicate) {
        return filter(new DropWhile<>(predicate));
    }

    default Sequence<T> slice(long from, long howMany) {
        return drop(from).take((int) howMany);
    }

    default Sequence<T> chain(Stream<T> other) {
        return from(Stream.concat(this, other));
    }

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
