package net.emaze.dysfunctional;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.filtering.FilteringIterator;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.SingletonIterator;
import net.emaze.dysfunctional.iterations.TransformingIterator;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.options.DropMaybe;
import net.emaze.dysfunctional.options.Either;
import net.emaze.dysfunctional.options.FmapMaybe;
import net.emaze.dysfunctional.options.FromJust;
import net.emaze.dysfunctional.options.IsJust;
import net.emaze.dysfunctional.options.LiftMaybe;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.options.MaybeLeft;
import net.emaze.dysfunctional.options.MaybeRight;
import net.emaze.dysfunctional.options.PureBox;
import net.emaze.dysfunctional.options.PureEither;
import net.emaze.dysfunctional.options.PureMaybe;

/**
 *
 * @author rferranti
 */
public abstract class Options {

    public abstract static class Maybes {

        /**
         *
         * @param <T>
         * @param value
         * @return
         */
        public static <T> Maybe<T> pure(T value) {
            return new PureMaybe<T>().perform(value);
        }

        /**
         *
         * @param <T>
         * @param values
         * @return
         */
        public static <T> Iterator<Maybe<T>> pures(Iterator<T> values) {
            return new TransformingIterator<Maybe<T>, T>(values, new PureMaybe<T>());
        }

        /**
         *
         * @param <T>
         * @param values
         * @return
         */
        public static <T> Iterator<Maybe<T>> pures(Iterable<T> values) {
            dbc.precondition(values != null, "cannot perform pures on a null iterable");
            return new TransformingIterator<Maybe<T>, T>(values.iterator(), new PureMaybe<T>());
        }

        /**
         *
         * @param <T>
         * @param value
         * @return
         */
        public static <T> Iterator<Maybe<T>> pures(T value) {
            return new TransformingIterator<Maybe<T>, T>(new SingletonIterator<T>(value), new PureMaybe<T>());
        }

        /**
         *
         * @param <T>
         * @param first
         * @param second
         * @return
         */
        public static <T> Iterator<Maybe<T>> pures(T first, T second) {
            return new TransformingIterator<Maybe<T>, T>(Iterations.iterator(first, second), new PureMaybe<T>());
        }

        /**
         *
         * @param <T>
         * @param first
         * @param second
         * @param third
         * @return
         */
        public static <T> Iterator<Maybe<T>> pures(T first, T second, T third) {
            return new TransformingIterator<Maybe<T>, T>(Iterations.iterator(first, second, third), new PureMaybe<T>());
        }

        /**
         *
         * @param <T>
         * @param values
         * @return
         */
        public static <T> Iterator<Maybe<T>> pures(T... values) {
            return new TransformingIterator<Maybe<T>, T>(Iterations.iterator(values), new PureMaybe<T>());
        }

        /**
         * Filters nothings out of an Iterator of Maybe T, returning an Iterator
         * of T.
         *
         * @param <T> the Maybe type parameter
         * @param maybes an iterator of Maybe<T>
         * @return an iterator of T
         */
        public static <T> Iterator<T> justs(Iterator<Maybe<T>> maybes) {
            final Iterator<Maybe<T>> justs = new FilteringIterator<Maybe<T>>(maybes, new IsJust<T>());
            return new TransformingIterator<T, Maybe<T>>(justs, new FromJust<T>());
        }

        /**
         * Filters nothings out of an Iterable of Maybe T, returning an Iterator
         * of T.
         *
         * @param <T> the Maybe type parameter
         * @param maybes an iterable of Maybe<T>
         * @return an iterator of T
         */
        public static <T> Iterator<T> justs(Iterable<Maybe<T>> maybes) {
            dbc.precondition(maybes != null, "cannot perform justs on a null iterable of Maybes");
            final Iterator<Maybe<T>> justs = new FilteringIterator<Maybe<T>>(maybes.iterator(), new IsJust<T>());
            return new TransformingIterator<T, Maybe<T>>(justs, new FromJust<T>());
        }

        /**
         * Filters nothings out of an array of Maybe T, returning an Iterator of
         * T.
         *
         * @param <T> the Maybe type parameter
         * @param first
         * @param second
         * @return an iterator of T
         */
        public static <T> Iterator<T> justs(Maybe<T> first, Maybe<T> second) {
            final Iterator<Maybe<T>> iterator = Iterations.iterator(first, second);
            final Iterator<Maybe<T>> justs = Filtering.filter(iterator, new IsJust<T>());
            return new TransformingIterator<T, Maybe<T>>(justs, new FromJust<T>());
        }

