package net.emaze.dysfunctional.collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class CollectionFactoryTest {

    @Test
    public void canCreateAnArrayList() {
        ArrayList<Integer> got = new ArrayListFactory<Integer>().provide();
        Assert.assertNotNull(got);
    }

    @Test
    public void canCreateAnHashSet() {
        HashSet<Integer> got = new HashSetFactory<Integer>().provide();
        Assert.assertNotNull(got);
    }

    @Test
    public void canCreateLinkedHashSet() {
        LinkedHashSet<Integer> got = new LinkedHashSetFactory<Integer>().provide();
        Assert.assertNotNull(got);
    }

    @Test
    public void canCreateLinkedList() {
        LinkedList<Integer> got = new LinkedListFactory<Integer>().provide();
        Assert.assertNotNull(got);
    }

    @Test
    public void canCreateTreeSet() {
        TreeSet<Integer> got = new TreeSetFactory<Integer>().provide();
        Assert.assertNotNull(got);
    }
}
