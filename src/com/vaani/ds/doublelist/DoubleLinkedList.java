package com.vaani.ds.doublelist;

import java.util.Enumeration;

import com.vaani.ds.exception.NoSuchElementException;


public class DoubleLinkedList {   
	  DoubleListNode head;
	 /*
	  * creates an empty list
	  */
	  public DoubleLinkedList() {
	    head = new DoubleListNode(null);
	    head.next = head.previous = head;
	  }

	 /*
	  * remove all elements in the list
	  */
	  public final synchronized void clear() {
	    head.next = head.previous = head;
	  }

	 /*
	  * returns true if this container is empty.
	  */
	  public final boolean isEmpty() {
	    return head.next == head;
	  }

	 /*
	  * insert element after current position
	  */
	  public final synchronized void insertAfter(Object obj, DoubleListIterator cursor) {
	    DoubleListNode newItem = new DoubleListNode(cursor.pos, obj, cursor.pos.next);
	    newItem.next.previous = newItem;
	    cursor.pos.next = newItem;
	  }

	 /*
	  * insert element before current position
	  */
	  public final synchronized void insertBefore(Object obj, DoubleListIterator cursor) {
	    DoubleListNode newItem = new DoubleListNode(cursor.pos.previous, obj, cursor.pos);
	    newItem.previous.next = newItem;
	    cursor.pos.previous = newItem;
	  }

	 /*
	  * remove the element at current position
	  */
	  public final synchronized void remove(DoubleListIterator cursor) throws Exception {
	    if (isEmpty()) {
	      throw new IndexOutOfBoundsException("empty list.");
	    }
	    if (cursor.pos == head) {
	      throw new NoSuchElementException("cannot remove the head");
	    }
	    cursor.pos.previous.next = cursor.pos.next;
	    cursor.pos.next.previous = cursor.pos.previous;
	  }

	 /*
	  * Return an iterator positioned at the head.
	  */
	  public final DoubleListIterator head() {
	    return new DoubleListIterator(this, head);
	  }

	 /*
	  * find the first occurrence of the object in a list
	  */
	  public final synchronized DoubleListIterator find(Object obj) throws Exception {
	    if (isEmpty()) {
	      throw new IndexOutOfBoundsException("empty list.");
	    }
	    DoubleListNode pos = head;
	    while (pos.next != head) {  // There are still elements to be inspected
	      pos = pos.next;
	      if (pos.obj == obj) {
	        return new DoubleListIterator(this, pos);
	      }
	    }
	    throw new NoSuchElementException("no such object found");
	  }

	 /*
	  * Returns an enumeration of the elements. Use the Enumeration methods on
	  * the returned object to fetch the elements sequentially.
	  */
	  public final synchronized Enumeration elements() {
	    return new DoubleListEnumerator(this);
	  }
	}