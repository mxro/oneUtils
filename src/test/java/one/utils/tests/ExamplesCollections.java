package one.utils.tests;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import de.mxro.concurrency.Concurrency;
import one.utils.jre.OneUtilsJre;

public class ExamplesCollections {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final Concurrency con = OneUtilsJre.newJreConcurrency();
		// ^-- replace with 'new GwtConcurrency()' for GWT environments
		// see https://gist.github.com/2791639

		// -----
		// Thread Safe List
		// -----

		// If any of the collections is instantiated in a GWT environment, they
		// are created as default (non-synchronized) collections, since in
		// GWT it is assured that there is no concurrent access to the
		// collections

		final List<String> threadSafeList = con.newCollection()
				.newThreadSafeList(String.class);

		threadSafeList.add("item1");

		// -----
		// Thread Safe Map
		// -----
		final Map<Integer, String> threadSafeMap = con.newCollection()
				.newThreadSafeMap(Integer.class, String.class);

		threadSafeMap.put(25, "text");

		// -----
		// Thread Safe Queue
		// -----
		final Queue<Integer> threadSafeQueue = con.newCollection()
				.newThreadSafeQueue(Integer.class);

		threadSafeQueue.add(25);

		// -----
		// Thread Safe Set
		// -----
		final Set<String> threadSafeSet = con.newCollection().newThreadSafeSet(
				String.class);
		threadSafeSet.add("element1");

	}

}
