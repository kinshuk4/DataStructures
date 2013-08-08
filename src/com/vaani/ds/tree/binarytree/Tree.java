package com.vaani.ds.tree.binarytree;


import java.util.HashMap;

//Class for a tree node
class TreeNode<T> {

	// data members
	private T data;
	private TreeNode<T> left;
	private TreeNode<T> right;

	// Accessor methods
	public T key()        { return data; }
	public TreeNode<T> left()  { return left; }
	public TreeNode<T> right() { return right; }

	// Constructor
	public TreeNode(T key) { this.data = key; left = null; right = null; }

	// Methods to set left and right subtrees
	public void setLeft(TreeNode<T> left)   { this.left = left; }
	public void setRight(TreeNode<T> right) { this.right = right; }
}

//Class for a Binary Tree
class Tree<T> {

	private TreeNode<T> root;

	// Constructors
	public Tree() { root = null; }
	public Tree(TreeNode<T> n) { root = n; }

	// Method to be called by the consumer classes like Main class
	


}