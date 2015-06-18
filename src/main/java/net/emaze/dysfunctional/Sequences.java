package net.emaze.dysfunctional;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;
import net.emaze.dysfunctional.streams.DefaultSequence;
import net.emaze.dysfunctional.streams.Sequence;

public abstract class Sequences {

    public static <T> Sequence<T> empty() {
        return new DefaultSequence<>(Stream.empty());
    }

    @SafeVarargs
    public static <T> Sequence<T> of(T... array) {
        return new DefaultSequence<>(Arrays.stream(array));
    }

    public static <T> Sequence<T> of(Iterator<T> iterator) {
        return DefaultSequence.fromIterator(iterator);
    }

    public static <T> Sequence<T> of(Iterable<T> iterable) {
        return DefaultSequence.fromIterator(iterable.iterator());
    }
}
