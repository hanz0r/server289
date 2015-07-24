package org.hanonator.game.event.listener;

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
	public void listen(Session session, GameEvent event) throws GameException {
		String state = session.getAttributes().get("login-state", "handshake");
		switch (state) {
		case "handshake":
			
			break;
		}
	}

}