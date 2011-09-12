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
public class PartitionBy<C extends Collection<T>, T>  implements Delegate<Pair<C,C>, Iterator<T>>{

    private final Predicate<T> partitioner;
    private final Provider<C> acceptedCollectionProvider;
    private final Provider<C> refusedCollectionProvider;

    public PartitionBy(Predicate<T> partitioner, Provider<C> acceptedCollectionProvider, Provider<C> refusedCollectionProvider) {
        this.partitioner = partitioner;
        this.acceptedCollectionProvider = acceptedCollectionProvider;
        this.refusedCollectionProvider = refusedCollectionProvider;
    }

    @Override
    public Pair<C, C> perform(Iterator<T> values) {
        final C accepted = acceptedCollectionProvider.provide();
        final C refused = refusedCollectionProvider.provide();
        while(values.hasNext()){
            final T value = values.next();
            final C collection = partitioner.accept(value) ? accepted : refused;
            collection.add(value);
        }
        return Pair.of(accepted, refused);
    }
    
}
