package com.vaani.ds.tree;

/**
 * Given a binary tree where all the right nodes are leaves.
 * Write a function to turn the tree upside down.
 * e.g.
 *      root -> 0                    0
 *             / \                  / 
 *            1   2                1 - 2
 *           /                    /
 *          3             ===>   3
 *         / \                  /
 *        4   5                4 - 5
 *       / \                  /
 *      6   7                6 - 7
 *
 * Spcae: O(1), Time: O(n)
 */
public class UpsideDownBinaryTree {

	public static class TreeNode {

		int val;
		TreeNode left;
		TreeNode right;
	
		public TreeNode(int val) {
			this.val = val;
		}
	}
	
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		TreeNode parent = null;
		TreeNode parentRight = null;
		return upsideDownBinaryTree(root, parent, parentRight); 
	}

	private TreeNode upsideDownBinaryTree(TreeNode cur, TreeNode parent, TreeNode parentRight) {
		if (cur == null) {
			return parent;
		}
		TreeNode left = cur.left;
		TreeNode curRight = cur.right;
		cur.left = parentRight;
		cur.right = parent;
		return upsideDownBinaryTree(left, cur, curRight);
	}

}
