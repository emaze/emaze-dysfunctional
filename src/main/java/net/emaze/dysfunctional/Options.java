package net.emaze.dysfunctional;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.filtering.FilteringIterator;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.SingletonIterator;
import net.emaze.dysfunctional.iterations.TransformingIterator;
import net.emaze.dysfunctional.options.*;

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
         * Yields Optional.pure() of a value. (Just get) E.g:
         * <code>Maybes.pure(1) -> Optional.of(1)</code>
         *
         * @param <T> the get type
         * @param value the get to be transformed
         * @return the resulting maybe
         */
        public static <T> Optional<T> pure(T value) {
            return Optional.of(value);
        }

        /**
         * Creates an iterator transforming values from the source iterator into
         * pure() Optional<T> monadic values. E.g:
         * <code>Maybes.pures([1,2,3]) -> [Just 1, Just 2, Just 3]</code>
         *
         * @param <T> the iterator element type
         * @param values the source iterator
         * @return the resulting iterator
         */
        public static <T> Iterator<Optional<T>> pures(Iterator<T> values) {
            return new TransformingIterator<>(values, Optional::of);
        }

        /**
         * Creates an iterator transforming values from the source iterable into
         * pure() Optional<T> monadic values. E.g:
         * <code>Maybes.pures([1,2,3]) -> [Just 1, Just 2, Just 3]</code>
         *
         * @param <T> the iterable element type
         * @param values the source iterable
         * @return the resulting iterator
         */
        public static <T> Iterator<Optional<T>> pures(Iterable<T> values) {
            dbc.precondition(values != null, "cannot perform pures on a null iterable");
            return new TransformingIterator<>(values.iterator(), Optional::of);
        }

        /**
         * Creates a singleton iterator yielding pure() Optional<T> monadic get
         * of the passed get. E.g: <code>Maybes.pures(1) -> [Just 1]</code>
         *
         * @param <T> the element type
         * @param value the source get
         * @return the resulting iterator
         */
        public static <T> Iterator<Optional<T>> pures(T value) {
            return new TransformingIterator<>(new SingletonIterator<T>(value), Optional::of);
        }

        /**
         * Creates an iterator yielding pure() Optional<T> monadic get of the
         * passed values. E.g:
         * <code>Maybes.pures(1, 2) -> [Just 1, Just 2]</code>
         *
         * @param <T> the elements type
         * @param first the first element
         * @param second the second element
         * @return the resulting iterator
         */
        public static <T> Iterator<Optional<T>> pures(T first, T second) {
            return new TransformingIterator<>(Iterations.iterator(first, second), Optional::of);
        }

        /**
         * Creates an iterator yielding pure() Optional<T> monadic get of the
         * passed values. E.g:
         * <code>Maybes.pures(1, 2, 3) -> [Just 1, Just 2, Just 3]</code>
         *
         * @param <T> the elements type
         * @param first the first element
         * @param second the second element
         * @param third the third element
         * @return the resulting iterator
         */
        public static <T> Iterator<Optional<T>> pures(T first, T second, T third) {
            return new TransformingIterator<>(Iterations.iterator(first, second, third), Optional::of);
        }

        /**
         * Creates an iterator yielding values pure() Optional<T> monadic get of
         * the passed values. E.g:
         * <code>Maybes.pures(1, 2, 3, 4) -> [Just 1, Just 2, Just 3, Just 4]</code>
         *
         * @param <T> the array element type
         * @param values the source array
         * @return the resulting array
         */
        public static <T> Iterator<Optional<T>> pures(T... values) {
            return new TransformingIterator<>(Iterations.iterator(values), Optional::of);
        }

        /**
         * Filters nothings out of an Iterator of Optional T, returning an
         * Iterator of T. E.g:
         * <code>Maybes.justs([Just 1, Nothing, Just null]) -> [1, null]</code>
         *
         * @param <T> the Optional type parameter
         * @param maybes an iterator of Optional<T>
         * @return the resulting iterator
         */
        public static <T> Iterator<T> justs(Iterator<Optional<T>> maybes) {
            final Iterator<Optional<T>> justs = new FilteringIterator<Optional<T>>(maybes, new IsJust<T>());
            return new TransformingIterator<>(justs, new FromJust<T>());
        }

        /**
         * Filters nothings out of an Iterable of Optional T, returning an
         * Iterator of T. E.g:
         * <code>Maybes.justs([Just 1, Nothing, Just null]) -> [1, null]</code>
         *
         * @param <T> the Optional type parameter
         * @param maybes an iterable of Optional<T>
         * @return the resulting iterator
         */
        public static <T> Iterator<T> justs(Iterable<Optional<T>> maybes) {
            dbc.precondition(maybes != null, "cannot perform justs on a null iterable of Maybes");
            final Iterator<Optional<T>> justs = new FilteringIterator<Optional<T>>(maybes.iterator(), new IsJust<T>());
            return new TransformingIterator<>(justs, new FromJust<T>());
        }

        /**
         * Filters nothings out of an array of Optional T, returning an Iterator
         * of T. E.g: <code>Maybes.justs([Nothing, Just null]) -> [null]</code>
         *
         * @param <T> the Optional type parameter
         * @param first the first element
         * @param second the second element
         * @return the resulting iterator
         */
        public static <T> Iterator<T> justs(Optional<T> first, Optional<T> second) {
            final Iterator<Optional<T>> iterator = Iterations.iterator(first, second);
            final Iterator<Optional<T>> justs = Filtering.filter(iterator, new IsJust<T>());
            return new TransformingIterator<>(justs, new FromJust<T>());
        }

        /**
         * Filters nothings out of an array of Optional T, returning an Iterator
         * of T. E.g:
         * <code>Maybes.justs([Just 1, Nothing, Just null]) -> [1, null]</code>
         *
         * @param <T> the Optional type parameter
         * @param first the first element
         * @param second the second element
         * @param third the third element
         * @return the resulting iterator
         */
        public static <T> Iterator<T> justs(Optional<T> first, Optional<T> second, Optional<T> third) {
            final Iterator<Optional<T>> iterator = Iterations.iterator(first, second, third);
            final Iterator<Optional<T>> justs = Filtering.filter(iterator, new IsJust<T>());
            return new TransformingIterator<>(justs, new FromJust<T>());
        }

        /**
         * Transforms a null get to Optional.empty, a non-null get to
         * Optional.of(get)
         *
         * @param <T> the get type parameter
         * @param value the get to be lifted
         * @return a Optional
         */
        public static <T> Optional<T> lift(T value) {
            return new LiftMaybe<T>().apply(value);
        }

        /**
         * Transforms a delegate to another working on maybe monadic values.
         *
         * @param <T> the delegate parameter type
         * @param <R> the delegate return type
         * @param delegate the delegate to be lifted
         * @return the transformed delegate
         */
        public static <T, R> Function<Optional<T>, Optional<R>> lift(Function<T, R> delegate) {
            return new FmapMaybe<>(delegate);
        }

        /**
         * Creates an iterator transforming elements from the source iterator to
         * Optional.empty when the source element is null, to
         * Optional.of(sourceValue) otherwise. E.g:
         * <code>Maybes.lift([null, 1, 2]) -> [Nothing, Just 1, Just 2]</code>
         *
         * @param <T> the get type parameter
         * @param iterator the iterator to be lifted
         * @return the resulting iterator
         */
        public static <T> Iterator<Optional<T>> lifts(Iterator<T> iterator) {
            return new TransformingIterator<>(iterator, new LiftMaybe<T>());
        }

        /**
         * Creates an iterator transforming elements from the source iterable to
         * Optional.empty when the source element is null, to
         * Optional.of(sourceValue) otherwise. E.g:
         * <code>Maybes.lift([null, 1, 2]) -> [Nothing, Just 1, Just 2]</code>
         *
         * @param <T> the get type parameter
         * @param iterable the iterable to be lifted
         * @return the resulting iterator
         */
        public static <T> Iterator<Optional<T>> lifts(Iterable<T> iterable) {
            dbc.precondition(iterable != null, "cannot perform lifts on a null iterable");
            return new TransformingIterator<>(iterable.iterator(), new LiftMaybe<T>());
        }

        /**
         * Creates an iterator transforming passed values to Optional.empty when
         * the source element is null, to Optional.of(sourceValue) otherwise.
         * E.g: <code>Maybes.lift(null, 1) -> [Nothing, Just 1]</code>
         *
         * @param <T> the get type
         * @param first the first element
         * @param second the second element
         * @return the resulting iterator
         */
        public static <T> Iterator<Optional<T>> lifts(T first, T second) {
            final Iterator<T> iterator = Iterations.iterator(first, second);
            return new TransformingIterator<>(iterator, new LiftMaybe<T>());
        }

        /**
         * Creates an iterator transforming passed values to Optional.empty when
         * the source element is null, to Optional.of(sourceValue) otherwise.
         * E.g:
         * <code>Maybes.lift(null, 1, 3) -> [Nothing, Just 1, Just 3]</code>
         *
         * @param <T> the get type
         * @param first the first element
         * @param second the second element
         * @param third the third element
         * @return the resulting iterator
         */
        public static <T> Iterator<Optional<T>> lifts(T first, T second, T third) {
            final Iterator<T> iterator = Iterations.iterator(first, second, third);
            return new TransformingIterator<>(iterator, new LiftMaybe<T>());
        }

        /**
         * Transforms a Optional.empty to null, a Optional.of to the wrapped
         * get. E.g: <code>Maybes.drop(Just 1) -> 1</code>
         * <code>Maybes.drop(Nothing) -> null</code>
         *
         * @param <T> the get type parameter
         * @param optional the right to be dropped
         * @return null or a get
         */
        public static <T> T drop(Optional<T> optional) {
            dbc.precondition(optional != null, "cannot drop a null optional");
            return optional.orElse(null);
        }

        /**
         * Creates an iterator transforming Maybes from the source iterator to
         * null when the source element is Nothing, to the wrapped get
         * otherwise. E.g:
         * <code>Maybes.drops([Just null, Nothing, Just 3]) -> [null, null, 3]</code>
         *
         * @param <T> the get type parameter
         * @param iterator the iterator to be dropped
         * @return an iterator of T
         */
        public static <T> Iterator<T> drops(Iterator<Optional<T>> iterator) {
            return new TransformingIterator<>(iterator, Maybes::drop);
        }

        /**
         * Creates an iterator transforming Maybes from the source iterable to
         * null when the source element is Nothing, to the wrapped get
         * otherwise. E.g:
         * <code>Maybes.drops([Just null, Nothing, Just 3]) -> [null, null, 3]</code>
         *
         * @param <T> the get type parameter
         * @param iterable the Iterable to be dropped
         * @return the resulting iterator
         */
        public static <T> Iterator<T> drops(Iterable<Optional<T>> iterable) {
            dbc.precondition(iterable != null, "cannot perform drops on a null iterable");
            return new TransformingIterator<>(iterable.iterator(), Maybes::drop);
        }

        /**
         * Creates an iterator transformed passed elements such as the become
         * null when the source element is Nothing, the wrapped get otherwise.
         * E.g: <code>Maybes.drops([Just null, Just 3]) -> [null, 3]</code>
         *
         * @param <T> the get type parameter
         * @param first the first maybe
         * @param second the second maybe
         * @return the resulting iterator
         */
        public static <T> Iterator<T> drops(Optional<T> first, Optional<T> second) {
            return new TransformingIterator<>(Iterations.iterator(first, second), Maybes::drop);
        }

        /**
         * Creates an iterator transformed passed elements such as the become
         * null when the source element is Nothing, the wrapped get otherwise.
         * E.g: <code>Maybes.drops([Just null, Just 3]) -> [null, 3]</code>
         *
         * @param <T> the get type parameter
         * @param first the first maybe
         * @param second the second maybe
         * @param third the third maybe
         * @return the resulting iterator
         */
        public static <T> Iterator<T> drops(Optional<T> first, Optional<T> second, Optional<T> third) {
            return new TransformingIterator<>(Iterations.iterator(first, second, third), Maybes::drop);
        }

        /**
         * Conventional monad join operator. It is used to remove one level of
         * monadic structure, projecting its bound argument into the outer
         * level.
         *
         * @param <T> the get type parameter
         * @param maybe the source monad
         * @return the unwrapped monad
         */
        public static <T> Optional<T> join(Optional<Optional<T>> maybe) {
            if (maybe.isPresent()) {
                return maybe.get();
            }
            return Optional.empty();
        }
    }

    /**
     * pure, pures.
     */
    public abstract static class Boxes {

        /**
         * Yields Boxes.pure() of a get. (Box.of(get)) E.g:
         * <code>Boxes.pure(1) -> Box.of(1)</code>
         *
         * @param <T> the get type
         * @param value the get to be transformed
         * @return the resulting box
         */
        public static <T> Box<T> pure(T value) {
            return new PureBox<T>().apply(value);
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
            return new TransformingIterator<>(values, new PureBox<T>());
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
            return new TransformingIterator<>(values.iterator(), new PureBox<T>());
        }

        /**
         * Creates a singleton iterator yielding pure() Box<T> monadic get of
         * the passed get. E.g: <code>Boxes.pures(1) -> [Box 1]</code>
         *
         * @param <T> the element type
         * @param value the source get
         * @return the resulting iterator
         */
        public static <T> Iterator<Box<T>> pures(T value) {
            return new TransformingIterator<>(new SingletonIterator<T>(value), new PureBox<T>());
        }

        /**
         * Creates an iterator yielding values pure() Box<T> monadic get of the
         * passed values. E.g: <code>Boxes.pures(1, 2) -> [Box 1, Box 2]</code>
         *
         * @param <T> the elements type
         * @param first the first element
         * @param second the second element
         * @return the resulting iterator
         */
        public static <T> Iterator<Box<T>> pures(T first, T second) {
            return new TransformingIterator<>(Iterations.iterator(first, second), new PureBox<T>());
        }

        /**
         * Creates an iterator yielding values pure() Box<T> monadic get of the
         * passed values. E.g:
         * <code>Boxes.pures(1, 2, 3) -> [Box 1, Box 2, Box 3]</code>
         *
         * @param <T> the elements type
         * @param first the first element
         * @param second the second element
         * @param third the third element
         * @return the resulting iterator
         */
        public static <T> Iterator<Box<T>> pures(T first, T second, T third) {
            return new TransformingIterator<>(Iterations.iterator(first, second, third), new PureBox<T>());
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
            return new TransformingIterator<>(Iterations.iterator(values), new PureBox<T>());
        }

        /**
         * Conventional monad join operator. It is used to remove one level of
         * monadic structure, projecting its bound argument into the outer
         * level.
         *
         * @param <T> the get type parameter
         * @param box the source monad
         * @return the unwrapped monad
         */
        public static <T> Box<T> join(Box<Box<T>> box) {
            if (box.isPresent()) {
                return box.getContent();
            }
            return Box.empty();
        }
    }

    /**
     * pure, pures, lefts, rights.
     */
    public abstract static class Eithers {

        /**
         * Yields Either.pure() of a get. (Either.right(get)) E.g:
         * <code>Eithers.pure(1) -> Either.right(1)</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param value the get to be transformed
         * @return the resulting either
         */
        public static <LT, RT> Either<LT, RT> pure(RT value) {
            return new PureEither<LT, RT>().apply(value);
        }

        /**
         * Yields Either.pure() of a get. (Either.right(get)) E.g:
         * <code>Eithers.pure(1) -> Either.right(1)</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param leftClass the left type (used for type inference)
         * @param value the get to be transformed
         * @return the resulting either
         */
        public static <LT, RT> Either<LT, RT> pure(Class<LT> leftClass, RT value) {
            return new PureEither<LT, RT>().apply(value);
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
            return new TransformingIterator<>(values, new PureEither<LT, RT>());
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
            return new TransformingIterator<>(values.iterator(), new PureEither<LT, RT>());
        }

        /**
         * Creates a singleton iterator yielding pure() Either<LT, RT> monadic
         * get of the passed get. E.g:
         * <code>Eithers.pures(1) -> [Right 1]</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param value the get to be transformed
         * @return the resulting iterator
         */
        public static <LT, RT> Iterator<Either<LT, RT>> pures(RT value) {
            return new TransformingIterator<>(new SingletonIterator<RT>(value), new PureEither<LT, RT>());
        }

        /**
         * Creates an iterator yielding pure() Either<LT, RT> monadic values of
         * the passed values. E.g:
         * <code>Eithers.pures(1, 2) -> [Right 1, Right 2]</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param first the first get
         * @param second the second get
         * @return the resulting iterator
         */
        public static <LT, RT> Iterator<Either<LT, RT>> pures(RT first, RT second) {
            return new TransformingIterator<>(Iterations.iterator(first, second), new PureEither<LT, RT>());
        }

        /**
         * Creates an iterator yielding pure() Either<LT, RT> monadic values of
         * the passed values. E.g:
         * <code>Eithers.pures(1, 2, 3) -> [Right 1, Right 2, Right 3]</code>
         *
         * @param <LT> the left type
         * @param <RT> the right type
         * @param first the first get
         * @param second the second get
         * @param third the third get
         * @return the resulting iterator
         */
        public static <LT, RT> Iterator<Either<LT, RT>> pures(RT first, RT second, RT third) {
            return new TransformingIterator<>(Iterations.iterator(first, second, third), new PureEither<LT, RT>());
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
            return new TransformingIterator<>(ArrayIterator.of(values), new PureEither<LT, RT>());
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
            final Iterator<Optional<LT>> maybes = new TransformingIterator<>(eithers, new MaybeLeft<LT, RT>());
            final Iterator<Optional<LT>> justs = new FilteringIterator<Optional<LT>>(maybes, new IsJust<LT>());
            return new TransformingIterator<>(justs, new FromJust<LT>());
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
            final Iterator<Optional<LT>> maybes = new TransformingIterator<>(eithers.iterator(), new MaybeLeft<LT, RT>());
            final Iterator<Optional<LT>> justs = new FilteringIterator<Optional<LT>>(maybes, new IsJust<LT>());
            return new TransformingIterator<>(justs, new FromJust<LT>());
        }

        /**
         * Get the right value of an Either.
         *
         * @param <L> the left type
         * @param <R> the right type
         * @param either
         * @return the optional right value
         */
        public static <L, R> Optional<R> right(Either<L, R> either) {
            dbc.precondition(either != null, "cannot transform a null either to an optional");
            return either.right();
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
            final Iterator<Optional<RT>> maybes = new TransformingIterator<>(eithers, new MaybeRight<LT, RT>());
            final Iterator<Optional<RT>> justs = new FilteringIterator<Optional<RT>>(maybes, new IsJust<RT>());
            return new TransformingIterator<>(justs, new FromJust<RT>());
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
            final Iterator<Optional<RT>> maybes = new TransformingIterator<>(eithers.iterator(), new MaybeRight<LT, RT>());
            final Iterator<Optional<RT>> justs = new FilteringIterator<Optional<RT>>(maybes, new IsJust<RT>());
            return new TransformingIterator<>(justs, new FromJust<RT>());
        }
    }
}
