package com.vaani.ds.stack.impl;

public class TwoStacks {

	int[] arr;
	int size;
	int top1, top2;

	public TwoStacks(int n) // constructor
	{
		size = n;
		arr = new int[n];
		top1 = -1;
		top2 = size;
	}

	// Method to push an element x to stack1
	void push1(int x) {
		// There is at least one empty space for new element
		if (top1 < top2 - 1) {
			top1++;
			arr[top1] = x;
		} else {
			throw new IllegalStateException("Stack Overflow");
		}
	}

	// Method to push an element x to stack2
	void push2(int x) {
		// There is at least one empty space for new element
		if (top1 < top2 - 1) {
			top2--;
			arr[top2] = x;
		} else {
			throw new IllegalStateException("Stack Overflow");
		}
	}

	// Method to pop an element from first stack
	int pop1() {
		if (top1 >= 0) {
			int x = arr[top1];
			top1--;
			return x;
		} else {
			throw new IllegalStateException("Stack UnderFlow");
		}
	}

	// Method to pop an element from second stack
	int pop2() {
		if (top2 < size) {
			int x = arr[top2];
			top2++;
			return x;
		} else {
			throw new IllegalStateException("Stack UnderFlow");
		}
	}

	/* Driver program to test twStacks class */
	public static void main(String[] args) {
		TwoStacks ts = new TwoStacks(5);
		ts.push1(5);
		ts.push2(10);
		ts.push2(15);
		ts.push1(11);
		ts.push2(7);
		System.out.println("Popped element from stack1 is " + ts.pop1());
		ts.push2(40);
		System.out.println("\nPopped element from stack2 is " + ts.pop2());

	}
}
