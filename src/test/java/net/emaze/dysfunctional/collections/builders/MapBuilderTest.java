package net.emaze.dysfunctional.collections.builders;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;
import org.junit.Test;

public class MapBuilderTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingBuilderWithNullMapYieldsException() {
        new MapBuilder<Object, Object>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mergingNullMapYieldsException() {
        new MapBuilder<Object, Object>(new HashMap<Object, Object>()).add(null);
    }

    @Test
    public void toMapYieldsSameMapPassedInConstructor() {
        final Map<Object, Object> state = new HashMap<Object, Object>();
        final Map<Object, Object> got = new MapBuilder<Object, Object>(state).toMap();
        Assert.assertSame(state, got);
    }

    @Test
    public void addingToBuilderIsReflectedInState() {
        final String key = "mario";
        final Object value = new Object();
        final Map<String, Object> state = new HashMap<String, Object>();
        new MapBuilder<String, Object>(state).add(key, value);
        Assert.assertEquals(value, state.get(key));
    }

    @Test
    public void mergingMapCopiesInState() {
        final Map<String, Object> toBeMerged = new HashMap<String, Object>();
        toBeMerged.put("mario", new Object());
        final Map<String, Object> state = new HashMap<String, Object>();
        new MapBuilder<String, Object>(state).add(toBeMerged);
        Assert.assertEquals(toBeMerged, state);
    }

    @Test
    public void oldStateValuesArePreservedWhenMerging() {
        final Map<String, Object> toBeMerged = new HashMap<String, Object>();
        toBeMerged.put("mario", new Object());
        final Map<String, Object> state = new HashMap<String, Object>();
        state.put("luigi", new Object());
        new MapBuilder<String, Object>(state).add(toBeMerged);
        Assert.assertTrue(state.containsKey("luigi"));
    }

    @Test
    public void canReturnAnUnmodifiableMap() {
        final MapBuilder<String, Object> instance = new MapBuilder<String, Object>(new HashMap<String, Object>());
        Assert.assertNotNull(instance.toUnmodifiableMap());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void cannotPutANewEntryOnUnmodifiableMap() {
        final MapBuilder<String, Object> instance = new MapBuilder<String, Object>(new HashMap<String, Object>());
        final Map<String, Object> expectedToBeUnmodifiable = instance.toUnmodifiableMap();
        expectedToBeUnmodifiable.put("key", "value");
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void cannotPutAWholeMapOnUnmodifiableMap() {
        final MapBuilder<String, Object> instance = new MapBuilder<String, Object>(new HashMap<String, Object>());
        final Map<String, Object> expectedToBeUnmodifiable = instance.toUnmodifiableMap();
        expectedToBeUnmodifiable.putAll(Collections.<String, Object>singletonMap("key", "value"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void cannotRemoveAnEntryOnUnmodifiableMap() {
        final MapBuilder<String, Object> instance = new MapBuilder<String, Object>(new HashMap<String, Object>());
        final Map<String, Object> expectedToBeUnmodifiable = instance.
                add("key", "value").toUnmodifiableMap();
        expectedToBeUnmodifiable.remove("key");
    }
}
