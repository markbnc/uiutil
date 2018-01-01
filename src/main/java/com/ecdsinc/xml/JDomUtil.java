package com.ecdsinc.xml;

import java.util.List;

import org.jdom.*;

public class JDomUtil {

    public JDomUtil() {
    }

	static public void removeWhitespace (Object node) {

		@SuppressWarnings("rawtypes")
		List        childList;
		Object      curChild;
		int         numChildren;

		childList = getMixedContent (node);
		if (childList == null) {
			return;
		}

		numChildren = childList.size();
		for (int index = numChildren-1; index >= 0; index--) {

			curChild = childList.get(index);
			if ((curChild instanceof String) &&
				(((String) curChild).trim().length() == 0)) {
					childList.remove(index);
			}
			else {
				removeWhitespace(curChild);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	static public List getMixedContent (Object node) {

		List    childList;

		if (node instanceof Document) {
			childList = ((Document) node).getContent();
		}
		else if (node instanceof Element) {
			childList = ((Element) node).getContent();
		}
//		else if (node instanceof EntityRef) {
//			childList = ((EntityRef) node).getMixedContent();
//		}
		else {
			childList = null;
		}
		return childList;
	}

}