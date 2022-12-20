import java.util.Arrays;

public class MyArrayListTester {
    
    public static void main(String [] args) {
        
        MyArrayList<String> use1 = new MyArrayList<String>();
        MyArrayList<String> use2 = new MyArrayList<String> (3);
        MyArrayList<String> use3 = new MyArrayList<String> (10);
        MyArrayList<Double> use4 = new MyArrayList<Double> ();
        MyArrayList<Integer> use5 = new MyArrayList<Integer> (60);
          /*
        use1.add ("happy");
        use1.add ("sad");
        use1.add ("mad");
        use1.add ("sick");
		System.out.println("4: " + use1.size());
		use1.add (6, "take");
		System.out.println("5: " + use1.size());
		System.out.println("false: " + use1.isEmpty ());
        System.out.println("true: " + use2.isEmpty ());
        System.out.println("happy: " + use1.get (0));
        use1.add (0, "ok");
        System.out.println("ok: " + use1.get (0));
        System.out.println ("ok: " + use1.set (0, "old"));
        System.out.println("old: " + use1.get (0));
        

        use2.add ("ready");
        use2.add (2, "go");
        use2.add (1, "set");
        System.out.println("ready set go: " + use2.toString());

        use2.remove(1);
        System.out.println("ready go: " + use2.toString());

        use2.add(1, "new");
        use2.add ("ok");
        use2.remove ("go");
        System.out.println("ready new ok: " + use2.toString());
        */
        System.out.println ("0: " + use3.size ());
        System.out.println ("true: " + use3.isEmpty());
        use3.add ("test");
        System.out.println ("1: " + use3.size ());
        System.out.println ("false: " + use3.isEmpty());
        System.out.println ("test: " + use3.get(0));


		      /*
        System.out.println("is empty: " + use3.isEmpty ());
        System.out.println("is empty: " + use2.isEmpty ());
        System.out.println("is empty: " + use1.isEmpty ());

        System.out.println("number: " + use1.get (3));
        System.out.println("number: " + use1.get (1));
        System.out.println("number: " + use2.get (2));
        System.out.println("number: " + use1.get (0));

        System.out.println("number: " + use1.get (0));
        System.out.println ("old: " + use1.set (0, "ok"));
        System.out.println("number: " + use1.get (0));
        */
    }
    
}