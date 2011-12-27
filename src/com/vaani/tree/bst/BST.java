package com.vaani.tree.bst;



/*
 * BST implementation in which insertion and deletion are accomplished by
 * recursive algorithms (insertion after Carrano, deletion after Weiss).

 * Data Structure (class) BST --- binary search tree
 * public member functions:
 *    constructor --- for the tree itself
 *    clear       --- empty out the tree
 *    walk        --- traverse the data structure, showing the values
 *                    in increasing order.
 *    pretty      --- Display the tree structure (level-order traversal)
 *    insert      --- insert one cell into the data structure
 *    find        --- find, based on data value; returns pointer to cell
 *    delete      --- remove one cell from the data structure
 *    avgDepth    --- average depth of all tree nodes
 *    height      --- height of the entire tree
 *    size        --- number of nodes in the tree

 * Additional classes used:
 *   BSTnode: single binary search tree node.
 *   Wrapper: in the pretty-printing, the int field is the tree level
 *
 * Author  Timothy J. Rolfe
 * Version 2010 October 22
 */
import java.util.*;     // Deque, ArrayDeque

@SuppressWarnings("unchecked")
public class BST
{
	/**
	 * Binary search tree node
	 *
	 *    data area   --- generic Comparable
	 *    height      --- height within the tree of THIS node
	 *    size        --- number of nodes in this (sub)tree
	 *    util        --- miscellaneous int value, should we every need it
	 *    left link   --- left subtree
	 *    right link  --- right subtree
	 *
	 * The outer class has full access to this embedded class's fields.

	 */
	static class BSTnode
	{  
		Comparable data;   // Realistic case, this could be quite large
		int        height; // Height of THIS NODE
		int        size;   // Number of nodes in this (sub)tree
		int        util;   // Generic utility area for an int value
		BSTnode    left,   // "<" subtree
		right;  // ">" subtree

		BSTnode ( Comparable data ) // Constructor for leaf node
		{  
			this.data   = data;
			this.height = 0;         // Leaf node has height zero
			this.size   = 1;
			this.left   = null;
			this.right  = null;
		}
	}

	// Instance fields:

	protected BSTnode root = null,
			current = null;   // Spare reference for processing
	private int nItems;                 // Used in the protected tree walk
	private BSTnode free = null;        // nodes for re-use

	// Class fields:

	// ? ? Show nodes on recycling ? ?
	protected static final boolean DEBUG  = false;
	// ? ? Enforce unique keys ? ?
	protected static final boolean UNIQUE = false;

	// no constructor needed:  root and free initialized to null already

	//  ************** Over-all tree characteristics **************  //
	/*
	 * Tree size --- available as root.size, if there is a root
	 */
	public int size()
	{  return root == null ? 0 : root.size;  }
	/*
	 * Tree height == number of levels in the tree = root height
	 */
	public int height()       // root = null means empty = -1
	{  return root == null ? -1 : root.height;  }
	/*
	 * Average node depth
	 */
	public double avgDepth()
	{  if ( root == null )
		return -1;     // Root as level zero forces this
	else   // root as level zero goes here
		return sumDepths(root, 0) / root.size;
	}
	// Total all depths of nodes within the tree:
	double sumDepths(BSTnode node, int deep)
	{  if (node == null)
		return 0;      // Empty node does not contribute
	else
		return deep
				+ sumDepths (node.left, deep+1)
				+ sumDepths (node.right, deep+1);
	} // end sumDepths()

	////******************* Simple Find *******************////
	/**
	 * Find the cell with the data field corresponding to Value
	 */
	Comparable find(Comparable value)
	{  current = root;
	while ( current != null && !current.data.equals(value) )
	{  if (value.compareTo(current.data) < 0)
		current = current.left;
	else
		current = current.right;
	}

	return current == null ? null : current.data;
	}

	//  ************** Tree modifier methods **************  //
	/*
	 * Empty out the tree.  The "autumn" method recycles the nodes
	 * to save on garbage collection expense.
	 */
	public void clear()
	{  autumn(root);  root = null;  }

