package org.hanonator.game.event.listener;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.Random;

import org.hanonator.game.GameException;
import org.hanonator.game.event.GameEvent;
import org.hanonator.net.Session;

/**
 * Handles all the events to do with login (id=-1)
 * 
 * @author Red
 *
 */
public class LoginEventListener implements EventListener<GameEvent> {

	/**
	 * The generator for randoms lol
	 */
	private static final Random RANDOM_GENERATOR = new Random();

	/**
	 * The initial response (idk lol)
	 */
	private static final byte[] INITIAL_RESPONSE = {
			0, 0, 0, 0, 0, 0, 0, 0
	};

	@Override
	public void listen(Session<?> session, GameEvent event) throws GameException {
		String state = session.getAttributes().get("login-state", "handshake");
		ByteBuffer payload = event.getAttributes().get("payload");
		
		/*
		 * Do nothing if the payload is null
		 */
		if (payload == null) {
			throw new NullPointerException("payload is null");
		}
		
		/*
		 * Perform the login sequence
		 */
		switch (state) {
		case "handshake":
			/*
			 * Handshake requires 2 bytes
			 */
			if (payload.remaining() < 2) {
				throw new BufferUnderflowException();
			}
			
			/*
			 * The opcode (14: game server, 15: file server)
			 */
			int opcode = payload.get() & 0xFF;
			
			/*
			 * 14 is connection request to the game server
			 */
			if (opcode == 14) {
				/*
				 * junk data?
				 */
				@SuppressWarnings("unused")
				int junk$0 = payload.get() & 0xFF;
				
				/*
				 * Write the login response
				 */
				session.channel().stream().write(ByteBuffer.wrap(INITIAL_RESPONSE)).write((byte) 0)
						.write(RANDOM_GENERATOR.nextLong()).flush();
				
				/*
				 * Update the login state
				 */
				session.getAttributes().set("login-state", "details");
			}
			
			/*
			 * 15 is connection request to the file server
			 */
			else if (opcode == 15) {
				throw new UnsupportedOperationException("not yet implemented");
			}
			
			/*
			 * Else throw game exception
			 */
			else {
				session.channel().closeSilently();
				throw new IllegalStateException("opcode needs to be 14 or 15, is " + opcode);
			}
			break;
		case "details":
			
			break;
		}
	}

}