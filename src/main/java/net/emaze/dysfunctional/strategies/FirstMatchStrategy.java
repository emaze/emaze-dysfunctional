package net.emaze.dysfunctional.strategies;

import java.util.List;
import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;

public class FirstMatchStrategy<T, R> implements Function<T, R> {

    private final List<Clause<T, R>> clauses;

    public FirstMatchStrategy(List<Clause<T, R>> clauses) {
        dbc.precondition(clauses != null, "clauses cannot be null");
        this.clauses = clauses;
    }

    @Override
    public R apply(T t) {
        return clauses.stream()
                .filter(clause -> clause.condition.test(t))
                .map(clause -> clause.body.apply(t))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("No clauses found for %s", t)));
    }
}
