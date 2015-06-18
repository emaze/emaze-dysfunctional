package net.emaze.dysfunctional.streams;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface SequenceOperation<T> {

    <U> Sequence<T> distinctBy(Function<T, U> key);

    Sequence<T> take(int howMany);

    Sequence<T> takeLast(int howMany);

    Sequence<T> takeAtMostLast(int howMany);

    Sequence<T> takeWhile(Predicate<T> predicate);

    Sequence<T> drop(long howMany);

    Sequence<T> dropWhile(Predicate<T> predicate);

    Sequence<T> slice(long from, long howMany);

    Sequence<T> chain(Stream<T> other);
}
