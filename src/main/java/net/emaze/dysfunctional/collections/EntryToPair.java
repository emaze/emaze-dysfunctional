package net.emaze.dysfunctional.collections;

import java.util.Map.Entry;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * Transforms a Map.Entry to a Pair.
 *
 * @author rferranti, sbaruzza
 * @param <K> the key type
 * @param <V> the value type
 */
public class EntryToPair<K, V> implements Function<Entry<K, V>, Pair<K, V>> {

    @Override
    public Pair<K, V> apply(Entry<K, V> entry) {
        dbc.precondition(entry != null, "canno transform a null entry to a pair");
        return Pair.of(entry.getKey(), entry.getValue());
    }
}
