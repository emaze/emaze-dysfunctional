package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

/**
 * A mutable container of an optional value.
 *
 * @param <T> the content type
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

    public <R> Box<R> fmap(Delegate<R, T> delegate) {
        dbc.precondition(delegate != null, "cannot perform fmap with a null delegate");
        final Maybe<R> m = content.fmap(delegate);
        return m.hasValue() ? Box.of(m.value()) : Box.<R>empty();
    }

    public boolean isEmpty() {
        return !content.hasValue();
    }

    public boolean hasContent() {
        return content.hasValue();
    }

    public Maybe<T> unload() {
        final Maybe<T> old = content;
        content = Maybe.nothing();
        return old;
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
        final Box<T> other = (Box<T>) rhs;
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
