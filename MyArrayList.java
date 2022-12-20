/* See ArrayList documentation here:
 * http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
 */

/*
 * Your indexed functions should throw IndexOutOfBoundsException if index is invalid!
 */
/*
I DID IT MR CHIEN, LOTS OF PEOPLE DOUBTED ME BUT I DID IT
Matthew Paul and Isaih Jeter
*/

public class MyArrayList<E> {
    
    /* Internal Object counter */
    protected int objectCount;
    
    /* Internal Object array */
    protected E [] internalArray;
    
    /* Constructor: Create it with whatever capacity you want? */
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        this.internalArray = (E[])new Object[100];
    }
    
    /* Constructor with initial capacity */
    @SuppressWarnings("unchecked")
    public MyArrayList(int initialCapacity){
        this.internalArray = (E[])new Object[initialCapacity];
    }
    
    /* Return the number of active slots in the array list */
    public int size() {
        return (objectCount);
    }
    
    /* Are there zero objects in the array list? */
    public boolean isEmpty() {
        if (objectCount == 0) {
            return true;
        }
        return false;
    }
    
    /* Get the index-th object in the list. */
    public E get(int index) {
        return (internalArray[index]);
    }
    
    /* Replace the object at index with obj.  returns object that was replaced. */
    public E set(int index, E obj) {
       E old = internalArray[index];
       internalArray[index] = obj;
       return old;
    }

    /* Insert an object at index */
    @SuppressWarnings("unchecked")
    public void add(int index, E obj) {
    	if (index > objectCount) {
            throw new IndexOutOfBoundsException();
        }
        else if (objectCount == internalArray.length) {
            E[] current = (E[])new Object[internalArray.length * 2];
            for (int a=0; a < index; a++){
                current[a] = internalArray[a];
            }
            current[index] = obj;
            for (int b = index + 1; b < current.length; b++) {
                current[b] = internalArray[b-1];
            }
            internalArray = current;
        }
        else {
            for (int b = objectCount; b > index; b--) {
                internalArray[b] = internalArray[b-1];
            }
            internalArray[index] = obj;
        }
        objectCount++;
    }

    /* Add an object to the end of the list; returns true */
    @SuppressWarnings("unchecked")
    public boolean add(E obj) {
        if (objectCount == internalArray.length) {
            E[] current = (E[])new Object[internalArray.length * 2];
            for (int a = 0; a < internalArray.length; a++){
                current[a] = internalArray[a];
            }
            current[internalArray.length] = obj;
            internalArray = current;
        }
        else {
            internalArray[objectCount] = obj;
        }
        objectCount++;
        return true;
    }

    /* Remove the object at index and shift.  Returns removed object. */
    public E remove(int index) {
       E past = internalArray[index];
       for (int a = index; a < internalArray.length -1; a++) {
            internalArray[a] = internalArray[a+1];
       }
       objectCount--;
       return past;
    }
    
    /* Removes the first occurrence of the specified element from this list, 
     * if it is present. If the list does not contain the element, it is unchanged. 
     * More formally, removes the element with the lowest index i such that
     * (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists). 
     * Returns true if this list contained the specified element (or equivalently, 
     * if this list changed as a result of the call). */
    public boolean remove(E obj) {
        for (int a = 0; a < internalArray.length; a++){
            if(internalArray[a]==null&&obj==null){
                remove(a);
                return true;
            }
            
            if (internalArray[a].equals(obj)){
                this.remove(a);
                a = internalArray.length;
                return true;
            }
            
        }
        return false;
    }
    
    /* For testing; your string should output as "{ X X X X ... }" where X X X X ... are the elements in the array.
     * If the array is empty, it should return "{ }".  If there is one element, "{ X }", etc.
     * Elements are separated by a space. */
    public String toString() {
        String st = "{ ";
        for (int a = 0; a < objectCount; a++){
            st += (internalArray[a] + " ");
        }
        st += "}";
        return st;
    }
}