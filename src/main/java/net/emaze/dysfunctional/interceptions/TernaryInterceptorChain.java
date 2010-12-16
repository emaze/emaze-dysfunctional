package net.emaze.dysfunctional.interceptions;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Multicasting;
import net.emaze.dysfunctional.delegates.TernaryDelegate;
import net.emaze.dysfunctional.iterations.OneTimeIterable;

/**
 *
 * @param <R>
 * @param <T1>
 * @param <T2>
 * @param <T3> 
 * @author rferranti
 */
public class TernaryInterceptorChain<R, T1, T2, T3> implements TernaryDelegate<R, T1, T2, T3>, Multicasting<TernaryInterceptor<T1, T2, T3>> {

    private final Deque<TernaryInterceptor<T1, T2, T3>> chain = new ArrayDeque<TernaryInterceptor<T1, T2, T3>>();
    private final TernaryDelegate<R, T1, T2, T3> innermost;

    public TernaryInterceptorChain(TernaryDelegate<R, T1, T2, T3> innermost) {
        dbc.precondition(innermost != null, "innermost delegate cannot be null");
        this.innermost = innermost;
    }

    @Override
    public R perform(T1 first, T2 second, T3 third) {
        TernaryDelegate<R, T1, T2, T3> current = innermost;
        for (TernaryInterceptor<T1, T2, T3> interceptor : new OneTimeIterable<TernaryInterceptor<T1, T2, T3>>(chain.descendingIterator())) {
            current = new TernaryInterceptorAdapter<R, T1, T2, T3>(interceptor, current);
        }
        return current.perform(first, second, third);
    }

    @Override
    public void add(TernaryInterceptor<T1, T2, T3> functor) {
        dbc.precondition(functor != null, "cannot add a null functor to an InterceptorChain");
        chain.add(functor);
    }

    @Override
    public boolean remove(TernaryInterceptor<T1, T2, T3> functor) {
        dbc.precondition(functor != null, "cannot remove null functor from an InterceptorChain");
        return chain.remove(functor);
    }

    @Override
    public void setFunctors(Collection<TernaryInterceptor<T1, T2, T3>> functors) {
        dbc.precondition(functors != null, "functors cannot be null");
        chain.clear();
        chain.addAll(functors);
    }
}
