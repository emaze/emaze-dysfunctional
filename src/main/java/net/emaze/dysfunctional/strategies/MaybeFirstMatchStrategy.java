package net.emaze.dysfunctional.strategies;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;

public class MaybeFirstMatchStrategy<T, R> implements Function<T, Optional<R>> {

    private final List<Clause<T, R>> clauses;

    public MaybeFirstMatchStrategy(List<Clause<T, R>> clauses) {
        dbc.precondition(clauses != null, "clauses cannot be null");
        this.clauses = clauses;
    }

    @Override
    public Optional<R> apply(T t) {
        return clauses.stream()
                .filter(clause -> clause.condition.test(t))
                .map(clause -> clause.body.apply(t))
                .findFirst();
    }
}
