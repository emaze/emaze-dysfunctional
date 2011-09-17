package net.emaze.dysfunctional.groups;

import java.util.Collection;
import java.util.Iterator;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public class PartitionBy<CA extends Collection<T>, CR extends Collection<T>, T>  implements Delegate<Pair<CA,CR>, Iterator<T>>{

    private final Predicate<T> partitioner;
    private final Provider<CA> acceptedCollectionProvider;
    private final Provider<CR> refusedCollectionProvider;

    public PartitionBy(Predicate<T> partitioner, Provider<CA> acceptedCollectionProvider, Provider<CR> refusedCollectionProvider) {
        this.partitioner = partitioner;
        this.acceptedCollectionProvider = acceptedCollectionProvider;
        this.refusedCollectionProvider = refusedCollectionProvider;
    }

    @Override
    public Pair<CA, CR> perform(Iterator<T> values) {
        final CA accepted = acceptedCollectionProvider.provide();
        final CR refused = refusedCollectionProvider.provide();
        while(values.hasNext()){
            final T value = values.next();
            final Collection<T> collection = partitioner.accept(value) ? accepted : refused;
            collection.add(value);
        }
        return Pair.of(accepted, refused);
    }
    
}
