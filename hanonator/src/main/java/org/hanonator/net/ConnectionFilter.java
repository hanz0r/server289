package org.hanonator.net;

import java.io.IOException;

import org.glassfish.grizzly.filterchain.BaseFilter;
import org.glassfish.grizzly.filterchain.FilterChainContext;
import org.glassfish.grizzly.filterchain.NextAction;

public class ConnectionFilter extends BaseFilter {

	@Override
	public NextAction handleAccept(FilterChainContext ctx) throws IOException {
		System.out.println("connection accepted m8 from " + ctx.getConnection().getPeerAddress());
		return super.handleConnect(ctx);
	}

	@Override
	public NextAction handleClose(FilterChainContext ctx) throws IOException {
		System.out.println("connection closed m8 from " + ctx.getConnection().getPeerAddress());
		return super.handleClose(ctx);
	}

}