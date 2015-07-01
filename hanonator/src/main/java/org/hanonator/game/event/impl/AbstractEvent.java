package org.hanonator.game.event.impl;

import org.hanonator.game.User;
import org.hanonator.game.event.Event;

public abstract class AbstractEvent implements Event {

	private final User user;

	public AbstractEvent(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}