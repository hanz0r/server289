package org.hanonator.net;


@FunctionalInterface
public interface Transformer<I, O> {

	/**
	 * 
	 * @param input
	 * @return
	 */
	public abstract O transform(I input) throws TransformationException;

}