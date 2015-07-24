package org.hanonator.net.grizzly;

import java.io.IOException;
import java.util.logging.Logger;

import org.glassfish.grizzly.filterchain.BaseFilter;
import org.glassfish.grizzly.filterchain.FilterChainContext;
import org.glassfish.grizzly.filterchain.NextAction;
import org.hanonator.game.GameService;
import org.hanonator.net.Session;
import org.hanonator.net.Sessions;
import org.hanonator.service.Services;
import org.hanonator.util.Attributes;

public class ConnectionFilter extends BaseFilter {

	/**
	 * The logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConnectionFilter.class.getName());

	@Override
	public NextAction handleAccept(FilterChainContext ctx) throws IOException {
		logger.info("connection accepted " + ctx.getConnection());
		
		/*
		 * Create the attributes required to create a new session
		 */
		Attributes attributes = new Attributes();
		attributes.set("connection", ctx.getConnection());
		
		/*
		 * Create session
		 */
		Session session = Sessions.create(attributes); //new User(ctx.getConnection());
		
		/*
		 * Add the session to the attributes
		 */
		ctx.getConnection().getAttributes().setAttribute("session", session);
		
		/*
		 * Register the connection
		 */
		GameService service = Services.get(GameService.class);
		service.register(ctx.getConnection());
		
		/*
		 * Let superclass handle the next action
		 */
		return ctx.getInvokeAction();
	}

	@Override
	public NextAction handleClose(FilterChainContext ctx) throws IOException {
		/*
		 * Remove the connection
		 */
		GameService service = Services.get(GameService.class);
		if (ctx.getConnection() != null && service != null) {
			service.remove(ctx.getConnection());
		}
		
		/*
		 * Logger
		 */
		logger.info("connection closed " + ctx.getConnection());
		return ctx.getInvokeAction();
	}

}