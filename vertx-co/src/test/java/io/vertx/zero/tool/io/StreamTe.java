package io.vertx.zero.tool.io;

import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.StoreBase;
import io.vertx.zero.exception.EmptyStreamException;
import org.junit.Test;

public class StreamTe extends StoreBase {

    @Test(expected = EmptyStreamException.class)
    public void testRead(final TestContext context) {
        context.assertNotNull(Stream.read(getFile("ini.txt")));
    }
}
