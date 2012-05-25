package one.utils.concurrent;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public interface CollectionFactory {

	public abstract <GPType> Queue<GPType> newThreadSafeQueue(
			Class<GPType> itemType);

	public abstract <KeyType, ValueType> Map<KeyType, ValueType> newThreadSafeMap(
			Class<KeyType> keyType, Class<ValueType> valueType);

	public abstract <ItemType> List<ItemType> newThreadSafeList(
			Class<ItemType> itemType);

	public abstract <ItemType> Set<ItemType> newThreadSafeSet(
			Class<ItemType> itemType);

}
