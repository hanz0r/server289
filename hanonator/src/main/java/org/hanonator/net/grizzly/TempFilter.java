package org.hanonator.net.grizzly;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

import org.glassfish.grizzly.Connection;
import org.glassfish.grizzly.filterchain.BaseFilter;
import org.glassfish.grizzly.filterchain.FilterChainContext;
import org.glassfish.grizzly.filterchain.NextAction;
import org.glassfish.grizzly.memory.HeapBuffer;
import org.hanonator.net.Session;
import org.hanonator.net.Session.State;

public class TempFilter extends BaseFilter {
	
	/**
	 * The logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TempFilter.class.getName());
	
	@Override
	public NextAction handleRead(FilterChainContext ctx) throws IOException {
		Session<?> session = (Session<?>) ctx.getConnection().getAttributes().getAttribute("session");
		
		/*
		 * The session cannot be null
		 */
		if (session == null || session.getState() == State.DISCONNECTED) {
			ctx.getConnection().closeSilently();
			return ctx.getStopAction();
		}

		/*
		 * Get the client's data
		 */
		final ByteBuffer buffer = ((HeapBuffer) ctx.getMessage()).toByteBuffer();
		
		/*
		 * Invoke next filter
		 */
		return ctx.getInvokeAction();
	}

	@Override
	public NextAction handleWrite(FilterChainContext ctx) throws IOException {
		Session<?> session = (Session<?>) ctx.getAttributes().getAttribute("session");
		
		/*
		 * The session cannot be null
		 */
		if (session == null || session.getState() == State.DISCONNECTED) {
			ctx.getConnection().closeSilently();
			return ctx.getStopAction();
		}
		return super.handleWrite(ctx);
	}

	@Override
	public NextAction handleAccept(FilterChainContext ctx) throws IOException {
		logger.info("connection accepted " + ctx.getConnection());
		
		/*
		 * Create session
		 */
		Session<Connection<?>> session = new Session<>();
		
		/*
		 * Register a new gamechannel to the session
		 */
		session.register(new GameChannel(session, ctx.getConnection()));
		
		/*
		 * Add the session to the attributes
		 */
		ctx.getConnection().getAttributes().setAttribute("session", session);
		
		/*
		 * Let superclass handle the next action
		 */
		return ctx.getInvokeAction();
	}
	
	@Override
	public void exceptionOccurred(FilterChainContext ctx, Throwable error) {
		error.printStackTrace();
		super.exceptionOccurred(ctx, error);
	}

}