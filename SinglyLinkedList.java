// Implements a singly-linked list.					
					
					
public class SinglyLinkedList {					
	private ListNode head;				
	private int nodeCount;				
					
	// Constructor: creates an empty list				
	public SinglyLinkedList() {				
		head=null;			
		nodeCount=0;			
	}				
					
	// Constructor: creates a list that contains				
	// all elements from the array values, in the same order				
	public SinglyLinkedList(Object[] values) {				
		head = new ListNode (values[0], new ListNode (values[1]));			
		ListNode node = head.getNext();			
		if (values.length > 1) {			
			for(int i=1;i<values.length - 1;i++){		
				node.setNext(new ListNode (values[i+1]));	
				node=node.getNext();	
			}		
		}			
	nodeCount=values.length;				
	}				
					
	// Returns true if this list is empty; otherwise returns false.				
	public boolean isEmpty() {				
		if(nodeCount==0) {			
			return true;		
		}			
		return false;			
	}				
					
	// Returns the number of elements in this list.				
	public int size() {				
		return nodeCount;			
	}				
					
	// Returns true if this list contains an element equal to obj;				
	// otherwise returns false.				
	public boolean contains(Object obj) {				
		for(ListNode node=head; node!=null; node=node.getNext()){			
			if (obj != null && node.getValue().equals(obj)) {		
				return true;	
			}		
			if (obj == null && node.getValue() == null) {		
				return true;	
			}		
		}			
		return false;			
	}				
					
	// Returns the index of the first element in equal to obj;				
	// if not found, retunrs -1.				
	public int indexOf(Object obj) {				
		int count=0;			
		for(ListNode node=head; node!=null; node=node.getNext()){			
			if(node.getValue () != null && node.getValue().equals(obj)) {		
				return count;	
			}		
			if (node.getValue () == null && obj == null) {		
				return count;	
			}		
			count++;		
		}			
		return (-1);			
	}				
					
	// Adds obj to this collection.  Returns true if successful;				
	// otherwise returns false.				
	public boolean add(Object obj) {				
		if (nodeCount > 0) {			
			for(ListNode node=head; node!=null; node=node.getNext()){		
				if(node.getNext() == (null)){	
					node.setNext(new ListNode (obj));
					nodeCount++;
					return true;
				}	
			}		
		}			
		else {			
			head = new ListNode (obj);		
			nodeCount++;		
			return true;		
		}			
		return false;			
	}				
					
	// Removes the first element that is equal to obj, if any.				
	// Returns true if successful; otherwise returns false.				
	public boolean remove(Object obj) {		
		if (head.getValue () != null && head.getValue().equals (obj) && nodeCount == 1){
				head = null;
				nodeCount--;
				return true;
			}
		for(ListNode node=head; node.getNext()!=null; node=node.getNext()){			
			if (head.getValue () != null && head.getValue().equals (obj)){
				head = node.getNext();
				nodeCount--;
				return true;
			}
			if (head.getValue () == null && head.getValue() == (null)){
				head = node.getNext();
				nodeCount--;	
				return true;
			}
			if (obj != null && node.getNext().getValue().equals (obj)){		
				node.setNext(node.getNext().getNext());	
				nodeCount--;	
				return true; 	
			}		
			if (obj == null && node.getNext().getValue() == (null)) {		
				node.setNext(node.getNext().getNext());	
				nodeCount--;	
				return true;	
			}		
		}			
		return false;			
	}				
					
	// Returns the i-th element.               				
	public Object get(int i) {				
		int count=0;			
		for(ListNode node=head; node!=null; node=node.getNext()){			
			if (count==i)		
				return (node.getValue());	
			count++;		
		}			
		throw new IndexOutOfBoundsException();			
	}				
					
	// Replaces the i-th element with obj and returns the old value.				
	public Object set(int i, Object obj) {				
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException ();
		}
		Object store = null;
		int count = 0;
		for(ListNode node=head; count < nodeCount; node = node.getNext()) {
			if (count == i) {
				if (node.getValue() == null) {
					node.setValue(obj);	
					return store;
				}
				else {
				store = node.getValue();	
				node.setValue(obj);	
				return store;	
				}
			}
			count++;
		}	
		throw new IndexOutOfBoundsException ();	
	}				
					
	// Inserts obj to become the i-th element. Increments the size				
	// of the list by one.				
	public void add(int i, Object obj) {				
		if (i  < 0 || i > nodeCount) {			
			throw new IndexOutOfBoundsException();		
		}			
		else if (i == 0) {			
			ListNode store = head;		
			head = new ListNode (obj, store);		
			nodeCount++;		
		}			
		else {			
			int count=0;		
			for(ListNode node=head; node!=null; node=node.getNext()){		
				if(count == i - 1) {	
					ListNode store = new ListNode (obj);
					store.setNext(node.getNext());
					node.setNext(store);
					nodeCount++;
				}	
				count++;	
			}		
		}			
					
	}				
					
	// Removes the i-th element and returns its value.				
	// Decrements the size of the list by one.				
	public Object remove(int i) {				
		if (i < 0 || i > nodeCount) {			
			throw new IndexOutOfBoundsException();		
		}			
		int count=0;			
		Object a = null;			
		for(ListNode node=head; node!=null; node=node.getNext()){			
			if (count==i){		
				a = node.getValue();	
				this.remove(node.getValue());		
			}		
			count++;		
		}			
		return a;			
	}
	// Returns a string representation of this list.				
	public String toString() {				
		String str = ("{");			
		for(ListNode node = head; node!=null; node=node.getNext()){			
			str += (" " + node.getValue());		
		}			
		str += (" }");			
		return str;			
	}				
					
}					