package net.emaze.dysfunctional.multiplexing;

import java.util.Collection;
import java.util.Iterator;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.delegates.IteratorPlucker;
import net.emaze.dysfunctional.iterations.Iterations;

/**
 *
 * @author rferranti
 */
public abstract class Multiplexing {

    public static <E, T extends Collection<E>> Iterator<E> flatten(Iterator<T> iterators){
        return new ChainIterator<E>(Consumers.all(Iterations.transform(iterators, new IteratorPlucker<E, T>())));
    }

}
