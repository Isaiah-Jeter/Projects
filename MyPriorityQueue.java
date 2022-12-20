//Matthew Paul and Isaiah Jeter
import java.util.*;
public class MyPriorityQueue{
	private ArrayList<Comparable> list;

	public MyPriorityQueue () {
		list = new ArrayList<Comparable>();
	}

	public Comparable add (Comparable obj) {
		if (list.size() < 1) {
			list.add (obj);
			return obj;
		}
		for (int i = 0; i < list.size (); i++) {
			if (obj.compareTo(list.get(i)) <= 0) {
				list.add(i , obj);
				return obj;
			}
		}
		list.add (obj);
		return obj;
	}

	public Comparable removeMin() {
		if (list.size () < 1) {
			return null;
		}
		return (list.remove (0));
	}

	public Comparable peekMin() {
		if (list.size () < 1) {
			return null;
		}
		return (list.get(0));
	}

	public int size() {
		return (list.size ());
	}
}