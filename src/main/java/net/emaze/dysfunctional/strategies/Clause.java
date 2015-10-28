package net.emaze.dysfunctional.strategies;

import java.util.function.Function;
import java.util.function.Predicate;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A condition and a function to apply when the condition is true.
 *
 * @param <T> the type of the condition and of the input to the function
 * @param <R> the type of the result of the function
 */
public class Clause<T, R> {

    public final Predicate<T> condition;
    public final Function<T, R> body;

    private Clause(Predicate<T> condition, Function<T, R> body) {
        dbc.precondition(condition != null, "cannot create a clause without a condition");
        dbc.precondition(body != null, "cannot create a clause without a body");
        this.condition = condition;
        this.body = body;
    }

    public static <T, R> Clause<T, R> of(Predicate<T> condition, Function<T, R> body) {
        return new Clause<>(condition, body);
    }
}
