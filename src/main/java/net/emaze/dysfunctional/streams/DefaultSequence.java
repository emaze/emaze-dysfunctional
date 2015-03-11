package net.emaze.dysfunctional.streams;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.emaze.dysfunctional.consumers.FirstElement;
import net.emaze.dysfunctional.consumers.LastElement;
import net.emaze.dysfunctional.consumers.MaybeLastElement;
import net.emaze.dysfunctional.consumers.MaybeOneElement;
import net.emaze.dysfunctional.consumers.OneElement;
import net.emaze.dysfunctional.filtering.AtIndex;
import net.emaze.dysfunctional.filtering.Nth;

public class DefaultSequence<T> extends BaseSequence<T> {

    public DefaultSequence(Stream<T> stream) {
        super(stream);
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
}
