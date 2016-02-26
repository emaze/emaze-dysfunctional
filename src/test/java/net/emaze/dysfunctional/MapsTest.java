package net.emaze.dysfunctional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.emaze.dysfunctional.Maps.Nested;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.collections.builders.MapBuilder;
import net.emaze.dysfunctional.collections.builders.NestedMapBuilder;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
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
    MapsTest.Facades.class
})
public class MapsTest {

    private static final Map<String, Object> A_MAP = new HashMap<String, Object>();

    public static class Builder {

        @Test
        public void canCreateBuilderFromMap() {
            final MapBuilder<String, Object> builder = Maps.from(A_MAP);
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateBuilderFromProvider() {
            final Provider<Map<String, Object>> provider = Compositions.compose(new Vary<Map<String, Object>, HashMap<String, Object>>(), new HashMapFactory<String, Object>());
            final MapBuilder<String, Object> builder = Maps.from(provider);
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
            final Provider<Map<String, Object>> provider = Compositions.compose(new Vary<Map<String, Object>, HashMap<String, Object>>(), new HashMapFactory<String, Object>());
            final NestedMapBuilder<String> builder = Nested.from(provider);
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
            final Map<String, String> got = Maps.mapKeys(input, new Delegate<String, String>() {

                @Override
                public String perform(String key) {
                    return key.concat("BecameKeyMapped");
                }
            });
            final Map<String, String> expected = Collections.singletonMap("keyToMapBecameKeyMapped", "value");
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canMapKeysMergingValues() {
            final Map<String, String> input = Maps.<String, String>builder().add("a", "b").add("c", "d").toMap();
            final Map<String, String> got = Maps.mapKeys(input, new Delegate<String, String>() {

                @Override
                public String perform(String key) {
                    return "sameKey";
                }
            }, new BinaryDelegate<String, String, String>() {

                @Override
                public String perform(String former, String latter) {
                    return latter;
                }
            });
            final Map<String, String> expected = Collections.singletonMap("sameKey", "d");
            Assert.assertEquals(expected, got);
        }

        @Test(expected = IllegalStateException.class)
        public void mapKeysThrowsIfDuplicateKey() {
            final Map<String, String> input = Maps.<String, String>builder().add("a", "b").add("c", "d").toMap();
            Maps.mapKeys(input, new Delegate<String, String>() {

                @Override
                public String perform(String key) {
                    return "sameKey";
                }
            });
        }

        @Test
        public void canMapValues() {
            final Map<String, String> input = Collections.singletonMap("key", "valueToMap");
            final Map<String, String> got = Maps.mapValues(input, new Delegate<String, String>() {

                @Override
                public String perform(String value) {
                    return value.concat("BecameValueMapped");
                }
            });
            final Map<String, String> expected = Collections.singletonMap("key", "valueToMapBecameValueMapped");
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canMapKeysAndValues() {
            final Map<String, String> input = Collections.singletonMap("keyToMap", "valueToMap");
            final Map<String, String> got = Maps.mapKeysAndValues(input, new Delegate<String, String>() {

                @Override
                public String perform(String key) {
                    return key.concat("BecameKeyMapped");
                }
            }, new Delegate<String, String>() {

                @Override
                public String perform(String value) {
                    return value.concat("BecameValueMapped");
                }
            });
            final Map<String, String> expected = Collections.singletonMap("keyToMapBecameKeyMapped", "valueToMapBecameValueMapped");
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canMapKeysAndValuesMergingValues() {
            final Map<String, String> input = Maps.<String, String>builder().add("a", "b").add("c", "d").toMap();
            final Map<String, String> got = Maps.mapKeysAndValues(input, new Delegate<String, String>() {

                @Override
                public String perform(String key) {
                    return "sameKey";
                }
            }, new Delegate<String, String>() {

                @Override
                public String perform(String value) {
                    return value.concat("BecameValueMapped");
                }
            }, new BinaryDelegate<String, String, String>() {

                @Override
                public String perform(String former, String latter) {
                    return latter;
                }
            });
            final Map<String, String> expected = Collections.singletonMap("sameKey", "dBecameValueMapped");
            Assert.assertEquals(expected, got);
        }
    }
}
