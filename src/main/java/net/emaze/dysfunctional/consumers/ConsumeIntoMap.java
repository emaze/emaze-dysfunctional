package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import java.util.Map;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * Consumes every pair from the consumable into the map provided by the
 * provider.
 *
 * @param <M> the map type parameter
 * @param <K> the map key type parameter
 * @param <V> the map value type parameter
 * @author rferranti
 */
public class ConsumeIntoMap<M extends Map<K, V>, K, V> implements Delegate<M, Iterator<Pair<K, V>>> {

    private final Provider<M> provider;

    public ConsumeIntoMap(Provider<M> provider) {
        dbc.precondition(provider != null, "collection provider cannot be null");
        this.provider = provider;
    }

    @Override
    public M perform(Iterator<Pair<K, V>> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        final M out = provider.provide();
        while (consumable.hasNext()) {
            final Pair<K, V> pair = consumable.next();
            out.put(pair.first(), pair.second());
        }
        return out;
    }
}
