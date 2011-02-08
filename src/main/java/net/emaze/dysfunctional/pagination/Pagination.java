package net.emaze.dysfunctional.pagination;

import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.filtering.Filtering;
import net.emaze.dysfunctional.reductions.Count;
import net.emaze.dysfunctional.reductions.Reductions;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public abstract class Pagination {

    public static <T> Pair<Integer, List<T>> page(long start, long howMany, Iterator<T> iterator){
        final List<T> sliced = Consumers.all(Filtering.slice(start, howMany, iterator));
        final long prefetchingCompensation = sliced.size() == howMany ? 1 : 0;
        final long fullSize = Reductions.reduce(iterator, new Count<T>(), start + sliced.size() + prefetchingCompensation);
        return Pair.of((int)fullSize, sliced);
    }

}
