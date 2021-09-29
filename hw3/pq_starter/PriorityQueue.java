import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.AssertionError;
/**
 * A priority queue class implemented using a min heap.
 * Priorities cannot be negative.
 * 
 * @author Dominic Meconi
 * @version Date
 *
 */

 //We can use a red-black tree 
 /*
 Rules
 1) every node is colored black or red
 2) root node is black
 3) If node is red then children are black
 4) every path from a node to a null must contain the same number of black nodes
 -Dom
 */
public class PriorityQueue {

	protected Map<Integer, Integer> location;
	protected List<Pair<Integer, Integer>> heap;

	public static void main(String[] args) {

	}
	/**
	 *  Constructs an empty priority queue
	 */

	 //prob last to be done -DOM 
	public PriorityQueue() {
		// TODO: Fill in
		this.location = new HashMap<>();
		this.heap = new ArrayList<>();
	}


	/**
	 *  Insert a new element into the queue with the
	 *  given priority.
	 *
	 *	@param priority priority of element to be inserted
	 *	@param element element to be inserted
	 *	<br><br>
	 *	<b>Preconditions:</b>
	 *	<ul>
	 *	<li> The element does not already appear in the priority queue.</li>
	 *	<li> The priority is non-negative.</li>
	 *	</ul>
	 *  
	 */
	public void push(int priority, int element) {
		// TODO: Fill in
		if(priority >= 0 && !this.location.containskey(element)) {
			Pair<Integer, Integer> newPair = new Pair<Integer, Integer>(priority,element);
			this.heap.add(newPair);
			this.location.put(element, this.heap.indexOf(newPair));
			percolateUp(heap.size() - 1);
		}
		else {
			throw new AssertionError("Push Error: The element does not already appear in the priority queue");
		}
	}

	/**
	 *  Remove the highest priority element
	 *  <br><br>
	 *	<b>Preconditions:</b>
	 *	<ul>
	 *	<li> The priority queue is non-empty.</li>
	 *	</ul>
	 *  
	 */
	public void pop(){
		// TODO: Fill in
		int elemToRemove = this.location.get(pushDown(topElement()));
		this.heap.remove(elemToRemove);
		this.location.remove(elemToRemove);
	}


	/**
	 *  Returns the highest priority in the queue
	 *  @return highest priority value
	 *  <br><br>
	 *	<b>Preconditions:</b>
	 *	<ul>
	 *	<li> The priority queue is non-empty.</li>
	 *	</ul>
	 */
	public int topPriority() {		
		// TODO: Fill in
		return this.heap.get(0).priority;

	}


	/**
	 *  Returns the element with the highest priority
	 *  @return element with highest priority
	 *  <br><br>
	 *	<b>Preconditions:</b>
	 *	<ul>
	 *	<li> The priority queue is non-empty.</li>
	 *	</ul>
	 */
	public int topElement() {
		// TODO: Fill in
		return this.heap.get(0).element;
	}


	/**
	 *  Change the priority of an element already in the
	 *  priority queue.
	 *  
	 *  @param newpriority the new priority	  
	 *  @param element element whose priority is to be changed
	 *  <br><br>
	 *	<b>Preconditions:</b>
	 *	<ul>
	 *	<li> The element exists in the priority queue</li>
	 *	<li> The new priority is non-negative </li>
	 *	</ul>
	 */
	public void changePriority(int newpriority, int element) {
		// TODO: Fill in
		if(newpriority >= 0 && isPresent(element) && !this.location.containsKey(newPriority)) {
			this.heap.get(this.location.get(element)).priority = newpriority;
			if(newpriority < highestPriority){
				highestPriority = newpriority;
			}
		}
	}


	/**
	 *  Gets the priority of the element
	 *  
	 *  @param element the element whose priority is returned
	 *  @return the priority value
	 *  <br><br>
	 *	<b>Preconditions:</b>
	 *	<ul>
	 *	<li> The element exists in the priority queue</li>
	 *	</ul>
	 */
	//Talk to chambers! two hash map?
	public int getPriority(int element) {
		// TODO: Fill in
		if(this.location.containsValue(element)) {
			int returnVal = this.heap.get(this.location.get(element));
			return returnVal.priority;
		}
		return -1;
	}


