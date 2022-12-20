// Implements a singly-linked list.					
					
					
public class SinglyLinkedListWithTail {					
	private ListNode head;
	private ListNode tail;			
	private int nodeCount;				
					
	// Constructor: creates an empty list				
	public SinglyLinkedListWithTail() {				
		head=null;	
		tail=null;		
		nodeCount=0;			
	}				
					
	// Constructor: creates a list that contains				
	// all elements from the array values, in the same order				
	public SinglyLinkedListWithTail(Object[] values) {				
		if (values.length == 1) {
			head = new ListNode (values[0]);
			tail = head;	
		}
		else {
			head = new ListNode (values[0], new ListNode (values[1]));
			tail = head.getNext();					
			for(int i=1;i<values.length - 1;i++){		
				tail.setNext(new ListNode (values[i+1]));	
				tail=tail.getNext();	
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
		if ((head.getValue() != null && head.getValue().equals(obj)) || (tail.getValue() != null && tail.getValue().equals(obj))) {
			return true;
		}
		if ((head.getValue() == null && obj == null) || (tail.getValue() == null && obj == null)) {
			return true;
		}			
		for(ListNode node=head.getNext(); node!=null; node=node.getNext()){			
			if (node.getValue() != null && node.getValue().equals(obj)) {		
				return true;	
			}		
			if (node.getValue() == null && obj == null) {		
				return true;	
			}		
		}			
		return false;			
	}				
					
	// Returns the index of the first element in equal to obj;				
	// if not found, retunrs -1.				
	public int indexOf(Object obj) {				
		if (head.getValue() != null && head.getValue().equals(obj)) {
			return 0;
		}
		if (head.getValue() == null && obj == null) {
			return 0;
		}
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
	// otherwise returns false.				.
	public boolean add(Object obj) {				
		if (nodeCount > 0) {
			tail.setNext(new ListNode (obj));
			tail = tail.getNext();
			System.out.println(tail.getValue());
			nodeCount++;
			return true;
		}			
		else if (nodeCount == 0) {			
			head = new ListNode (obj);
			tail = head;	
			nodeCount++;		
			return true;		
		}
		else {			
			return false;			
		}
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
		if (i == 0) {
			return head.getValue();
		}
		if (i == nodeCount) {
			return tail.getValue();
		}
		int count=0;			
		for(ListNode node=head; node!=null; node=node.getNext()){			
			if (count==i) {
				return (node.getValue());
			}
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
		if (i == 0) {
			if (head.getValue() == null) {
					head.setValue(obj);	
					return store;
			}
			else {
				store = head.getValue();	
				head.setValue(obj);	
				return store;	
			}
		}
		if (i == nodeCount) {
			if (tail.getValue() == null) {
					tail.setValue(obj);	
					return store;
			}
			else {
				store = tail.getValue();	
				tail.setValue(obj);	
				return store;
			}
		}
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
		else if (i == nodeCount) {
			tail.setNext(new ListNode (obj));
			tail = tail.getNext();
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
		if (i == 0) {
			a = head.getValue();
			this.remove (head.getValue());
			return a;
		}	
		if (i == nodeCount) {
			a = tail.getValue();
			this.remove (tail.getValue());
			return a;
		}		
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