package org.hanonator.net.grizzly;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

import org.glassfish.grizzly.Buffer;
import org.glassfish.grizzly.Connection;
import org.glassfish.grizzly.filterchain.BaseFilter;
import org.glassfish.grizzly.filterchain.FilterChainContext;
import org.glassfish.grizzly.filterchain.NextAction;
import org.glassfish.grizzly.memory.HeapBuffer;
import org.hanonator.net.Session;
import org.hanonator.net.Session.State;
import org.hanonator.net.event.ConnectEvent;
import org.hanonator.net.event.DisconnectEvent;
import org.hanonator.net.event.ReadEvent;
import org.hanonator.net.io.Header;

public class TempFilter extends BaseFilter {
	
	/**
	 * The grizzly server this filter is filtering
	 */
	private final GrizzlyServer server;
	
	public TempFilter(GrizzlyServer server) {
		this.server = server;
	}

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
		final HeapBuffer heap_buffer = (HeapBuffer) ctx.getMessage();
		
		/*
		 * Convert to a ByteBuffer
		 */
		final ByteBuffer buffer = ByteBuffer.allocate(heap_buffer.capacity());
		
		/*
		 * Fill the buffer
		 */
		heap_buffer.get(buffer);
		
		/*
		 * Flip the buffer
		 */
		buffer.flip();
		
		/*
		 * Read the message's header
		 */
		Header header = session.getState() == State.CONNECTED ? Header.wrap(buffer) : Header.estimate(buffer);
		
		/*
		 * Send the message to the channel for distribution
		 */
		server.getListener().fire(new ReadEvent(header.resolve(buffer), session));
		
		/*
		 * If there is still data remaining in the buffer, rerun this filter to get the leftover data
		 */
		if (buffer.remaining() - header.size() > 0) {
			Buffer out = ctx.getMemoryManager().allocate(buffer.remaining());
			out.put((ByteBuffer) buffer.flip());
			return ctx.getRerunFilterAction();
		}
		
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
		
		// TODO

		/*
		 * Let superclass handle the next action
		 */
		return super.handleWrite(ctx);
	}

	@Override
	public NextAction handleAccept(FilterChainContext ctx) throws IOException {
		/*
		 * Create session
		 */
		Session<Connection<?>> session = new Session<Connection<?>>();
		
		/*
		 * Register the grizzly channel and bind it to the connection
		 */
		session.register(new GrizzlyChannel()).bind(ctx.getConnection());
		
		/*
		 * Add the session to the attributes
		 */
		ctx.getConnection().getAttributes().setAttribute("session", session);
		
		/*
		 * Fire event
		 */
		server.getListener().fire(new ConnectEvent(session, (SocketAddress) ctx.getConnection().getPeerAddress()));
		
		/*
		 * Let superclass handle the next action
		 */
		return ctx.getInvokeAction();
	}
	
	@Override
	public NextAction handleClose(FilterChainContext ctx) throws IOException {
		Session<?> session = (Session<?>) ctx.getAttributes().getAttribute("session");

		/*
		 * Fire event
		 */
		server.getListener().fire(new DisconnectEvent(session, (SocketAddress) ctx.getConnection().getPeerAddress()));
		
		/*
		 * Let superclass handle the next action
		 */
		return super.handleClose(ctx);
	}

}