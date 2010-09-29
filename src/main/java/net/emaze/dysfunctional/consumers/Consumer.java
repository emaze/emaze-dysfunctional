package net.emaze.dysfunctional.consumers;

/**
 *
 * @param <R> Result type
 * @param <E> Element Type
 * @author rferranti
 */
public interface Consumer<R,E> {
    public R consume(E element);
}
