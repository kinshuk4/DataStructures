package com.vaani.ds.tree.binarytree;

import java.util.HashMap;

public class IntegerTree {
	private TreeNode<Integer> root;

	// Constructors
	public IntegerTree() { root = null; }
	public IntegerTree(TreeNode<Integer> n) { root = n; }
	
	
	// A wrapper over VerticalSumUtil()
	private void VerticalSum(TreeNode<Integer> root) {

		// base case
		if (root == null) { return; }

		// Creates an empty hashMap hM
		HashMap<Integer, Integer> hM = new HashMap<Integer, Integer>();

		// Calls the VerticalSumUtil() to store the vertical sum values in hM
		VerticalSumUtil(root, 0, hM);

		// Prints the values stored by VerticalSumUtil()
		if (hM != null) {
			System.out.println(hM.entrySet());
		}
	}

	// Traverses the tree in In order form and builds a hashMap hM that
	// contains the vertical sum
	private void VerticalSumUtil(TreeNode<Integer> root, int hD,
			HashMap<Integer, Integer> hM) {

		// base case
		if (root == null) {  return; }

		// Store the values in hM for left subtree
		VerticalSumUtil(root.left(), hD - 1, hM);

		// Update vertical sum for hD of this node
		int prevSum = (hM.get(hD) == null) ? 0 : hM.get(hD);
		hM.put(hD, prevSum + root.key());

		// Store the values in hM for right subtree
		VerticalSumUtil(root.right(), hD + 1, hM);
	}

	public void VerticalSumMain() { VerticalSum(root); }

	//Driver method to test the verticalSum methods


	public static void main(String[] args) {
		/* Create following Binary Tree
           1
         /    \
       2        3
      / \      / \
     4   5    6   7

		 */
		TreeNode<Integer> root = new TreeNode<Integer>(1);
		root.setLeft(new TreeNode<Integer>(2));
		root.setRight(new TreeNode<Integer>(3));
		root.left().setLeft(new TreeNode<Integer>(4));
		root.left().setRight(new TreeNode<Integer>(5));
		root.right().setLeft(new TreeNode<Integer>(6));
		root.right().setRight(new TreeNode<Integer>(7));
		IntegerTree t = new IntegerTree(root);

		System.out.println("Following are the values of vertical sums with "
				+ "the positions of the columns with respect to root ");
		t.VerticalSumMain();

	}

}
