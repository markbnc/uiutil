package com.ecdsinc.uiutil;

import java.awt.datatransfer.*;
import java.io.*;
import javax.swing.ImageIcon;

public class TransferableIcon implements Transferable {

	final public static DataFlavor IMAGE_ICON_FLAVOR =
	    new DataFlavor(ImageIcon.class, "ImageIcon");

	public TransferableIcon(ImageIcon icon) {
	    m_Icon = icon;
	}

	public DataFlavor[] getTransferDataFlavors() {
	    return m_Flavors;
	}

	public Object getTransferData(DataFlavor flavor)
	  throws UnsupportedFlavorException,
		     IOException {

		Object returnObject;

		if (flavor.equals(m_Flavors[ICON])) {
			returnObject = m_Icon;
		}
		else if (flavor.equals(m_Flavors[STRING])) {
		    returnObject = m_Icon.getDescription();
		}
		else {
		  throw new UnsupportedFlavorException(flavor);
		}
		return returnObject;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
	    boolean returnValue = false;
	    for (int i=0, n=m_Flavors.length; i<n; i++) {
			if (flavor.equals(m_Flavors[i])) {
				returnValue = true;
				break;
			}
	    }
	    return returnValue;
	}

	//  Data members
	private ImageIcon           m_Icon;
	private static DataFlavor   m_Flavors[] = {
	    IMAGE_ICON_FLAVOR,
		DataFlavor.stringFlavor,
	};

	private final static int ICON = 0;
	private final static int STRING = 1;
}