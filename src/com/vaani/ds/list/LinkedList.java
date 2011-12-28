package com.vaani.ds.list;

import java.util.Iterator;


public class LinkedList<T> {
	ListNode<T> head;

	// Function to add new nodes to the linked list 
	public void add(T value) 
	{ 
		ListNode<T> temp = new ListNode<T>(value) ;
		temp.next=null;

		if(head==null) {
			head=temp; 
			return ;
		} 

		ListNode<T> current = head;
		while(current.next!=null)
		{
			current=current.next;
		}
		current.next=temp;
	}

	public T get(int index){
		ListNode<T> current = head;
		for(int i = 0; i<=index;i++)
			current = current.next;
		return current.data;
	}

	public void addAll(T[] arr){
		for(int i=0;i<arr.length;i++)
			add(arr[i]);
	}

	public void print ()
	{
		if (head == null)
			return;
		else {
			System.out.println(head.data+"->");
			LinkListUtil.printRecursive(head.next);
		};
	}


	/*iterative algo*/
	public void reverseIterative(){
		head = LinkListUtil.reverseIterative(head);
	}


	public void reverseListRecursive(ListNode<T> root){
		if(head==null)
			return ;
		if(head.next!=null) {
			reverseListRecursive(root.next); 
			root.next.next=root; 
		} 
		else { 
			head=root; 
		}
	}

	/**
	 * Uses fast pointer approach
	 * @return
	 */
	public boolean hasLoop()
	{
		ListNode<T> slow,fast;
		slow=fast=head;    //Start with the first loop

		while (fast != null)     //If p2 reaches end of linked list, no cycle exists
		{
			if(fast.next!=null)
				fast=fast.next.next;
			else
				return false;
			//			if(slow.next!=null && slow.next.next!=null){
			//				slow=slow.next;   //Move to next
			//				fast=fast.next.next; //Move to 2 steps next
			//			}else	{
			//				return false;
			//			}
			if(slow==null || fast==null)
				return false;

			if(slow==fast)
				return true;     //p1 and p2 met, so this means that there is a cycle
		}
		return false;
	}


	/**
	 * Returns the node at the start of a loop in the given circular linked
	 * list. A circular list is one in which a node's next pointer points
	 * to an earlier node, so as to make a loop in the linked list. For
	 * instance:
	 *
	 * input: A -> B -> C -> D -> E -> C [the same C as earlier]
	 * output: C
	 *
	 * (CCI_0205)
	 *
	 * @param linkedList
	 *            list to be tested
	 * @return the node at the start of the loop if there is a loop, null
	 * otherwise
	 */
	public  ListNode<T> getLoopStart() {
		if (head == null ) {
			return null;
		}

		ListNode<T> loopStartNode = null;
		ListNode<T> slow = this.getHead();
		ListNode<T> fast = this.getHead();

		while (slow != null && fast != null) {
			slow = slow.next;
			if (fast.next == null) {
				loopStartNode = null;
				break;
			}
			if(fast.next!=null)
				fast = fast.next.next;

			// If slow and fast point to the same node, it means that the
			// linkedList contains a loop.
			if (slow == fast) {

				slow = this.getHead();

				while (slow != fast) {
					// Keep incrementing the two pointers until they both
					// meet again. When this happens, both the pointers will
					// point to the beginning of the loop
					slow = slow.next; // Can't be null, as we have a loop
					fast = fast.next; // Can't be null, as we have a loop
				}

				loopStartNode = slow;
				break;
			}
		}

		return loopStartNode;
	}


	private ListNode<T> getHead() {

		return head;
	}

	/** This function uses one slow and one fast 
	 pointer to get to the middle of the LL. 
	 The slow pointer is advanced only by one node 
	 and the fast pointer is advanced by two nodes! 
	 */
	public T getMiddleElement() {
		ListNode<T> normalPointer = head; 
		ListNode<T> fastPointer = head; 
		if(fastPointer!=null) {
			while((fastPointer.next)!=null && (fastPointer.next.next)!=null) {
				normalPointer=(normalPointer!=null?normalPointer.next:null);
				fastPointer=(fastPointer!=null?fastPointer.next:null);
				fastPointer=(fastPointer!=null?fastPointer.next:null); 
			} 
			System.out.println("The middle element is " + normalPointer.data); 
			return normalPointer.data;
		} 
		return null;
	}

	// Function to get to the middle of the LL 
	ListNode<T> getTheMiddle() {
		ListNode<T> middle = null; 
		int i; 
		for(i=1; head!=null; head=head.next,i++) {
			if(i==1) middle=head; 
			else if ((i%2)==1) 
				middle=middle.next; 
		} 
		return middle; 
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		ListNode<T> currNode = head;		
		builder.append(currNode.data);

		ListNode<T> nextNode = currNode.next;
		while(nextNode != null) {
			builder.append(nextNode.data);
			nextNode = nextNode.next;
		}
		return builder.toString();
	}

	public static void main(String args[]){
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.getMiddleElement();
		list.reverseIterative();
		System.out.println(list.toString());
	}

	public Iterator<T> iterator(){
		return new com.vaani.ds.list.LinkedListIterator<T>(head);
	}
}
