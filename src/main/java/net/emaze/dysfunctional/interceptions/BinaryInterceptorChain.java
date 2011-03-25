package net.emaze.dysfunctional.interceptions;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.multicasting.Multicasting;
import net.emaze.dysfunctional.iterations.OneTimeIterable;

/**
 *
 * @param <R>
 * @param <T1>
 * @param <T2> 
 * @author rferranti
 */
public class BinaryInterceptorChain<R, T1, T2> implements BinaryDelegate<R, T1, T2>, Multicasting<BinaryInterceptor<T1, T2>> {

    private final Deque<BinaryInterceptor<T1, T2>> chain = new ArrayDeque<BinaryInterceptor<T1, T2>>();
    private final BinaryDelegate<R, T1, T2> innermost;

    public BinaryInterceptorChain(BinaryDelegate<R, T1, T2> innermost) {
        dbc.precondition(innermost != null, "innermost delegate cannot be null");
        this.innermost = innermost;
    }

    @Override
    public R perform(T1 first, T2 second) {
        BinaryDelegate<R, T1, T2> current = innermost;
        for (BinaryInterceptor<T1, T2> interceptor : new OneTimeIterable<BinaryInterceptor<T1, T2>>(chain.descendingIterator())) {
            current = new BinaryInterceptorAdapter<R, T1, T2>(interceptor, current);
        }
        return current.perform(first, second);
    }

    @Override
    public void add(BinaryInterceptor<T1, T2> functor) {
        dbc.precondition(functor != null, "cannot add a null functor to an InterceptorChain");
        chain.add(functor);
    }

    @Override
    public boolean remove(BinaryInterceptor<T1, T2> functor) {
        dbc.precondition(functor != null, "cannot remove null functor from an InterceptorChain");
        return chain.remove(functor);
    }

    @Override
    public void setFunctors(Collection<BinaryInterceptor<T1, T2>> functors) {
        dbc.precondition(functors != null, "functors cannot be null");
        chain.clear();
        chain.addAll(functors);
    }
}
