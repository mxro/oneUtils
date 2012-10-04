package one.utils.gwt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import one.utils.concurrent.CollectionFactory;
import one.utils.concurrent.Concurrency;
import one.utils.concurrent.ExecutorFactory;
import one.utils.concurrent.OneExecutor;
import one.utils.concurrent.OneLock;
import one.utils.concurrent.OneTimer;
import one.utils.concurrent.TimerFactory;

import com.google.gwt.user.client.Timer;

/**
 * 
 * @author Max
 * 
 */
public final class GwtConcurrency implements Concurrency {
	private static final Object THREAD = new Object();

	@Override
	public TimerFactory newTimer() {

		return new TimerFactory() {

			@Override
			public OneTimer scheduleOnce(final int when, final Runnable runnable) {
				final Timer timer = new Timer() {

					@Override
					public void run() {
						runnable.run();
					}

				};
				timer.schedule(when);
				return new OneTimer() {

					@Override
					public void stop() {
						timer.cancel();
					}
				};
			}

			@Override
			public OneTimer scheduleRepeating(final int offsetInMs,
					final int intervallInMs, final Runnable runnable) {

				final Timer timer = new Timer() {

					@Override
					public void run() {
						runnable.run();
					}

				};
				timer.scheduleRepeating(intervallInMs);
				return new OneTimer() {

					@Override
					public void stop() {
						timer.cancel();
					}
				};

			}

		};
	}

	@Override
	public ExecutorFactory newExecutor() {

		return new ExecutorFactory() {

			@Override
			public OneExecutor newSingleThreadExecutor(final Object owner) {
				return new OneExecutor() {

					@Override
					public void shutdown(final WhenExecutorShutDown callback) {
						callback.thenDo();
					}

					@Override
					public Object execute(final Runnable runnable) {
						newTimer().scheduleOnce(1, runnable);
						return THREAD; // only one Thread in JS
					}

					@Override
					public Object getCurrentThread() {

						return THREAD;
					}
				};
			}

			@Override
			public OneExecutor newParallelExecutor(
					final int maxParallelThreads, final Object owner) {

				return newSingleThreadExecutor(owner);
			}

			@Override
			public OneExecutor newImmideateExecutor() {

				return new OneExecutor() {

					@Override
					public void shutdown(final WhenExecutorShutDown callback) {
						callback.thenDo();
					}

					@Override
					public Object execute(final Runnable runnable) {
						runnable.run();
						return THREAD; // only one Thread in JS
					}

					@Override
					public Object getCurrentThread() {

						return THREAD;
					}
				};
			}

		};
	}

	@Override
	public void runLater(final Runnable runnable) {

	}

	@Override
	public OneLock newLock() {

		return new OneLock() {

			@Override
			public void lock() {

			}

			@Override
			public void unlock() {

			}

			@Override
			public boolean isHeldByCurrentThread() {
				return true;
			}

		};
	}

	@Override
	public CollectionFactory newCollection() {

		return new CollectionFactory() {

			@Override
			public <GPType> Queue<GPType> newThreadSafeQueue(
					final Class<GPType> itemType) {
				return new LinkedList<GPType>();
			}

			@Override
			public <KeyType, ValueType> Map<KeyType, ValueType> newThreadSafeMap(
					final Class<KeyType> keyType,
					final Class<ValueType> valueType) {
				return new HashMap<KeyType, ValueType>();
			}

			@Override
			public <ItemType> List<ItemType> newThreadSafeList(
					final Class<ItemType> itemType) {
				return new ArrayList<ItemType>();
			}

			@Override
			public <ItemType> Set<ItemType> newThreadSafeSet(
					final Class<ItemType> itemType) {
				return new HashSet<ItemType>();
			}

		};
	}

}