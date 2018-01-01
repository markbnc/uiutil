package com.ecdsinc.uiutil;

import com.ecdsinc.xml.JDomUtil;

import org.jdom.Document;

@SuppressWarnings("serial")
public class XMLJDomTree extends XMLTree {

    public XMLJDomTree() {
    }

	public XMLJDomTree(Document doc) {
		setDocument(doc);
	}

    public void setDocument(Document doc) {

		Object        rootNode;

		if (doc != null) {
			if (getShowAllNodes()) {
				rootNode = doc;
			}
			else {
				rootNode = doc.getRootElement();
			}

			JDomUtil.removeWhitespace (rootNode);
			setRootNode(new XMLJDomTreeNode(rootNode, null));
		}
		else {
			setRootNode(null);
		}
    }

	/*
    public org.jdom.Document getJDomDocument() {
		XMLTreeNode rootNode = (XMLTreeNode) getModel().getRoot();
		if (rootNode instanceof XMLJDomTreeNode) {
			return (org.jdom.Document) rootNode.getNode();
		}
		return null;
    }
	*/

}

