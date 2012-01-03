package com.vaani.ds.tree.heap;

import java.io.PrintWriter;

import com.vaani.ds.exception.EmptyDSException;
import com.vaani.ds.exception.OverFlowException;
import com.vaani.ds.exception.UnderFlowException;


public class MinHeap extends BinaryHeap {

	public MinHeap(int max) {
		super(max);
		setMin();
		//Heap[0] = Integer.MIN_VALUE;
	}

	private void setMin(){
		for(int i=0; i<maxSize;i++)
			Heap[i]=Integer.MAX_VALUE;			
	}

	public MinHeap(int[] keys) {
		super(keys.length);
		build(keys);
	}

	public void build(int [] keys){
		for (int key : keys) {
			Heap[currentSize++]= key;
		}
		for (int k = currentSize / 2 - 1; k >= 0; k--) {
			siftDownIterative(k);
		}
	}

	public void insert(int elem) throws OverFlowException {
		if (currentSize == Heap.length)
			throw new OverFlowException("Heap's underlying storage is overflow");

		Heap[currentSize] = elem;
		currentSize++;
		int current = currentSize-1;
		/* iterative sifting up */
		siftUpIterative(current);
	}


	public void insertRecursiveSiftUp(int elem) throws OverFlowException{
		if (currentSize == Heap.length)
			throw new OverFlowException("Heap's underlying storage is overflow");
		currentSize++;
		Heap[currentSize] = elem;
		siftUpRecursive(currentSize);
	}

	/**
	 * Called by various names - bubbleup, percolateUP
	 * 
	 * @param pos
	 */
	private void siftUpIterative(int pos){
		int parentIndex = getParentIndex(pos);
		int parentValue = Heap[parentIndex];
		int currentValue = Heap[pos];
		while (currentValue < parentValue) {
			swap(pos, parentIndex);
			pos = getParentIndex(pos);

			//update parent value and current value accordingly
			parentIndex = getParentIndex(pos);
			currentValue = Heap[pos];
			parentValue = Heap[parentIndex];
		}	
	}

	private void siftUpRecursive(int nodeIndex) {

		int parentIndex;
		if (nodeIndex != 0) {
			parentIndex = getParentIndex(nodeIndex);

			if (Heap[parentIndex] > Heap[nodeIndex]) {
				swap(parentIndex, nodeIndex);
				siftUpRecursive(parentIndex);
			}

		}

	}



	public int getMinimum() throws Throwable {
		if (isEmpty())
			throw new EmptyDSException("Heap is empty");
		else
			return Heap[0];
	}
	public int removeMin() throws Exception {
		if (isEmpty())
			throw new UnderFlowException("Heap is empty");
		swap(0,currentSize-1);
		currentSize--;
		if (currentSize != 0)
			siftDownIterative(0);
		return Heap[currentSize];
	}

	private void siftDownRecursive(int nodeIndex) {

		int leftChildIndex, rightChildIndex, minIndex;
		leftChildIndex = getLeftChildIndex(nodeIndex);
		rightChildIndex = getRightChildIndex(nodeIndex);

		if (rightChildIndex >= currentSize) {
			if (leftChildIndex >= currentSize)
				return;
			else
				minIndex = leftChildIndex;

		} else {
			if (Heap[leftChildIndex] <= Heap[rightChildIndex])
				minIndex = leftChildIndex;
			else
				minIndex = rightChildIndex;
		}

		if (Heap[nodeIndex] > Heap[minIndex]) {
			swap(minIndex,nodeIndex);
			siftDownRecursive(minIndex);

		}
	}


	private void siftDownIterative(int position) {
		int smallestchild;
		while (!isLeaf(position)) {

			int leftChildIndex = getLeftChildIndex(position);
			int rightChildIndex = getRightChildIndex(position);
			smallestchild = leftChildIndex;
			if (rightChildIndex >= currentSize) {
				if (leftChildIndex >= currentSize)
					return;
				else
					smallestchild = leftChildIndex;
			}else   if ((Heap[leftChildIndex] > Heap[rightChildIndex]))
				smallestchild = rightChildIndex;

			if (Heap[position] <= Heap[smallestchild]) return;
			swap(position,smallestchild);
			position = smallestchild;
		}
	}

	@Override
	public void heapify(int i) {
		siftUpIterative(i);

	}

	public static void heapSort(int []array) throws Throwable{
		BinaryHeap bHeap = new MinHeap(array.length);
		for (int a : array)
			bHeap.insert(a);
		for(int i = 0; i<array.length;i++)
		{
			array[i] = bHeap.remove();
			
		}
//		for (int i = bHeap.size()-1; i >= 0; i--) {
//			bHeap.swap(i, bHeap.size()-1);	
//			bHeap.heapify( i);
//		}
		//bHeap.printHeap();
	}


	public static void printArray(int arr[]) {
		
		int i;
		for (i=0; i<=arr.length-1;i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	public static void main(String args[]) throws Throwable{
		int[] arr = {2, 3,1, 7,4, 0 };
		heapSort(arr);
		printArray(arr);
		//		MinHeap mH = new MinHeap(arr.length);
		//		for(int i : arr)
			//			mH.insert(i);
		//		
		//		mH.printHeap();
		//		int min = mH.removeMin();
		//		System.out.println(min);
		//		
		//		int min2 = mH.getMinimum();
		//		System.out.println(min2);
	}

	@Override
	public int remove() throws Exception {
		return removeMin();
	}
}

//public class MinHeap<E extends Comparable<E>> {
//	List<E> h = new ArrayList<E>();
//
//	public MinHeap(E[] keys) {
//		for (E key : keys) {
//			h.add(key);
//		}
//		for (int k = h.size() / 2 - 1; k >= 0; k--) {
//			percolateDown(k, h.get(k));
//		}
//	}
//
//	public void add(E node) {
//		h.add(null);
//		int k = h.size() - 1;
//		while (k > 0) {
//			int parent = (k - 1) / 2;
//			E p = h.get(parent);
//			if (node.compareTo(p) >= 0) {
//				break;
//			}
//			h.set(k, p);
//			k = parent;
//		}
//		h.set(k, node);
//	}
//
//	public E remove() {
//		E removedNode = h.get(0);
//		E lastNode = h.remove(h.size() - 1);
//		percolateDown(0, lastNode);
//		return removedNode;
//	}
//
//	public E min() {
//		return h.get(0);
//	}
//
//	public boolean isEmpty() {
//		return h.isEmpty();
//	}
//
//	void percolateDown(int k, E node) {
//		if (h.isEmpty()) {
//			return;
//		}
//		while (k < h.size() / 2) {
//			int child = 2 * k + 1;
//			if (child < h.size() - 1
//					&& h.get(child).compareTo(h.get(child + 1)) > 0) {
//				child++;
//			}
//			if (node.compareTo(h.get(child)) <= 0) {
//				break;
//			}
//			h.set(k, h.get(child));
//			k = child;
//		}
//		h.set(k, node);
//	}
//
//	// Usage example
//	public static void main(String[] args) {
//		MinHeap<Integer> heap = new MinHeap<Integer>(
//				new Integer[] { 2, 5, 1, 3 });
//		// print keys in sorted order
//		while (!heap.isEmpty()) {
//			System.out.println(heap.remove());
//		}
//	}
//}