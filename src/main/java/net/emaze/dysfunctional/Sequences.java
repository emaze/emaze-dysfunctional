package net.emaze.dysfunctional;

import java.util.Iterator;
import java.util.stream.Stream;
import net.emaze.dysfunctional.streams.Sequence;

public abstract class Sequences {

    public static <T> Sequence<T> empty() {
        return Sequence.from(Stream.empty());
    }

    @SafeVarargs
    public static <T> Sequence<T> of(T... array) {
        return Sequence.from(Stream.of(array));
    }

    public static <T> Sequence<T> of(Iterator<T> iterator) {
        return Sequence.from(iterator);
    }

    public static <T> Sequence<T> of(Iterable<T> iterable) {
        return Sequence.from(iterable.iterator());
    }
}
