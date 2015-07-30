package org.hanonator.net.event;

import org.hanonator.net.Session;
import org.hanonator.net.io.Message;

public class ReadEvent {

	/**
	 * The message
	 */
	private final Message message;
	
	/**
	 * The session that has received the message
	 */
	private final Session<?> session;

	public ReadEvent(Message message, Session<?> session) {
		this.message = message;
		this.session = session;
	}

	/**
	 * @return the message
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * @return the session
	 */
	public Session<?> getSession() {
		return session;
	}

}