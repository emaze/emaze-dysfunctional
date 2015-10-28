package net.emaze.dysfunctional.strategies;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.emaze.dysfunctional.contracts.dbc;

public class AllMatchesStrategy<T, R> implements Function<T, List<R>> {

    private final List<Clause<T, R>> clauses;

    public AllMatchesStrategy(List<Clause<T, R>> clauses) {
        dbc.precondition(clauses != null, "clauses cannot be null");
        this.clauses = clauses;
    }

    @Override
    public List<R> apply(T t) {
        return clauses.stream()
                .filter(clause -> clause.condition.test(t))
                .map(clause -> clause.body.apply(t))
                .collect(Collectors.toList());
    }
}
