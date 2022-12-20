// Implements a singly-linked list.
// Matthew Paul and Isaiah Jeter
public class MyHashSet {
    private ListNode[] buckets;
    private int objCount;
    private double loadFactorLimit;

    // Constructor: creates an empty hash set with default parameters
    public MyHashSet()
    {
        this.buckets = new ListNode[10];
        this.objCount = 0;
        this.loadFactorLimit = 0.75;
    }

    // Constructor: creates a hash set with the given initial bucket size and load factor limit
    public MyHashSet(int bucketCount, double loadFactorLimit)
    {
        this.buckets = new ListNode[bucketCount];
        this.objCount = 0;
        this.loadFactorLimit = loadFactorLimit;
    }

    // Return a pointer to the bucket array
    public ListNode[] getBuckets() {
        return this.buckets;
    }

    // Returns true if this set is empty; otherwise returns false.
    public boolean isEmpty()
    {
        return (objCount == 0);
    }

    // Returns the number of elements in this set.
    public int size()
    {
        return objCount;
    }

    // Returns the current load factor (objCount / buckets)
    public double currentLoadFactor() {
        return (double)objCount / (double)buckets.length;
    }

    // Return the bucket index for the object
    public int whichBucket(Object obj) {
        return obj.hashCode() % this.buckets.length;
    }

    // Return true if the object exists in the set, otherwise false.
    // Use the .equals method to check equality.
    public boolean contains(Object obj) {
        int a = whichBucket(obj);
        //fix for condition until .getnext == null
        for (ListNode node = buckets[a]; node != null; node = node.getNext()) {
            if (node.getValue().equals(obj)) {
                return true;
            }
        }
        return false;
    }

    // Add an object to the set.
    // If the object already exists in the set you should *not* add another.
    // Return true if the object was added; false if the object already exists.
    // If an item should be added, add it to the beginning of the bucket.
    // After adding the element, check if the load factor is greater than the limit.
    // - If so, you must call rehash with double the current bucket size.
    public boolean add(Object obj) {
        if (contains (obj)) {
            return false;
        }

        int a = whichBucket(obj);
        if (buckets[a] != null) {
            ListNode store = buckets[a];
            buckets[a] = new ListNode (obj, store);
            objCount++;
        }
        else {
            buckets[a] = new ListNode (obj, null);
            objCount++;
        }

        if (currentLoadFactor() > loadFactorLimit) {
            rehash (buckets.length*2);
        }
        return true;
    }

    // Remove the object.  Return true if successful, false if the object did not exist
    public boolean remove(Object obj) {
        if (contains (obj)) {
            int a = whichBucket(obj);
            if (buckets[a].getNext() == null) {
               buckets[a] = null;
               objCount--;
               return true;
            }
            if (buckets[a].getValue().equals(obj)) {
            	buckets[a] = buckets[a].getNext();
            	objCount--;
            	return true;
            }
            for (ListNode node = buckets[a]; node.getNext() != null; node = node.getNext()) {
                if (node.getNext().getValue().equals(obj)) {
                    node.setNext(node.getNext().getNext());
                    objCount--;
                    return true;
                }
            }
        }
        return false;
    }

    // Rehash the set so that it contains the given number of buckets
    // Loop through all existing buckets, from 0 to length
    // rehash each object into the new bucket array in the order they appear on the original chain.
    public void rehash(int newBucketCount) {
        ListNode[] bucketsClone =  new ListNode[newBucketCount];
        
        for (int a = 0; a < buckets.length; a++) {
            for (ListNode node = buckets[a]; node != null; node = node.getNext()) {
                int number = node.getValue().hashCode() % newBucketCount;
                bucketsClone[number] = new ListNode (node.getValue(), bucketsClone[number]);
            }
        }
        buckets = bucketsClone;
    }

    // The output should be in the following format:
    // [ #1, #2 | { b#: v1 v2 v3 } { b#: v1 v2 } ]
    // #1 is the objCount
    // #2 is the number of buckets
    // For each bucket that contains objects, create a substring that indicates the bucket index
    // And list all of the items in the bucket (in the order they appear)
    public String toString() {

        String start = "[ " + objCount + ", " + (buckets.length) + " | ";
        String str = "";
        for (int a = 0; a < buckets.length; a++) {
            if (buckets[a] != null) {
                str += "{ " + a + ": ";
                for (ListNode node = buckets[a]; node!=null; node=node.getNext()) {
                    str += node.getValue() + " ";
                }
                str += "} ";
            }
        }
        str += "]";
        return start + str;
    }

}
