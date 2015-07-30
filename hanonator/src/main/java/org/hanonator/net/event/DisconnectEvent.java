package org.hanonator.net.event;

import java.net.SocketAddress;

import org.hanonator.net.Session;

public class DisconnectEvent {

	/**
	 * The newly created session
	 */
	private final Session<?> session;
	
	/**
	 * The remote address
	 */
	private final SocketAddress remoteAddress;

	public DisconnectEvent(Session<?> session, SocketAddress remoteAddress) {
		this.session = session;
		this.remoteAddress = remoteAddress;
	}

	/**
	 * @return the session
	 */
	public Session<?> getSession() {
		return session;
	}

	/**
	 * @return the remoteAddress
	 */
	public SocketAddress getRemoteAddress() {
		return remoteAddress;
	}
}