package org.hanonator.net;

public interface Transformer<I, O> {

	/**
	 * 
	 * @param input
	 * @return
	 */
	public abstract O transform(I input) throws TransformationException;

}