package com.vaani.ds.stack.application;

import com.vaani.ds.stack.impl.ArrayStack;

public class QueueFromStack {
	ArrayStack inStack = new ArrayStack();
	ArrayStack outStack = new ArrayStack();

	public static void main(String[] args)
	{
		QueueFromStack queue = new QueueFromStack();
		queue.enqueue(new String("first"));
		queue.enqueue(new String("second"));
		queue.enqueue(new String("third"));
		queue.enqueue(new String("fourth"));
		queue.enqueue(new String("fifth"));
		System.out.println("1. " + queue.dequeue());
		System.out.println("2. " + queue.dequeue());
		System.out.println("3. " + queue.dequeue());
		System.out.println("4. " + queue.dequeue());
		System.out.println("5. " + queue.dequeue());
	}

	public void enqueue(Object value)
	{
		inStack.push(value);
	}
	public Object dequeue()
	{
		if(outStack.isEmpty())
		{
			while( ! inStack.isEmpty())
			{
				outStack.push(inStack.pop());
			}
		}
		return outStack.pop();
	}
}
