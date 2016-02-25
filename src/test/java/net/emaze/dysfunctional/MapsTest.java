package net.emaze.dysfunctional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.emaze.dysfunctional.Maps.Nested;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.collections.builders.MapBuilder;
import net.emaze.dysfunctional.collections.builders.NestedMapBuilder;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.emaze.dysfunctional.order.ComparableComparator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    MapsTest.Builder.class,
    MapsTest.NestedBuilder.class,
    MapsTest.Facades.class,
    MapsTest.Mapper.class
})
public class MapsTest {

    private static final Map<String, Object> A_MAP = new HashMap<String, Object>();
    private static final Supplier<Map<String, Object>> PROVIDER = Compositions.compose(new Vary<HashMap<String, Object>, Map<String, Object>>(), new HashMapFactory<String, Object>());

    public static class Builder {

        @Test
        public void canCreateBuilderFromMap() {
            final MapBuilder<String, Object> builder = Maps.from(A_MAP);
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateBuilderFromProvider() {
            final MapBuilder<String, Object> builder = Maps.from(PROVIDER);
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyUnsortedBuilder() {
            final MapBuilder<String, Object> builder = Maps.builder();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyLinkedBuilder() {
            final MapBuilder<String, Object> builder = Maps.linked();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyTreeBuilder() {
            final MapBuilder<String, Object> builder = Maps.tree();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyTreeWithComparatorBuilder() {
            final MapBuilder<String, Object> builder = Maps.tree(new ComparableComparator<String>());
            Assert.assertNotNull(builder);
        }
    }

    public static class NestedBuilder {

        @Test
        public void canCreateBuilderFromProvider() {
            final NestedMapBuilder<String> builder = Nested.from(PROVIDER);
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyBuilder() {
            final NestedMapBuilder<String> builder = Nested.builder();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyLinkedBuilder() {
            final NestedMapBuilder<String> builder = Nested.linked();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyTreeBuilder() {
            final NestedMapBuilder<String> builder = Nested.tree();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyTreeWithComparatorBuilder() {
            final NestedMapBuilder<String> builder = Nested.tree(new ComparableComparator<String>());
            Assert.assertNotNull(builder);
        }
    }

    public static class Facades {

        @Test
        public void mapsIsNotFinal() {
            new Maps() {
            };
        }

        @Test
        public void nestedIsNotFinal() {
            new Nested() {
            };
        }

    }

    public static class Mapper {

        @Test
        public void canMapKeys() {
            final Map<String, String> input = Collections.singletonMap("keyToMap", "value");
            final Map<String, String> got = Maps.mapKeys(input, key -> key.concat("BecameKeyMapped"));
            final Map<String, String> expected = Collections.singletonMap("keyToMapBecameKeyMapped", "value");
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canMapKeysMergingValues() {
            final Map<String, String> input = Maps.<String, String>builder().add("a", "b").add("c", "d").toMap();
            Map<String, String> got = Maps.mapKeys(input, key -> "sameKey", (first, second) -> second);
            final Map<String, String> expected = Collections.singletonMap("sameKey", "d");
            Assert.assertEquals(expected, got);
        }

        @Test(expected = IllegalStateException.class)
        public void mapKeysThrowsIfDuplicateKey() {
            final Map<String, String> input = Maps.<String, String>builder().add("a", "b").add("c", "d").toMap();
            Maps.mapKeys(input, key -> "sameKey");
        }

        @Test
        public void canMapValues() {
            final Map<String, String> input = Collections.singletonMap("key", "valueToMap");
            final Map<String, String> got = Maps.mapValues(input, value -> value.concat("BecameValueMapped"));
            final Map<String, String> expected = Collections.singletonMap("key", "valueToMapBecameValueMapped");
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canMapKeysAndValues() {
            final Map<String, String> input = Collections.singletonMap("keyToMap", "valueToMap");
            final Map<String, String> got = Maps.mapKeysAndValues(input, key -> key.concat("BecameKeyMapped"), value -> value.concat("BecameValueMapped"));
            final Map<String, String> expected = Collections.singletonMap("keyToMapBecameKeyMapped", "valueToMapBecameValueMapped");
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canMapKeysAndValuesMergingValues() {
            final Map<String, String> input = Maps.<String, String>builder().add("a", "b").add("c", "d").toMap();
            final Map<String, String> got = Maps.mapKeysAndValues(input, key -> "sameKey", value -> value.concat("BecameValueMapped"), (first, second) -> second);
            final Map<String, String> expected = Collections.singletonMap("sameKey", "dBecameValueMapped");
            Assert.assertEquals(expected, got);
        }

    }
}
