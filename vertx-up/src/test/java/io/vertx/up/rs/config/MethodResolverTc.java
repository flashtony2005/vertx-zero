package io.vertx.up.rs.config;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.unit.TestContext;
import io.vertx.up.example.RMethod1;
import io.vertx.zero.test.UnitBase;
import org.junit.Test;

import java.lang.reflect.Method;

public class MethodResolverTc extends UnitBase {

    @Test
    public void testMethod1(final TestContext context)
            throws NoSuchMethodException {
        final Method method = RMethod1.class.getDeclaredMethod("sayHell");
        final HttpMethod httpMethod = MethodResolver.resolve(method);
        context.assertEquals(HttpMethod.GET, httpMethod);
    }

    @Test
    public void testMethod2(final TestContext context)
            throws NoSuchMethodException {
        final Method method = RMethod1.class.getDeclaredMethod("sayHell1");
        final HttpMethod httpMethod = MethodResolver.resolve(method);
        context.assertNull(httpMethod);
    }
}