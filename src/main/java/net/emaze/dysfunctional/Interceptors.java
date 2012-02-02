package net.emaze.dysfunctional;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.interceptions.BinaryInterceptor;
import net.emaze.dysfunctional.interceptions.BinaryInterceptorChain;
import net.emaze.dysfunctional.interceptions.Interceptor;
import net.emaze.dysfunctional.interceptions.InterceptorChain;
import net.emaze.dysfunctional.interceptions.TernaryInterceptor;
import net.emaze.dysfunctional.interceptions.TernaryInterceptorChain;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.SingletonIterator;

public abstract class Interceptors {

    /**
     * Creates an interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T> the delegate parameter type
     * @param innermost the delegate to be intercepted
     * @param interceptor the interceptor
     * @return the resulting delegate
     */
    public <R, T> Delegate<R, T> intercept(Delegate<R, T> innermost, Interceptor<T> interceptor) {
        dbc.precondition(interceptor != null, "cannot create an interceptor chain with a null interceptor");
        return new InterceptorChain<R, T>(innermost, new SingletonIterator<Interceptor<T>>(interceptor));
    }

    /**
     * Creates an interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T> the delegate parameter type
     * @param innermost the delegate to be intercepted
     * @param first the first interceptor
     * @param second the second interceptor
     * @return the resulting delegate
     */
    public <R, T> Delegate<R, T> intercept(Delegate<R, T> innermost, Interceptor<T> first, Interceptor<T> second) {
        dbc.precondition(first != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(second != null, "cannot create an interceptor chain with a null interceptor");
        final ArrayIterator<Interceptor<T>> interceptors = ArrayIterator.of(first, second);
        return new InterceptorChain<R, T>(innermost, interceptors);
    }

    /**
     * Creates an interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T> the delegate parameter type
     * @param innermost the delegate to be intercepted
     * @param first the first interceptor
     * @param second the second interceptor
     * @param third the third interceptor
     * @return the resulting delegate
     */
    public <R, T> Delegate<R, T> intercept(Delegate<R, T> innermost, Interceptor<T> first, Interceptor<T> second, Interceptor<T> third) {
        dbc.precondition(first != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(second != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(third != null, "cannot create an interceptor chain with a null interceptor");
        final ArrayIterator<Interceptor<T>> interceptors = ArrayIterator.of(first, second, third);
        return new InterceptorChain<R, T>(innermost, interceptors);
    }

    /**
     * Creates an interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T> the delegate parameter type
     * @param innermost the delegate to be intercepted
     * @param interceptors an iterable of interceptor
     * @return the resulting delegate
     */
    public <R, T, I extends Interceptor<T>> Delegate<R, T> intercept(Delegate<R, T> innermost, Iterable<I> interceptors) {
        dbc.precondition(interceptors != null, "cannot create an interceptor chain with a null iterable of interceptors");
        return new InterceptorChain<R, T>(innermost, interceptors.iterator());
    }

    /**
     * Creates an interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T> the delegate parameter type
     * @param innermost the delegate to be intercepted
     * @param interceptors an iterator of interceptor
     * @return the resulting delegate
     */
    public <R, T, I extends Interceptor<T>> Delegate<R, T> intercept(Delegate<R, T> innermost, Iterator<I> interceptors) {
        return new InterceptorChain<R, T>(innermost, interceptors);
    }

    /**
     * Creates an interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T> the delegate parameter type
     * @param innermost the delegate to be intercepted
     * @param interceptors an array of interceptor
     * @return the resulting delegate
     */
    public <R, T> Delegate<R, T> intercept(Delegate<R, T> innermost, Interceptor<T>... interceptors) {
        return new InterceptorChain<R, T>(innermost, new ArrayIterator<Interceptor<T>>(interceptors));
    }

    /**
     * Creates a binary interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param innermost the delegate to be intercepted
     * @param interceptor an interceptor
     * @return the resulting delegate
     */
    public <R, T1, T2> BinaryDelegate<R, T1, T2> intercept(BinaryDelegate<R, T1, T2> innermost, BinaryInterceptor<T1, T2> interceptor) {
        dbc.precondition(interceptor != null, "cannot create an interceptor chain with a null interceptor");
        return new BinaryInterceptorChain<R, T1, T2>(innermost, new SingletonIterator<BinaryInterceptor<T1, T2>>(interceptor));
    }

    /**
     * Creates a binary interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param innermost the delegate to be intercepted
     * @param first the first interceptor
     * @param second the second interceptor
     * @return the resulting delegate
     */
    public <R, T1, T2> BinaryDelegate<R, T1, T2> intercept(BinaryDelegate<R, T1, T2> innermost, BinaryInterceptor<T1, T2> first, BinaryInterceptor<T1, T2> second) {
        dbc.precondition(first != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(second != null, "cannot create an interceptor chain with a null interceptor");
        final ArrayIterator<BinaryInterceptor<T1, T2>> interceptors = ArrayIterator.of(first, second);
        return new BinaryInterceptorChain<R, T1, T2>(innermost, interceptors);
    }

    /**
     * Creates a binary interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param innermost the delegate to be intercepted
     * @param first the first interceptor
     * @param second the second interceptor
     * @param third the third interceptor
     * @return the resulting delegate
     */
    public <R, T1, T2> BinaryDelegate<R, T1, T2> intercept(BinaryDelegate<R, T1, T2> innermost, BinaryInterceptor<T1, T2> first, BinaryInterceptor<T1, T2> second, BinaryInterceptor<T1, T2> third) {
        dbc.precondition(first != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(second != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(third != null, "cannot create an interceptor chain with a null interceptor");
        final ArrayIterator<BinaryInterceptor<T1, T2>> interceptors = ArrayIterator.of(first, second, third);
        return new BinaryInterceptorChain<R, T1, T2>(innermost, interceptors);
    }

    /**
     * Creates a binary interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param innermost the delegate to be intercepted
     * @param interceptors an iterable of interceptors
     * @return the resulting delegate
     */
    public <R, T1, T2, I extends BinaryInterceptor<T1, T2>> BinaryDelegate<R, T1, T2> intercept(BinaryDelegate<R, T1, T2> innermost, Iterable<I> interceptors) {
        dbc.precondition(interceptors != null, "cannot create an interceptor chain with a null iterable of interceptors");
        return new BinaryInterceptorChain<R, T1, T2>(innermost, interceptors.iterator());
    }

    /**
     * Creates a binary interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param innermost the delegate to be intercepted
     * @param interceptors an iterator of interceptors
     * @return the resulting delegate
     */
    public <R, T1, T2, I extends BinaryInterceptor<T1, T2>> BinaryDelegate<R, T1, T2> intercept(BinaryDelegate<R, T1, T2> innermost, Iterator<I> interceptors) {
        return new BinaryInterceptorChain<R, T1, T2>(innermost, interceptors);
    }

    /**
     * Creates a binary interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param innermost the delegate to be intercepted
     * @param interceptors an array of interceptors
     * @return the resulting delegate
     */
    public <R, T1, T2> BinaryDelegate<R, T1, T2> intercept(BinaryDelegate<R, T1, T2> innermost, BinaryInterceptor<T1, T2>... interceptors) {
        return new BinaryInterceptorChain<R, T1, T2>(innermost, new ArrayIterator<BinaryInterceptor<T1, T2>>(interceptors));
    }

    /**
     * Creates a ternary interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param innermost the delegate to be intercepted
     * @param interceptor an interceptor
     * @return the resulting delegate
     */
    public <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> intercept(TernaryDelegate<R, T1, T2, T3> innermost, TernaryInterceptor<T1, T2, T3> interceptor) {
        dbc.precondition(interceptor != null, "cannot create an interceptor chain with a null interceptor");
        return new TernaryInterceptorChain<R, T1, T2, T3>(innermost, new SingletonIterator<TernaryInterceptor<T1, T2, T3>>(interceptor));
    }

    /**
     * Creates a ternary interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param innermost the delegate to be intercepted
     * @param first the first interceptor
     * @param second the second interceptor
     * @return the resulting delegate
     */
    public <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> intercept(TernaryDelegate<R, T1, T2, T3> innermost, TernaryInterceptor<T1, T2, T3> first, TernaryInterceptor<T1, T2, T3> second) {
        dbc.precondition(first != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(second != null, "cannot create an interceptor chain with a null interceptor");
        final ArrayIterator<TernaryInterceptor<T1, T2, T3>> interceptors = ArrayIterator.of(first, second);
        return new TernaryInterceptorChain<R, T1, T2, T3>(innermost, interceptors);
    }

    /**
     * Creates a ternary interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param innermost the delegate to be intercepted
     * @param first the first interceptor
     * @param second the second interceptor
     * @param third the third interceptor
     * @return the resulting delegate
     */
    public <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> intercept(TernaryDelegate<R, T1, T2, T3> innermost, TernaryInterceptor<T1, T2, T3> first, TernaryInterceptor<T1, T2, T3> second, TernaryInterceptor<T1, T2, T3> third) {
        dbc.precondition(first != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(second != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(third != null, "cannot create an interceptor chain with a null interceptor");
        final ArrayIterator<TernaryInterceptor<T1, T2, T3>> interceptors = ArrayIterator.of(first, second, third);
        return new TernaryInterceptorChain<R, T1, T2, T3>(innermost, interceptors);
    }

    /**
     * Creates a ternary interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param innermost the delegate to be intercepted
     * @param interceptors an iterable of interceptors
     * @return the resulting delegate
     */
    public <R, T1, T2, T3, I extends TernaryInterceptor<T1, T2, T3>> TernaryDelegate<R, T1, T2, T3> intercept(TernaryDelegate<R, T1, T2, T3> innermost, Iterable<I> interceptors) {
        dbc.precondition(interceptors != null, "cannot create an interceptor chain with a null iterable of interceptors");
        return new TernaryInterceptorChain<R, T1, T2, T3>(innermost, interceptors.iterator());
    }

    /**
     * Creates a ternary interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param innermost the delegate to be intercepted
     * @param interceptors an iterator of interceptors
     * @return the resulting delegate
     */
    public <R, T1, T2, T3, I extends TernaryInterceptor<T1, T2, T3>> TernaryDelegate<R, T1, T2, T3> intercept(TernaryDelegate<R, T1, T2, T3> innermost, Iterator<I> interceptors) {
        return new TernaryInterceptorChain<R, T1, T2, T3>(innermost, interceptors);
    }

    /**
     * Creates a ternary interceptor chain.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param innermost the delegate to be intercepted
     * @param interceptors an array of interceptors
     * @return the resulting delegate
     */
    public <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> intercept(TernaryDelegate<R, T1, T2, T3> innermost, TernaryInterceptor<T1, T2, T3>... interceptors) {
        return new TernaryInterceptorChain<R, T1, T2, T3>(innermost, new ArrayIterator<TernaryInterceptor<T1, T2, T3>>(interceptors));
    }
}
