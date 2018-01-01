package com.ecdsinc.uiutiltest;

import javax.swing.*;
import java.awt.*;
import com.ecdsinc.uiutil.*;

@SuppressWarnings("serial")
public class XMLTreeDlg extends ModalDialog {

    public XMLTreeDlg(Frame parent, org.w3c.dom.Document domDocument) {
		super(parent, "XML Tree Control Test (DOM Parser)");
		try {

			xmlTreeCtrl = new XMLDOMTree(domDocument);
            jbInit();
			setSize(300, 500);
			setResizable(false);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

	public XMLTreeDlg(Frame parent, org.jdom.Document jdomDocument) {
		super(parent, "XML Tree Control Test (JDOM Parser)");
		try {
			xmlTreeCtrl = new XMLJDomTree(jdomDocument);
            jbInit();
			setSize(300, 500);
			setResizable(false);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
	}
    private void jbInit() throws Exception {
        contentPane.setLayout(contentBorderLayout);
        getContentPane().add(contentPane, BorderLayout.CENTER);
        contentPane.add(treeScrollPane, BorderLayout.CENTER);
        treeScrollPane.getViewport().add(xmlTreeCtrl, null);
    }

	//  UI Objects
    JPanel contentPane = new JPanel();
    BorderLayout contentBorderLayout = new BorderLayout();
    JScrollPane treeScrollPane = new JScrollPane();
    XMLTree xmlTreeCtrl = new XMLTree();

}