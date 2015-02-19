package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import java.util.Map;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.function.Supplier;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * Consumes every pair from the consumable into the map provided by the
 * provider.
 *
 * @param <K> the map key type parameter
 * @param <V> the map value type parameter
 * @param <M> the map type parameter
 * @author rferranti
 */
public class ConsumeIntoMap<K, V, M extends Map<K, V>> implements Function<Iterator<Pair<K, V>>, M> {

    private final Supplier<M> provider;

    public ConsumeIntoMap(Supplier<M> provider) {
        dbc.precondition(provider != null, "collection provider cannot be null");
        this.provider = provider;
    }

    @Override
    public M apply(Iterator<Pair<K, V>> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        final M out = provider.get();
        while (consumable.hasNext()) {
            final Pair<K, V> pair = consumable.next();
            out.put(pair.first(), pair.second());
        }
        return out;
    }
}
