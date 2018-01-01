package com.ecdsinc.uiutil;

import java.beans.*;

/**
 * Title:        User Interface Utility Package
 * Description:  Utility classes and beans for deleloping user interfaces
 * Copyright:    Copyright (c) 2000 East Coast Dealer Services
 * Company:      East Coast Dealer Services
 * @author Mark Biamonte
 * @version 1.0
 */

public class XMLTreeBeanInfo extends SimpleBeanInfo {
	Class<?> beanClass = XMLTree.class;
    String iconColor16x16Filename = "images/XMLTree16C.gif";
    String iconColor32x32Filename = "images/XMLTree32C.gif";
    String iconMono16x16Filename = "images/XMLTree16BW.gif";
    String iconMono32x32Filename = "images/XMLTree32BW.gif";

    public XMLTreeBeanInfo() {
    }
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor _rootNode = new PropertyDescriptor("rootNode", beanClass, "getRootNode", "setRootNode");
            PropertyDescriptor _showAllNodes = new PropertyDescriptor("showAllNodes", beanClass, "getShowAllNodes", "setShowAllNodes");
            _showAllNodes.setDisplayName("showAllNodes");
            _showAllNodes.setShortDescription("showAllNodes");
            PropertyDescriptor _showAttributes = new PropertyDescriptor("showAttributes", beanClass, "getShowAttributes", "setShowAttributes");
            PropertyDescriptor[] pds = new PropertyDescriptor[] {
	            _rootNode,
	            _showAllNodes,
	            _showAttributes};
            return pds;



}
        catch(IntrospectionException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public java.awt.Image getIcon(int iconKind) {
        switch (iconKind) {
        case BeanInfo.ICON_COLOR_16x16:
              return iconColor16x16Filename != null ? loadImage(iconColor16x16Filename) : null;
        case BeanInfo.ICON_COLOR_32x32:
              return iconColor32x32Filename != null ? loadImage(iconColor32x32Filename) : null;
        case BeanInfo.ICON_MONO_16x16:
              return iconMono16x16Filename != null ? loadImage(iconMono16x16Filename) : null;
        case BeanInfo.ICON_MONO_32x32:
              return iconMono32x32Filename != null ? loadImage(iconMono32x32Filename) : null;
                                }
        return null;
    }
    public BeanInfo[] getAdditionalBeanInfo() {
    	Class<?> superclass = beanClass.getSuperclass();
        try {
            BeanInfo superBeanInfo = Introspector.getBeanInfo(superclass);
            return new BeanInfo[] { superBeanInfo };
        }
        catch(IntrospectionException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}