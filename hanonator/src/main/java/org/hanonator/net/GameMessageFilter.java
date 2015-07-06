package org.hanonator.net;

import java.io.IOException;

import org.glassfish.grizzly.Buffer;
import org.glassfish.grizzly.filterchain.BaseFilter;
import org.glassfish.grizzly.filterchain.FilterChainContext;
import org.glassfish.grizzly.filterchain.NextAction;
import org.glassfish.grizzly.memory.HeapBuffer;
import org.hanonator.net.util.PacketLength;

public class GameMessageFilter extends BaseFilter {

	@Override
	public NextAction handleRead(FilterChainContext ctx) throws IOException {
		final HeapBuffer buffer = (HeapBuffer) ctx.getMessage();
		
		if (buffer.remaining() > 1) {
			int index = buffer.get() & 0xFF;
			int length = PacketLength.get(index);
			
			/*
			 * If the length is specified as -1 it means the length is given
			 * as the first readable byte by the client
			 */
			if (length == -1) {
				if (buffer.remaining() <= 0) {
					return ctx.getStopAction();
				}
				
				length = buffer.get();
			}
			
			/*
			 * Read the payload of the message
			 */
			if (buffer.remaining() >= length) {
				ctx.setMessage(new GameMessage(index, length, buffer.slice()));
				return ctx.getInvokeAction();
			}
		}
		return ctx.getStopAction();
	}

	@Override
	public NextAction handleWrite(FilterChainContext ctx) throws IOException {
		final GameMessage message = (GameMessage) ctx.getMessage();
		
		/*
		 * Create the buffer
		 */
		Buffer buffer = ctx.getMemoryManager().allocate(message.getPayload().capacity() + 3);
		
		/*
		 * if opcode is -1, send data without header
		 */
		if (message.getId() != -1) {
			/*
			 * First byte should be the opcode
			 */
			buffer.put((byte) message.getId());
			
			/*
			 * Some of the messages have varying lengths specified as short or byte
			 */
			if (message.getLength() == GameMessage.VAR_LENGTH_BYTE) {
				buffer.put((byte) message.getPayload().capacity());
			}
			else if (message.getLength() == GameMessage.VAR_LENGTH_SHORT) {
				buffer.putShort((short) message.getPayload().capacity());
			}
		}
		/*
		 * Put the payload in the buffer
		 */
		buffer.put(message.getPayload());
		
		/*
		 * Set the filterchain's message as the buffer
		 */
		ctx.setMessage(buffer.flip());
		
		/*
		 * Go to the next filter
		 */
		return ctx.getInvokeAction();
	}

	@Override
	public void exceptionOccurred(FilterChainContext ctx, Throwable error) {
		super.exceptionOccurred(ctx, error);
		
		error.printStackTrace();
	}

}
