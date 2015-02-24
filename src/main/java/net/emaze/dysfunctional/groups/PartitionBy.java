package net.emaze.dysfunctional.groups;

import java.util.Collection;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Predicate;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * A unary function partitioning elements from the passed iterator. Partitioning
 * is done by evaluating the passed predicate.
 *
 * @param <T> the element type
 * @param <CA> the accepted values collection type
 * @param <CR> the rejected values collection type
 * @author rferranti
 */
public class PartitionBy<T, CA extends Collection<T>, CR extends Collection<T>> implements Function<Iterator<T>, Pair<CA, CR>> {

    private final Predicate<T> partitioner;
    private final Supplier<CA> acceptedCollectionProvider;
    private final Supplier<CR> rejectedCollectionProvider;

    public PartitionBy(Predicate<T> partitioner, Supplier<CA> acceptedCollectionProvider, Supplier<CR> rejectedCollectionProvider) {
        dbc.precondition(partitioner != null, "cannot partition using a null partitioner");
        dbc.precondition(acceptedCollectionProvider != null, "cannot partition using a null collection supplier for accepted values");
        dbc.precondition(rejectedCollectionProvider != null, "cannot partition using a null collection supplier for rejected values");
        this.partitioner = partitioner;
        this.acceptedCollectionProvider = acceptedCollectionProvider;
        this.rejectedCollectionProvider = rejectedCollectionProvider;
    }

    @Override
    public Pair<CA, CR> apply(Iterator<T> values) {
        dbc.precondition(values != null, "cannot partition a null iterator");
        final CA accepted = acceptedCollectionProvider.get();
        final CR refused = rejectedCollectionProvider.get();
        while (values.hasNext()) {
            final T value = values.next();
            final Collection<T> collection = partitioner.test(value) ? accepted : refused;
            collection.add(value);
        }
        return Pair.of(accepted, refused);
    }
}
