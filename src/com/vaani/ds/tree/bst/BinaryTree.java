package com.vaani.ds.tree.bst;

import java.util.ArrayList;
import java.util.List;


// BinaryTree.java
public class BinaryTree<T extends Comparable> {
	// Root node pointer. Will be null for an empty tree.
	private Node root;

	/**
   --Node--
   The binary tree is built using this nested node class.
   Each node stores one data element, and has left and right
   sub-tree pointer which may be null.
   The node is a "dumb" nested class -- we just use it for
   storage; it does not have any methods.
	 */
	private  class Node {
		Node left;
		Node right;
		T data;

		Node(T newData) {
			left = null;
			right = null;
			data = newData;
		}
	}

	/**
   Creates an empty binary tree -- a null root pointer.
	 */
	public void BinaryTree() {
		root = null;
	}


	/**
   Returns true if the given target is in the binary tree.
   Uses a recursive helper.
	 */
	public boolean lookup(T data) {
		return(lookup(root, data));
	}


	/**
   Recursive lookup  -- given a node, recur
   down searching for the given data.
	 */
	private boolean lookup(Node node, T data) {
		if (node==null) {
			return(false);
		}

		if (data==node.data) {
			return(true);
		}
		else if (data.compareTo(node.data) < 0) {
			return(lookup(node.left, data));
		}
		else {
			return(lookup(node.right, data));
		}
	}


	/**
   Inserts the given data into the binary tree.
   Uses a recursive helper.
	 */
	public void insert(T data) {
		root = insert(root, data);
	}


	/**
   Recursive insert -- given a node pointer, recur down and
   insert the given data into the tree. Returns the new
   node pointer (the standard way to communicate
   a changed pointer back to the caller).
	 */
	private Node insert(Node node, T data) {
		if (node==null) {
			node = new Node(data);
		}
		else {
			if (data.compareTo(node.data)<=0) {
				node.left = insert(node.left, data);
			}
			else {
				node.right = insert(node.right, data);
			}
		}

		return(node); // in any case, return the new pointer to the caller
	}


	/**
	 Returns the number of nodes in the tree.
	 Uses a recursive helper that recurs
	 down the tree and counts the nodes.
	 */
	public int size() {
		return(size(root));
	}

	private int size(Node node) {
		if (node == null) return(0);
		else {
			return(size(node.left) + 1 + size(node.right));
		}
	}

	/**
	 Returns the max root-to-leaf depth of the tree.
	 Uses a recursive helper that recurs down to find
	 the max depth.
	 */
	public int maxDepth() {
		return(maxDepth(root));
	}

	private int maxDepth(Node node) {
		if (node==null) {
			return(0);
		}
		else {
			int lDepth = maxDepth(node.left);
			int rDepth = maxDepth(node.right);

			// use the larger + 1
			return(Math.max(lDepth, rDepth) + 1);
		}
	}

	public T maxValue(){
		return (maxValue(root));

	}
	private T maxValue(Node node){
		Node current = node;
		while (current.right != null) {
			current = current.right;
		}

		return(current.data);
	}

	/**
	 Returns the min value in a non-empty binary search tree.
	 Uses a helper method that iterates to the left to find
	 the min value.
	 */
	public T minValue() {
		return( minValue(root) );
	}


	/**
	 Finds the min value in a non-empty binary search tree.
	 */
	private T minValue(Node node) {
		Node current = node;
		while (current.left != null) {
			current = current.left;
		}

		return(current.data);
	} 

	/**
	 Prints the node values in the "inorder" order.
	 Uses a recursive helper to do the traversal.
	 */
	public void printTree() {
		printTree(root);
		System.out.println();
	}

	private void printTree(Node node) {
		if (node == null) return;

		// left, node itself, right
		printTree(node.left);
		System.out.print(node.data + "  ");
		printTree(node.right);
	} 

	/**
	 Prints the node values in the "postorder" order.
	 Uses a recursive helper to do the traversal.
	 */
	public void printPostorder() {
		printPostorder(root);
		System.out.println();
	}

	public void printPostorder(Node node) {
		if (node == null) return;

		// first recur on both subtrees
		printPostorder(node.left);
		printPostorder(node.right);

		// then deal with the node
		System.out.print(node.data + "  ");
	}
	/**
	 Given a tree and a sum, returns true if there is a path from the root
	 down to a leaf, such that adding up all the values along the path
	 equals the given sum.

	 Strategy: subtract the node value from the sum when recurring down,
	 and check to see if the sum is 0 when you run out of tree.
	 */
	public boolean hasPathSum(int sum) {
		if(!(root.data instanceof Integer))
			return false;

		return( hasPathSum(root, sum) );
	}

	/*Presently works only for integer */
	private boolean hasPathSum(Node node, int sum) {
		// return true if we run out of tree and sum==0


		if (node == null) {
			return(sum == 0);
		}
		else {
			// otherwise check both subtrees
			int subSum = sum - (Integer)node.data;
			return(hasPathSum(node.left, subSum) || hasPathSum(node.right, subSum));
		}
	}

	/**
	 Given a binary tree, prints out all of its root-to-leaf
	 paths, one per line. Uses a recursive helper to do the work.
	 */
	public void printPaths() {
		ArrayList<T> path = new ArrayList<T>();
		//T[] path = new T[1000];
		printPaths(root, path, 0);
	}

