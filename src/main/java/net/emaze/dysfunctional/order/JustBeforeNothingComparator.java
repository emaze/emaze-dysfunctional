package net.emaze.dysfunctional.order;

import java.util.Comparator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.Optional;

public class JustBeforeNothingComparator<T> implements Comparator<Optional<T>> {

    private final Comparator<T> inner;

    public JustBeforeNothingComparator(Comparator<T> inner) {
        dbc.precondition(inner != null, "cannot create a JustBeforeNothingComparator<T> with a null Comparator<T>");
        this.inner = inner;
    }

    @Override
    public int compare(Optional<T> lhs, Optional<T> rhs) {
        if (!lhs.isPresent() && !rhs.isPresent()) {
            return 0;
        }
        if (!lhs.isPresent()) {
            return 1;
        }
        if (!rhs.isPresent()) {
            return -1;
        }
        return inner.compare(lhs.get(), rhs.get());
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
