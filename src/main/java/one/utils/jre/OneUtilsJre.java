package one.utils.jre;

import delight.concurrency.Concurrency;
import delight.concurrency.jre.JreConcurrency;

import java.io.IOException;
import java.io.InputStream;

import one.utils.jre.internal.IOUtils;

public class OneUtilsJre {

    // TODO move static method to ConcurrencyJre
    public static Concurrency newJreConcurrency() {
        return new JreConcurrency();
    }

    public final static byte[] toByteArray(final InputStream is) throws IOException {

        return IOUtils.toByteArray(is);

    }
}