        /**
         * Filters nothings out of an array of Maybe T, returning an Iterator of
         * T.
         *
         * @param <T> the Maybe type parameter
         * @param first
         * @param second
         * @param third
         * @return an iterator of T
         */
        public static <T> Iterator<T> justs(Maybe<T> first, Maybe<T> second, Maybe<T> third) {
            final Iterator<Maybe<T>> iterator = Iterations.iterator(first, second, third);
            final Iterator<Maybe<T>> justs = Filtering.filter(iterator, new IsJust<T>());
            return new TransformingIterator<T, Maybe<T>>(justs, new FromJust<T>());
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
         * Transforms a delegate to another working on maybe monadic values.
         *
         * @param <R> the delegate return type
         * @param <T> the delegate parameter type
         * @param delegate the delegate to be lifted
         * @return the transformed delegate
         */
        public static <R, T> Delegate<Maybe<R>, Maybe<T>> lift(Delegate<R, T> delegate) {
            return new FmapMaybe<R, T>(delegate);
        }

        /**
         * Applies lift (lazily) to an iterator
         *
         * @param <T> the value type parameter
         * @param iterator the iterator to be lifted
         * @return an iterator of Maybe
         */
        public static <T> Iterator<Maybe<T>> lifts(Iterator<T> iterator) {
            return new TransformingIterator<Maybe<T>, T>(iterator, new LiftMaybe<T>());
        }

        /**
         * Applies lift (lazily) to an iterable
         *
         * @param <T> the value type parameter
         * @param iterable the iterable to be lifted
         * @return an iterator of Maybe
         */
        public static <T> Iterator<Maybe<T>> lifts(Iterable<T> iterable) {
            dbc.precondition(iterable != null, "cannot perform lifts on a null iterable");
            return new TransformingIterator<Maybe<T>, T>(iterable.iterator(), new LiftMaybe<T>());
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
            final Iterator<T> iterator = Iterations.iterator(first, second);
            return new TransformingIterator<Maybe<T>, T>(iterator, new LiftMaybe<T>());
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
            final Iterator<T> iterator = Iterations.iterator(first, second, third);
            return new TransformingIterator<Maybe<T>, T>(iterator, new LiftMaybe<T>());
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
            return new TransformingIterator<T, Maybe<T>>(iterator, new DropMaybe<T>());
        }

        /**
         * Applies drop (lazily) to an iterator
         *
         * @param <T> the value type parameter
         * @param iterable the Iterable to be dropped
         * @return an iterator of T
         */
        public static <T> Iterator<T> drops(Iterable<Maybe<T>> iterable) {
            dbc.precondition(iterable != null, "cannot perform drops on a null iterable");
            return new TransformingIterator<T, Maybe<T>>(iterable.iterator(), new DropMaybe<T>());
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
            return new TransformingIterator<T, Maybe<T>>(Iterations.iterator(first, second), new DropMaybe<T>());
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
            return new TransformingIterator<T, Maybe<T>>(Iterations.iterator(first, second, third), new DropMaybe<T>());
        }
    }

    public abstract static class Boxes {

        /**
         *
         * @param <T>
         * @param value
         * @return
         */
        public static <T> Box<T> pure(T value) {
            return new PureBox<T>().perform(value);
        }

        /**
         *
         * @param <T>
         * @param values
         * @return
         */
        public static <T> Iterator<Box<T>> pures(Iterator<T> values) {
            return new TransformingIterator<Box<T>, T>(values, new PureBox<T>());
        }

        /**
         *
         * @param <T>
         * @param values
         * @return
         */
        public static <T> Iterator<Box<T>> pures(Iterable<T> values) {
            dbc.precondition(values != null, "cannot perform pures on a null iterable");
            return new TransformingIterator<Box<T>, T>(values.iterator(), new PureBox<T>());
        }

        /**
         *
         * @param <T>
         * @param value
         * @return
         */
        public static <T> Iterator<Box<T>> pures(T value) {
            return new TransformingIterator<Box<T>, T>(new SingletonIterator<T>(value), new PureBox<T>());
        }

        /**
         *
         * @param <T>
         * @param first
         * @param second
         * @return
         */
        public static <T> Iterator<Box<T>> pures(T first, T second) {
            return new TransformingIterator<Box<T>, T>(Iterations.iterator(first, second), new PureBox<T>());
        }

        /**
         *
         * @param <T>
         * @param first
         * @param second
         * @param third
         * @return
         */
        public static <T> Iterator<Box<T>> pures(T first, T second, T third) {
            return new TransformingIterator<Box<T>, T>(Iterations.iterator(first, second, third), new PureBox<T>());
        }

        /**
         *
         * @param <T>
         * @param values
         * @return
         */
        public static <T> Iterator<Box<T>> pures(T... values) {
            return new TransformingIterator<Box<T>, T>(Iterations.iterator(values), new PureBox<T>());
        }
    }

    public abstract static class Eithers {

        /**
         *
         * @param <LT>
         * @param <RT>
         * @param value
         * @return
         */
        public static <LT, RT> Either<LT, RT> pure(RT value) {
            return new PureEither<LT, RT>().perform(value);
        }

        /**
         *
         * @param <LT>
         * @param <RT>
         * @param leftClass
         * @param value
         * @return
         */
        public static <LT, RT> Either<LT, RT> pure(Class<LT> leftClass, RT value) {
            return new PureEither<LT, RT>().perform(value);
        }

