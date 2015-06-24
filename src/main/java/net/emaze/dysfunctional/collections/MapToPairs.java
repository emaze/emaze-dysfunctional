package net.emaze.dysfunctional.collections;

import java.util.Iterator;
import java.util.Map;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.iterations.TransformingIterator;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * Transforms a Map to an Iterator of pairs.
 *
 * @author rferranti, sbaruzza
 * @param <K> the key type
 * @param <V> the value type
 */
public class MapToPairs<K, V> implements Function<Map<K, V>, Iterator<Pair<K, V>>> {

    @Override
    public Iterator<Pair<K, V>> apply(Map<K, V> map) {
        dbc.precondition(map != null, "cannot transform a null map to an iterator of pairs");
        return new TransformingIterator<>(map.entrySet().iterator(), new EntryToPair<K, V>());
    }
}
