package com.vaani.ds.misc;

/**
 * Consider this string representation for binary trees. Each node is of the form (lr), 
 * where l represents the left child and r represents the right child. 
 * If l is the character 0, then there is no left child. Similarly, 
 * if r is the character 0, then there is no right child. Otherwise, 
 * the child can be a node of the form (lr), and the representation continues recursively. 
 * For example: (00) is a tree that consists of one node. ((00)0) is a two-node 
 * tree in which the root has a left child, and the left child is a leaf. 
 * And ((00)(00)) is a three-node tree, with a root, a left and a right child. 
 * 
 * Write a function that takes as input such a string, 
 * and returns -1 if the string is malformed, and the depth of the tree if the string is well-formed. 
 *
 * Time complexity: O(N), space complexity O(N).
 */
public class DepthOfStringRepresentation {

	public int depth(String str) {
		int leftPar = 0;
		int maxDepth = 0;
		int numZero = 0;
		for (int i = 0; i < str.length(); ++i) {
			char ch = str.charAt(i);
			if (ch != '(' && ch != ')' && ch != '0') {
				return -1;
			}
			if (ch == '(') {
				++leftPar;
				maxDepth = Math.max(maxDepth, leftPar);
			} else if (ch == '0') {
				if (leftPar == 0 || numZero > leftPar) {
					return -1;
				}
				++numZero;
			} else if (ch == ')') {
				if (leftPar == 0 || numZero < 2) {
					return -1;
				}
				--leftPar;
				--numZero;
			}

		}
		if (leftPar != 0 || numZero != 1) {
			return -1;
		}	else {
			return maxDepth - 1;
		}
	}
}
