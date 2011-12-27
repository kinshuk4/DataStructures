package com.vaani.ds.tree.bst;

import java.util.Stack;
import static java.lang.System.out;;
public class BSTUtils {

	public static <T extends Comparable>
	void  ZigZagLevelOrder(BSTNode<T> root) {
		Stack<BSTNode<T>> stack1 = new Stack<BSTNode<T>>();
		Stack<BSTNode<T>> stack2 = new Stack<BSTNode<T>>();
		Stack<BSTNode<T>> cur_level, next_level, temp;

		boolean left2right = true;

		cur_level = stack1;
		next_level = stack2;

		// push root in stack
		cur_level.push(root); 

		while (!cur_level.empty()) {
			BSTNode<T> node = cur_level.firstElement(); //top();
			cur_level.pop();
			if (node!=null) {
				out.println(node.data+" ");
				//cout << node.data << " ";
				if (left2right) {
					//if left to right, start pushing from left
					next_level.push(node.left);
					next_level.push(node.right);
				} else {
					next_level.push(node.right);
					next_level.push(node.left);
				}
			}
			if (cur_level.empty()) {
				left2right = !left2right;
				// swap stacks
				temp = cur_level;
				cur_level = next_level;
				next_level = temp;     
			}
		}
	}

}
