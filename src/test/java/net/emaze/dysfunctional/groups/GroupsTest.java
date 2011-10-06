package net.emaze.dysfunctional.groups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.iterations.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class GroupsTest {

    @Test(expected=IllegalArgumentException.class)
    public void coupleByWithNullIteratorYieldsException(){
        final Delegate<O, O> delegate = new Identity<O>();
        final Provider<HashMap<O, O>> mapProvider = new HashMapFactory<O, O>();
        Groups.coupleBy(null, delegate, mapProvider);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void coupleByWithNullDelegateYieldsException(){
        final Iterator<O> iterator = Iterations.iterator();
        final Provider<HashMap<O, O>> mapProvider = new HashMapFactory<O, O>();
        Groups.coupleBy(iterator, null, mapProvider);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void coupleByWithNullProvideYieldsException(){
        final Iterator<O> iterator = Iterations.iterator();
        final Delegate<O, O> delegate = new Identity<O>();
        Groups.coupleBy(iterator, delegate, null);
    }
    @Test
    public void everyElementFromIteratorIsCoupled(){
        final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER);
        final Delegate<O, O> delegate = new Identity<O>();
        final Provider<HashMap<O, O>> provider = new HashMapFactory<O, O>();
        final Map<O, O> coupleBy = Groups.coupleBy(iterator, delegate, provider);
        Assert.assertEquals(Arrays.asList(O.ONE, O.ANOTHER), new ArrayList<O>(coupleBy.values()));
    }
}
