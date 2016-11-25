package avaliacao.util;

import java.util.Collection;
import java.util.Iterator;

public class StringUtil {

	public static String join(Collection<?> s, String delimitador){
		
		StringBuilder builder = new StringBuilder();
		Iterator<?> i = s.iterator();
		
		while (i.hasNext()) {
			builder.append(i.next());
			if (!i.hasNext()) {
				break;
			}
			builder.append(delimitador);
		}
		return builder.toString();
	}
	
}
