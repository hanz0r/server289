package org.hanonator.net.transform;


@FunctionalInterface
public interface Transformer<I, O> {

	/**
	 * 
	 * @param input
	 * @return
	 */
	public abstract O transform(I input) throws TransformationException;

}