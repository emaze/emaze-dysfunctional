package net.emaze.dysfunctional;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.interceptions.BinaryInterceptor;
import net.emaze.dysfunctional.interceptions.BinaryInterceptorChain;
import net.emaze.dysfunctional.interceptions.Interceptor;
import net.emaze.dysfunctional.interceptions.InterceptorChain;
import net.emaze.dysfunctional.interceptions.TernaryInterceptor;
import net.emaze.dysfunctional.interceptions.TernaryInterceptorChain;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.SingletonIterator;

/**
 * intercept.
 *
 * @author rferranti
 */
public abstract class Interceptors {

    /**
     * Creates an interceptor chain.
     *
     * @param <T> the function parameter type
     * @param <R> the function result type
     * @param innermost the function to be intercepted
     * @param interceptor the interceptor
     * @return the resulting function
     */
    public static <T, R> Function<T, R> intercept(Function<T, R> innermost, Interceptor<T> interceptor) {
        dbc.precondition(interceptor != null, "cannot create an interceptor chain with a null interceptor");
        return new InterceptorChain<>(innermost, new SingletonIterator<Interceptor<T>>(interceptor));
    }

    /**
     * Creates an interceptor chain.
     *
     * @param <T> the function parameter type
     * @param <R> the function result type
     * @param innermost the function to be intercepted
     * @param first the first interceptor
     * @param second the second interceptor
     * @return the resulting function
     */
    public static <T, R> Function<T, R> intercept(Function<T, R> innermost, Interceptor<T> first, Interceptor<T> second) {
        dbc.precondition(first != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(second != null, "cannot create an interceptor chain with a null interceptor");
        final ArrayIterator<Interceptor<T>> interceptors = ArrayIterator.of(first, second);
        return new InterceptorChain<>(innermost, interceptors);
    }

    /**
     * Creates an interceptor chain.
     *
     * @param <R> the function result type
     * @param <T> the function parameter type
     * @param innermost the function to be intercepted
     * @param first the first interceptor
     * @param second the second interceptor
     * @param third the third interceptor
     * @return the resulting function
     */
    public static <T, R> Function<T, R> intercept(Function<T, R> innermost, Interceptor<T> first, Interceptor<T> second, Interceptor<T> third) {
        dbc.precondition(first != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(second != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(third != null, "cannot create an interceptor chain with a null interceptor");
        final ArrayIterator<Interceptor<T>> interceptors = ArrayIterator.of(first, second, third);
        return new InterceptorChain<>(innermost, interceptors);
    }

    /**
     * Creates an interceptor chain.
     *
     * @param <T> the function parameter type
     * @param <R> the function result type
     * @param <I> the interceptor type
     * @param innermost the function to be intercepted
     * @param interceptors an iterable of interceptor
     * @return the resulting function
     */
    public static <T, R, I extends Interceptor<T>> Function<T, R> intercept(Function<T, R> innermost, Iterable<I> interceptors) {
        dbc.precondition(interceptors != null, "cannot create an interceptor chain with a null iterable of interceptors");
        return new InterceptorChain<>(innermost, interceptors.iterator());
    }

    /**
     * Creates an interceptor chain.
     *
     * @param <T> the function parameter type
     * @param <R> the function result type
     * @param <I> the interceptor type
     * @param innermost the function to be intercepted
     * @param interceptors an iterator of interceptor
     * @return the resulting function
     */
    public static <T, R, I extends Interceptor<T>> Function<T, R> intercept(Function<T, R> innermost, Iterator<I> interceptors) {
        return new InterceptorChain<>(innermost, interceptors);
    }

    /**
     * Creates an interceptor chain.
     *
     * @param <T> the function parameter type
     * @param <R> the function result type
     * @param innermost the function to be intercepted
     * @param interceptors an array of interceptor
     * @return the resulting function
     */
    public static <T, R> Function<T, R> intercept(Function<T, R> innermost, Interceptor<T>... interceptors) {
        return new InterceptorChain<>(innermost, new ArrayIterator<Interceptor<T>>(interceptors));
    }

    /**
     * Creates a binary interceptor chain.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <R> the function result type
     * @param innermost the function to be intercepted
     * @param interceptor an interceptor
     * @return the resulting function
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> intercept(BiFunction<T1, T2, R> innermost, BinaryInterceptor<T1, T2> interceptor) {
        dbc.precondition(interceptor != null, "cannot create an interceptor chain with a null interceptor");
        return new BinaryInterceptorChain<>(innermost, new SingletonIterator<BinaryInterceptor<T1, T2>>(interceptor));
    }

    /**
     * Creates a binary interceptor chain.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <R> the function result type
     * @param innermost the function to be intercepted
     * @param first the first interceptor
     * @param second the second interceptor
     * @return the resulting function
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> intercept(BiFunction<T1, T2, R> innermost, BinaryInterceptor<T1, T2> first, BinaryInterceptor<T1, T2> second) {
        dbc.precondition(first != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(second != null, "cannot create an interceptor chain with a null interceptor");
        final ArrayIterator<BinaryInterceptor<T1, T2>> interceptors = ArrayIterator.of(first, second);
        return new BinaryInterceptorChain<>(innermost, interceptors);
    }

    /**
     * Creates a binary interceptor chain.
     *
     * @param <R> the function result type
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param innermost the function to be intercepted
     * @param first the first interceptor
     * @param second the second interceptor
     * @param third the third interceptor
     * @return the resulting function
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> intercept(BiFunction<T1, T2, R> innermost, BinaryInterceptor<T1, T2> first, BinaryInterceptor<T1, T2> second, BinaryInterceptor<T1, T2> third) {
        dbc.precondition(first != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(second != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(third != null, "cannot create an interceptor chain with a null interceptor");
        final ArrayIterator<BinaryInterceptor<T1, T2>> interceptors = ArrayIterator.of(first, second, third);
        return new BinaryInterceptorChain<>(innermost, interceptors);
    }

    /**
     * Creates a binary interceptor chain.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <I> the binary interceptor type
     * @param <R> the function result type
     * @param innermost the function to be intercepted
     * @param interceptors an iterable of interceptors
     * @return the resulting function
     */
    public static <T1, T2, I extends BinaryInterceptor<T1, T2>, R> BiFunction<T1, T2, R> intercept(BiFunction<T1, T2, R> innermost, Iterable<I> interceptors) {
        dbc.precondition(interceptors != null, "cannot create an interceptor chain with a null iterable of interceptors");
        return new BinaryInterceptorChain<>(innermost, interceptors.iterator());
    }

    /**
     * Creates a binary interceptor chain.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <I> the interceptor type
     * @param <R> the function result type
     * @param innermost the function to be intercepted
     * @param interceptors an iterator of interceptors
     * @return the resulting function
     */
    public static <T1, T2, I extends BinaryInterceptor<T1, T2>, R> BiFunction<T1, T2, R> intercept(BiFunction<T1, T2, R> innermost, Iterator<I> interceptors) {
        return new BinaryInterceptorChain<>(innermost, interceptors);
    }

    /**
     * Creates a binary interceptor chain.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <R> the function result type
     * @param innermost the function to be intercepted
     * @param interceptors an array of interceptors
     * @return the resulting function
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> intercept(BiFunction<T1, T2, R> innermost, BinaryInterceptor<T1, T2>... interceptors) {
        return new BinaryInterceptorChain<>(innermost, new ArrayIterator<BinaryInterceptor<T1, T2>>(interceptors));
    }

    /**
     * Creates a ternary interceptor chain.
     *
     * @param <R> the function result type
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param innermost the function to be intercepted
     * @param interceptor an interceptor
     * @return the resulting function
     */
    public static <T1, T2, T3, R> TriFunction<T1, T2, T3, R> intercept(TriFunction<T1, T2, T3, R> innermost, TernaryInterceptor<T1, T2, T3> interceptor) {
        dbc.precondition(interceptor != null, "cannot create an interceptor chain with a null interceptor");
        return new TernaryInterceptorChain<T1, T2, T3, R>(innermost, new SingletonIterator<TernaryInterceptor<T1, T2, T3>>(interceptor));
    }

    /**
     * Creates a ternary interceptor chain.
     *
     * @param <R> the function result type
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param innermost the function to be intercepted
     * @param first the first interceptor
     * @param second the second interceptor
     * @return the resulting function
     */
    public static <T1, T2, T3, R> TriFunction<T1, T2, T3, R> intercept(TriFunction<T1, T2, T3, R> innermost, TernaryInterceptor<T1, T2, T3> first, TernaryInterceptor<T1, T2, T3> second) {
        dbc.precondition(first != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(second != null, "cannot create an interceptor chain with a null interceptor");
        final ArrayIterator<TernaryInterceptor<T1, T2, T3>> interceptors = ArrayIterator.of(first, second);
        return new TernaryInterceptorChain<T1, T2, T3, R>(innermost, interceptors);
    }

    /**
     * Creates a ternary interceptor chain.
     *
     * @param <R> the function result type
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param innermost the function to be intercepted
     * @param first the first interceptor
     * @param second the second interceptor
     * @param third the third interceptor
     * @return the resulting function
     */
    public static <T1, T2, T3, R> TriFunction<T1, T2, T3, R> intercept(TriFunction<T1, T2, T3, R> innermost, TernaryInterceptor<T1, T2, T3> first, TernaryInterceptor<T1, T2, T3> second, TernaryInterceptor<T1, T2, T3> third) {
        dbc.precondition(first != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(second != null, "cannot create an interceptor chain with a null interceptor");
        dbc.precondition(third != null, "cannot create an interceptor chain with a null interceptor");
        final ArrayIterator<TernaryInterceptor<T1, T2, T3>> interceptors = ArrayIterator.of(first, second, third);
        return new TernaryInterceptorChain<T1, T2, T3, R>(innermost, interceptors);
    }

    /**
     * Creates a ternary interceptor chain.
     *
     * @param <R> the function result type
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param <I> the interceptor type
     * @param innermost the function to be intercepted
     * @param interceptors an iterable of interceptors
     * @return the resulting function
     */
    public static <R, T1, T2, T3, I extends TernaryInterceptor<T1, T2, T3>> TriFunction<T1, T2, T3, R> intercept(TriFunction<T1, T2, T3, R> innermost, Iterable<I> interceptors) {
        dbc.precondition(interceptors != null, "cannot create an interceptor chain with a null iterable of interceptors");
        return new TernaryInterceptorChain<T1, T2, T3, R>(innermost, interceptors.iterator());
    }

    /**
     * Creates a ternary interceptor chain.
     *
     * @param <R> the function result type
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param <I> the ternary interceptor type
     * @param innermost the function to be intercepted
     * @param interceptors an iterator of interceptors
     * @return the resulting function
     */
    public static <R, T1, T2, T3, I extends TernaryInterceptor<T1, T2, T3>> TriFunction<T1, T2, T3, R> intercept(TriFunction<T1, T2, T3, R> innermost, Iterator<I> interceptors) {
        return new TernaryInterceptorChain<T1, T2, T3, R>(innermost, interceptors);
    }

    /**
     * Creates a ternary interceptor chain.
     *
     * @param <R> the function result type
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param innermost the function to be intercepted
     * @param interceptors an array of interceptors
     * @return the resulting function
     */
    public static <T1, T2, T3, R> TriFunction<T1, T2, T3, R> intercept(TriFunction<T1, T2, T3, R> innermost, TernaryInterceptor<T1, T2, T3>... interceptors) {
        return new TernaryInterceptorChain<T1, T2, T3, R>(innermost, new ArrayIterator<TernaryInterceptor<T1, T2, T3>>(interceptors));
    }
}
