package net.emaze.dysfunctional.collections.builders;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import net.emaze.dysfunctional.casts.Casts;
import net.emaze.dysfunctional.casts.Narrow;
import net.emaze.dysfunctional.collections.LinkedHashMapFactory;
import net.emaze.dysfunctional.dispatching.Dispatching;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import org.junit.Assert;
import org.junit.Test;

public class MapTreeBuilderTest {

    private final Provider<Map<String, Object>> factory = Dispatching.compose(new Narrow<Map<String, Object>, LinkedHashMap<String, Object>>(), new LinkedHashMapFactory<String, Object>());

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullProviderYieldsException() {
        new MapTreeBuilder<Object>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void poppingFromEmptyStackYieldsException() {
        new MapTreeBuilder<String>(factory).pop();
    }

    @Test(expected = IllegalArgumentException.class)
    public void mergingNullMapYieldsException() {
        new MapTreeBuilder<String>(factory).add(null);
    }

    @Test
    public void ___isSynonymOfThis() {
        MapTreeBuilder<String> builder = new MapTreeBuilder<String>(factory);
        Assert.assertSame(builder, builder.___);
    }

    @Test
    public void afterPushingEntriesAreInsertedInNestedMap() {
        final MapTreeBuilder<String> builder = new MapTreeBuilder<String>(factory);
        final Map<String, Object> built = builder.push("mario").add("mushroom", "").toMap();
        final Map<String, Object> got = Casts.widen(built.get("mario"));
        Assert.assertTrue(got.containsKey("mushroom"));
    }
    
    @Test
    public void afterPoppingEntriesAreInsertedInParentMap() {
        final MapTreeBuilder<String> builder = new MapTreeBuilder<String>(factory);
        final Map<String, Object> built = builder.push("mario").pop().add("mushroom", "").toMap();
        Assert.assertTrue(built.containsKey("mushroom"));
    }
    @Test
    public void canPushMultipleTimes() {
        final MapTreeBuilder<String> builder = new MapTreeBuilder<String>(factory);
        final Map<String, Object> built = builder.push("mario").push("mushrooms").add("mushroom", "").toMap();
        final Map<String, Object> mario = Casts.widen(built.get("mario"));
        final Map<String, Object> mushrooms = Casts.widen(mario.get("mushrooms"));
        Assert.assertTrue(mushrooms.containsKey("mushroom"));
    }
    

    @Test
    public void mergingMapIsReflectedByToMap() {
        final Map<String, Object> toBeMerged = new HashMap<String, Object>();
        toBeMerged.put("mario", new Object());        
        Map<String, Object> built = new MapTreeBuilder<String>(factory).add(toBeMerged).toMap();
        Assert.assertEquals(toBeMerged, built);
    }
    
    @Test
    public void oldStateValuesArePreservedWhenMerging() {
        final Map<String, Object> toBeMerged = new HashMap<String, Object>();
        toBeMerged.put("mario", new Object());        
        Map<String, Object> built = new MapTreeBuilder<String>(factory).add("luigi", new Object()).add(toBeMerged).toMap();
        Assert.assertTrue(built.containsKey("luigi"));
    }    
}
