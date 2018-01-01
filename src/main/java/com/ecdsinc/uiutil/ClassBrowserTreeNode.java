package com.ecdsinc.uiutil;

public interface ClassBrowserTreeNode {

	public ClassBrowserTreeNode getParent();
	public int getNumChildren();
	public ClassBrowserTreeNode getChild(int index);
	public ClassBrowserTreeNode getChild(String className);
	public int getIndexOfChild(ClassBrowserTreeNode child);
	public void addChild(ClassBrowserTreeNode child);
	public ClassBrowserTreeNode addChild (String displayName, String className);
	public boolean isLeaf();
	public String getClassName();
	public String getDisplayName();
	public int compare(ClassBrowserTreeNode node);

}