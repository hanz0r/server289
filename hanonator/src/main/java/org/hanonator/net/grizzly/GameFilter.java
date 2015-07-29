package org.hanonator.net.grizzly;

import java.io.IOException;
import java.util.logging.Logger;

import org.glassfish.grizzly.filterchain.BaseFilter;
import org.glassfish.grizzly.filterchain.FilterChainContext;
import org.glassfish.grizzly.filterchain.NextAction;

/**
 * 
 * 
 * @author user104
 */
public class GameFilter extends BaseFilter {

	/**
	 * The logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GameFilter.class.getName());

	@Override
	public NextAction handleAccept(FilterChainContext ctx) throws IOException {
//		logger.info("connection accepted " + ctx.getConnection());
//		
//		/*
//		 * Create session
//		 */
//		Session<Connection<?>> session = new Session<>();
//		
//		/*
//		 * Register a new gamechannel to the session
//		 */
//		session.register(new GrizzlyChannel(session, ctx.getConnection()));
//		
//		/*
//		 * Add the session to the attributes
//		 */
//		ctx.getConnection().getAttributes().setAttribute("session", session);
//		
//		/*
//		 * Let superclass handle the next action
//		 */
		return ctx.getInvokeAction();
	}

	@Override
	public NextAction handleClose(FilterChainContext ctx) throws IOException {
		logger.info("connection closed " + ctx.getConnection());
		return ctx.getInvokeAction();
	}

	@Override
	public NextAction handleRead(FilterChainContext ctx) throws IOException {
//		final HeapBuffer buffer = (HeapBuffer) ctx.getMessage();
//		
//		/*
//		 * Get the session
//		 */
//		@SuppressWarnings("unchecked")
//		Session<Connection<?>> session = (Session<Connection<?>>) ctx.getConnection().getAttributes().getAttribute("session");
//		
//		/*
//		 * If there is no session, discard all data
//		 */
//		if (session == null) {
//			buffer.rewind();
//			return ctx.getStopAction();
//		}
//		
//		/*
//		 * As long as there is data, keep keep reading, I keep keep reading love.
//		 */
//		if (buffer.hasRemaining()) {
//			int index = session.getState() == State.CONNECTED ? -1 : buffer.get() & 0xFF;
//			int length = session.getState() == State.CONNECTED ? buffer.remaining() : Headers.get(index);
//			
//			/*
//			 * If the length is specified as -1 it means the length is given
//			 * as the first readable byte by the client
//			 */
//			if (length == -1) {
//				if (buffer.remaining() <= 0) {
//					return ctx.getStopAction();
//				}
//				
//				length = buffer.get();
//			}
//			
//			/*
//			 * Read the payload of the message
//			 */
//			if (buffer.remaining() >= length) {
//				ByteBuffer payload = ByteBuffer.allocate(length);
//				
//				/*
//				 * Read payload
//				 */
//				 buffer.get(payload);
//				
//				/*
//				 * Create the message
//				 */
//				//Message message = new Message(index, length, payload);
//				
//				/*
//				 * Offer the message to be read by the session
//				 */
//				//session.channel().read(message, Transformers.DEFAULT_EVENT_TRANSFORMER);
//				
//				/*
//				 * If there is still data remaining, rerun the filter, otherwise go to next action
//				 */
//				return buffer.hasRemaining() ? ctx.getRerunFilterAction() : ctx.getInvokeAction();
//			}
//		}
		return ctx.getStopAction();
	}

	@Override
	public NextAction handleWrite(FilterChainContext ctx) throws IOException {
		return null;
//		if (ctx.getMessage() instanceof Message) {
//			final Message message = (Message) ctx.getMessage();
//			
//			/*
//			 * Create the buffer
//			 */
//			Buffer buffer = ctx.getMemoryManager().allocate(message.getPayload().capacity() + 3);
//			
//			/*
//			 * if opcode is -1, send data without header
//			 */
//			if (message.getId() != -1) {
//				/*
//				 * First byte should be the opcode
//				 */
//				buffer.put((byte) message.getId());
//				
//				/*
//				 * Some of the messages have varying lengths specified as short or byte
//				 */
//				if (message.getLength() == Message.VAR_LENGTH_BYTE) {
//					buffer.put((byte) message.getLength());
//				}
//				else if (message.header().getLength() == Message.VAR_LENGTH_SHORT) {
//					buffer.putShort((short) message.getLength());
//				}
//			}
//			
//			/*
//			 * Put the payload in the buffer
//			 */
//			buffer.put(message.payload());
//			
//			/*
//			 * Write the buffer
//			 */
//			ctx.setMessage(buffer.flip());
//		}
//		
//		/*
//		 * Go to the next filter
//		 */
//		return ctx.getInvokeAction();
	}
	
	@Override
	public void exceptionOccurred(FilterChainContext ctx, Throwable error) {
		error.printStackTrace();
	}

}