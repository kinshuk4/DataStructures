package com.vaani.ds.util;

import com.vaani.ds.list.ListNode;

public class LinkListUtil {
	public  static void printRecursive(ListNode node){
		if (node == null)
			return;
		else {
			System.out.println(node.data+"->");
			printRecursive(node.next);
		}
	}

	public static ListNode search (ListNode l, Object value)	{

		if (l == null || l.data == value)
			return l;
		else
			return search(l.next, value);
	}
	
	// Reverse the linked list recursively 
	public static ListNode reverse_recurse(ListNode current, ListNode next) {
		ListNode ret; 
		if(current==null) {
			return null; 
		}
		ret = null; 
		if (current.next != null) {
			ret = reverse_recurse(current.next, current); 
		} else {
			ret = current; 
		} 
		current.next = next; 
		return ret; 
	}
	
	public static <T> ListNode<T> reverseRecursive(ListNode<T> list)
	{
		if (list == null || list.next==null) return list;
		ListNode<T> nextItem = list.next;
		list.next = null;
		ListNode<T> reverseRest = reverseRecursive(nextItem);
		nextItem.next = list;
		return reverseRest;
	}
	
	public  static <T> ListNode<T> reverseIterative(ListNode<T> head){
		//Node currentNode, nextNode, loopNode;
		if(head==null)
			return null;

		ListNode<T> currentNode=head;
		ListNode<T> newHead=null;
		ListNode<T> tmp = null;
		
		while(currentNode != null)
		{
			tmp = currentNode.next;
			currentNode.next=newHead;
			newHead = currentNode;			
			currentNode = tmp;
		}
		return newHead;
	}

}



