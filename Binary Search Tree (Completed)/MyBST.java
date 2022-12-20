// Implements a BST with TreeNode nodes
// Matthew Paul and Isaiah Jeter 

import java.util.Stack;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
// Normally, TreeNode and MyTreeSet would be "generic" (type-specific)
// classes and hold Comparable objects, but this is beyond the scope of
// the Java Methods book. Without @SuppressWarnings, this class would give
// three "Unchecked cast" warnings.

public class MyBST
{
	private TreeNode root;  // holds the root of this BST

	// Constructor: creates an empty BST.
	public MyBST()
	{
		root = null;
	}
	public TreeNode getRoot()
	{
		return root;
	}

	// Returns true if this BST contains value; otherwise returns false.
	public boolean contains(Object value)
	{
		return contains(root, value);
	}

	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public boolean add(Object value)
	{
		if (contains(value))
			return false;
		root = add(root, value);
		return true;
	}

	// Removes value from this BST.  Returns true if value has been
	// found and removed; otherwise returns false.
	public boolean remove(Object value)
	{
		if (!contains(value))
			return false;
		root = remove(root, value);
		return true;
	}

	// Returns a string representation of this BST.
	public String toString()
	{
		String str = toString(root);
		if (str.endsWith(", "))
			str = str.substring(0, str.length() - 2);
		return "[" + str + "]";
	}

	//*************** Private helper methods: *********************

	// Returns true if the BST rooted at node contains value;
	// otherwise returns false (recursive version).

	private boolean contains(TreeNode node, Object value)
	{
		if (node == null)
			return false;
		else
		{
			int  diff = ((Comparable<Object>)value).compareTo(node.getValue());
			if (diff == 0)
				return true;
			else if (diff < 0)
				return contains(node.getLeft(), value);
			else // if (diff > 0)
				return contains(node.getRight(), value);
		}
	}

	/*
  // Iterative version:
  private boolean contains(TreeNode node, Object value)
  {
    while (node != null)
    {
      int  diff = ((Comparable<Object>)value).compareTo(node.getValue());
      if (diff == 0)
        return true;
      else if (diff < 0)
        node = node.getLeft();
      else // if (diff > 0)
        node = node.getRight();
    }
    return false;
  }
	 */

	// Adds value to the BST rooted at node. Returns the
	// root of the new tree.
	// Precondition: the tree rooted at node does not contain value.
	private TreeNode add(TreeNode node, Object value)
	{
		if (node==null) {
			return (new TreeNode (value));
		}

		if (node.getRight() == null && node.getLeft() == null) {
			if (((Comparable<Object>)value).compareTo(node.getValue()) <= 0) {
				node.setLeft (new TreeNode (value));
				return root;
			}
			else {
				node.setRight (new TreeNode (value));
				return root;
			}
		}
		if(node.getLeft()==null || node.getRight() == null) {
			if (node.getLeft() == null) {
				if (((Comparable<Object>)value).compareTo(node.getValue()) <= 0) {
				node.setLeft(new TreeNode (value));
				return root;
				}
				else {
					return add (node.getRight(), value);
				}
			}
			else {
				if (((Comparable<Object>)value).compareTo(node.getValue()) >= 0) {
					node.setRight(new TreeNode (value));
					return root;
				}
				else {
					return add (node.getLeft(), value);
				}
			}
		}

		else {
				if (((Comparable<Object>)value).compareTo(node.getValue()) >= 0) {
					return add (node.getRight(), value);
				}
				else {
					return add (node.getLeft(), value);
				}
		}
	}

	// Removes value from the BST rooted at node.
	// Returns the root of the new tree.
	// Precondition: the tree rooted at node contains value.
	private TreeNode remove(TreeNode node, Object value)
	{	
		
			if (((Comparable<Object>)value).compareTo(node.getValue()) > 0) {
				node.setRight(remove (node.getRight(), value));
				return node;
			}
			if (((Comparable<Object>)value).compareTo(node.getValue()) < 0) {
				node.setLeft(remove (node.getLeft(), value));
				return node;
			}
				return removeRoot (node);
	}

	// Removes the root of the BST rooted at root
	// replacing it with the smallest node from the right subtree.
	// Returns the root of the new tree.
	private TreeNode removeRoot(TreeNode root)
	{
		if (root.getRight () == null && root.getLeft () == null) {
			return null;
		}
		else if (root.getRight () == null) {
			return root.getLeft();
		}
		else if (root.getLeft () == null) {
			return root.getRight();
		}
		else {
			TreeNode node = root.getRight();
			if (node.getLeft() == null && node.getRight() == null) {
				root.setValue(node.getValue());
				root.setRight(null);
				return root;
			}
			if (node.getLeft() == null && node.getRight() != null) {
				root.setValue(node.getValue());
				root.setRight(node.getRight());
				return root;
			}
			else {
				for (node = root.getRight (); node.getLeft().getLeft() != null; node = node.getLeft()) { 
				}
				root.setValue(node.getLeft().getValue());
				node.setLeft(node.getLeft().getRight());
				return root;
			}
		}
	}

	// Returns a string representation of the tree rooted at node.
	private String toString(TreeNode node)
	{
		if (node == null)
			return "";
		else
			return toString(node.getLeft()) + node.getValue() + ", " +
			toString(node.getRight());
	}

}
