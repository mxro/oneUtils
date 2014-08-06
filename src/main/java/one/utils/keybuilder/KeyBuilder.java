/**
 * 
 */
package one.utils.keybuilder;

import java.util.List;

/**
 * @author <a href="http://www.mxro.de/">Max Erik Rohde</a>
 * 
 * Copyright Max Erik Rohde 2011. All rights reserved.
 */
public interface KeyBuilder<K> {
	
	/**
	 * Given a key of an arbitrary type, produce a list of strings to identify this key.<br/>
	 * Any passed key should ALWAYS produce the same list of strings.<br />
	 * For example:
	 * "this.is.a.key" --> ["this", "is", "a", "key"]
	 *  
	 * 
	 * @param key
	 * @return
	 */
	public List<String> makeKey(K key);
	
}
