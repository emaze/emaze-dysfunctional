package net.emaze.dysfunctional.reductions;

import java.util.function.BiFunction;

/**
 *
 * @author rferranti
 */
public class Count<E> implements BiFunction<Long, E, Long> {

    @Override
    public Long apply(Long former, E latter) {
        return former + 1;
    }
}
