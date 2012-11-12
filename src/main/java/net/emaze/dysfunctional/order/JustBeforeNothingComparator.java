package net.emaze.dysfunctional.order;

import java.util.Comparator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Maybe;

public class JustBeforeNothingComparator<T> implements Comparator<Maybe<T>> {

    private final Comparator<T> inner;

    public JustBeforeNothingComparator(Comparator<T> inner) {
        dbc.precondition(inner != null, "cannot create a JustBeforeNothingComparator<T> with a null Comparator<T>");
        this.inner = inner;
    }

    @Override
    public int compare(Maybe<T> lhs, Maybe<T> rhs) {
        if (!lhs.hasValue() && !rhs.hasValue()) {
            return 0;
        }
        if (!lhs.hasValue()) {
            return 1;
        }
        if (!rhs.hasValue()) {
            return -1;
        }
        return inner.compare(lhs.value(), rhs.value());
    }

    @Override
    public int hashCode() {
        return inner.hashCode();
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof JustBeforeNothingComparator == false) {
            return false;
        }
        final JustBeforeNothingComparator<T> other = (JustBeforeNothingComparator<T>) rhs;
        return this.inner.equals(other.inner);
    }
}
