package net.emaze.dysfunctional.options;

import java.util.Iterator;
import net.emaze.dysfunctional.adapting.ArrayIterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.filtering.Filtering;
import net.emaze.dysfunctional.iterations.Iterations;

/**
 *
 * @author rferranti
 */
public abstract class Options {

    /**
     * Transforms an iterator of Maybe<R> to an iterator of Maybe<T>.
     * @param <R> the target type
     * @param <T> the source type
     * @param maybes an iterator of Maybe<T>
     * @param delegate a transformer applied to Just Ts in the maybes
     * @return an iterator of Maybe<R>
     */
    public static <R, T> Iterator<Maybe<R>> transform(Iterator<Maybe<T>> maybes, Delegate<R, T> delegate) {
        return Iterations.transform(maybes, new WithJust<R, T>(delegate));
    }

    /**
     * Transforms an iterable of Maybe<R> to an iterator of Maybe<T>.
     * @param <R> the target type
     * @param <T> the source type
     * @param maybes an iterable of Maybe<T>
     * @param delegate a transformer applied to Just Ts in the maybes
     * @return an iterator of Maybe R
     */
    public static <R, T> Iterator<Maybe<R>> transform(Iterable<Maybe<T>> maybes, Delegate<R, T> delegate) {
        dbc.precondition(maybes != null, "cannot perform transform on a null iterable of Maybes");
        return Options.transform(maybes.iterator(), delegate);
    }

    /**
     * Transforms an array of Maybe<R> to an iterator of Maybe<T>.
     * @param <R> the target type
     * @param <T> the source type
     * @param maybes an array of Maybe<T>
     * @param delegate a transformer applied to Just Ts in the maybes
     * @return an iterator of Maybe R
     */
    public static <R, T> Iterator<Maybe<R>> transform(Maybe<T>[] maybes, Delegate<R, T> delegate) {
        return Options.transform(new ArrayIterator<Maybe<T>>(maybes), delegate);
    }

    /**
     * Filters nothings out of an Iterator of Maybe T, returning an Iterator of T.
     * @param <T> the Maybe type parameter
     * @param maybes an iterator of Maybe<T>
     * @return an iterator of T
     */
    public static <T> Iterator<T> justs(Iterator<Maybe<T>> maybes) {
        final Iterator<Maybe<T>> justs = Filtering.filter(maybes, new IsJust<T>());
        return Iterations.transform(justs, new FromJust<T>());
    }

    /**
     * Filters nothings out of an Iterable of Maybe T, returning an Iterator of T.
     * @param <T> the Maybe type parameter
     * @param maybes an iterable of Maybe<T>
     * @return an iterator of T
     */
    public static <T> Iterator<T> justs(Iterable<Maybe<T>> maybes) {
        dbc.precondition(maybes != null, "cannot perform justs on a null iterable of Maybes");
        return Options.justs(maybes.iterator());
    }

    /**
     * Filters nothings out of an array of Maybe T, returning an Iterator of T.
     * @param <T> the Maybe type parameter
     * @param maybes an array of Maybe<T>
     * @return an iterator of T
     */
    public static <T> Iterator<T> justs(Maybe<T>[] maybes) {
        return Options.justs(new ArrayIterator<Maybe<T>>(maybes));
    }
}
