package com.vaani.ds.list;


public class ListNode<T>{
	public T data;
	public ListNode<T> next;	
	public ListNode(T value){
		data = value;
	}
	
	public String toString(){
		return data.toString();
	}
}

