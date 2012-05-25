package one.utils.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Enables to schedule items in a fixed number of intervals.
 * 
 * @author mroh004
 * 
 * @param <ItemType>
 */
public class Scheduler<ItemType> {

	public interface ScheduleEventListener {
		public void onUnpaused();

		public void onProcessed();
	}

	public interface ScheduledListener<ItemType> {
		public void process(List<ItemType> toProcess);
	}

	public final static int[] INTERVALS = new int[] { 200, 400, 600, 800, 1000,
			1200, 1400, 1600, 1800, 2000, 3000, 4000, 5000, 6000, 7000, 8000,
			9000, 10000, 15000, 20000, 30000, 40000, 50000, 60000, 80000,
			100000, 60 * 60 * 1000, 24 * 60 * 60 * 1000 };

	private final ScheduledListener<ItemType> listener;

	private OneTimer timer;

	private final Concurrency con;

	@SuppressWarnings("rawtypes")
	private final Map<Integer, List> scheduledItems;
	private final Set<ItemType> pausedItems;
	private final List<ItemType> toProcess;
	private final List<ScheduleEventListener> scheduleEventListeners;

	private volatile int counter;
	private volatile boolean globalPaused;

	public void stop() {
		if (timer != null) {
			globalPaused = false;
			toProcess.clear();
			timer.stop();
			timer = null;
		}
	}

	private boolean validInterval(final int interval) {
		for (final int testInt : INTERVALS) {
			if (testInt == interval) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Prevents this scheduler from triggering new processes temporarily.
	 * 
	 */
	public void pause() {
		globalPaused = true;
	}

	public void unpause() {
		globalPaused = false;

		for (final ScheduleEventListener listener : scheduleEventListenersCopy()) {
			listener.onUnpaused();
		}
	}

	private final List<ScheduleEventListener> scheduleEventListenersCopy() {
		return new ArrayList<ScheduleEventListener>(scheduleEventListeners);
	}

	public boolean isPaused() {
		return globalPaused || pausedItems.size() > 0;
	}

	public boolean processingPending(final ItemType item) {
		return toProcess.contains(item);
	}

	public boolean processingPending() {
		return toProcess.size() > 0;
	}

	public void addScheduleEventListener(final ScheduleEventListener listener) {
		assert !this.scheduleEventListeners.contains(listener);

		this.scheduleEventListeners.add(listener);
	}

	public void removeScheduleEventListener(final ScheduleEventListener listener) {
		assert this.scheduleEventListeners.contains(listener);

		this.scheduleEventListeners.remove(listener);
	}

	@SuppressWarnings("unchecked")
	public void scheduleAt(final int interval, final ItemType item) {
		if (!validInterval(interval)) {
			throw new IllegalArgumentException("Interval [" + interval
					+ "] not allowed. Only use one of [" + INTERVALS + "]");
		}
		scheduledItems.get(interval).add(item);
		if (timer == null) {
			startTimer();
		}
	}

	public void pause(final int interval, final ItemType item) {

		assert !this.pausedItems.contains(item);

		this.pausedItems.add(item);
	}

	public void unpause(final int interval, final ItemType item) {
		assert this.pausedItems.contains(item);

		this.pausedItems.remove(item);

		for (final ScheduleEventListener listener : scheduleEventListenersCopy()) {
			listener.onUnpaused();
		}
	}

	public void unscheduleAt(final int interval, final ItemType item) {
		if (!validInterval(interval)) {
			throw new IllegalArgumentException("Interval [" + interval
					+ "] not allowed. Only use one of [" + INTERVALS + "]");
		}

		assert scheduledItems.get(interval).contains(item) : "Item cannot be removed since it was not scheduled.\n"
				+ "  Item: ["
				+ item
				+ "]\n"
				+ "  At interval: ["
				+ interval
				+ "]" + "  Registered Items: [" + scheduledItems + "]";

		scheduledItems.get(interval).remove(item);

		toProcess.remove(item);

		if (scheduledItemCount() == 0) {
			stop();
		}
	}

	@SuppressWarnings("rawtypes")
	public boolean isScheduled(final ItemType item) {
		for (final Entry<Integer, List> e : scheduledItems.entrySet()) {
			if (e.getValue().contains(item)) {
				return true;
			}
		}
		return false;
	}

	public void unscheduleAll(final ItemType item) {
		for (final int intervall : INTERVALS) {
			unscheduleAt(intervall, item);
		}
		assert scheduledItemCount() == 0;
		stop();
	}

	/**
	 * How many items are scheduled in total.
	 * 
	 * @return
	 */
	public int scheduledItemCount() {
		int count = 0;
		for (final int interval : INTERVALS) {
			count = count + scheduledItems.get(interval).size();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	protected void tick() {

		counter += 200;
		if (counter > INTERVALS[INTERVALS.length - 1]) {
			counter = 200;
		}

		final List<ItemType> toProcessAdd = new ArrayList<ItemType>();
		for (final int interval : INTERVALS) {
			if (counter % interval == 0) {

				toProcessAdd.addAll(scheduledItems.get(interval));
			}
		}

		for (final ItemType toProcessItem : toProcessAdd) {
			if (!toProcess.contains(toProcessItem)) {
				toProcess.add(toProcessItem);
			}
		}

		if (!this.globalPaused) {

			this.listener.process(filterActive(toProcess));
			toProcess.clear();
			for (final ScheduleEventListener listener : scheduleEventListenersCopy()) {
				listener.onProcessed();
			}
		}
	}

	private final List<ItemType> filterActive(final List<ItemType> items) {
		final List<ItemType> activeItems = new ArrayList<ItemType>(items.size());

		for (final ItemType item : items) {
			if (isScheduled(item) && !pausedItems.contains(item)) {
				activeItems.add(item);
			}
		}

		return activeItems;
	}

	protected void startTimer() {
		assert this.timer == null;

		this.timer = con.newTimer().scheduleRepeating(1, 200, new Runnable() {

			@Override
			public void run() {
				tick();
			}

		});
	}

	public Scheduler(final Class<ItemType> itemType, final Concurrency con,
			final ScheduledListener<ItemType> listener) {
		super();
		this.listener = listener;
		this.scheduledItems = con.newCollection().newThreadSafeMap(
				Integer.class, List.class);
		this.toProcess = con.newCollection().newThreadSafeList(itemType);

		for (final int interval : INTERVALS) {
			this.scheduledItems.put(interval, con.newCollection()
					.newThreadSafeList(itemType));
		}
		this.con = con;
		this.counter = 0;
		this.globalPaused = false;

		this.pausedItems = con.newCollection().newThreadSafeSet(itemType);

		this.scheduleEventListeners = con.newCollection().newThreadSafeList(
				ScheduleEventListener.class);
	}

}
