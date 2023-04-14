package comparators;

/**
 * clase definida que implementa la interfaz del Comparator y le pasamos su
 * objeto al TreeMap para que se ordene Map por valor
 * 
 */
import java.util.Comparator;
import java.util.Map;

public class PuntajeComparator implements Comparator<Object> {

	Map<String, Integer> map;

	public PuntajeComparator(Map<String, Integer> map) {
		this.map = map;
	}

	public int compare(Object o1, Object o2) {
		if (map.get(o2) == map.get(o1))
			return 1;
		else
			return ((Integer) map.get(o1)).compareTo((Integer) map.get(o2)) * -1;
	}

}
