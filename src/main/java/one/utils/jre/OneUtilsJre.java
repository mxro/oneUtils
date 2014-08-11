package one.utils.jre;

import java.io.IOException;
import java.io.InputStream;

import de.mxro.concurrency.Concurrency;
import one.utils.jre.concurrent.JreConcurrency;
import one.utils.jre.internal.IOUtils;

public class OneUtilsJre {

	public static Concurrency newJreConcurrency() {
		return new JreConcurrency();
	}

	public final static byte[] toByteArray(final InputStream is)
			throws IOException {

		return IOUtils.toByteArray(is);

	}
}
