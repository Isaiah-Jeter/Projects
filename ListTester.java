public class ListTester {
    public static void main(String [] args) {
        SinglyLinkedList use = new SinglyLinkedList((Object[]) new String[] { "a", "b", "c" });
        System.out.println(use);
        System.out.println("1: " + use.indexOf("b"));
        System.out.println("true: " + use.add("d"));
        System.out.println(use);
        SinglyLinkedList use2 = new SinglyLinkedList();
        System.out.println(use2);
        use2.add("a");
        use2.add(null);
        System.out.println(use2);
        System.out.println("1: " + use2.indexOf(null));
        System.out.println(use2);
        System.out.println("true: " + use2.remove(null));
        System.out.println(use2);
        use2.add("e");
        System.out.println(use2);
        System.out.println("true " + use2.remove("e"));
        System.out.println(use2);
        use2.add("f");
        System.out.println(use2);

        SinglyLinkedList use3 = new SinglyLinkedList();
        for (int i = 0; i < 100; i++) {
            use3.add("" + i);
        }
        System.out.println(use3);

        for (int i = 25; i < 75; i++) {
            use2.remove(i);      
        }
        System.out.println(use3);

        System.out.println("Linked list size: " + use2.size());
        System.out.println("19: " + use3.get(19));
        System.out.println("19:" + use3.set(19, "ff"));
        System.out.println("0: " + use3.set(0, "bjork is angry"));

        System.out.println(use3);

 	}
}
