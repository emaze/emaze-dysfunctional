package net.emaze.dysfunctional.strategies;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import net.emaze.dysfunctional.Tuples;
import net.emaze.dysfunctional.tuples.Pair;

public class BinaryClause<T, U, R> {

    public final BiPredicate<T, U> condition;
    public final BiFunction<T, U, R> body;

    private BinaryClause(BiPredicate<T, U> condition, BiFunction<T, U, R> body) {
        this.condition = condition;
        this.body = body;
    }

    public static <T, U, R> BinaryClause<T, U, R> of(BiPredicate<T, U> condition, BiFunction<T, U, R> body) {
        return new BinaryClause<>(condition, body);
    }

    public Clause<Pair<T, U>, R> tupled() {
        return Clause.of(Tuples.tupled(condition), Tuples.tupled(body));
    }
}
