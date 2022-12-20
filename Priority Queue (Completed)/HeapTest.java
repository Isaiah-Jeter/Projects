import java.util.*;
public class HeapTest {
	public static void main (String [] args) {
		HeapPQ use = new HeapPQ();
		use.add(6);
		System.out.println("added 6");
		System.out.println (use.print());
		System.out.println (use.removeMin());
		System.out.println (use.print());
		use.add(3);
		System.out.println("added 3");
		use.add(5);
		System.out.println("added 5");
		System.out.println (use.print());
		System.out.println (use.removeMin());
		use.add(1);
		System.out.println("added 1");
		System.out.println (use.print());
		System.out.println (use.removeMin());
		System.out.println (use.print());
		use.add(10);
		System.out.println("added 10");
		System.out.println (use.print());
		use.add(12);
		System.out.println("added 12");
		System.out.println (use.print());
		
		use.add(3);
		System.out.println("added 3");
		System.out.println (use.print());
		System.out.println (use.peek());
		use.add(17);
		use.add(1);
		System.out.println (use.peek());
		System.out.println (use.size());
		System.out.println (use.removeMin());
		System.out.println (use.peek());
	}
}
