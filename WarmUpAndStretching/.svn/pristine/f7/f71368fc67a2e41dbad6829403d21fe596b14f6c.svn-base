package priorityQueue;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class implements a priority queue.
 * 
 * @author <TODO: put your name here>
 * @param <T>
 *            the type of elements in the priority queue
 */
public class PQ<T> {

	// You are not allowed to change this instance variable or add any
	// additional instance variables.
	private TreeSet<PQElement<T>> treeSet;

	/**
	 * Constructs a new priority queue.
	 */
	public PQ() {
		// TODO: implement this constructor
		 this.treeSet = new TreeSet<PQElement<T>>();
		
	}

	/**
	 * @return an element with the smallest priority
	 */
	public T findMin() {
		this.treeSet.descendingIterator();
		if(this.treeSet.size()==0){
			throw new NoSuchElementException();
		}
		else{
			return this.treeSet.last().getElement();
		}
	}

	/**
	 * Deletes the minimum-priority element.
	 */
	public void deleteMin() {
		if(this.treeSet.size()==0){
			throw new NoSuchElementException();
		}
		else{
			this.treeSet.remove(this.treeSet.last());
		}
	}

	/**
	 * Inserts the given element with the given priority.
	 *
	 * @param priority
	 * @param element
	 */
	public void insert(int priority, T element) {
		PQElement tempElement = new PQElement(priority, element);
		this.treeSet.add(tempElement);
		this.treeSet.descendingIterator();
	}

}
