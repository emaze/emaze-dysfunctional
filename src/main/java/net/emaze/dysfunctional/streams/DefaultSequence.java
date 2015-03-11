package net.emaze.dysfunctional.streams;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public <K> Sequence<T> distinctBy(Function<T, K> key) {
        final Set<K> seen = new HashSet<>();
        return filter(t -> seen.add(key.apply(t)));
    }
}
