package com.ecdsinc.uiutil;

import org.w3c.dom.*;

public class XMLDOMTreeNode implements XMLTreeNode {

    public XMLDOMTreeNode(Node domNode) {
		m_DOMNode = domNode;
    }

    public XMLTreeNode getParent() {
		return new XMLDOMTreeNode(m_DOMNode.getParentNode());
    }

    public int getNumChildren() {
		return m_DOMNode.getChildNodes().getLength();
    }

    public XMLTreeNode getChild(int index) {
		NodeList     children = m_DOMNode.getChildNodes();
		return new XMLDOMTreeNode(children.item(index));
    }

    public int getIndexOfChild(XMLTreeNode child) {
		Node    curNode = null;
		int     index = 0;

		curNode = m_DOMNode.getFirstChild();
		while (curNode != null) {
			if (curNode == child) {
	    		return index;
		    }
			curNode = curNode.getNextSibling();
			index++;
		}
		return -1;
    }

	public int getNumAttributes() {

		NamedNodeMap    attributes = m_DOMNode.getAttributes();
		if (attributes != null) {
		    return m_DOMNode.getAttributes().getLength();
		}
		else {
			return 0;
		}
	}

	public XMLTreeNode getAttribute(int index) {
		NamedNodeMap    attributes = m_DOMNode.getAttributes();
		return new XMLDOMTreeNode(attributes.item(index));
	}

    public boolean isLeaf() {
       return m_DOMNode.getChildNodes().getLength() == 0;
    }

	public boolean isAttribute() {
		return m_DOMNode.getNodeType() == Node.ATTRIBUTE_NODE;
	}

	public Object getNode() {
		return m_DOMNode;
	}

    public String toString() {

		String  nodeName = m_DOMNode.getNodeName();
		int     nodeType = m_DOMNode.getNodeType();
		String  displayText;

		switch (nodeType) {

			case Node.TEXT_NODE :
			    displayText = m_DOMNode.getNodeValue().trim();
			    if (displayText.length() == 0) {
				    displayText = "(whitespace)";
			    }
				break;

			case Node.DOCUMENT_TYPE_NODE:
				displayText = "<!DOCTYPE>";
				break;

			case Node.COMMENT_NODE:
				displayText = "<-- " + m_DOMNode.getNodeValue().trim() + " -->";
				break;

			case Node.PROCESSING_INSTRUCTION_NODE:
				displayText = "<? Processing Instruction ?>";
				break;

			case Node.ATTRIBUTE_NODE:
				displayText = nodeName;
				break;

			default:
				displayText = "<" + nodeName + ">";
		}
		return displayText;
    }

	private Node       m_DOMNode;

}