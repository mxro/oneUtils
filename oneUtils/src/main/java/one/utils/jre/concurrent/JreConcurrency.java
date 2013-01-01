package one.utils.jre.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import one.utils.concurrent.CollectionFactory;
import one.utils.concurrent.Concurrency;
import one.utils.concurrent.ExecutorFactory;
import one.utils.concurrent.OneAtomicBoolean;
import one.utils.concurrent.OneExecutor;
import one.utils.concurrent.OneLock;
import one.utils.concurrent.OneTimer;
import one.utils.concurrent.TimerFactory;

public class JreConcurrency implements Concurrency {

	@Override
	public ExecutorFactory newExecutor() {

		return new ExecutorFactory() {

			@Override
			public OneExecutor newSingleThreadExecutor(final Object owner) {
				final ExecutorService executor = newExecutor(1, owner);

				return new JavaExecutor(executor);
			}

			@Override
			public OneExecutor newParallelExecutor(
					final int maxParallelThreads, final Object owner) {
				final ExecutorService executor = newExecutor(
						maxParallelThreads, owner);

				return new JavaExecutor(executor);
			}

			@Override
			public OneExecutor newImmideateExecutor() {
				return new OneExecutor() {

					@Override
					public Object execute(final Runnable runnable) {
						runnable.run();
						return Thread.currentThread();
					}

					@Override
					public void shutdown(final WhenExecutorShutDown callback) {
						callback.thenDo();
					}

					@Override
					public Object getCurrentThread() {

						return Thread.currentThread();
					}

				};
			}

		};
	}

	@Override
	public TimerFactory newTimer() {
		return new TimerFactory() {

			@Override
			public OneTimer scheduleOnce(final int when, final Runnable runnable) {

				final java.util.Timer javaTimer = new java.util.Timer();
				final TimerTask timerTask = new TimerTask() {

					@Override
					public void run() {
						runnable.run();
					}

				};

				javaTimer.schedule(timerTask, when);

				return new OneTimer() {

					@Override
					public void stop() {
						javaTimer.cancel();
					}

				};

			}

			@Override
			public OneTimer scheduleRepeating(final int offsetInMs,
					final int intervallInMs, final Runnable runnable) {
				final java.util.Timer javaTimer = new java.util.Timer();
				final TimerTask timerTask = new TimerTask() {

					@Override
					public void run() {
						runnable.run();
					}

				};

				javaTimer.scheduleAtFixedRate(timerTask, offsetInMs,
						intervallInMs);

				return new OneTimer() {

					@Override
					public void stop() {
						javaTimer.cancel();
					}

				};
			}

		};
	}

	@Override
	public void runLater(final Runnable runnable) {
		new Thread() {

			@Override
			public void run() {
				runnable.run();
			}

		}.start();
	}

	@Override
	public OneLock newLock() {

		return new OneLock() {
			private final ReentrantLock lock = new ReentrantLock();

			@Override
			public void lock() {
				lock.lock();
			}

			@Override
			public void unlock() {
				lock.unlock();
			}

			@Override
			public boolean isHeldByCurrentThread() {
				return lock.isHeldByCurrentThread();
			}

		};
	}

	@Override
	public CollectionFactory newCollection() {

		return new CollectionFactory() {

			@Override
			public <GPType> Queue<GPType> newThreadSafeQueue(
					final Class<GPType> itemType) {
				return new ConcurrentLinkedQueue<GPType>();
			}

			@Override
			public <ItemType> List<ItemType> newThreadSafeList(
					final Class<ItemType> itemType) {

				return Collections.synchronizedList(new ArrayList<ItemType>());
			}

			@Override
			public <KeyType, ValueType> Map<KeyType, ValueType> newThreadSafeMap(
					final Class<KeyType> keyType,
					final Class<ValueType> valueType) {
				return Collections
						.synchronizedMap(new HashMap<KeyType, ValueType>());
			}

			@Override
			public <ItemType> Set<ItemType> newThreadSafeSet(
					final Class<ItemType> itemType) {
				return Collections.synchronizedSet(new HashSet<ItemType>());
			}
		};
	}

	private static ExecutorService newExecutor(final int capacity,
			final Object owner) {
		final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
		final String threadName = owner.getClass().getName();
		final ThreadFactory threadFacory = new ThreadFactory() {

			@Override
			public Thread newThread(final Runnable r) {

				return new Thread(r, threadName);
			}
		};
		final RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {

			@Override
			public void rejectedExecution(final Runnable arg0,
					final ThreadPoolExecutor arg1) {
				throw new RuntimeException(
						"Thread executor could not execute: [" + arg0 + "]. \n"
								+ "  Executor: [" + arg1 + "]\n"
								+ "  Thread owner: [" + threadName + "]");
			}
		};

		final ExecutorService executor = new ThreadPoolExecutor(capacity,
				capacity, 50, TimeUnit.MILLISECONDS, workQueue, threadFacory,
				rejectedExecutionHandler);
		return executor;
	}

	@Override
	public OneAtomicBoolean newAtomicBoolean(final boolean value) {

		return new OneAtomicBoolean() {

			private final AtomicBoolean wrapped = new AtomicBoolean(value);

			@Override
			public void set(final boolean newValue) {
				wrapped.set(newValue);
			}

			@Override
			public boolean getAndSet(final boolean newValue) {

				return wrapped.getAndSet(newValue);
			}

			@Override
			public boolean get() {
				return wrapped.get();
			}

			@Override
			public boolean compareAndSet(final boolean expect,
					final boolean update) {
				return wrapped.compareAndSet(expect, update);
			}
		};
	}

}
