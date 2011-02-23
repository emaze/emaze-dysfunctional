    package net.emaze.dysfunctional.options;

import java.util.Iterator;
import net.emaze.dysfunctional.adapting.ArrayIterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.filtering.Filtering;
import net.emaze.dysfunctional.iterations.Iterations;

/**
 *
 * @author rferranti
 */
public abstract class Options {

    public static <R, T> Iterator<Maybe<R>> transform(Iterator<Maybe<T>> maybes, Delegate<R, T> delegate) {
        return Iterations.transform(maybes, new WithJust<R, T>(delegate));
    }

    public static <R, T> Iterator<Maybe<R>> transform(Iterable<Maybe<T>> maybes, Delegate<R, T> delegate) {
        dbc.precondition(maybes != null, "cannot perform transform on a null iterable of Maybes");
        return Options.transform(maybes.iterator(), delegate);
    }

    public static <R, T> Iterator<Maybe<R>> transform(Maybe<T>[] maybes, Delegate<R, T> delegate) {
        return Options.transform(new ArrayIterator<Maybe<T>>(maybes), delegate);
    }

    public static <T> Iterator<T> justs(Iterator<Maybe<T>> maybes) {
        final Iterator<Maybe<T>> justs = Filtering.filter(maybes, new IsJust<T>());
        return Iterations.transform(justs, new FromJust<T>());
    }

    public static <T> Iterator<T> justs(Iterable<Maybe<T>> maybes) {
        dbc.precondition(maybes != null, "cannot perform justs on a null iterable of Maybes");
        return Options.justs(maybes.iterator());
    }

    public static <T> Iterator<T> justs(Maybe<T>[] maybes) {
        return Options.justs(new ArrayIterator<Maybe<T>>(maybes));
    }
}
