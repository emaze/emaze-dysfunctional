package net.emaze.dysfunctional.consumers;

/**
 *
 * @param <R> Result type
 * @param <E> Element Type
 * @author rferranti
 */
public interface Consumer<R,E> {
    R consume(E element);
}
