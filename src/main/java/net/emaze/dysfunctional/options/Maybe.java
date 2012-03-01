package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

/**
 * Holds an optional value.
 *
 * @param <E> the value type
 * @author rferranti
 */
public class Maybe<E> {

    private final E element;
    private final boolean hasValue;

    public Maybe(E element, boolean hasValue) {
        this.element = element;
        this.hasValue = hasValue;
    }

    public boolean hasValue() {
        return hasValue;
    }

    public E value() {
        dbc.state(hasValue, "fetching value from nothing");
        return element;
    }

    public <T> Maybe<T> fmap(Delegate<T, E> delegate) {
        dbc.precondition(delegate != null, "cannot perform fmap with a null delegate");
        if (hasValue) {
            return Maybe.just(delegate.perform(element));
        }
        return Maybe.nothing();
    }

    public <T> Either<T, E> either(Provider<T> nothing) {
        if (hasValue) {
            return Either.right(element);
        }
        return Either.left(nothing.provide());
    }

    public E orElse(E otherwise){
        if(hasValue){
           return element; 
        }
        return otherwise;
    }
    
    public static <E> Maybe<E> nothing() {
        return new Maybe<E>(null, false);
    }

    public static <E> Maybe<E> just(E element) {
        return new Maybe<E>(element, true);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.hasValue).
                append(this.element).
                toHashCode();
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof Maybe == false) {
            return false;
        }
        final Maybe<E> other = (Maybe<E>) rhs;
        return new EqualsBuilder().append(this.hasValue, other.hasValue).
                append(this.element, other.element).
                isEquals();
    }

    @Override
    public String toString() {
        if (!hasValue) {
            return "Nothing";
        }
        return String.format("Just %s", element);
    }
}