	// All the leaf (nodes) fall . . . recursively
	void autumn (BSTnode node)
	{  if ( node != null )
	{  autumn(node.left);  // post-order traversal
	autumn(node.right);
	recycle(node);      // This is now a leaf.  Goodbye!
	}
	} // end autumn()

	/*
	 * Insertion in the BST
	 */
	// Insert the value into its proper place in the binary search tree
	public void insert(Comparable value)
	{  root = insert(root, value);  }

	// Recursive insertion, returning reference to subtree generated.
	BSTnode insert(BSTnode node, Comparable value)
	{  if ( node == null )
		node = build(value);
	else if ( value.compareTo(node.data) < 0 )
		node.left  = insert (node.left, value);
	// ***** If equal keys allowed, place them right *****
	else if ( ! UNIQUE )
		node.right = insert (node.right, value);
	// ***** Equal keys must be FORBIDDEN *****
	else if ( value.compareTo(node.data) > 0 )
		node.right = insert (node.right, value);
	// ***** If equal keys are NOT allowed, ERROR  *****
	else
	{  System.err.println (value + " is already in.");
	} // end if/else if/else if/else if/else
	// Correct this node's height and size after the insertion.
	// The correction propagates upwards in the recursive call stack.
	node.height = newHt(node);
	node.size = newSize(node);
	return node;
	} // end insert(BSTnode, Comparable)

	//  ******************* Deletion *******************  ///

	// Fields required as stable in delete(BSTnode, int)
	BSTnode deleteNode, lastNode;

	/*
	 * Delete the node with the value passed.
	 */
	public void delete(Comparable value)
	{  deleteNode = null;  lastNode = null;
	root = delete(root, value);
	}

	// Interchange the .data fields on two BSTnodes passed
	static void swapData (BSTnode d, BSTnode s)
	{  Comparable temp = d.data;  d.data = s.data;  s.data = temp; }

	BSTnode delete(BSTnode node, Comparable value)
	{  if ( node == null ) return null;

	lastNode = node;                      // Reference to LAST node seen
	if ( value.compareTo(node.data) < 0 )
		node.left = delete (node.left, value);
	else
	{//When we FIND the node and take one step right, all subsequent
		//steps will be left --- down to the in-order successor
		deleteNode = node;                 // Potentially the node to go
		node.right = delete (node.right, value);
	}

	// In the returns, the call where we are dealing with the replacement
	if ( node == lastNode )
	{//Check to see if we indeed DID find the value
		if ( deleteNode != null && value.equals(deleteNode.data) )
		{//Final check:  if node is RIGHTMOST in its subtree
			if ( deleteNode == lastNode )   // Half-nodes are easy!
			{  node = node.left;            // Return left subtree
			}
			//node is NOT rightmost in its subtree.  Copy replacement up.
			else
			{  swapData (deleteNode, lastNode);
			node = node.right;           // Return right subtree
			}
			recycle (lastNode);             // Return the node for re-use
		}
	}
	else  // Adjust heights on the way back up the recursive stack
	{  node.height = newHt(node);
	node.size = newSize(node);
	}
	return node;
	}

	//  ******************* Recursive traversal *******************  //
	/*
	 * Walk through the tree; display is to be ascending order
	 */
	public void walk()
	{  if (root != null)
	{  nItems = 0;
	inOrder(root);
	// Check whether final '\n' is required.
	if (nItems % 10 != 0)
		System.out.println();
	}
	} // end walk()

	// To get ascending order, do an in-order traversal of the tree
	void inOrder(BSTnode item)
	{  if ( item == null) return;            // I.e., empty tree

	inOrder(item.left);                   // Process left sub-tree
	System.out.printf("%4s(%d)", item.data,item.height);
	if ( ++nItems % 10 == 0 )
		System.out.println();
	inOrder(item.right);                  // Process right sub-tree
	}

	//  ******************* NON-Recursive traversal *******************  //
	/*
	 *  Display the BST horizontally --- based on a level-order traversal
	 */
	// Keep track of position on the line across calls to setPos
	static int skip;
	public void pretty()
	{  skip = 0;

	if ( root == null )  // Nothing to display!
	{  System.out.println ("Empty tree!"); return;  }

	setPos (root);       // Find line position for each node
	pretty (root);       // level-order traversal displaying the nodes
	} // end pretty()       // one line for each level, in proper position

