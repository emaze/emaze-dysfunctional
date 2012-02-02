package net.emaze.dysfunctional.collections;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.iterations.TransformingIterator;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * Transforms a Map to an Iterator of pairs.
 *
 * @author rferranti, sbaruzza
 * @param <K> the key type
 * @param <V> the value type
 */
public class MapToPairs<K, V> implements Delegate<Iterator<Pair<K, V>>, Map<K, V>> {

    @Override
    public Iterator<Pair<K, V>> perform(Map<K, V> map) {
        dbc.precondition(map != null, "cannot transform a null map to an iterator of pairs");
        return new TransformingIterator<Pair<K, V>, Entry<K, V>>(map.entrySet().iterator(), new EntryToPair<K, V>());
    }
}
