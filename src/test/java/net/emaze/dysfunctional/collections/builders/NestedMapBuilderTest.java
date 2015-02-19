package net.emaze.dysfunctional.collections.builders;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import net.emaze.dysfunctional.Casts;
import net.emaze.dysfunctional.Compositions;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.LinkedHashMapFactory;
import java.util.function.Supplier;
import org.junit.Assert;
import org.junit.Test;

public class NestedMapBuilderTest {

    private final Supplier<Map<String, Object>> factory = Compositions.compose(new Vary<LinkedHashMap<String, Object>, Map<String, Object>>(), new LinkedHashMapFactory<String, Object>());

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
    
    @Test
    public void canReturnAnUnmodifiableMap() {
        final NestedMapBuilder<String> builder = new NestedMapBuilder<String>(factory);
        Assert.assertNotNull(builder.toUnmodifiableMap());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void cannotPutANewEntryOnUnmodifiableMap() {
        final NestedMapBuilder<String> builder = new NestedMapBuilder<String>(factory);
        final Map<String, Object> expectedToBeUnmodifiable = builder.toUnmodifiableMap();
        expectedToBeUnmodifiable.put("key", "value");
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void cannotPutAWholeMapOnUnmodifiableMap() {
        final NestedMapBuilder<String> builder = new NestedMapBuilder<String>(factory);
        final Map<String, Object> expectedToBeUnmodifiable = builder.toUnmodifiableMap();
        expectedToBeUnmodifiable.putAll(Collections.<String, Object>singletonMap("key", "value"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void cannotRemoveAnEntryOnUnmodifiableMap() {
        final NestedMapBuilder<String> builder = new NestedMapBuilder<String>(factory);
        final Map<String, Object> expectedToBeUnmodifiable = builder.
                add("key", "value").toUnmodifiableMap();
        expectedToBeUnmodifiable.remove("key");
    }
}
