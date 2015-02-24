package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Given f, g yields f ° g (f of g, f following g).
 * @param <T1> the former element Type
 * @param <T2> the latter element Type
 * @param <R> the return Type
 * @author rferranti
 */
public class Composer<T2, T1, R> implements Function<T1, R> {

    private final Function<T1, T2> g;
    private final Function<T2, R> f;

    public Composer(Function<T2, R> f, Function<T1, T2> g) {
        dbc.precondition(f != null, "cannot compose a null function");
        dbc.precondition(g != null, "cannot compose a null function");
        this.f = f;
        this.g = g;
    }

    @Override
    public R apply(T1 t) {
        return f.apply(g.apply(t));
    }
}
