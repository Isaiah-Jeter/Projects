//Matthew Paul and Isaiah Jeter
public class HeapPQ <E extends Comparable<E>> /*implements MyPriorityQueue<E>*/ {

	private E[] heap;
	private int objectCount;
	
	public HeapPQ()
    {
        this.heap = (E[])new Comparable[3];
        this.objectCount = 0;
    }

	//Adds obj to the Priority Queue
	public void add(E obj)
	{
		if (objectCount == heap.length-1) {
			increaseCapacity();
		}
		heap[objectCount+1] = obj;
		objectCount++;
		if (objectCount == 2 && obj.compareTo (peek()) < 0) {
			heap[2] = heap[1];
			heap[1] = obj;
		}
		if (objectCount > 2) {
			bubbleUp (objectCount);
		}
	}
	
	//Removes and returns the MINIMUM element from the Priority Queue
	public E removeMin()
	{
		if (objectCount == 0) {
			throw new NullPointerException();
		}

		E obj = heap[1];

		if (objectCount == 1) {
			heap[1] = null;
			objectCount = 0;
		}
		if (objectCount == 2) {
			heap[1] = heap[2];
			heap[2] = null;
			objectCount--;

		}
		if (objectCount > 2) {
		swap (1, objectCount);
		heap[objectCount] = null;
		objectCount--;
		bubbleDown (1);
		}

		return obj;
	}
	
	//Returns the MINIMUM element from the Priority Queue without removing it
	public E peek()
	{
		if (objectCount == 0) {
			throw new NullPointerException();
		}
		return heap[1];
	}
	
	// Returns true if the priority queue is empty
	public boolean isEmpty()
	{
		return (objectCount == 0);
	}
	
	//Returns the number of elements in the priority queue
	public int size()
	{
		return objectCount;
	}
	
	public String toString()
	{
		StringBuffer stringbuf = new StringBuffer("[");
		for (int i = 0; i < objectCount; i++)
		{
			stringbuf.append(heap[i+1]);
			if (i < objectCount - 1)
				stringbuf.append(", ");
		}
		stringbuf.append("]\nor alternatively,\n");

		for(int rowLength = 1, j = 0; j < objectCount; rowLength *= 2)
		{
			for (int i = 0; i < rowLength && j < objectCount; i++, j++)
			{
				stringbuf.append(heap[j+1] + " ");
			}
			stringbuf.append("\n");
			if (j < objectCount)
			{
				for (int i = 0; i < Math.min(objectCount - j, rowLength*2); i++)
				{
					if (i%2 == 0)
						stringbuf.append("/");
					else
						stringbuf.append("\\ ");
				}
				stringbuf.append("\n");
			}
		}
		return stringbuf.toString();
	}
	
	//Doubles the size of the heap array
	private void increaseCapacity()
	{
		E[] heapClone =  (E[]) new Comparable[objectCount*2];
		for (int a = 0; a < heap.length; a++) {
			heapClone[a] = heap[a];
		}
		heap = heapClone;
	}

	//Returns the index of the "parent" of index i
	private int parent(int i)
	{
		if (i == 1) {
			return -1;
		}
		else {
		return (i/2);
		}
	}
	//Returns the *smaller child* of index i
	private int smallerChild(int i)
	{
		if (i * 2 > objectCount) {
			return -1;
		}
		else if (heap.length < i*2+1 || heap[i*2+1] == null) {
			return i*2;
		}
		else if (heap[i*2].compareTo(heap[i*2+1]) > 0) {
			return i*2+1;
		}
		else {
			return i*2;
		}
	}

	//Swaps the contents of indices i and j
	private void swap(int i, int j)
	{
		E obj = heap[i];
		heap[i] = heap[j];
		heap[j] = obj;

	}

	// Bubbles the element at index i upwards until the heap properties hold again.
	private void bubbleUp(int i)
	{
		while (i != 1 && heap[i].compareTo(heap[parent(i)]) < 0) {
				swap(i, parent(i));
				i=i/2;
		}
	}
	
	// Bubbles the element at index i downwards until the heap properties hold again.
	private void bubbleDown(int i)
	{
		while(smallerChild(i) != -1 && heap[smallerChild(i)].compareTo (heap[i]) < 0) {
				int a = smallerChild(i);
				swap(i, a);
				i = a;
		}
	}
}
