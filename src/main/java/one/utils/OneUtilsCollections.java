package one.utils;

import java.util.ArrayList;
import java.util.List;

public class OneUtilsCollections {

	/**
	 * Reverse the order of a list.
	 * 
	 * @param list
	 * @return
	 */
	public static <GPEntry> List<GPEntry> flip(final List<GPEntry> list) {
		final List<GPEntry> flipped = new ArrayList<GPEntry>(list.size());
		for (final GPEntry entry : list) {
			flipped.add(0, entry);
		}
		return flipped;
	}

	public interface Predicate<GType> {
		/**
		 * If the specified element passes the test, this method should return
		 * true.
		 * 
		 * @param element
		 * @return
		 */
		public boolean testElement(GType element);
	}

	/**
	 * Specify a predicate to filter a list.
	 * 
	 * @param list
	 * @param type
	 * @return
	 */
	public static <GPType> List<GPType> filterList(final List<GPType> list,
			final Predicate<GPType> test) {
		final List<GPType> newList = new ArrayList<GPType>(list.size());
		for (final GPType o : list) {
			if (test.testElement(o)) {
				newList.add(o);
			}
		}
		return newList;
	}

}
