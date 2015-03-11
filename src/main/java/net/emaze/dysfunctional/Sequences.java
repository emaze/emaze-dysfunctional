package net.emaze.dysfunctional;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.StreamSupport;
import net.emaze.dysfunctional.streams.DefaultSequence;
import net.emaze.dysfunctional.streams.Sequence;

public abstract class Sequences {

    @SafeVarargs
    public static <T> Sequence<T> of(T... array) {
        return new DefaultSequence<>(Arrays.stream(array));
    }

    public static <T> Sequence<T> of(Iterator<T> iterator) {
        final Iterable<T> iterable = () -> iterator;
        return new DefaultSequence<>(StreamSupport.stream(iterable.spliterator(), false));
    }

    public static <T> Sequence<T> of(Iterable<T> iterable) {
        return new DefaultSequence<>(StreamSupport.stream(iterable.spliterator(), false));
    }
}
