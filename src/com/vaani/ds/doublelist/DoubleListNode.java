package com.vaani.ds.doublelist;

final class DoubleListNode {
	Object obj;
	DoubleListNode previous, next;

	public DoubleListNode(Object obj) {
		this(null, obj, null);
	}

	public DoubleListNode(DoubleListNode previous, Object obj, DoubleListNode next) {
		this.previous = previous;
		this.obj = obj;
		this.next = next;
	}
}