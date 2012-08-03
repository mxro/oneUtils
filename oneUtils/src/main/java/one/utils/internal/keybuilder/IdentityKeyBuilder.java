package one.utils.internal.keybuilder;

import java.util.ArrayList;
import java.util.List;

import one.utils.keybuilder.ReversableKeyBuilder;

public class IdentityKeyBuilder implements ReversableKeyBuilder<String> {

	@Override
	public List<String> makeKey(final String key) {
		final List<String> keyList = new ArrayList<String>(1);
		keyList.add(key);
		return keyList;
	}

	@Override
	public String retrieveKey(final List<String> keyElements) {
		return keyElements.get(0);
	}

}
