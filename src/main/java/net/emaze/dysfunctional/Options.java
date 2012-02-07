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

    /**
     * pure, pures, justs, lift, lifts, drop, drops.
     */
    public abstract static class Maybes {

        /**
         * Yields Maybe.pure() of a value. (Just value) E.g:
         * <code>Maybes.pure(1) -> Maybe.just(1)</code>
         *
         * @param <T> the value type
         * @param value the value to be transformed
         * @return the resulting maybe
         */
        public static <T> Maybe<T> pure(T value) {
            return new PureMaybe<T>().perform(value);
        }

        /**
         * Creates an iterator transforming values from the source iterator into
         * pure() Maybe<T> monadic values. E.g:
         * <code>Maybes.pures([1,2,3]) -> [Just 1, Just 2, Just 3]</code>
         *
         * @param <T> the iterator element type
         * @param values the source iterator
         * @return the resulting iterator
         */
        public static <T> Iterator<Maybe<T>> pures(Iterator<T> values) {
            return new TransformingIterator<Maybe<T>, T>(values, new PureMaybe<T>());
        }

        /**
         * Creates an iterator transforming values from the source iterable into
         * pure() Maybe<T> monadic values. E.g:
         * <code>Maybes.pures([1,2,3]) -> [Just 1, Just 2, Just 3]</code>
         *
         * @param <T> the iterable element type
         * @param values the source iterable
         * @return the resulting iterator
         */
        public static <T> Iterator<Maybe<T>> pures(Iterable<T> values) {
            dbc.precondition(values != null, "cannot perform pures on a null iterable");
            return new TransformingIterator<Maybe<T>, T>(values.iterator(), new PureMaybe<T>());
        }

        /**
         * Creates a singleton iterator yielding pure() Maybe<T> monadic value
         * of the passed value. E.g:
         * <code>Maybes.pures(1) -> [Just 1]</code>
         *
         * @param <T> the element type
         * @param value the source value
         * @return the resulting iterator
         */
        public static <T> Iterator<Maybe<T>> pures(T value) {
            return new TransformingIterator<Maybe<T>, T>(new SingletonIterator<T>(value), new PureMaybe<T>());
        }

        /**
         * Creates an iterator yielding pure() Maybe<T> monadic value of
         * the passed values. E.g:
         * <code>Maybes.pures(1, 2) -> [Just 1, Just 2]</code>
         *
         * @param <T> the elements type
         * @param first the first element
         * @param second the second element
         * @return the resulting iterator
         */
        public static <T> Iterator<Maybe<T>> pures(T first, T second) {
            return new TransformingIterator<Maybe<T>, T>(Iterations.iterator(first, second), new PureMaybe<T>());
        }

        /**
         * Creates an iterator yielding pure() Maybe<T> monadic value of
         * the passed values. E.g:
         * <code>Maybes.pures(1, 2, 3) -> [Just 1, Just 2, Just 3]</code>
         *
         * @param <T> the elements type
         * @param first the first element
         * @param second the second element
         * @param third the third element
         * @return the resulting iterator
         */
        public static <T> Iterator<Maybe<T>> pures(T first, T second, T third) {
            return new TransformingIterator<Maybe<T>, T>(Iterations.iterator(first, second, third), new PureMaybe<T>());
        }

        /**
         * Creates an iterator yielding values pure() Maybe<T> monadic value of
         * the passed values. E.g:
         * <code>Maybes.pures(1, 2, 3, 4) -> [Just 1, Just 2, Just 3, Just 4]</code>
         *
         * @param <T> the array element type
         * @param values the source array
         * @return the resulting array
         */
        public static <T> Iterator<Maybe<T>> pures(T... values) {
            return new TransformingIterator<Maybe<T>, T>(Iterations.iterator(values), new PureMaybe<T>());
        }

        /**
         * Filters nothings out of an Iterator of Maybe T, returning an Iterator
         * of T. E.g:
         * <code>Maybes.justs([Just 1, Nothing, Just null]) -> [1, null]</code>
         *
         * @param <T> the Maybe type parameter
         * @param maybes an iterator of Maybe<T>
         * @return the resulting iterator
         */
        public static <T> Iterator<T> justs(Iterator<Maybe<T>> maybes) {
            final Iterator<Maybe<T>> justs = new FilteringIterator<Maybe<T>>(maybes, new IsJust<T>());
            return new TransformingIterator<T, Maybe<T>>(justs, new FromJust<T>());
        }

        /**
         * Filters nothings out of an Iterable of Maybe T, returning an Iterator
         * of T. E.g:
         * <code>Maybes.justs([Just 1, Nothing, Just null]) -> [1, null]</code>
         *
         * @param <T> the Maybe type parameter
         * @param maybes an iterable of Maybe<T>
         * @return the resulting iterator
         */
        public static <T> Iterator<T> justs(Iterable<Maybe<T>> maybes) {
            dbc.precondition(maybes != null, "cannot perform justs on a null iterable of Maybes");
            final Iterator<Maybe<T>> justs = new FilteringIterator<Maybe<T>>(maybes.iterator(), new IsJust<T>());
            return new TransformingIterator<T, Maybe<T>>(justs, new FromJust<T>());
        }

        /**
         * Filters nothings out of an array of Maybe T, returning an Iterator of
         * T. E.g:
         * <code>Maybes.justs([Nothing, Just null]) -> [null]</code>
         *
         * @param <T> the Maybe type parameter
         * @param first the first element
         * @param second the second element
         * @return the resulting iterator
         */
        public static <T> Iterator<T> justs(Maybe<T> first, Maybe<T> second) {
            final Iterator<Maybe<T>> iterator = Iterations.iterator(first, second);
            final Iterator<Maybe<T>> justs = Filtering.filter(iterator, new IsJust<T>());
            return new TransformingIterator<T, Maybe<T>>(justs, new FromJust<T>());
        }

        /**
         * Filters nothings out of an array of Maybe T, returning an Iterator of
         * T. E.g:
         * <code>Maybes.justs([Just 1, Nothing, Just null]) -> [1, null]</code>
         *
         * @param <T> the Maybe type parameter
         * @param first the first element
         * @param second the second element
         * @param third the third element
         * @return the resulting iterator
         */
        public static <T> Iterator<T> justs(Maybe<T> first, Maybe<T> second, Maybe<T> third) {
            final Iterator<Maybe<T>> iterator = Iterations.iterator(first, second, third);
            final Iterator<Maybe<T>> justs = Filtering.filter(iterator, new IsJust<T>());
            return new TransformingIterator<T, Maybe<T>>(justs, new FromJust<T>());
        }

        /**
         * Transforms a null value to Maybe.nothing, a non-null value to
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
         * Creates an iterator transforming elements from the source iterator to
         * Maybe.nothing when the source element is null, to
         * Maybe.just(sourceValue) otherwise. E.g:
         * <code>Maybes.lift([null, 1, 2]) -> [Nothing, Just 1, Just 2]</code>
         *
         * @param <T> the value type parameter
         * @param iterator the iterator to be lifted
         * @return the resulting iterator
         */
        public static <T> Iterator<Maybe<T>> lifts(Iterator<T> iterator) {
            return new TransformingIterator<Maybe<T>, T>(iterator, new LiftMaybe<T>());
        }

        /**
         * Creates an iterator transforming elements from the source iterable to
         * Maybe.nothing when the source element is null, to
         * Maybe.just(sourceValue) otherwise. E.g:
         * <code>Maybes.lift([null, 1, 2]) -> [Nothing, Just 1, Just 2]</code>
         *
         * @param <T> the value type parameter
         * @param iterable the iterable to be lifted
         * @return the resulting iterator
         */
        public static <T> Iterator<Maybe<T>> lifts(Iterable<T> iterable) {
            dbc.precondition(iterable != null, "cannot perform lifts on a null iterable");
            return new TransformingIterator<Maybe<T>, T>(iterable.iterator(), new LiftMaybe<T>());
        }

        /**
         * Creates an iterator transforming passed values to Maybe.nothing when
         * the source element is null, to Maybe.just(sourceValue) otherwise.
         * E.g:
         * <code>Maybes.lift(null, 1) -> [Nothing, Just 1]</code>
         *
         * @param <T> the value type
         * @param first the first element
         * @param second the second element
         * @return the resulting iterator
         */
        public static <T> Iterator<Maybe<T>> lifts(T first, T second) {
            final Iterator<T> iterator = Iterations.iterator(first, second);
            return new TransformingIterator<Maybe<T>, T>(iterator, new LiftMaybe<T>());
        }

        /**
         * Creates an iterator transforming passed values to Maybe.nothing when
         * the source element is null, to Maybe.just(sourceValue) otherwise.
         * E.g:
         * <code>Maybes.lift(null, 1, 3) -> [Nothing, Just 1, Just 3]</code>
         *
         * @param <T> the value type
         * @param first the first element
         * @param second the second element
         * @param third the third element
         * @return the resulting iterator
         */
        public static <T> Iterator<Maybe<T>> lifts(T first, T second, T third) {
            final Iterator<T> iterator = Iterations.iterator(first, second, third);
            return new TransformingIterator<Maybe<T>, T>(iterator, new LiftMaybe<T>());
        }

        /**
         * Transforms a Maybe.nothing to null, a Maybe.just to the wrapped
         * value. E.g:
         * <code>Maybes.drop(Just 1) -> 1</code>
         * <code>Maybes.drop(Nothing) -> null</code>
         *
         * @param <T> the value type parameter
         * @param maybe the maybe to be dropped
         * @return null or a value
         */
        public static <T> T drop(Maybe<T> maybe) {
            return new DropMaybe<T>().perform(maybe);
        }

        /**
         * Creates an iterator transforming Maybes from the source iterator to
         * null when the source element is Nothing, to the wrapped value
         * otherwise. E.g:
         * <code>Maybes.drops([Just null, Nothing, Just 3]) -> [null, null, 3]</code>
         *
         * @param <T> the value type parameter
         * @param iterator the iterator to be dropped
         * @return an iterator of T
         */
        public static <T> Iterator<T> drops(Iterator<Maybe<T>> iterator) {
            return new TransformingIterator<T, Maybe<T>>(iterator, new DropMaybe<T>());
        }

        /**
         * Creates an iterator transforming Maybes from the source iterable to
         * null when the source element is Nothing, to the wrapped value
         * otherwise. E.g:
         * <code>Maybes.drops([Just null, Nothing, Just 3]) -> [null, null, 3]</code>
         *
         * @param <T> the value type parameter
         * @param iterable the Iterable to be dropped
         * @return the resulting iterator
         */
        public static <T> Iterator<T> drops(Iterable<Maybe<T>> iterable) {
            dbc.precondition(iterable != null, "cannot perform drops on a null iterable");
            return new TransformingIterator<T, Maybe<T>>(iterable.iterator(), new DropMaybe<T>());
        }

        /**
         * Creates an iterator transformed passed elements such as the become
         * null when the source element is Nothing, the wrapped value otherwise.
         * E.g:
         * <code>Maybes.drops([Just null, Just 3]) -> [null, 3]</code>
         *
         * @param <T> the value type parameter
         * @param first the first maybe
         * @param second the second maybe
         * @return the resulting iterator
         */
        public static <T> Iterator<T> drops(Maybe<T> first, Maybe<T> second) {
            return new TransformingIterator<T, Maybe<T>>(Iterations.iterator(first, second), new DropMaybe<T>());
        }

        /**
         * Creates an iterator transformed passed elements such as the become
         * null when the source element is Nothing, the wrapped value otherwise.
         * E.g:
         * <code>Maybes.drops([Just null, Just 3]) -> [null, 3]</code>
         *
         * @param <T> the value type parameter
         * @param first the first maybe
         * @param second the second maybe
         * @param third the third maybe
         * @return the resulting iterator
         */
        public static <T> Iterator<T> drops(Maybe<T> first, Maybe<T> second, Maybe<T> third) {
            return new TransformingIterator<T, Maybe<T>>(Iterations.iterator(first, second, third), new DropMaybe<T>());
        }
    }

    /**
     * pure, pures.
     */
    public abstract static class Boxes {

        /**
         * Yields Boxes.pure() of a value. (Box.of(value)) E.g:
         * <code>Boxes.pure(1) -> Box.of(1)</code>
         *
         * @param <T> the value type
         * @param value the value to be transformed
         * @return the resulting box
         */
        public static <T> Box<T> pure(T value) {
            return new PureBox<T>().perform(value);
        }

        /**
         * Creates an iterator transforming values from the source iterator into
         * pure() Box<T> monadic values. E.g:
         * <code>Boxes.pures([1,2,3]) -> [Box 1, Box 2, Box 3]</code>
         *
         * @param <T> the iterator element type
         * @param values the source iterator
         * @return the resulting iterator
         */
        public static <T> Iterator<Box<T>> pures(Iterator<T> values) {
            return new TransformingIterator<Box<T>, T>(values, new PureBox<T>());
        }

        /**
         * Creates an iterator transforming values from the source iterable into
         * pure() Box<T> monadic values. E.g:
         * <code>Boxes.pures([1,2,3]) -> [Box 1, Box 2, Box 3]</code>
         *
         * @param <T> the iterator element type
         * @param values the source iterator
         * @return the resulting iterator
         */
        public static <T> Iterator<Box<T>> pures(Iterable<T> values) {
            dbc.precondition(values != null, "cannot perform pures on a null iterable");
            return new TransformingIterator<Box<T>, T>(values.iterator(), new PureBox<T>());
        }

        /**
         * Creates a singleton iterator yielding pure() Box<T> monadic value of
         * the passed value. E.g:
         * <code>Boxes.pures(1) -> [Box 1]</code>
         *
         * @param <T> the element type
         * @param value the source value
         * @return the resulting iterator
         */
        public static <T> Iterator<Box<T>> pures(T value) {
            return new TransformingIterator<Box<T>, T>(new SingletonIterator<T>(value), new PureBox<T>());
        }

        /**
         * Creates an iterator yielding values pure() Box<T> monadic value of
         * the passed values. E.g:
         * <code>Boxes.pures(1, 2) -> [Box 1, Box 2]</code>
         *
         * @param <T> the elements type
         * @param first the first element
         * @param second the second element
         * @return the resulting iterator
         */
        public static <T> Iterator<Box<T>> pures(T first, T second) {
            return new TransformingIterator<Box<T>, T>(Iterations.iterator(first, second), new PureBox<T>());
        }

        /**
         * Creates an iterator yielding values pure() Box<T> monadic value of
         * the passed values. E.g:
         * <code>Boxes.pures(1, 2, 3) -> [Box 1, Box 2, Box 3]</code>
         *
         * @param <T> the elements type
         * @param first the first element
         * @param second the second element
         * @param third the third element
         * @return the resulting iterator
         */
        public static <T> Iterator<Box<T>> pures(T first, T second, T third) {
            return new TransformingIterator<Box<T>, T>(Iterations.iterator(first, second, third), new PureBox<T>());
        }

        /**
         * Creates an iterator transforming values from the source array into
         * pure() Box<T> monadic values. E.g:
         * <code>Boxes.pures([1,2,3]) -> [Box 1, Box 2, Box 3]</code>
         *
         * @param <T> the iterator element type
         * @param values the source iterator
         * @return the resulting iterator
         */
        public static <T> Iterator<Box<T>> pures(T... values) {
            return new TransformingIterator<Box<T>, T>(Iterations.iterator(values), new PureBox<T>());
        }
    }

    /**
     * pure, pures, lefts, rights.
     */
    public abstract static class Eithers {

        /**
         * Yields Either.pure() of a value. (Either.right(value)) E.g:
         * <code>Eithers.pure(1) -> Either.right(1)</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param value the value to be transformed
         * @return the resulting either
         */
        public static <LT, RT> Either<LT, RT> pure(RT value) {
            return new PureEither<LT, RT>().perform(value);
        }

        /**
         * Yields Either.pure() of a value. (Either.right(value)) E.g:
         * <code>Eithers.pure(1) -> Either.right(1)</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param leftClass the left type (used for type inference)
         * @param value the value to be transformed
         * @return the resulting either
         */
        public static <LT, RT> Either<LT, RT> pure(Class<LT> leftClass, RT value) {
            return new PureEither<LT, RT>().perform(value);
        }

        /**
         * Creates an iterator transforming values from the source iterator into
         * pure() Either<LT, RT> monadic values. E.g:
         * <code>Eithers.pures([1,2,3]) -> [Right 1, Right 2, Right 3]</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param values the source iterator
         * @return the resulting iterator
         */
        public static <LT, RT> Iterator<Either<LT, RT>> pures(Iterator<RT> values) {
            return new TransformingIterator<Either<LT, RT>, RT>(values, new PureEither<LT, RT>());
        }

        /**
         * Creates an iterator transforming values from the source iterable into
         * pure() Either<LT, RT> monadic values. E.g:
         * <code>Eithers.pures([1,2,3]) -> [Right 1, Right 2, Right 3]</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param values the source iterable
         * @return the resulting iterator
         */
        public static <LT, RT> Iterator<Either<LT, RT>> pures(Iterable<RT> values) {
            dbc.precondition(values != null, "cannot perform pures on a null iterable");
            return new TransformingIterator<Either<LT, RT>, RT>(values.iterator(), new PureEither<LT, RT>());
        }

        /**
         * Creates a singleton iterator yielding pure() Either<LT, RT> monadic
         * value of the passed value. E.g:
         * <code>Eithers.pures(1) -> [Right 1]</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param value the value to be transformed
         * @return the resulting iterator
         */
        public static <LT, RT> Iterator<Either<LT, RT>> pures(RT value) {
            return new TransformingIterator<Either<LT, RT>, RT>(new SingletonIterator<RT>(value), new PureEither<LT, RT>());
        }

        /**
         * Creates an iterator yielding pure() Either<LT, RT> monadic values of
         * the passed values. E.g:
         * <code>Eithers.pures(1, 2) -> [Right 1, Right 2]</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param first the first value
         * @param second the second value
         * @return the resulting iterator
         */
        public static <LT, RT> Iterator<Either<LT, RT>> pures(RT first, RT second) {
            return new TransformingIterator<Either<LT, RT>, RT>(Iterations.iterator(first, second), new PureEither<LT, RT>());
        }

        /**
         * Creates an iterator yielding pure() Either<LT, RT> monadic values of
         * the passed values. E.g:
         * <code>Eithers.pures(1, 2, 3) -> [Right 1, Right 2, Right 3]</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param first the first value
         * @param second the second value
         * @param third the third value
         * @return the resulting iterator
         */
        public static <LT, RT> Iterator<Either<LT, RT>> pures(RT first, RT second, RT third) {
            return new TransformingIterator<Either<LT, RT>, RT>(Iterations.iterator(first, second, third), new PureEither<LT, RT>());
        }

        /**
         * Creates an iterator transforming values from the source array into
         * pure() Either<LT, RT> monadic values. E.g:
         * <code>Eithers.pures([1,2,3]) -> [Right 1, Right 2, Right 3]</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param values the source array
         * @return the resulting iterator
         */
        public static <LT, RT> Iterator<Either<LT, RT>> pures(RT... values) {
            return new TransformingIterator<Either<LT, RT>, RT>(ArrayIterator.of(values), new PureEither<LT, RT>());
        }

        /**
         * Creates an iterator yielding only left wrapped values from an
         * iterator of Either. E.g:
         * <code>Eithers.lefts([Right 1, Left 2, Right 3, Left 4]) -> [2, 4]</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param eithers the source iterator
         * @return the resulting iterator
         */
        public static <LT, RT> Iterator<LT> lefts(Iterator<Either<LT, RT>> eithers) {
            dbc.precondition(eithers != null, "can not fetch lefts from a null iterator");
            final Iterator<Maybe<LT>> maybes = new TransformingIterator<Maybe<LT>, Either<LT, RT>>(eithers, new MaybeLeft<LT, RT>());
            final Iterator<Maybe<LT>> justs = new FilteringIterator<Maybe<LT>>(maybes, new IsJust<LT>());
            return new TransformingIterator<LT, Maybe<LT>>(justs, new FromJust<LT>());
        }

        /**
         * Creates an iterator yielding only left wrapped values from an
         * iterable of Either. E.g:
         * <code>Eithers.lefts([Right 1, Left 2, Right 3, Left 4]) -> [2, 4]</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param eithers the source iterable
         * @return the resulting iterator
         */
        public static <LT, RT> Iterator<LT> lefts(Iterable<Either<LT, RT>> eithers) {
            dbc.precondition(eithers != null, "can not fetch lefts from a null iterable");
            final Iterator<Maybe<LT>> maybes = new TransformingIterator<Maybe<LT>, Either<LT, RT>>(eithers.iterator(), new MaybeLeft<LT, RT>());
            final Iterator<Maybe<LT>> justs = new FilteringIterator<Maybe<LT>>(maybes, new IsJust<LT>());
            return new TransformingIterator<LT, Maybe<LT>>(justs, new FromJust<LT>());
        }

        /**
         * Creates an iterator yielding only right wrapped values from an
         * iterator of Either. E.g:
         * <code>Eithers.rights([Right 1, Left 2, Right 3, Left 4]) -> [1, 3]</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param eithers the source iterator
         * @return the resulting iterator
         */
        public static <LT, RT> Iterator<RT> rights(Iterator<Either<LT, RT>> eithers) {
            dbc.precondition(eithers != null, "can not fetch rights from a null iterator");
            final Iterator<Maybe<RT>> maybes = new TransformingIterator<Maybe<RT>, Either<LT, RT>>(eithers, new MaybeRight<LT, RT>());
            final Iterator<Maybe<RT>> justs = new FilteringIterator<Maybe<RT>>(maybes, new IsJust<RT>());
            return new TransformingIterator<RT, Maybe<RT>>(justs, new FromJust<RT>());
        }

        /**
         * Creates an iterator yielding only right wrapped values from an
         * iterable of Either. E.g:
         * <code>Eithers.rights([Right 1, Left 2, Right 3, Left 4]) -> [1, 3]</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param eithers the source iterable
         * @return the resulting iterator
         */
        public static <LT, RT> Iterator<RT> rights(Iterable<Either<LT, RT>> eithers) {
            dbc.precondition(eithers != null, "can not fetch rights from a null iterator");
            final Iterator<Maybe<RT>> maybes = new TransformingIterator<Maybe<RT>, Either<LT, RT>>(eithers.iterator(), new MaybeRight<LT, RT>());
            final Iterator<Maybe<RT>> justs = new FilteringIterator<Maybe<RT>>(maybes, new IsJust<RT>());
            return new TransformingIterator<RT, Maybe<RT>>(justs, new FromJust<RT>());
        }
    }
}
