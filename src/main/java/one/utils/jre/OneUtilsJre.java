package one.utils.jre;

import java.io.IOException;
import java.io.InputStream;

import one.utils.jre.internal.IOUtils;
import de.mxro.concurrency.Concurrency;
import de.mxro.concurrency.jre.JreConcurrency;

public class OneUtilsJre {

    // TODO move static method to ConcurrencyJre
    public static Concurrency newJreConcurrency() {
        return new JreConcurrency();
    }

    public final static byte[] toByteArray(final InputStream is) throws IOException {

        return IOUtils.toByteArray(is);

    }
}
