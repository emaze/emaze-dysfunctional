package net.emaze.dysfunctional.interceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.delegates.Delegate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class InterceptorChainTest {

    public static class BucketFillingInterceptor implements Interceptor<String> {

        private final int id;
        private final List<Integer> bucket;
        
        public BucketFillingInterceptor(int id, List<Integer> bucket) {
            this.id = id;
            this.bucket = bucket;
        }

        @Override
        public void before(String value) {
            bucket.add(id);
        }

        @Override
        public void after(String value) {
            bucket.add(id);
        }
    }

    
    public static class BucketFillingDelegate implements Delegate<String,String>{
        private final int id;
        private final List<Integer> bucket;
        
        public BucketFillingDelegate(int id, List<Integer> bucket) {
            this.id = id;
            this.bucket = bucket;
        }

        @Override
        public String perform(String value) {
            bucket.add(id);
            return value;
        }
    
    }

    @Test
    public void chainingIsDoneInCorrectOrder() {
        List<Integer> bucket = new ArrayList<Integer>();
        InterceptorChain<String, String> chain = new InterceptorChain<String, String>(new BucketFillingDelegate(4, bucket));
        chain.add(new BucketFillingInterceptor(1, bucket));
        chain.add(new BucketFillingInterceptor(2, bucket));
        chain.add(new BucketFillingInterceptor(3, bucket));
        chain.perform("useless_value");
        Assert.assertEquals(Arrays.asList(1,2,3,4,3,2,1), bucket);
    }
}
