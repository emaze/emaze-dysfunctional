package net.emaze.dysfunctional.interceptions;

import java.util.List;

public class BucketFillingInterceptor implements Interceptor<String> {

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
