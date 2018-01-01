package com.ecdsinc.uiutil;

import com.ecdsinc.xml.DOMUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

@SuppressWarnings("serial")
public class XMLDOMTree extends XMLTree {

    public XMLDOMTree() {
    }

    public XMLDOMTree(Document doc) {
		setDocument(doc);
    }

    public void setDocument(org.w3c.dom.Document doc) {


		Node        rootNode;

		if (doc != null) {
			if (getShowAllNodes()) {
				rootNode = doc;
			}
			else {
				rootNode = DOMUtil.getContentNode(doc);
			}

			DOMUtil.removeWhitespace (rootNode);
			setRootNode(new XMLDOMTreeNode(rootNode));
		}
		else {
			setRootNode(null);
		}
    }

	/*
    public org.w3c.dom.Document getDOMDocument() {
		XMLTreeNode rootNode = (XMLTreeNode) getModel().getRoot();
		if (rootNode instanceof XMLDOMTreeNode) {
			return (org.w3c.dom.Document) rootNode.getNode();
		}
		return null;
    }
	*/


}

