package net.emaze.dysfunctional.interceptions;

public class ThrowingInterceptor implements Interceptor<String> {

    @Override
    public void before(String value) {
        throw new IllegalStateException();
    }

    @Override
    public void after(String value) {
        throw new IllegalStateException();
    }
}
