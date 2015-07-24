package org.hanonator.net;

import org.hanonator.net.grizzly.GrizzlySession;
import org.hanonator.util.Attributes;

public abstract class Sessions {

	/**
	 * 
	 * 
	 * @param attributes
	 * @return
	 */
	public static final Session create(Attributes attributes) {
		return new GrizzlySession(attributes);
	}

}