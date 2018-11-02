package edu.smith.cs.csc212.p6;

import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;
import edu.smith.cs.csc212.p6.errors.P6NotImplemented;

public class GrowableList<T> implements P6List<T> {
	public static final int START_SIZE = 32;
	private Object[] array;
	private int fill;
	
	public GrowableList() {
		this.array = new Object[START_SIZE];
		this.fill = 0;
	}
	
	// efficiency is O(n) because it calls a method 
	// that needs to shift everything and uses up 
	// O(n)
	@Override
	public T removeFront() {
		return removeIndex(0);
	}
	
	// O(1) because we're not shifting anything
	@Override
	public T removeBack() {
		// if list is empty
		if (fill == 0) {
			throw new EmptyListError();
		}
		T value = this.getIndex(fill-1);
		fill--;
		this.array[fill] = null;
		return value;
	}
	
	// O(n)
	@Override
	public T removeIndex(int index) {
		// if list is empty
		if(fill==0) {
			throw new EmptyListError();
		}
		
		// if index does not exist
		if(index>=fill) {
			throw new BadIndexError();
		}
		
		T removed = this.getIndex(index);
		fill--;
		for (int i=index; i<fill; i++) {
			this.array[i] = this.array[i+1];
		}
		this.array[fill] = null;
		return removed;
	}
	
	// O(n) because addIndex uses O(n) time
	@Override
	public void addFront(T item) {
		addIndex(item, 0);
	}
	
	// O(1)
	@Override
	public void addBack(T item) {
		// I've implemented part of this for you.
		if (fill >= this.array.length) { 
			throw new P6NotImplemented();
		}
		this.array[fill++] = item;
	}
	
	// O(n) --> shifting items
	@Override
	public void addIndex(T item, int index) {
		for (int j=fill; j>index; j--) {
			array[j] = array[j-1];
		}
		array[index] = item;
		fill++;		
	}
	
	// O(1), it's easy to get anything in this list
	@Override
	public T getFront() {
		return this.getIndex(0);
	}
	
	// O(1)
	@Override
	public T getBack() {
		return this.getIndex(this.fill-1);
	}

	/**
	 * Do not allow unchecked warnings in any other method.
	 * Keep the "guessing" the objects are actually a T here.
	 * Do that by calling this method instead of using the array directly.
	 * 
	 * O(1) -- Efficiency
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getIndex(int index) {
		return (T) this.array[index];
	}
	
	// O(1)
	@Override
	public int size() {
		return fill;
	}
	
	// O(1)
	@Override
	public boolean isEmpty() {
		return fill == 0;
	}
}
