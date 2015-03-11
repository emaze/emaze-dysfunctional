package net.emaze.dysfunctional.streams;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public interface Sequence<T> extends Stream<T> {

    List<T> toList();

    Set<T> toSet();

    <U> Sequence<T> distinctBy(Function<T, U> key);
}
