package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.casts.Casts;
import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

/**
 *
 * @author rferranti
 */
public class Box<T> {

    private Maybe<T> content = Maybe.nothing();

    public T getContent() {
        return content.value();
    }

    public void setContent(T content) {
        this.content = Maybe.just(content);
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof Box == false) {
            return false;
        }
        final Box<T> other = Casts.widen(content);
        return new EqualsBuilder().append(this.content, other.content).
                isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(content).toHashCode();
    }

    @Override
    public String toString() {
        return String.format("(Box %s)", content);
    }
}
