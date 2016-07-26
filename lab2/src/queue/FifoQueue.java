package queue;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		
	}

	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new MyIterator();
	}

	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	x the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E x) {
		QueueNode<E> newNode = new QueueNode<E>(x);
		if(size == 0) 
			last = newNode;

		newNode.next = last.next;
		last.next = newNode;
		last = newNode;
		size++;
		return true;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		E head = null;
		if(size >= 1) {
			head = last.next.element;
			if(size <= 1) 
				last = null;
			else
				last.next = last.next.next;
			size--;
		}
		return head;
	}

	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		return last != null ? last.next.element : null;
	}
	
	 /**
     * Appends the specified queue to this queue
     * post: all elements from the specified queue are appended
     *       to this queue. The specified queue (q) is empty
     * @param q the queue to append
     */
    public void append(FifoQueue<E> q) {
    	if(q.size > 0 && size > 0) {
    		QueueNode<E> myHead = last.next;
    		last.next = q.last.next;
    		q.last.next = myHead;
    		last = q.last;
    	} else if(q.size > 0) {
    		last = q.last;
    	}
    	size += q.size;
    	q.size = 0;
    	q.last = null;
    }


	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}

	}
	
	
	private class MyIterator implements Iterator<E> {
		private QueueNode<E> current;
		
		private MyIterator() {
			if(size > 0)
				current = last.next;
		}
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			} else {
				E element = current.element;
				if(current == last)
					current = null;
				else
					current = current.next;
				return element;
			}
		}
		
	}

}
