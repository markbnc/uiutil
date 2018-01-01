package com.ecdsinc.uiutil;

public class ClassBrowserRootTreeNode extends ClassBrowserDefaultTreeNode {

    public ClassBrowserRootTreeNode(
		String name,
		ClassBrowserTreeNode parent) {

		super(name, "", parent);
    }

    public boolean isLeaf() {

		return false;
    }
}