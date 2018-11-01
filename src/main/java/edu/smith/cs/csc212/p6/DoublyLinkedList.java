package edu.smith.cs.csc212.p6;

import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;

public class DoublyLinkedList<T> implements P6List<T> {
	private Node<T> start;
	private Node<T> end;
	public Node<T> lastNode;
	/**
	 * A doubly-linked list starts empty.
	 */
	public DoublyLinkedList() {
		this.start = null;
		this.end = null;
	}

	@Override
	public T removeFront() {
		checkNotEmpty();
		T removed = start.value;
		start = start.after;
		if(start != null) {
			start.before = null;
		}
		return removed;
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		if(start.after==null) {
			return this.removeFront();
		}
		T removed = end.value;
		end = end.before;
		return removed;
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		
		// if removing the first item 
		if ( index == 0 ) {
			return this.removeFront();
		}
		
		// if removing last item 
		if ( index == this.size() - 1) {
			return this.removeBack();
		}
		
		int at = 0;
		
		for(Node<T> current = start; 
				current!=null; 
				current=current.after) {
			if(at==(index-1)) {
				T removed = current.after.value;
				current.after = current.after.after;
				return removed;
			}
			at++;
		}
		throw new BadIndexError();
	}

	@Override
	public void addFront(T item) {
		Node<T> second = start;
		this.start = new Node <T> (item);
		start.after = second;
		start.before = null;
		if (second != null) {
			second.before = start;
		} else {
			end = start;
		}
	}

	@Override
	public void addBack(T item) {
		Node<T> second = end;
		this.end = new Node <T> (item);
		end.after = second;
		end.before = null;
		if (second != null) {
			second.before = start;
		} else {
			end = start;
		}
	}

	@Override
	public void addIndex(T item, int index) {
		// if the list is empty
				if(this.isEmpty() || (index==0)) {
					addFront(item);
				}
				
				// if adding to the back
				if(index==(this.size()-1)) {
					addBack(item);
				}
				
				// if adding somewhere in the middle
				else {
					int at = 0;
					
					for(Node<T> current = start; 
							current!=null; 
							current=current.after) {
						if(at==(index-1)) {
							Node<T> C = current.after;
							//current.after = new Node<T>(item, C);
						}
						at++;
					}
				}
	}

	@Override
	public T getFront() {
		return start.value;
	}

	@Override
	public T getBack() {
		return lastNode.getData();
	}
	
	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		
		int at = 0;
		
		for(Node<T> current = start; 
				current!=null; 
				current=current.after) {
			if(at==(index)) {
				T found = current.value;
				return found;
			}
			at++;
		}
		throw new BadIndexError();
	}

	@Override
	public int size() {
		int index = 0;
		for(Node<T> current=start;
				current!=null;
				current=current.after) {
			index++;
		}
		return index;
	}

	@Override
	public boolean isEmpty() {
		return start == null;
	}
	
	private void checkNotEmpty() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
	}
	
	/**
	 * The node on any linked list should not be exposed.
	 * Static means we don't need a "this" of DoublyLinkedList to make a node.
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes before me?
		 */
		public Node<T> before;
		/**
		 * What node comes after me?
		 */
		public Node<T> after;
		/**
		 * What value is stored in this node?
		 */
		public T value;
		public T data;
		/**
		 * Create a node with no friends.
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
		
		public T getData() {
	       return data;
	    } 
	}
}