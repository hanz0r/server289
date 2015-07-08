package org.hanonator.net.grizzly;

import java.io.IOException;
import java.util.logging.Logger;

import org.glassfish.grizzly.filterchain.BaseFilter;
import org.glassfish.grizzly.filterchain.FilterChainContext;
import org.glassfish.grizzly.filterchain.NextAction;
import org.hanonator.game.User;
import org.hanonator.net.Session;

public class ConnectionFilter extends BaseFilter {

	/**
	 * The logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConnectionFilter.class.getName());

	@Override
	public NextAction handleAccept(FilterChainContext ctx) throws IOException {
		logger.info("connection accepted from " + ctx.getConnection().getPeerAddress());
		
		/*
		 * Create session
		 */
		Session session = new User(ctx.getConnection());
		
		/*
		 * Add the session to the attributes
		 */
		ctx.getConnection().getAttributes().setAttribute("session", session);
		
		/*
		 * Let superclass handle the next action
		 */
		return super.handleConnect(ctx);
	}

	@Override
	public NextAction handleClose(FilterChainContext ctx) throws IOException {
		logger.info("connection closed at " + ctx.getConnection().getPeerAddress());
		return super.handleClose(ctx);
	}

}