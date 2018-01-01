package com.ecdsinc.uiutil;

import java.util.Vector;

public class ClassBrowserDefaultTreeNode implements ClassBrowserTreeNode {

	private String                  displayName;
	private String                  className;
	private ClassBrowserTreeNode    parent;

	@SuppressWarnings("rawtypes")
	private Vector                  children;


	@SuppressWarnings("rawtypes")
	public ClassBrowserDefaultTreeNode(
		String displayName,
		String className,
		ClassBrowserTreeNode parent) {

		this.displayName = displayName;
		this.className = className;
		this.parent = parent;
		this.children = new Vector();
    }


	public ClassBrowserTreeNode getParent() {

		return this.parent;
    }


    public int getNumChildren() {

		return this.children.size();
    }


    public ClassBrowserTreeNode getChild(int index) {

		return (ClassBrowserTreeNode) this.children.get(index);
    }


	public ClassBrowserTreeNode getChild(String displayName) {

		ClassBrowserTreeNode    curNode = null;
		int                     numChildren = this.children.size();

		for (int index = 0; index < numChildren; index++) {

			curNode = (ClassBrowserTreeNode) this.children.get(index);
			if (displayName.equals(curNode.getDisplayName())) {

				return curNode;
			}
		}

		//  child with specified name not found
		return null;
	}


    public int getIndexOfChild(ClassBrowserTreeNode child) {

		//  Child should not be null
		if (child == null) {

			return -1;
		}

		int     numChildren = getNumChildren();

		for (int index = 0; index < numChildren; index++) {

			if (child.equals(getChild(index))) {

				//  Child found
				return index;
			}
		}

		//  Child not found
		return -1;
    }


    public boolean isLeaf() {

		return (this.children.size() == 0);
    }


    public String getDisplayName() {

		return this.displayName;
    }


	public String getClassName() {

		return this.className;
	}

	public ClassBrowserTreeNode addChild(
		String displayName,
		String className) {

		ClassBrowserTreeNode childNode =
			new ClassBrowserDefaultTreeNode(displayName, className, this);

		addChild(childNode);

		return childNode;
	}


	@SuppressWarnings("unchecked")
	public void addChild (ClassBrowserTreeNode childNode) {

		int index = getInsertIndex(childNode);
		this.children.add(index, childNode);
	}


	public int compare(ClassBrowserTreeNode node){

		return this.displayName.compareTo(node.getDisplayName());
	}


    public String toString() {

        return getDisplayName();
    }


	private int getInsertIndex(ClassBrowserTreeNode node) {

		//  Using linear search for initial implementation.  Need to switch
		//  to binary search
		int     numChildren = getNumChildren();
		int     compareVal;

		for (int index = 0; index < numChildren; index++) {

			compareVal = this.getChild(index).compare(node);

			if (compareVal > 0) {

				//  The current node is greater (comes after) then the node
				//  passed in.  Add the node before the current node
				return index;
			}
		}

		//  All the current nodes are less then the node passed in.  Add
		//  the name to the end
		return numChildren;

	}
}
