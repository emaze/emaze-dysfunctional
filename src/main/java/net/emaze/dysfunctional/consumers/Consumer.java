package net.emaze.dysfunctional.consumers;

/**
 *
 * @author rferranti
 */
public interface Consumer<R,E> {
    public R consume(E element);
}
