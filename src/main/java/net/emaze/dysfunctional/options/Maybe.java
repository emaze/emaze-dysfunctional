package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.casts.Casts;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

/**
 * Responsibility: Holds an optional value, sadly not actually a Maybe monad :'(
 * @param <E> 
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
        dbc.stateprecondition(hasValue, "fetching value from nothing");
        return element;
    }

    public <T> Maybe<T> withValue(Delegate<T, E> delegate){
        dbc.precondition(delegate != null, "cannot perform withValue with a null delegate");
        if(hasValue){
            return Maybe.just(delegate.perform(element));
        }
        return Maybe.nothing();
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
        final Maybe<E> other = Casts.widen(rhs);
        return new EqualsBuilder().append(this.hasValue, other.hasValue).
                append(this.element, other.element).
                isEquals();
    }

    @Override
    public String toString() {
        if(!hasValue){
            return "Nothing";
        }
        return String.format("Just %s", element);
    }

    
}