	/**
	 Recursive printPaths helper -- given a node, and an array containing
	 the path from the root node up to but not including this node,
	 prints out all the root-leaf paths.
	 */
	private void printPaths(Node node, ArrayList<T> path, int pathLen) {
		if (node==null) return;

		// append this node to the path array
		path.set(pathLen,node.data);
		//path[pathLen] = node.data;
		pathLen++;

		// it's a leaf, so print the path that led to here
		if (node.left==null && node.right==null) {
			printArray(path, pathLen);
		}
		else {
			// otherwise try both subtrees
			printPaths(node.left, path, pathLen);
			printPaths(node.right, path, pathLen);
		}
	}

	/**
	 Utility that prints ints from an array on one line.
	 */
	private void printArray(ArrayList<T> ints, int len) {
		int i;
		for (i=0; i<len; i++) {
			System.out.print(ints.get(i) + " ");
		}
		System.out.println();
	}
	/**
	 Changes the tree into its mirror image.

	 So the tree...
	       4
	      / \
	     2   5
	    / \
	   1   3

	 is changed to...
	       4
	      / \
	     5   2
	        / \
	       3   1

	 Uses a recursive helper that recurs over the tree,
	 swapping the left/right pointers.
	 */
	public void mirror() {
		mirror(root);
	}

	private void mirror(Node node) {
		if (node != null) {
			// do the sub-trees
			mirror(node.left);
			mirror(node.right);

			// swap the left/right pointers
			Node temp = node.left;
			node.left = node.right;
			node.right = temp;
		}
	} 
	/**
	 Changes the tree by inserting a duplicate node
	 on each nodes's .left.



	 So the tree...
	    2
	   / \
	  1   3

	 Is changed to...
	       2
	      / \
	     2   3
	    /   /
	   1   3
	  /
	 1

	 Uses a recursive helper to recur over the tree
	 and insert the duplicates.
	 */
	public void doubleTree() {
		doubleTree(root);
	}

	private void doubleTree(Node node) {
		Node oldLeft;

		if (node == null) return;

		// do the subtrees
		doubleTree(node.left);
		doubleTree(node.right);

		// duplicate this node to its left
		oldLeft = node.left;
		node.left = new Node(node.data);
		node.left.left = oldLeft;
	}
	/*
	 Compares the receiver to another tree to
	 see if they are structurally identical.
	 */
	public boolean sameTree(BinaryTree other) {
		return( sameTree(root, (Node) other.root) );
	}

	/**
	 Recursive helper -- recurs down two trees in parallel,
	 checking to see if they are identical.
	 */
	boolean sameTree(Node a, Node b) {
		// 1. both empty -> true
		if (a==null && b==null) return(true);

		// 2. both non-empty -> compare them
		else if (a!=null && b!=null) {
			return(
					a.data == b.data &&
					sameTree(a.left, b.left) &&
					sameTree(a.right, b.right)
					);
		}
		// 3. one empty, one not -> false
		else return(false);
	} 

	/**
	 Tests if a tree meets the conditions to be a
	 binary search tree (BST).
	 */
	public boolean isBST() {
		return(isBST(root));
	}

	/**
	 Recursive helper -- checks if a tree is a BST
	 using minValue() and maxValue() (not efficient).
	 */
	private boolean isBST(Node node) {
		if (node==null) return(true);

		// do the subtrees contain values that do not
		// agree with the node?
		if (node.left!=null && maxValue(node.left).compareTo(node.data)>0) return(false);
		if (node.right!=null && minValue(node.right).compareTo(node.data) <= 0) return(false);

		// check that the subtrees themselves are ok
		return( isBST(node.left) && isBST(node.right) );
	}



	/**
	 Tests if a tree meets the conditions to be a
	 binary search tree (BST). Uses the efficient
	 recursive helper.
	 */
	public boolean isBSTForInt() {
		if(root.data instanceof Integer)
			return( isBST2(root, Integer.MIN_VALUE, Integer.MAX_VALUE) );
		else
			throw new RuntimeException("This search works for integers only");
	}

	/**
	  Efficient BST helper -- Given a node, and min and max values,
	  recurs down the tree to verify that it is a BST, and that all
	  its nodes are within the min..max range. Works in O(n) time --
	  visits each node only once.
	 */
	private boolean isBST2(Node node, int min, int max) {
		if (node==null) {
			return(true);
		}
		else {
			// left should be in range  min...node.data
			boolean leftOk = isBST2(node.left, min, (Integer)node.data);

			// if the left is not ok, bail out
			if (!leftOk) return(false);

			// right should be in range node.data+1..max
			boolean rightOk = isBST2(node.right, (Integer)node.data+1, max);

			return(rightOk);
		}
	}

	public Node findFirstCommonAncestor(T p,T q) {

		if (root == null) {
			return null;
		}
		else return findFirstCommonAncestor(root, p,q);




	}

	private Node findFirstCommonAncestor(Node start, T p, T q){
		if (start.data == p || start.data == q) {
			return start;
		}

		Node left = findFirstCommonAncestor(root.left, p, q);
		Node right = findFirstCommonAncestor(root.right, p, q);

		if ((left == p && right == q) ||
				(left == q && right == q)) {
			return root;

		}
		return (left != null) ? left : right;
	}



}