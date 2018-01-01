package com.ecdsinc.uiutil;

import org.jdom.*;

public class XMLJDomTreeNode implements XMLTreeNode {

    public XMLJDomTreeNode(Object node, XMLTreeNode parentNode) {
		m_JDomNode = node;
		m_ParentNode = parentNode;
    }

    public XMLTreeNode getParent() {
		return m_ParentNode;
    }

    public int getNumChildren() {

		if (m_JDomNode instanceof Document) {
			return ((Document) m_JDomNode).getContent().size();
		}
		else if (m_JDomNode instanceof Element) {
			return ((Element) m_JDomNode).getContent().size();
		}
//		else if (m_JDomNode instanceof Entity) {
//			return ((Entity) m_JDomNode).getMixedContent().size();
//		}
		else if (m_JDomNode instanceof Attribute) {
			//  indicate that an attribute has one child.  The value of the
			//  attribute will be used as the child
			return 1;
		}
		else {
			return 0;
		}
    }

    public XMLTreeNode getChild(int index) {
		Object      item;

		if (m_JDomNode instanceof Document) {
			item = ((Document) m_JDomNode).getContent().get(index);
		}
		else if (m_JDomNode instanceof Element) {
			item = ((Element) m_JDomNode).getContent().get(index);
		}
//		else if (m_JDomNode instanceof Entity) {
//			item = ((Entity) m_JDomNode).getMixedContent().get(index);
//		}
		else if (m_JDomNode instanceof Attribute) {
			item = ((Attribute) m_JDomNode).getValue();
		}
		else {
			return null;
		}
		return new XMLJDomTreeNode(item, this);
    }

    public int getIndexOfChild(XMLTreeNode child) {

		if (m_JDomNode instanceof Document) {
			return ((Document) m_JDomNode).getContent().indexOf(child);
		}
		else if (m_JDomNode instanceof Element) {
			return ((Element) m_JDomNode).getContent().indexOf(child);
		}
//		else if (m_JDomNode instanceof Entity) {
//			return ((Entity) m_JDomNode).getMixedContent().indexOf(child);
//		}
		else {
			return -1;
		}
    }

	public int getNumAttributes() {

		if (m_JDomNode instanceof Element) {
			return ((Element) m_JDomNode).getAttributes().size();
		}
		else {
			return 0;
		}
	}

	public XMLTreeNode getAttribute(int index) {

		Object      item;

		if (m_JDomNode instanceof Element) {
			item = ((Element) m_JDomNode).getAttributes().get(index);
		}
		else {
			return null;
		}

		return new XMLJDomTreeNode(item, this);
	}

    public boolean isLeaf() {
		return (getNumChildren() == 0) ||
			   (m_JDomNode instanceof String);
    }

	public boolean isAttribute() {
		return m_JDomNode instanceof Attribute;
	}

    public Object getNode() {
		return m_JDomNode;
    }

    public String toString() {

		String      displayText;

		if (m_JDomNode instanceof Document) {
			displayText = "<#Document>";
		}
		else if (m_JDomNode instanceof Element) {
			Element     elementNode = (Element) m_JDomNode;
			displayText = "<" + elementNode.getQualifiedName() + ">";
		}
		else if (m_JDomNode instanceof Attribute) {
			Attribute   attributeNode = (Attribute) m_JDomNode;
			displayText = attributeNode.getQualifiedName();
		}
		else if (m_JDomNode instanceof EntityRef) {
			EntityRef      entityNode = (EntityRef) m_JDomNode;
			displayText = "<!ENTITY " + entityNode.getName() + ">";
		}
		else if (m_JDomNode instanceof CDATA) {
			displayText = "CDATA []";
		}
		else if (m_JDomNode instanceof Comment) {
			displayText = "<-- Comment -->";
		}
		else if (m_JDomNode instanceof DocType) {
			displayText = "<!DOCTYPE>";
		}
		else if (m_JDomNode instanceof ProcessingInstruction) {
			displayText = "<?Processing Instruction?>";
		}
		else if (m_JDomNode instanceof String) {
			displayText = (String) m_JDomNode;
			if (displayText.trim().length() == 0) {
				displayText = "(whitespace)";
			}
		}
		else {
			displayText = "<#unknown node type>";
		}
		return displayText;
    }


	private Object          m_JDomNode;
	private XMLTreeNode     m_ParentNode;
}