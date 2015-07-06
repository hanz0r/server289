package org.hanonator.game.event.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Builder for filterchain
 * 
 * @author user104
 */
public class FilterChainBuilder {

	/**
	 * The collection of filters
	 */
	private final List<Filter> patternFilterChain = new LinkedList<Filter>();

	public FilterChain build() {
		List<Filter> filters = new ArrayList<Filter>();
		filters.addAll(patternFilterChain);
		return new FilterChain(Collections.unmodifiableList(filters));
	}

    public FilterChainBuilder add(Filter filter) {
        return addLast(filter);
    }

    public FilterChainBuilder addFirst(Filter filter) {
        patternFilterChain.add(0, filter);
        return this;
    }
    
    public FilterChainBuilder addLast(Filter filter) {
        patternFilterChain.add(filter);
        return this;
    }

    public FilterChainBuilder add(int index, Filter filter) {
        patternFilterChain.add(index, filter);
        return this;
    }

    public FilterChainBuilder set(int index, Filter filter) {
        patternFilterChain.set(index, filter);
        return this;
    }

    public Filter get(int index)  {
        return patternFilterChain.get(index);
    }

    public FilterChainBuilder remove(int index) {
        patternFilterChain.remove(index);
        return this;
    }

    public FilterChainBuilder remove(Filter filter) {
        patternFilterChain.remove(filter);
        return this;
    }

    public FilterChainBuilder addAll(Filter[] array) {
        patternFilterChain.addAll(patternFilterChain.size(), Arrays.asList(array));
        return this;
    }

    public FilterChainBuilder addAll(int filterIndex, Filter[] array) {
        patternFilterChain.addAll(filterIndex, Arrays.asList(array));
        return this;
    }

    public FilterChainBuilder addAll(List<Filter> list) {
        return addAll(patternFilterChain.size(), list);
    }

    public FilterChainBuilder addAll(int filterIndex, List<Filter> list) {
        patternFilterChain.addAll(filterIndex, list);
        return this;
    }

    public FilterChainBuilder addAll(final FilterChainBuilder source) {
        patternFilterChain.addAll(source.patternFilterChain);
        return this;
    }

    public int indexOf(final Filter filter) {
        return patternFilterChain.indexOf(filter);
    }

}