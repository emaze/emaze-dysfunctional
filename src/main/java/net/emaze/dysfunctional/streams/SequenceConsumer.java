package net.emaze.dysfunctional.streams;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public interface SequenceConsumer<T> {

    List<T> toList();

    Set<T> toSet();

    <K, V> Map<K, V> toMap(Function<T, K> keyMapper, Function<T, V> valueMapper);

    T first();

    Optional<T> maybeFirst();

    T one();

    Optional<T> maybeOne();

    T last();

    Optional<T> maybeLast();

    T nth(long count);

    Optional<T> maybeNth(long count);

    T at(long index);

    Optional<T> maybeAt(long index);
}
