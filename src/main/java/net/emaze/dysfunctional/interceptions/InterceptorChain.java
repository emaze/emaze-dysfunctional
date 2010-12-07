package net.emaze.dysfunctional.interceptions;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.delegates.Multicasting;
import net.emaze.dysfunctional.iterations.OneTimeIterable;

/**
 *
 * @param <R>
 * @param <T> 
 * @author rferranti
 */
public class InterceptorChain<R, T> implements Delegate<R, T>, Multicasting<Interceptor<T>> {

    private final Deque<Interceptor<T>> chain = new LinkedList<Interceptor<T>>();
    private final Delegate<R, T> innermost;

    public InterceptorChain(Delegate<R, T> innermost) {
        dbc.precondition(innermost != null, "innermost delegate cannot be null");
        this.innermost = innermost;
    }

    @Override
    public R perform(T param) {
        Delegate<R, T> current = innermost;
        for (Interceptor<T> interceptor : new OneTimeIterable<Interceptor<T>>(chain.descendingIterator())) {
            current = new InterceptorAdapter<R, T>(interceptor, current);
        }
        return current.perform(param);
    }

    @Override
    public void add(Interceptor<T> functor) {
        chain.add(functor);
    }

    @Override
    public void remove(Interceptor<T> functor) {
        chain.remove(functor);
    }

    @Override
    public void setFunctors(Collection<Interceptor<T>> functors) {
        chain.clear();
        chain.addAll(functors);
    }
}
