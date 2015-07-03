package org.hanonator.game.event;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.dom4j.Document;
import org.dom4j.Element;
import org.hanonator.game.event.DataParser.DataType;
import org.hanonator.net.util.PacketLength;

public class Templates {

	private static final Logger logger = Logger.getLogger(Templates.class.getName());

	/**
	 * 
	 */
	private static final Map<Integer, Template> templates = new HashMap<>();
	
	/**
	 * Load all the templates from an xml file
	 * 
	 * @param file
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void load(Document document) throws Exception {
		/*
		 * Parse all packet information
		 */
		for (Iterator<Element> iterator = document.getRootElement().elements().iterator(); iterator.hasNext(); ) {
			Element element = iterator.next();

			/*
			 * Get the index of the decoder
			 */
			int index = Integer.valueOf(element.element("opcode").getText());
			
			/*
			 * Set the size of the packets
			 */
			PacketLength.put(index, Integer.valueOf(element.elementText("length")));
			
			/*
			 * Parse the decoder's attributes
			 */
			List<Element> elements = element.element("attributes").elements("attribute");
			
			/*
			 * The decoder's attributes
			 */
			Attribute[] attributes = new Attribute[elements.size()];
			
			/*
			 * The template
			 */
			Template template = new Template(index, PacketLength.get(index));
			
			/*
			 * loop through attributes
			 */
			for (Iterator<Element> it = elements.iterator(); it.hasNext(); ) {
				Element node = it.next();
				
				/*
				 * Get the type of the attribute
				 */
				DataType type = DataType.valueOf(node.attributeValue("type").toUpperCase());
				
				/*
				 * Get the name of the field
				 */
				String name = node.attributeValue("name");
				
				/*
				 * Add the attribute to the template
				 */
				template.add(new org.hanonator.game.event.Attribute(name, type));
			}
			
			/*
			 * Add the decoder to the collection
			 */
			templates.put(index, template);
			
			/*
			 * Debug information
			 */
			logger.info("Decoder added on index " + index + " with " + attributes.length + " attributes.");
		}
	}

	public static Template get(int id) {
		return templates.get(id);
	}

}