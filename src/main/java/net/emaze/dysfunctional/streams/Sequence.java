package net.emaze.dysfunctional.streams;

import java.util.stream.Stream;

public interface Sequence<T> extends Stream<T>, SequenceConsumer<T>, SequenceOperation<T> {
}
