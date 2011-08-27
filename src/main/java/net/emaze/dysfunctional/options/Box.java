package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.casts.Casts;
import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

/**
 *
 * @param <T>
 * @author rferranti
 */
public class Box<T> {

    private Maybe<T> content = Maybe.nothing();

    public static <E> Box<E> of(E element) {
        final Box<E> box = new Box<E>();
        box.setContent(element);
        return box;
    }

    public static <E> Box<E> empty() {
        return new Box<E>();
    }

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
        final Box<T> other = Casts.widen(rhs);
        return new EqualsBuilder().append(this.content, other.content).
                isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(content).toHashCode();
    }

    @Override
    public String toString() {
        return String.format("Box %s", content);
    }
}
