package org.hanonator.game.event.filter;

import static org.joox.JOOX.$;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.hanonator.game.event.filter.DataFilter.DataType;
import org.hanonator.net.Headers;
import org.xml.sax.SAXException;

/**
 * Sample decoder XML
 * 
 * 	<!-- Test packet -->
 *	<decoder opcode="60" length="4">
 *		<attribute type="SHORT" name="test-1" />
 *		<attribute type="SHORT" name="test-2" />
 *	</decoder>
 *
 * @author Red
 *
 */
public class Filters {

	/**
	 * The collection of filterchains
	 */
	private static final Map<Integer, FilterChain> filter_chains = new HashMap<>();
	
	public static void load(File file) throws IOException, SAXException {
		$($(file).document()).find("decoder").forEach(e -> {
			FilterChainBuilder builder = new FilterChainBuilder();
			
			int id = Integer.valueOf(e.getAttribute("opcode"));
			int size = Integer.valueOf(e.getAttribute("length"));
			
			$(e).children().forEach(a -> {
				String type = a.getAttribute("type");
				String name = a.getAttribute("name");
				
				builder.addLast(new AttributeFilter<>(name, DataType.valueOf(type)));
			});
			
			filter_chains.put(id, builder.build());
			Headers.put(id, size);
		});
	}

	/**
	 * Get the filter chain for the given opcode id
	 * @param integer
	 * @return
	 */
	public static FilterChain get(Integer integer) {
		return filter_chains.get(integer);
	}

}