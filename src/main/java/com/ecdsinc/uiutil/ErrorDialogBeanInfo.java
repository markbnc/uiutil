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

public class ErrorDialogBeanInfo extends SimpleBeanInfo {
    Class<?> beanClass = ErrorDialog.class;
    String iconColor16x16Filename;
    String iconColor32x32Filename;
    String iconMono16x16Filename;
    String iconMono32x32Filename;

    public ErrorDialogBeanInfo() {
    }
    public PropertyDescriptor[] getPropertyDescriptors() {
        PropertyDescriptor[] pds = new PropertyDescriptor[] { };
        return pds;
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