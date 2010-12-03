package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.adapting.ArrayToIterableAdapter;

/**
 *
 * @author rferranti
 */
public abstract class Consumers {

    public static <E> E first(Iterator<E> iterator) {
        return new GiveUpConsumer<E>().consume(iterator);
    }

    public static <E> E first(Iterable<E> iterable) {
        return first(iterable.iterator());
    }

    public static <E> E first(E[] array) {
        return first(new ArrayToIterableAdapter<E>(array));
    }

    public static <E> List<E> all(Iterator<E> iterator) {
        return new EagerConsumer<E>().consume(iterator);
    }

    public static <E> List<E> all(Iterable<E> iterable) {
        return all(iterable.iterator());
    }

    public static <E> List<E> all(E[] array) {
        return all(new ArrayToIterableAdapter<E>(array));
    }

    public static <E> E last(Iterator<E> iterator) {
        return new StubbornConsumer<E>().consume(iterator);
    }

    public static <E> E last(Iterable<E> iterable) {
        return last(iterable.iterator());
    }

    public static <E> E last(E[] array) {
        return last(new ArrayToIterableAdapter<E>(array));
    }

    public static <E> void pipe(Iterator<E> iterator, OutputIterator<E> outputIterator) {
        new PipingConsumer<E>(outputIterator).consume(iterator);
    }

    public static <E> void pipe(Iterable<E> iterable, OutputIterator<E> outputIterator) {
        pipe(iterable.iterator(), outputIterator);
    }

    public static <E> void pipe(E[] array, OutputIterator<E> outputIterator) {
        pipe(new ArrayToIterableAdapter<E>(array), outputIterator);
    }
}
