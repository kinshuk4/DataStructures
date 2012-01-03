package com.vaani.ds.list.doublelist;

final class DoubleListNode<T> {
	T value;
	DoubleListNode<T> prev, next;

	public DoubleListNode(T obj) {
		this(null, obj, null);
	}

	public DoubleListNode(DoubleListNode<T> previous, T value, DoubleListNode<T> next) {
		this.prev = previous;
		this.value = value;
		this.next = next;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public DoubleListNode<T> getPrev() {
		return prev;
	}

	public void setPrev(DoubleListNode<T> prev) {
		this.prev = prev;
	}

	public DoubleListNode<T> getNext() {
		return next;
	}

	public void setNext(DoubleListNode<T> next) {
		this.next = next;
	}
	
	
}