package net.emaze.dysfunctional.groups;

import java.util.Collection;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public class PartitionBy<CA extends Collection<T>, CR extends Collection<T>, T> implements Delegate<Pair<CA, CR>, Iterator<T>> {

    private final Predicate<T> partitioner;
    private final Provider<CA> acceptedCollectionProvider;
    private final Provider<CR> rejectedCollectionProvider;

    public PartitionBy(Predicate<T> partitioner, Provider<CA> acceptedCollectionProvider, Provider<CR> rejectedCollectionProvider) {
        dbc.precondition(partitioner != null, "cannot partition using a null partitioner");
        dbc.precondition(acceptedCollectionProvider != null, "cannot partition using a null collection provider for accepted values");
        dbc.precondition(rejectedCollectionProvider != null, "cannot partition using a null collection provider for rejected values");
        this.partitioner = partitioner;
        this.acceptedCollectionProvider = acceptedCollectionProvider;
        this.rejectedCollectionProvider = rejectedCollectionProvider;
    }

    @Override
    public Pair<CA, CR> perform(Iterator<T> values) {
        dbc.precondition(values != null, "cannot partition a null iterator");
        final CA accepted = acceptedCollectionProvider.provide();
        final CR refused = rejectedCollectionProvider.provide();
        while (values.hasNext()) {
            final T value = values.next();
            final Collection<T> collection = partitioner.accept(value) ? accepted : refused;
            collection.add(value);
        }
        return Pair.of(accepted, refused);
    }
}
