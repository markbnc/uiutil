package com.ecdsinc.uiutil;

import java.beans.*;
import java.io.IOException;

/**
 * Title:        User Interface Utility Package
 * Description:  Utility classes and beans for deleloping user interfaces
 * Copyright:    Copyright (c) 2000 East Coast Dealer Services
 * Company:      East Coast Dealer Services
 * @author Mark Biamonte
 * @version 1.0
 */

public class NumberFieldBeanInfo extends SimpleBeanInfo {

	Class<?> beanClass = NumberField.class;

	String iconColor16x16Filename = "images/NumberField16C.gif";
    String iconColor32x32Filename = "images/NumberField32C.gif";
    String iconMono16x16Filename = "images/NumberField16BW.gif";
    String iconMono32x32Filename = "images/NumberField32BW.gif";

    public NumberFieldBeanInfo() {
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor _allowNegative = new PropertyDescriptor("allowNegative", beanClass, "getAllowNegative", "setAllowNegative");
            PropertyDescriptor _maxDigits = new PropertyDescriptor("maxDigits", beanClass, "getMaxDigits", "setMaxDigits");
            PropertyDescriptor[] pds = new PropertyDescriptor[] {
	            _allowNegative,
	            _maxDigits};
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