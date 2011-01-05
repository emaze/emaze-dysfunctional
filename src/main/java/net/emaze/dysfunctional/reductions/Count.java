package net.emaze.dysfunctional.reductions;

import net.emaze.dysfunctional.delegates.BinaryDelegate;

/**
 *
 * @author rferranti
 */
public class Count<E> implements BinaryDelegate<Long, Long, E> {

    @Override
    public Long perform(Long former, E latter) {
        return former + 1;
    }
}
