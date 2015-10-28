package net.emaze.dysfunctional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import net.emaze.dysfunctional.Tuples.Pairs;
import net.emaze.dysfunctional.strategies.*;
import net.emaze.dysfunctional.tuples.Pair;

public abstract class Strategies {

    /**
     * Given some {@code Clause}s returns a function that forwards the result of the
     * first clause which condition is true.
     *
     * @throws IllegalStateException if no clause matches the condition
     */
    @SafeVarargs
    public static <T, R> Function<T, R> firstMatch(Clause<T, R>... clauses) {
        return new FirstMatchStrategy<>(Arrays.asList(clauses));
    }

    /**
     * Given some {@code Clause}s returns a binary function that forwards the result of
     * the first clause which condition is true.
     *
     * @throws IllegalStateException if no clause matches the condition
     */
    @SafeVarargs
    public static <T, U, R> BiFunction<T, U, R> firstMatch(BinaryClause<T, U, R>... clauses) {
        final List<Clause<Pair<T, U>, R>> unaryClauses = Sequences.of(clauses).map(BinaryClause::tupled).toList();
        return Pairs.untupled(new FirstMatchStrategy<>(unaryClauses));
    }

    /**
     * Given some {@code Clause}s returns a function that forwards the result of the
     * first clause which condition is true, of an empty {@code Optional} if no
     * clause matches the condition.
     */
    @SafeVarargs
    public static <T, R> Function<T, Optional<R>> maybeFirstMatch(Clause<T, R>... clauses) {
        return new MaybeFirstMatchStrategy<>(Arrays.asList(clauses));
    }

    /**
     * Given some {@code Clause}s returns a binary function that forwards the result of
     * the first clause which condition is true, of an empty {@code Optional} if
     * no clause matches the condition.
     */
    @SafeVarargs
    public static <T, U, R> BiFunction<T, U, Optional<R>> maybeFirstMatch(BinaryClause<T, U, R>... clauses) {
        final List<Clause<Pair<T, U>, R>> unaryClauses = Sequences.of(clauses).map(BinaryClause::tupled).toList();
        return Pairs.untupled(new MaybeFirstMatchStrategy<>(unaryClauses));
    }

    /**
     * Given some {@code Clause}s returns a function that forwards the results of all
     * clauses which condition is true, of an empty {@code List} if no clause
     * matches the condition.
     */
    @SafeVarargs
    public static <T, R> Function<T, List<R>> allMatches(Clause<T, R>... clauses) {
        return new AllMatchesStrategy<>(Arrays.asList(clauses));
    }

    /**
     * Given some {@code Clause}s returns a binary function that forwards the results of
     * all clauses which condition is true, of an empty {@code List} if no clause
     * matches the condition.
     */
    @SafeVarargs
    public static <T, U, R> BiFunction<T, U, List<R>> allMatches(BinaryClause<T, U, R>... clauses) {
        final List<Clause<Pair<T, U>, R>> unaryClauses = Sequences.of(clauses).map(BinaryClause::tupled).toList();
        return Pairs.untupled(new AllMatchesStrategy<>(unaryClauses));
    }
}
