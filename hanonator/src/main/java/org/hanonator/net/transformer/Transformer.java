package org.hanonator.net.transformer;


@FunctionalInterface
public interface Transformer<I, O> {

	/**
	 * 
	 * @param input
	 * @return
	 */
	public abstract O transform(I input) throws TransformationException;

}