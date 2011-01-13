package net.emaze.dysfunctional.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 *
 * @author rferranti
 */
public class LinkedHashSetFactory<E> implements CollectionFactory<LinkedHashSet<E>, E> {

    @Override
    public LinkedHashSet<E> create() {
        return new LinkedHashSet<E>();
    }
}
