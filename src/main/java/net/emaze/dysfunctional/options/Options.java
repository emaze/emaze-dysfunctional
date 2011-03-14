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

    /**
     * Transforms null values to Maybe.nothing, non-null values to Maybe.just(value)
     * @param <T> the value type parameter
     * @param value the value to be lifted
     * @return a Maybe
     */
    public static <T> Maybe<T> lift(T value) {
        return new LiftMaybe<T>().perform(value);
    }

    public static <T> Iterator<Maybe<T>> lifts(Iterator<T> iterator) {
        return Iterations.transform(iterator, new LiftMaybe<T>());
    }

    public static <T> Iterator<Maybe<T>> lifts(Iterable<T> iterable) {
        return Iterations.transform(iterable, new LiftMaybe<T>());
    }

    public static <T> Iterator<Maybe<T>> lifts(T... array) {
        return Iterations.transform(array, new LiftMaybe<T>());
    }

    /**
     * Transforms Maybe.nothing to null, Maybe.just to the wrapped value
     * @param <T> the value type parameter
     * @param maybe the maybe to be dropped
     * @return null or a value
     */
    public static <T> T drop(Maybe<T> maybe) {
        return new DropMaybe<T>().perform(maybe);
    }

    public static <T> Iterator<T> drops(Iterator<Maybe<T>> iterator) {
        return Iterations.transform(iterator, new DropMaybe<T>());
    }

    public static <T> Iterator<T> drops(Iterable<Maybe<T>> iterator) {
        return Iterations.transform(iterator, new DropMaybe<T>());
    }

    public static <T> Iterator<T> drops(Maybe<T>... array) {
        return Iterations.transform(array, new DropMaybe<T>());
    }
}
