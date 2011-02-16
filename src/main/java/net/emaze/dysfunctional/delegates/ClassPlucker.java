package net.emaze.dysfunctional.delegates;

/**
 *
 * @author rferranti
 */
public class ClassPlucker<T> implements Delegate<Class<?>, T> {

    @Override
    public Class<?> perform(T obj) {
        return obj.getClass();
    }
}
