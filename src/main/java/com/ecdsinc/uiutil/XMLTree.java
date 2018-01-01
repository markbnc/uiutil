package com.ecdsinc.uiutil;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

@SuppressWarnings("serial")
public class  XMLTree extends JTree {

	public XMLTree() {
		init();
	}

	public void setRootNode(XMLTreeNode rootNode){
		if (rootNode != null) {
		    setModel(new XMLTreeModel(rootNode));
		}
		else {
			setModel(null);
		}
	}

	public XMLTreeNode getRootNode() {

		TreeModel model = getModel();

		if (model != null) {
		    return (XMLTreeNode) model.getRoot();
		}
		else {
			return null;
		}
	}

    public void setShowAllNodes(boolean newShowAllNodes) {
        m_ShowAllNodes = newShowAllNodes;
    }

    public boolean getShowAllNodes() {
        return m_ShowAllNodes;
    }

	public void setShowAttributes(boolean showAttributes) {
		((XMLTreeModel) getModel()).setShowAttributes(showAttributes);
	}

	public boolean getShowAttributes() {
		return ((XMLTreeModel) getModel()).getShowAttributes();
	}

	private void init () {
		setCellRenderer(new XMLTreeCellRenderer());
        getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);

		putClientProperty("JTree.lineStyle", "Angled");

    }

	private boolean m_ShowAllNodes = false;


}