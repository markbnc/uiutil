package com.ecdsinc.uiutil;

import java.io.File;
import javax.swing.filechooser.*;


public class XMLDocumentFilter extends FileFilter {

    public XMLDocumentFilter() {
    }

    public boolean accept(File file) {

		if (file.isDirectory()) {
		    return true;
		}

		String extension = getExtension(file);
        if ((extension != null) &&
			extension.equals("xml")) {
		    return true;
        }
	    return false;
    }

    public String getDescription() {
        return "XML Files (*.xml)";
    }

	public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

}