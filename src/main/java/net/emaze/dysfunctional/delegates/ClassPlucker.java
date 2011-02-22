package net.emaze.dysfunctional.delegates;

import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public class ClassPlucker<T> implements Delegate<Class<?>, T> {

    @Override
    public Class<?> perform(T obj) {
        dbc.precondition(obj != null, "cannot pluck class from a null object");
        return obj.getClass();
    }
}
