package net.emaze.dysfunctional.streams;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
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

public class DefaultSequence<T> extends BaseSequence<T> {

    public DefaultSequence(Stream<T> stream) {
        super(stream);
    }

    public static <T> Sequence<T> fromIterator(Iterator<T> iterator) {
        dbc.precondition(iterator != null, "cannot create a sequence from a null iterator");
        final Iterable<T> iterable = () -> iterator;
        return new DefaultSequence<>(StreamSupport.stream(iterable.spliterator(), false));
    }

    @Override
    protected <T> Sequence<T> lift(Stream<T> stream) {
        return new DefaultSequence<>(stream);
    }

    @Override
    public List<T> toList() {
        return stream.collect(Collectors.toList());
    }

    @Override
    public Set<T> toSet() {
        return stream.collect(Collectors.toSet());
    }

    @Override
    public <K, V> Map<K, V> toMap(Function<T, K> keyMapper, Function<T, V> valueMapper) {
        return stream.collect(Collectors.toMap(keyMapper, valueMapper));
    }

    @Override
    public <K> Sequence<T> distinctBy(Function<T, K> key) {
        final Set<K> seen = new HashSet<>();
        return filter(t -> seen.add(key.apply(t)));
    }

    @Override
    public T first() {
        return new FirstElement<T>().apply(stream.iterator());
    }

    @Override
    public Optional<T> maybeFirst() {
        return stream.findFirst();
    }

    @Override
    public T one() {
        return new OneElement<T>().apply(stream.iterator());
    }

    @Override
    public Optional<T> maybeOne() {
        return new MaybeOneElement<T>().apply(stream.iterator());
    }

    @Override
    public T last() {
        return new LastElement<T>().apply(stream.iterator());
    }

    @Override
    public Optional<T> maybeLast() {
        return new MaybeLastElement<T>().apply(stream.iterator());
    }

    @Override
    public T nth(long count) {
        final Sequence<T> filtered = filter(new Nth<>(count));
        return new FirstElement<T>().apply(filtered.iterator());
    }

    @Override
    public Optional<T> maybeNth(long count) {
        return filter(new Nth<>(count)).findFirst();
    }

    @Override
    public T at(long index) {
        final Sequence<T> filtered = filter(new AtIndex<>(index));
        return new FirstElement<T>().apply(filtered.iterator());
    }

    @Override
    public Optional<T> maybeAt(long index) {
        return filter(new AtIndex<>(index)).findFirst();
    }

    @Override
    public Sequence<T> take(int howMany) {
        final Iterator<T> iterator = new TakeUpToIterator<>(stream.iterator(), howMany);
        return fromIterator(iterator);
    }

    @Override
    public Sequence<T> takeLast(int howMany) {
        final Iterator<T> iterator = new MemoryIterator<>(stream.iterator(), howMany);
        return fromIterator(iterator);
    }

    @Override
    public Sequence<T> takeAtMostLast(int howMany) {
        final Iterator<T> iterator = new AtMostMemoryIterator<>(stream.iterator(), howMany);
        return fromIterator(iterator);
    }

    @Override
    public Sequence<T> takeWhile(Predicate<T> predicate) {
        final Iterator<T> iterator = new TakeWhileIterator<>(stream.iterator(), predicate);
        return fromIterator(iterator);
    }

    @Override
    public Sequence<T> drop(long howMany) {
        return filter(new DropWhile<>(new UntilCount<>(howMany)));
    }

    @Override
    public Sequence<T> dropWhile(Predicate<T> predicate) {
        return filter(new DropWhile<>(predicate));
    }

    @Override
    public Sequence<T> slice(long from, long howMany) {
        return drop(from).take((int) howMany);
    }
}
