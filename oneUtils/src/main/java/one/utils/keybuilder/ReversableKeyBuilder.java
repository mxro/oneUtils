package one.utils.keybuilder;

import java.util.List;

/**
 * For a reversable key builder, it is possible to retrieve the value used to
 * generate the key from the key elements.
 * 
 * @author mx
 * 
 * @param <K>
 */
public interface ReversableKeyBuilder<K> extends KeyBuilder<K> {

	public K retrieveKey(List<String> keyElements);

}
