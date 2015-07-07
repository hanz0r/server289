package org.hanonator.net;

import java.net.SocketAddress;

import org.hanonator.service.Service;

/**
 * TODO
 * @author user104
 *
 */
public abstract class NetService implements Service {

	/**
	 * The socket address
	 */
	private final SocketAddress address;

	public NetService(SocketAddress address) {
		this.address = address;
	}

	public SocketAddress getAddress() {
		return address;
	}

}