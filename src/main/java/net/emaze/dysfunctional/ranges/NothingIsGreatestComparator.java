package net.emaze.dysfunctional.ranges;

import java.util.Comparator;
import net.emaze.dysfunctional.options.Maybe;

public class NothingIsGreatestComparator<T> implements Comparator<Maybe<T>> {
    private final Comparator<T> inner;

    public NothingIsGreatestComparator(Comparator<T> inner) {
        this.inner = inner;
    }

    @Override
    public int compare(Maybe<T> o1, Maybe<T> o2) {
        if (!o1.hasValue()) {
            if (!o2.hasValue()) {
                return 0;
            }
            return 1;
        } else if (!o2.hasValue()) {
            return -1;
        }
        return inner.compare(o1.value(), o2.value());
    }

}
