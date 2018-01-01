
package com.ecdsinc.uiutil;

public interface XMLTreeNode {

	public XMLTreeNode getParent();
	public int getNumChildren();
	public XMLTreeNode getChild(int index);
	public int getIndexOfChild(XMLTreeNode child);
	public int getNumAttributes();
	public XMLTreeNode getAttribute(int index);
	public boolean isLeaf();
	public boolean isAttribute();
	public Object getNode();

}