	/**
	 *  Returns true if the priority queue contains no elements
	 *  @return true if the queue contains no elements, false otherwise
	 */
	public boolean isEmpty() {
		// TODO: Fill in
		return this.location.isEmpty();
	}

	/**
	 *  Returns true if the element exists in the priority queue.
	 *  @return true if the element exists, false otherwise
	 */
	public boolean isPresent(int element) {
		// TODO: Fill in
		return this.location.contains(element);
	}

	/**
	 *  Removes all elements from the priority queue
	 */
	public void clear() {
		// TODO: Fill in
		this.heap.clear();
		this.location.clear();
	}

	/**
	 *  Returns the number of elements in the priority queue
	 *  @return number of elements in the priority queue
	 */
	public int size() {
		// TODO: Fill in
		return this.heap.size();
	}


	
	/*********************************************************
	 * 				Private helper methods
	 *********************************************************/
	

	/**
	 * Push down the element at the given position in the heap 
	 * @param start_index the index of the element to be pushed down
	 * @return the index in the list where the element is finally stored
	 */
	private int pushDown(int start_index) {	
		// TODO: Fill in
		if(heap.get(left(start_index)).priority < heap.get(start_index).priority) {
			swap(start_index, left(start_index));
			return left(start_index);
		}
		if(heap.get(right(start_index)).priority < heap.get(start_index).priority) {
			swap(start_index, right(start_index));
			return right(start_index);
		}
		return null;

	}

	/**
	 * Percolate up the element at the given position in the heap 
	 * @param start_index the index of the element to be percolated up
	 * @return the index in the list where the element is finally stored
	 */
	private int percolateUp(int start_index) {
		if(heap.get(start_index) > 0) {
			if (heap.get(start_index).priority < heap.get(parent(start_index)).priority) {
				swap(start_index, parent(start_index));
			}
			return parent(start_index);
		}
		return null;
		}


	/**
	 * Swaps two elements in the priority queue by updating BOTH
	 * the list representing the heap AND the map
	 * @param i The index of the element to be swapped
	 * @param j The index of the element to be swapped
	 */
	private void swap(int i, int j) {
		// TODO: Fill in
		Pair<Integer,Integer> tmp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j,tmp);
		location.replace(tmp.element, j);
		location.replace(heap.get(i).element, i);

	}

	/**
	 * Computes the index of the element's left child
	 * @param parent index of element in list
	 * @return index of element's left child in list
	 */
	private int left(int parent) {
		// TODO: Fill in
		int leftChild = (2*parent)+1;
		return leftChild;
	}

	/**
	 * Computes the index of the element's right child
	 * @param parent index of element in list
	 * @return index of element's right child in list
	 */
	private int right(int parent) {
		// TODO: Fill in
		int rightChild = (2*parent)+2;
		return rightChild;
	}

	/**
	 * Computes the index of the element's parent
	 * @param child index of element in list
	 * @return index of element's parent in list
	 */

	private int parent(int child) {
		// TODO: Fill in
		int parent = (child-1)/2;
		return parent;

	}
	

	/*********************************************************
	 * 	These are optional private methods that may be useful
	 *********************************************************/
//
//	/**
//	 * Push down the root element
//	 * @return the index in the list where the element is finally stored
//	 */
//
//	private int pushDownRoot() {
//		// TODO: A one-line function that calls pushDown()
//		return 0;
//	}
//
//	/**
//	 * Percolate up the last leaf in the heap, i.e. the most recently
//	 * added element which is stored in the last slot in the list
//	 *
//	 * @return the index in the list where the element is finally stored
//	 */
//	private int percolateUpLeaf(){
//		// TODO: A one-line function that calls percolateUp()
//	}
//
//	/**
//	 * Returns true if element is a leaf in the heap
//	 * @param i index of element in heap
//	 * @return true if element is a leaf
//	 */
//	private boolean isLeaf(int i){
//		// TODO: Fill in
//	}
//
//	/**
//	 * Returns true if element has two children in the heap
//	 * @param i index of element in the heap
//	 * @return true if element in heap has two children
//	 */
//	private boolean hasTwoChildren(int i) {
//		// TODO: Fill in
//	}
//
//	/**
//	 * Print the underlying list representation
//	 */
//	private void printHeap() {
//		// TODO: Fill in
//	}
//
//	/**
//	 * Print the entries in the location map
//	 */
//	private void printMap() {
//		// TODO: Fill in
//	}

	
}
