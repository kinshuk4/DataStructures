package com.vaani.ds.list;

import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {

	public LinkedListIterator(ListNode<T> node){
		currNode = node;		
	}
	ListNode<T> currNode;
	@Override
	public boolean hasNext() {
		if(currNode!=null)
			return true;
		else
			return false;
	}

	@Override
	public T next() {
		if(currNode!=null){
			T data = currNode.data;
			currNode = currNode.next;
		    return data;
		}
		else return null;
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

}