        /**
         *
         * @param <LT>
         * @param <RT>
         * @param values
         * @return
         */
        public static <LT, RT> Iterator<Either<LT, RT>> pures(Iterator<RT> values) {
            return new TransformingIterator<Either<LT, RT>, RT>(values, new PureEither<LT, RT>());
        }

        /**
         *
         * @param <LT>
         * @param <RT>
         * @param values
         * @return
         */
        public static <LT, RT> Iterator<Either<LT, RT>> pures(Iterable<RT> values) {
            dbc.precondition(values != null, "cannot perform pures on a null iterable");
            return new TransformingIterator<Either<LT, RT>, RT>(values.iterator(), new PureEither<LT, RT>());
        }

        /**
         *
         * @param <LT>
         * @param <RT>
         * @param value
         * @return
         */
        public static <LT, RT> Iterator<Either<LT, RT>> pures(RT value) {
            return new TransformingIterator<Either<LT, RT>, RT>(new SingletonIterator<RT>(value), new PureEither<LT, RT>());
        }

        /**
         *
         * @param <LT>
         * @param <RT>
         * @param first
         * @param second
         * @return
         */
        public static <LT, RT> Iterator<Either<LT, RT>> pures(RT first, RT second) {
            return new TransformingIterator<Either<LT, RT>, RT>(Iterations.iterator(first, second), new PureEither<LT, RT>());
        }

        /**
         *
         * @param <LT>
         * @param <RT>
         * @param first
         * @param second
         * @param third
         * @return
         */
        public static <LT, RT> Iterator<Either<LT, RT>> pures(RT first, RT second, RT third) {
            return new TransformingIterator<Either<LT, RT>, RT>(Iterations.iterator(first, second, third), new PureEither<LT, RT>());
        }

        /**
         *
         * @param <LT>
         * @param <RT>
         * @param values
         * @return
         */
        public static <LT, RT> Iterator<Either<LT, RT>> pures(RT... values) {
            return new TransformingIterator<Either<LT, RT>, RT>(ArrayIterator.of(values), new PureEither<LT, RT>());
        }

        /**
         * filters out right types
         *
         * @param LT
         * @param RT
         * @param eithers
         * @return
         */
        public static <LT, RT> Iterator<LT> lefts(Iterator<Either<LT, RT>> eithers) {
            dbc.precondition(eithers != null, "can not fetch lefts from a null iterator");
            final Iterator<Maybe<LT>> maybes = new TransformingIterator<Maybe<LT>, Either<LT, RT>>(eithers, new MaybeLeft<LT, RT>());
            final Iterator<Maybe<LT>> justs = new FilteringIterator<Maybe<LT>>(maybes, new IsJust<LT>());
            return new TransformingIterator<LT, Maybe<LT>>(justs, new FromJust<LT>());
        }

        /**
         * filters out right types
         *
         * @param LT
         * @param RT
         * @param eithers
         * @return
         */
        public static <LT, RT> Iterator<LT> lefts(Iterable<Either<LT, RT>> eithers) {
            dbc.precondition(eithers != null, "can not fetch lefts from a null iterable");
            final Iterator<Maybe<LT>> maybes = new TransformingIterator<Maybe<LT>, Either<LT, RT>>(eithers.iterator(), new MaybeLeft<LT, RT>());
            final Iterator<Maybe<LT>> justs = new FilteringIterator<Maybe<LT>>(maybes, new IsJust<LT>());
            return new TransformingIterator<LT, Maybe<LT>>(justs, new FromJust<LT>());
        }

        /**
         * filters out left types
         *
         * @param LT
         * @param RT
         * @param eithers
         * @return
         */
        public static <LT, RT> Iterator<RT> rights(Iterator<Either<LT, RT>> eithers) {
            dbc.precondition(eithers != null, "can not fetch rights from a null iterator");
            final Iterator<Maybe<RT>> maybes = new TransformingIterator<Maybe<RT>, Either<LT, RT>>(eithers, new MaybeRight<LT, RT>());
            final Iterator<Maybe<RT>> justs = new FilteringIterator<Maybe<RT>>(maybes, new IsJust<RT>());
            return new TransformingIterator<RT, Maybe<RT>>(justs, new FromJust<RT>());
        }

        /**
         * filters out left types
         *
         * @param LT
         * @param RT
         * @param eithers
         * @return
         */
        public static <LT, RT> Iterator<RT> rights(Iterable<Either<LT, RT>> eithers) {
            dbc.precondition(eithers != null, "can not fetch rights from a null iterator");
            final Iterator<Maybe<RT>> maybes = new TransformingIterator<Maybe<RT>, Either<LT, RT>>(eithers.iterator(), new MaybeRight<LT, RT>());
            final Iterator<Maybe<RT>> justs = new FilteringIterator<Maybe<RT>>(maybes, new IsJust<RT>());
            return new TransformingIterator<RT, Maybe<RT>>(justs, new FromJust<RT>());
        }
    }
}
