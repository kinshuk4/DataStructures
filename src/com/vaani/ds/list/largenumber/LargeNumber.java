package com.vaani.ds.list.largenumber;

import java.util.Iterator;
import java.util.ListIterator;

import com.vaani.ds.list.LinkedList;
import com.vaani.ds.list.ListNode;

public class LargeNumber {

	LinkedList<Integer> largeNumber;

	public LargeNumber(String number){
		largeNumber = new LinkedList<Integer>();
		createNumber(number);
	}

	private void createNumber(String string) {


		char[] array = string.toCharArray();

		int[] intArray = new int[array.length];
		for(int i=0;i<intArray.length;i++){
			intArray[i]=array[i]-'0';
			largeNumber.add(intArray[i]);
		}
	}

	public LinkedList<Integer> getList(){
		return largeNumber;
	}
	public void sum(LargeNumber largeNumber2){
		sum(this.largeNumber,largeNumber2.getList());
	}

	public void sum(String largeStr){
		LargeNumber num2 = new LargeNumber(largeStr);
		sum(num2);
	}

	private void sum(LinkedList<Integer> list1, LinkedList<Integer> list2){
		list1.reverseIterative();
		list2.reverseIterative();

		System.out.println(list1.toString()+ "----" +list2.toString());

		int carry = 0;

		LinkedList<Integer> sumList = new LinkedList<Integer>();
		Iterator<Integer> iter1 =  list1.iterator();
		Iterator<Integer> iter2 =  list2.iterator();
		SumDigit sumOfDig = new SumDigit(0, 0);
		while(iter1.hasNext() || iter2.hasNext()||sumOfDig.getCarry()!=0){			
			sumOfDig = sum(iter1.next(),iter2.next(),carry);
			if(sumOfDig!=null){
				sumList.add(sumOfDig.getDigit());
				carry = sumOfDig.getCarry();
			}
			else break;
		}
		sumList.reverseIterative();
		this.largeNumber=sumList;


	}
	//	public void sum(Node num1, Node num2) {
	//		Node n1 = reverse(num1);
	//		Node n2 = reverse(num2);
	//
	//		int carry = 0;
	//		Node sum = sum(n1, n2, carry);
	//
	//		sum = reverse(sum);
	//
	//		return sum;
	//	}
	//
	//	private static Node<Integer> sum(Node<Integer> num1, Node<Integer> num2, int carry) {
	//		Node<Integer> result;
	//		if(num1 == null && num2 == null) {
	//			if(carry == 0) 
	//				result = null;
	//			else 
	//				result = new Node<Integer>(carry);
	//
	//		}else if(num1 != null && num2 != null) {
	//			int temp = num1.data + num2.data + carry;
	//			int digit = temp % 10;
	//			carry = temp / 10;
	//
	//			result = new Node<Integer>(digit);
	//			result.next = sum(num1.next, num2.next, carry);
	//		} else if(num1 != null) {
	//			int temp = num1.data + carry;
	//			int digit = num1.data % 10;
	//			carry = temp / 10;
	//
	//			result = new Node(digit);
	//			result.next = sum(num1.next, null, carry);
	//		} else {
	//			// only num2 is not null here
	//			int temp = num2.data + carry;
	//			int digit = num2.data % 10;
	//			carry = temp / 10;
	//
	//			result = new Node(digit);
	//			result.next = sum(null, num2.next, carry);
	//		}
	//
	//		return result;
	//	}

	private static SumDigit sum(Integer num1, Integer num2, int carry) {
		SumDigit result;
		if(num1 == null && num2 == null) {	
			if(carry==0)
				result=null;
			else
				result = new SumDigit(carry, 0);			
		}else if(num1 != null && num2 != null) {
			int temp = num1+num2 + carry;
			int digit = temp % 10;
			carry = temp / 10;

			result = new SumDigit(digit, carry);

		} else if(num1 != null) {
			int temp = num1 + carry;
			int digit = temp % 10;
			carry = temp / 10;

			result = new SumDigit(digit, carry);			
		} else {
			// only num2 is not null here
			int temp = num2 + carry;
			int digit = temp % 10;
			carry = temp / 10;

			result = new SumDigit(digit, carry);
		}

		return result;
	}

	public String toString(){
		return largeNumber.toString();
	}
	
	public static String sum(String nu1, String nu2){
		LargeNumber num1 = new LargeNumber(nu1);
		LargeNumber num2 = new LargeNumber(nu2);
		num1.sum(num2);
		return num1.toString();
	}

	public static void main(String[] args){
		LargeNumber nu1 = new LargeNumber("13422");
		LargeNumber nu2 = new LargeNumber("789123");
		nu1.sum(nu2);
		System.out.println(nu1.toString());
	}

}
