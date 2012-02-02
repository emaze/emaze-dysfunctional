package net.emaze.dysfunctional.collections.builders;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import net.emaze.dysfunctional.Casts;
import net.emaze.dysfunctional.casts.Narrow;
import net.emaze.dysfunctional.collections.LinkedHashMapFactory;
import net.emaze.dysfunctional.Dispatching;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import org.junit.Assert;
import org.junit.Test;

public class NestedMapBuilderTest {

    private final Provider<Map<String, Object>> factory = Dispatching.compose(new Narrow<Map<String, Object>, LinkedHashMap<String, Object>>(), new LinkedHashMapFactory<String, Object>());

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullProviderYieldsException() {
        new NestedMapBuilder<Object>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void poppingFromEmptyStackYieldsException() {
        new NestedMapBuilder<String>(factory).pop();
    }

    @Test(expected = IllegalArgumentException.class)
    public void mergingNullMapYieldsException() {
        new NestedMapBuilder<String>(factory).add(null);
    }

    @Test
    public void ___isSynonymOfThis() {
        NestedMapBuilder<String> builder = new NestedMapBuilder<String>(factory);
        Assert.assertSame(builder, builder.___);
    }

    @Test
    public void afterPushingEntriesAreInsertedInNestedMap() {
        final NestedMapBuilder<String> builder = new NestedMapBuilder<String>(factory);
        final Map<String, Object> built = builder.push("mario").add("mushroom", "").toMap();
        final Map<String, Object> got = Casts.widen(built.get("mario"));
        Assert.assertTrue(got.containsKey("mushroom"));
    }
    
    @Test
    public void afterPoppingEntriesAreInsertedInParentMap() {
        final NestedMapBuilder<String> builder = new NestedMapBuilder<String>(factory);
        final Map<String, Object> built = builder.push("mario").pop().add("mushroom", "").toMap();
        Assert.assertTrue(built.containsKey("mushroom"));
    }
    @Test
    public void canPushMultipleTimes() {
        final NestedMapBuilder<String> builder = new NestedMapBuilder<String>(factory);
        final Map<String, Object> built = builder.push("mario").push("mushrooms").add("mushroom", "").toMap();
        final Map<String, Object> mario = Casts.widen(built.get("mario"));
        final Map<String, Object> mushrooms = Casts.widen(mario.get("mushrooms"));
        Assert.assertTrue(mushrooms.containsKey("mushroom"));
    }
    

    @Test
    public void mergingMapIsReflectedByToMap() {
        final Map<String, Object> toBeMerged = new HashMap<String, Object>();
        toBeMerged.put("mario", new Object());        
        Map<String, Object> built = new NestedMapBuilder<String>(factory).add(toBeMerged).toMap();
        Assert.assertEquals(toBeMerged, built);
    }
    
    @Test
    public void oldStateValuesArePreservedWhenMerging() {
        final Map<String, Object> toBeMerged = new HashMap<String, Object>();
        toBeMerged.put("mario", new Object());        
        Map<String, Object> built = new NestedMapBuilder<String>(factory).add("luigi", new Object()).add(toBeMerged).toMap();
        Assert.assertTrue(built.containsKey("luigi"));
    }    
}