	// Find line position for each node --- based on in-order traversal
	void setPos (BSTnode node)
	{//If the nodes were all printed on one line, their order is based
		// on an in-order traversal.  skip shows number of positions to skip
		// to properly position the node.  Note that this MUST be a class
		// data member --- the root depends on skip to come back with the
		// size of the entire left subtree, for instance.
		if ( node != null )
		{  setPos (node.left);
		node.util = skip;        // Store skip value in util data member
		skip += 2;               // Update for printing THIS node
		setPos (node.right);
		} // end if
	} // end setPos()

	// We need to retain information on tree level in a queue of BSTnodes.
	static class Wrapper
	{  int level;
	BSTnode node;

	Wrapper(BSTnode node, int level)
	{  this.level = level; this.node = node;  }
	}

	// Pretty-print:  each tree level prints on one line, with the node
	// horizontally positioned to be visually the parent of its children.
	void pretty (BSTnode node)
	{//work queue during traversal
		Deque<Wrapper> queue = new ArrayDeque<Wrapper>();

		int   position = 0,           // position in output line
				level = 1,              // level being processed
				current;                // value for node ABOUT to process

		// level-order traversal:  initialize the work queue with the root
		queue.offer(new Wrapper(node, level));// node initially IS the root

		while ( ! queue.isEmpty() )     // Go there's nothing left to do!
		{  Wrapper item = queue.remove();
		node    = item.node;
		current = item.level;
		if (node.left != null)     // Enqueue child nodes
			queue.offer(new Wrapper(node.left, current+1));
		if (node.right != null)
			queue.offer(new Wrapper(node.right, current+1));
		//    Check for line-break:  change in tree level
		if ( current != level )
		{  System.out.println();
		position = 0;
		level = current;
		}
		//    skip over to proper position
		for ( ; position < node.util ; position++ )
			System.out.print (" ");
		System.out.printf ("%2s", node.data);
		position += 2;    // Update position for the output just done
		} // end while
		System.out.println();      // Final line termination.
	} // end pretty(BSTnode)

	//  ******************* Utility functions *******************  //

	// Return the height based on the children; node must NOT be null.
	static int newHt ( BSTnode node )
	{  BSTnode lt = node.left,
	rt = node.right;

	if ( lt == null && rt == null )     // Leaf node is height zero
		return 0;
	else if ( lt == null )              // Half node cases
		return 1 + rt.height;
	else if (rt == null )
		return 1 + lt.height;
	else                                // Full node --- need java.lang.Math.max
		return 1 + Math.max(lt.height, rt.height);
	} // end newHt()

	// Return the size based on the children; node must NOT be null.
	static int newSize( BSTnode node )
	{  BSTnode lt = node.left,
	rt = node.right;

	if ( lt == null && rt == null )     // Leaf node has size 1
		return 1;
	else if ( lt == null )              // Half node cases
		return 1 + rt.size;
	else if (rt == null )
		return 1 + lt.size;
	else                                // Full node --- do the sum
		return 1 + lt.size + rt.size;
	} // end newSize()

	//  ************** free-space list mainenance ******************  //

	// Generate a node, either re-using from the free list or constructed
	BSTnode build( Comparable data )
	{  BSTnode rtn;

	if (free == null)
	{  rtn = new BSTnode (data);
	if ( rtn == null )
	{  System.err.println ("ALLOCATION ERROR.  Abort execution.");
	System.exit(7);
	}
	}
	else
	{  rtn = free;
	free = free.right;
	rtn.data = data;
	rtn.height = 0;
	rtn.size = 1;    // Leaf node
	rtn.left = null; rtn.right = null;
	}
	return rtn;
	}

	// push node onto free list.
	void recycle( BSTnode node )
	{  if ( DEBUG )
		System.out.println ("Recycling node \"" + node.data + '"');
	node.left   = null;
	node.right  = free;
	free = node;
	} // end recycle

} // end class BST
