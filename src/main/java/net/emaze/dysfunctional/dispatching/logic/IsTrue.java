package net.emaze.dysfunctional.dispatching.logic;

/**
 *
 * @author dangelocola
 */
public class IsTrue implements Predicate<Boolean> {

    @Override
    public boolean test(Boolean element) {
        return element;
    }
}
