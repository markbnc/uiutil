package com.ecdsinc.uiutil;

import java.util.Vector;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeModelListener;

public class XMLTreeModel implements TreeModel {

	//  Tree Model Interface
    @SuppressWarnings("rawtypes")
	public XMLTreeModel(XMLTreeNode root) {
		m_Root = root;
		m_Listeners = new Vector();
    }

    public Object getRoot() {
		return m_Root;
    }

    public Object getChild(Object parent, int index) {

		XMLTreeNode     parentNode = (XMLTreeNode) parent;
		int numAttributes = parentNode.getNumAttributes();

		if (getShowAttributes()) {
			if (index < numAttributes) {
				return parentNode.getAttribute(index);
			}
			else {
				return parentNode.getChild(index - numAttributes);
			}
		}
		else {
			return parentNode.getChild(index);
		}
    }

    public int getChildCount(Object parent) {

		XMLTreeNode parentNode = (XMLTreeNode) parent;
		if (getShowAttributes()) {
			return parentNode.getNumChildren() +
	    		   parentNode.getNumAttributes();
		}
		else {
			return parentNode.getNumChildren();
		}
    }

    public boolean isLeaf(Object node) {
 		XMLTreeNode curNode = (XMLTreeNode) node;
		return curNode.isLeaf();
    }

    public int getIndexOfChild(Object parent, Object child) {
 		XMLTreeNode parentNode = (XMLTreeNode) parent;
		return parentNode.getIndexOfChild((XMLTreeNode)child);
    }

 	public void valueForPathChanged(TreePath path, Object newValue) {
		System.out.println("XMLTreeModel: valueForPathChanged called!");
    }

	@SuppressWarnings("unchecked")
	public void addTreeModelListener(TreeModelListener listener) {
		m_Listeners.addElement(listener);
    }

	public void removeTreeModelListener(TreeModelListener listener) {
		m_Listeners.removeElement(listener);
    }

	public void setShowAttributes (boolean showAttributes) {
		m_ShowAttributes = showAttributes;
	}

	public boolean getShowAttributes () {
		return m_ShowAttributes;
	}

	private XMLTreeNode     m_Root = null;
	@SuppressWarnings("rawtypes")
	private Vector          m_Listeners;
	private boolean         m_ShowAttributes = true;

}