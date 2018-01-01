/*****************************************************************************
 *
 *	File: TestPaneBeanInfo.java
 *
 *	Description:
 *      Contains the BeanInfo class for the TestPane java bean.  NOTE - this
 *      file was originally created by JBuilder 4.
 *
 *	Revision History
 *		$Log: TestPaneBeanInfo.java $
 *		Revision 1.3  2001/04/01 11:38:11  markb
 *		moved bean icons to images folder
 *		Revision 1.2  2001/03/28 04:40:33  markb
 *		catch IOException in getIcon function
 *		Revision 1.1  2001/03/26 05:34:55  markb
 *		Initial revision
 *
 *		Copyright (c) 2000, East Coast Dealer Services Inc. All rights
 *		reserved worldwide.  This program or documentation is supplied
 *      pursuant to and its use is governed by a license agreement from
 *      East Coast Dealer Services and contains confidential information
 *      of East Coast Dealer Services Inc. Disclosure and copying are
 *      subject to the terms of the license agreement
 *
 *****************************************************************************/

package com.ecdsinc.uiutil;

import java.beans.*;
import java.io.IOException;

public class TestPaneBeanInfo extends SimpleBeanInfo {
	Class<?> beanClass = TestPane.class;
    String iconColor16x16Filename;
    String iconColor32x32Filename;
    String iconMono16x16Filename;
    String iconMono32x32Filename;

    public TestPaneBeanInfo() {
		iconColor16x16Filename = "images/TestPane16C.gif";
		iconColor32x32Filename = "images/TestPane32C.gif";
		iconMono16x16Filename = "images/TestPane16BW.gif";
		iconMono32x32Filename = "images/TestPane32BW.gif";
    }
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor _background = new PropertyDescriptor("background", beanClass, "getBackground", "setBackground");
            _background.setDisplayName("background");
            _background.setShortDescription("background");
            PropertyDescriptor _border = new PropertyDescriptor("border", beanClass, "getBorder", "setBorder");
            _border.setDisplayName("border");
            _border.setShortDescription("border");
            PropertyDescriptor _editable = new PropertyDescriptor("editable", beanClass, "isEditable", "setEditable");
            _editable.setDisplayName("editable");
            _editable.setShortDescription("editable");
            PropertyDescriptor _font = new PropertyDescriptor("font", beanClass, "getFont", "setFont");
            _font.setDisplayName("font");
            _font.setShortDescription("font");
            PropertyDescriptor _foreground = new PropertyDescriptor("foreground", beanClass, "getForeground", "setForeground");
            _foreground.setDisplayName("foreground");
            _foreground.setShortDescription("foreground");
            PropertyDescriptor _margin = new PropertyDescriptor("margin", beanClass, "getMargin", "setMargin");
            _margin.setDisplayName("margin");
            _margin.setShortDescription("margin");
            PropertyDescriptor _selectedTextColor = new PropertyDescriptor("selectedTextColor", beanClass, "getSelectedTextColor", "setSelectedTextColor");
            _selectedTextColor.setDisplayName("selectedTextColor");
            _selectedTextColor.setShortDescription("selectedTextColor");
            PropertyDescriptor _selectionColor = new PropertyDescriptor("selectionColor", beanClass, "getSelectionColor", "setSelectionColor");
            _selectionColor.setDisplayName("selectionColor");
            _selectionColor.setShortDescription("selectionColor");
            PropertyDescriptor _text = new PropertyDescriptor("text", beanClass, "getText", "setText");
            _text.setDisplayName("text");
            _text.setShortDescription("text");
            PropertyDescriptor _toolTipText = new PropertyDescriptor("toolTipText", beanClass, "getToolTipText", "setToolTipText");
            _toolTipText.setDisplayName("toolTipText");
            _toolTipText.setShortDescription("toolTipText");
            PropertyDescriptor[] pds = new PropertyDescriptor[] {
	            _background,
	            _border,
	            _editable,
	            _font,
	            _foreground,
	            _margin,
	            _selectedTextColor,
	            _selectionColor,
	            _text,
	            _toolTipText};
            return pds;
	    }
        catch(IntrospectionException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public java.awt.Image getIcon(int iconKind) {

		try {
		    switch (iconKind) {
				case BeanInfo.ICON_COLOR_16x16:
	    			return ImageUtil.getImage (iconColor16x16Filename);
		    	case BeanInfo.ICON_COLOR_32x32:
			    	  return ImageUtil.getImage(iconColor32x32Filename);
				case BeanInfo.ICON_MONO_16x16:
	    			  return ImageUtil.getImage(iconMono16x16Filename);
		    	case BeanInfo.ICON_MONO_32x32:
			    	  return ImageUtil.getImage(iconMono32x32Filename);
            }
			return null;
		}
		catch (IOException except) {
			return null;
		}
    }

    public BeanInfo[] getAdditionalBeanInfo() {

		//  Hide super class properties
		return new BeanInfo[] {};
    }



}