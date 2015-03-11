package net.emaze.dysfunctional.streams;

import java.util.function.Function;

public interface SequenceOperation<T> {

    <U> Sequence<T> distinctBy(Function<T, U> key);
}
