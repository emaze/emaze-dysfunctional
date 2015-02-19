package net.emaze.dysfunctional.options;

import java.util.Optional;
import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

/**
 * A mutable container of an optional get.
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

    public <R> Box<R> map(Function<T, R> delegate) {
        dbc.precondition(delegate != null, "cannot perform fmap with a null delegate");
        final Maybe<R> m = content.map(delegate);
        return m.isPresent() ? Box.of(m.get()) : Box.<R>empty();
    }

    public boolean isEmpty() {
        return !content.isPresent();
    }

    public boolean isPresent() {
        return content.isPresent();
    }

    public Optional<T> unload() {
        final Maybe<T> old = content;
        content = Maybe.nothing();
        return old.optional();
    }

    public T getContent() {
        return content.get();
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
