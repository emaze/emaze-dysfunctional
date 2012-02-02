package net.emaze.dysfunctional;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.options.DropMaybe;
import net.emaze.dysfunctional.options.Either;
import net.emaze.dysfunctional.options.FromJust;
import net.emaze.dysfunctional.options.IsJust;
import net.emaze.dysfunctional.options.LiftDelegate;
import net.emaze.dysfunctional.options.LiftMaybe;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.options.MaybeLeft;
import net.emaze.dysfunctional.options.MaybeRight;

/**
 *
 * @author rferranti
 */
public abstract class Options {

    /**
     * Filters nothings out of an Iterator of Maybe T, returning an Iterator of
     * T.
     *
     * @param <T> the Maybe type parameter
     * @param maybes an iterator of Maybe<T>
     * @return an iterator of T
     */
    public static <T> Iterator<T> justs(Iterator<Maybe<T>> maybes) {
        final Iterator<Maybe<T>> justs = Filtering.filter(maybes, new IsJust<T>());
        return Applications.transform(justs, new FromJust<T>());
    }

    /**
     * Filters nothings out of an Iterable of Maybe T, returning an Iterator of
     * T.
     *
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
     *
     * @param <T> the Maybe type parameter
     * @param first
     * @param second
     * @return an iterator of T
     */
    public static <T> Iterator<T> justs(Maybe<T> first, Maybe<T> second) {
        return Options.justs(Iterations.iterator(first, second));
    }

    /**
     * Filters nothings out of an array of Maybe T, returning an Iterator of T.
     *
     * @param <T> the Maybe type parameter
     * @param first
     * @param second
     * @param third
     * @return an iterator of T
     */
    public static <T> Iterator<T> justs(Maybe<T> first, Maybe<T> second, Maybe<T> third) {
        return Options.justs(Iterations.iterator(first, second, third));
    }

    /**
     * Transforms null values to Maybe.nothing, non-null values to
     * Maybe.just(value)
     *
     * @param <T> the value type parameter
     * @param value the value to be lifted
     * @return a Maybe
     */
    public static <T> Maybe<T> lift(T value) {
        return new LiftMaybe<T>().perform(value);
    }

    /**
     * Transforms a delegate to another working on maybe monadic values.\
     *
     * @param <R> the delegate return type
     * @param <T> the delegate parameter type
     * @param delegate the delegate to be lifted
     * @return the transformed delegate
     */
    public static <R, T> Delegate<Maybe<R>, Maybe<T>> lift(Delegate<R, T> delegate) {
        return new LiftDelegate<R, T>(delegate);
    }

    /**
     * Applies lift (lazily) to an iterator
     *
     * @param <T> the value type parameter
     * @param iterator the iterator to be lifted
     * @return an iterator of Maybe
     */
    public static <T> Iterator<Maybe<T>> lifts(Iterator<T> iterator) {
        return Applications.transform(iterator, new LiftMaybe<T>());
    }

    /**
     * Applies lift (lazily) to an iterable
     *
     * @param <T> the value type parameter
     * @param iterable the iterable to be lifted
     * @return an iterator of Maybe
     */
    public static <T> Iterator<Maybe<T>> lifts(Iterable<T> iterable) {
        return Applications.transform(iterable, new LiftMaybe<T>());
    }

    /**
     * Applies lift (lazily) to an array
     *
     * @param <T> the value type parameter
     * @param first
     * @param second
     * @return an iterator of Maybe
     */
    public static <T> Iterator<Maybe<T>> lifts(T first, T second) {
        return Applications.transform(Iterations.iterator(first, second), new LiftMaybe<T>());
    }

    /**
     * Applies lift (lazily) to an array
     *
     * @param <T>
     * @param first
     * @param second
     * @param third
     * @return
     */
    public static <T> Iterator<Maybe<T>> lifts(T first, T second, T third) {
        return Applications.transform(Iterations.iterator(first, second, third), new LiftMaybe<T>());
    }

    /**
     * Transforms Maybe.nothing to null, Maybe.just to the wrapped value
     *
     * @param <T> the value type parameter
     * @param maybe the maybe to be dropped
     * @return null or a value
     */
    public static <T> T drop(Maybe<T> maybe) {
        return new DropMaybe<T>().perform(maybe);
    }

    /**
     * Applies drop (lazily) to an iterator
     *
     * @param <T> the value type parameter
     * @param iterator the iterator to be dropped
     * @return an iterator of T
     */
    public static <T> Iterator<T> drops(Iterator<Maybe<T>> iterator) {
        return Applications.transform(iterator, new DropMaybe<T>());
    }

    /**
     * Applies drop (lazily) to an iterator
     *
     * @param <T> the value type parameter
     * @param iterable the Iterable to be dropped
     * @return an iterator of T
     */
    public static <T> Iterator<T> drops(Iterable<Maybe<T>> iterable) {
        return Applications.transform(iterable, new DropMaybe<T>());
    }

    /**
     * Applies drop (lazily) to an array
     *
     * @param <T> the value type parameter
     * @param first
     * @param second
     * @return an iterator of T
     */
    public static <T> Iterator<T> drops(Maybe<T> first, Maybe<T> second) {
        return Applications.transform(Iterations.iterator(first, second), new DropMaybe<T>());
    }

    /**
     * Applies drop (lazily) to an array
     *
     * @param <T> the value type parameter
     * @param first
     * @param second
     * @param third
     * @return an iterator of T
     */
    public static <T> Iterator<T> drops(Maybe<T> first, Maybe<T> second, Maybe<T> third) {
        return Applications.transform(Iterations.iterator(first, second, third), new DropMaybe<T>());
    }

    /**
     * filters out right types
     *
     * @param <T1>
     * @param <T2>
     * @param eithers
     * @return
     */
    public static <T1, T2> Iterator<T1> lefts(Iterator<Either<T1, T2>> eithers) {
        dbc.precondition(eithers != null, "can not fetch lefts from a null iterator");
        return Options.justs(Applications.transform(eithers, new MaybeLeft<T1, T2>()));
    }

    /**
     * filters out right types
     *
     * @param <T1>
     * @param <T2>
     * @param eithers
     * @return
     */
    public static <T1, T2> Iterator<T1> lefts(Iterable<Either<T1, T2>> eithers) {
        dbc.precondition(eithers != null, "can not fetch lefts from a null iterable");
        return Options.justs(Applications.transform(eithers.iterator(), new MaybeLeft<T1, T2>()));
    }

    /**
     * filters out left types
     *
     * @param <T1>
     * @param <T2>
     * @param eithers
     * @return
     */
    public static <T1, T2> Iterator<T2> rights(Iterator<Either<T1, T2>> eithers) {
        dbc.precondition(eithers != null, "can not fetch rights from a null iterator");
        return Options.justs(Applications.transform(eithers, new MaybeRight<T1, T2>()));
    }

    /**
     * filters out left types
     *
     * @param <T1>
     * @param <T2>
     * @param eithers
     * @return
     */
    public static <T1, T2> Iterator<T2> rights(Iterable<Either<T1, T2>> eithers) {
        dbc.precondition(eithers != null, "can not fetch rights from a null iterator");
        return Options.justs(Applications.transform(eithers.iterator(), new MaybeRight<T1, T2>()));
    }
}
