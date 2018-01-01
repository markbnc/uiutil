package com.ecdsinc.xml;

import java.util.Hashtable;
import org.w3c.dom.*;

/**
 * This class contains a set of misc utility functions that are useful when
 * working with DOM XML Documents
 */
public class DOMUtil {

    public DOMUtil() {
    }

	static public void removeWhitespace (Node domNode) {

		Node        curNode = null;
		Node        prevNode = null;


		curNode = domNode.getLastChild();
		while (curNode != null) {

			if ((curNode.getNodeType() == Node.TEXT_NODE) &&
				(curNode.getNodeValue().trim().length() == 0)) {

					prevNode = curNode.getPreviousSibling();
					domNode.removeChild(curNode);
					curNode = prevNode;
			}
			else {

				removeWhitespace(curNode);
				curNode = curNode.getPreviousSibling();
			}
		}
    }

	static public Node getContentNode (Document doc) {

		Node    curNode = doc.getFirstChild();
		while (curNode != null) {
			if (curNode.getNodeType() == Node.ELEMENT_NODE) {
				return curNode;
			}
			curNode = curNode.getNextSibling();
		}
		return null;
	}

	public static String getXPath(Node node) {
		String          xpath;
		Node            parentNode;

		switch (node.getNodeType()) {

			case Node.ELEMENT_NODE:
				parentNode = node.getParentNode();
			    xpath = "/" + node.getNodeName();
				break;

			case Node.ATTRIBUTE_NODE:
				parentNode = ((Attr) node).getOwnerElement();
				xpath = "/@" + node.getNodeName();
				break;

			case Node.TEXT_NODE:
				parentNode = node.getParentNode();
				if (parentNode.getNodeType() == Node.ATTRIBUTE_NODE) {
					xpath = "[@" + parentNode.getNodeName() + "=\"" +
							node.getNodeValue() + "\"]";
				    parentNode = ((Attr)parentNode).getOwnerElement();
				}
				else if (parentNode.getNodeType() == Node.ELEMENT_NODE) {
					xpath = "[" + parentNode.getNodeName() + "=\"" +
							node.getNodeValue() + "\"]";
				    parentNode = parentNode.getParentNode();
				}
				else {
					return "Unknown node type";
				}
				break;

			default:

				return "Unknown node type";

		}

		while ((parentNode != null) &&
			   (parentNode.getNodeType() != Node.DOCUMENT_NODE)) {
			xpath = "/" + parentNode.getNodeName() + xpath;
			parentNode = parentNode.getParentNode();
		}
		return xpath;
	}

	/**
	 * Iterates through the child elements of the node specified and builds
	 * a hastable that contains one entry for each distinct element found.  The
	 * contents of the hashtable entry is an interger count that indicates the
	 * number of occurrances of an element with a give name.
	 *
	 * @param node  the node from which to get the list of disting child
	 *              elements
	 *
	 * @return  a hashtable containing entries for each distinct element
	 */
	public static Hashtable<String, ElementEntry> getDistinctChildElements(Node node) {

		Hashtable<String, ElementEntry>           foundElements = new Hashtable<>();
		Node                curChildNode;
		ElementEntry        entry;

		curChildNode = node.getFirstChild();
		while (curChildNode != null) {

			//  Only care about element nodes
			if (curChildNode.getNodeType() == Node.ELEMENT_NODE) {

			    entry = (ElementEntry) foundElements.get(curChildNode.getNodeName());
			    if (entry == null) {
				    //  A node with this name was not previously found.
				    foundElements.put(curChildNode.getNodeName(),
								      new ElementEntry(curChildNode.getNodeName()));
			    }
			    else {
				    //  A node with this name was previously found, increment
					//  its count
					entry.incrementCount();
					foundElements.put(curChildNode.getNodeName(), entry);
			    }
			}
			curChildNode = curChildNode.getNextSibling();
		}
		return foundElements;
	}

	/**
	 * Determines whether the node specified has any child elements
	 *
	 * @param node the node to check for child elements
	 *
	 * @return true if the node has child elements, false otherwise
	 */
	public static boolean hasChildElements(Node node) {

		Node        curNode = node.getFirstChild();

		while (curNode != null) {
			if (curNode.getNodeType() == Node.ELEMENT_NODE) {
				return true;
			}
			curNode = curNode.getNextSibling();
		}
		return false;
	}

	/**
	 * Determines whether the specified node has any child elements
	 * that repeat
	 *
	 * @param node the node to check for repeating elements
	 *
	 * @return true if the node has repeating elements, false otherwise
	 */
	public static boolean hasRepeatingElements(Node node) {
		Hashtable<String, ElementEntry>           foundElements = new Hashtable<>();
		Node                curChildNode;
		ElementEntry        entry;

		curChildNode = node.getFirstChild();
		while (curChildNode != null) {

			//  Only care about element nodes
			if (curChildNode.getNodeType() == Node.ELEMENT_NODE) {

			    entry = (ElementEntry) foundElements.get(curChildNode.getNodeName());
			    if (entry == null) {
				    //  A node with this name was not previously found.
				    foundElements.put(curChildNode.getNodeName(),
								      new ElementEntry(curChildNode.getNodeName()));
			    }
			    else {
					return true;
			    }
			}
			curChildNode = curChildNode.getNextSibling();
		}
		return false;
	}

	public static String getValue(Node node) {

		StringBuffer        textValue = new StringBuffer();
		Node                curChildNode;
		String              value;

		curChildNode = node.getFirstChild();
		while (curChildNode != null) {

			if (curChildNode.getNodeType() == Node.TEXT_NODE) {
				textValue.append(curChildNode.getNodeValue());
			}
			curChildNode = curChildNode.getNextSibling();
		}

		value = textValue.toString().trim();
		if (value.length() == 0) {
			return null;
		}
		return value;
	}

	public static int getIndexOfChild(Node parentNode, Node childNode) {
		Node    curNode = null;
		int     index = 0;

		curNode = parentNode.getFirstChild();
		while (curNode != null) {
			if (curNode == childNode) {
	    		return index;
		    }
			curNode = curNode.getNextSibling();
			index++;
		}
		return -1;
    }

}

/**
 * The object type of the entries store in the Distinct Element hash table
 */
class ElementEntry {

	public ElementEntry(String elementName) {
		m_ElementName = elementName;
		m_ElementCount = 1;
	}

	public void incrementCount() {
		m_ElementCount++;
	}

	public String getElementName() {
		return m_ElementName;
	}

	public int getElementCount() {
		return m_ElementCount;
	}

	private String      m_ElementName;
	private int         m_ElementCount;
}

