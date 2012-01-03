package com.vaani.ds.tree.heap;

import com.vaani.ds.exception.OverFlowException;


public abstract class BinaryHeap  {
	/**
	 * This heap can be made generic by using E as type parameters:
	 * E extends Comparable
	 * Heap=(E[]) new Object[]
	 * and <,= operator convert to compareTo() and equals operator.
	 * For sake of clarity, int is used
	 */
	protected int[] Heap;
	protected int maxSize;
	protected int currentSize;

	public BinaryHeap(int max){
		maxSize = max;
		Heap = new int[maxSize];
		currentSize = 0 ;
	}

	protected int size(){
		return currentSize;
	}

	public boolean isEmpty() {
		return (currentSize == 0);
	}

	protected int getLeftChildIndex(int pos) {
		return 2*pos+1;
	}
	protected int getRightChildIndex(int pos) {
		return 2*pos + 2;
	}

	protected int getParentIndex(int pos) {
		return  (pos - 1) / 2;
	}
	protected boolean isLeaf(int pos) {
		return ((pos >= currentSize/2) && (pos <= currentSize));
	}
	
	protected int getParentValue(int pos) {
		return  Heap[(pos - 1) / 2];
	}
    protected void doubleArray( ) {
        int [ ] newArray;
        
        newArray = new int[ Heap.length * 2 ];
        for( int i = 0; i < Heap.length; i++ )
            newArray[ i ] = Heap[ i ];
        Heap = newArray;
    }

	protected void swap(int pos1, int pos2) {
		int tmp;
		tmp = Heap[pos1];
		Heap[pos1] = Heap[pos2];
		Heap[pos2] = tmp;
	}
	
	public void printHeap() {
		int i;
		for (i=0; i<=currentSize-1;i++)
			System.out.print(Heap[i] + " ");
		System.out.println();
	}
	
	public  abstract void insert(int i) throws OverFlowException;
	
	public abstract void heapify(int i);

	public abstract int remove()  throws Exception;
	

}
