package org.hanonator.game.event.listener;

import static org.joox.JOOX.$;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hanonator.game.GameException;
import org.hanonator.game.event.GameEvent;
import org.hanonator.net.Session;
import org.xml.sax.SAXException;

public class Listeners {

	/**
	 * The system ClassLoader
	 */
	private static final ClassLoader class_loader = ClassLoader.getSystemClassLoader();

	/**
	 * The logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Listeners.class.getName());

	/**
	 * The event handlers
	 */
	private static final Map<Integer, List<EventListener<? super GameEvent>>> listeners = new HashMap<>();

	/**
	 * Parses the file containing all of the eventlistener mappings
	 * 
	 * example:
	 * 	<listener listen-id="-1" action="org.hanonator.game.event.listener.LOGIN_LISTENER" />
	 * 
	 * @param file
	 * @throws IOException
	 * @throws SAXException
	 */
	@SuppressWarnings("unchecked")
	public static void load(File file) throws IOException, SAXException {
		$($(file).document()).find("listener").forEach(e -> {
			try {
				/*
				 * XML values
				 */
				int id = Integer.valueOf(e.getAttribute("listen-id"));
				String action = e.getAttribute("action");
				
				/*
				 * Get the list of event listeners mapped to the given id or create a new one
				 * if there is not one already present
				 */
				List<EventListener<? super GameEvent>> list = listeners.containsKey(id) ? listeners.get(id) : new ArrayList<>();
				
				/*
				 * Create a new instance of the event listener
				 */
				// Class<EventListener<? super GameEvent>> c = EventListener<? super GameEvent>.class;
				
				/*
				 * Add the class to the list of listeners
				 */
				list.add((EventListener<? super GameEvent>) class_loader.loadClass(action).newInstance());
				
				/*
				 * Register the list of listeners to its corresponding id (to make sure that
				 * when a new list of event has been created, it is mapped to the right id)
				 */
				listeners.put(id, list);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		});
		logger.info("EventListeners OK");
	}

	/**
	 * Notifies the correct EventListeners
	 * 
	 * @param events
	 * @param session
	 * @throws GameException
	 */
	public static void notify(Collection<GameEvent> events, Session<?> session) throws GameException {
		/*
		 * Iterator over all of the game events
		 */
		for (Iterator<GameEvent> iterator = events.iterator(); iterator.hasNext(); ) {
			GameEvent event = iterator.next();
			
			/*
			 * Get the listeners that listen to the give event id
			 */
			List<EventListener<? super GameEvent>> list = listeners.get(event.getIndex());
			for (Iterator<EventListener<? super GameEvent>> it$ = list.iterator(); it$.hasNext(); ) {
				EventListener<? super GameEvent> listener = it$.next();
				
				/*
				 * Send the event to the listener
				 */
				try {
					listener.listen(session, event);
				} catch (Exception ex) {
					listener.exceptionOccured(session, new GameException(ex));
				}
			}
		}
	}

}