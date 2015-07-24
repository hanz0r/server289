package org.hanonator.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Attributes implements Iterable<Entry<String, Object>>, Supplier<Stream<Entry<String, Object>>> {

	/**
	 * THe map of attributes
	 */
	private final Map<String, Object> attributes = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	public <T> T get(String name) {
		return (T) attributes.get(name);
	}
	
	public <T> T get(String name, T defaultValue) {
		return attributes.containsKey(name) ? get(name) : defaultValue;
	}
	
	public void set(String name, Object object) {
		this.attributes.put(name, object);
	}

	public int size() {
		return attributes.size();
	}

	@Override
	public Stream<Entry<String, Object>> get() {
		return attributes.entrySet().stream();
	}

	@Override
	public Iterator<Entry<String, Object>> iterator() {
		return attributes.entrySet().iterator();
	}

	public boolean contains(String key) {
		return attributes.containsKey(key);
	}
}
