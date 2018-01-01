package com.ecdsinc.uiutil;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ClassBrowser extends ModalDialog implements TreeSelectionListener {

	static private ClassBrowserTreeModel        classTreeModel = null;

	private String                              selectedClass;

	//  ***************************
	//  UI Elements
	//  ***************************

	//  Button Pane
    JPanel ButtonPane = new JPanel();
    FlowLayout buttonPaneFlowLayout = new FlowLayout();

    JButton okButton = new JButton();
    JButton cancelButton = new JButton();

	//  Control Pane
    JPanel ControlPane = new JPanel();
    GridBagLayout controlPaneGridBagLayout = new GridBagLayout();

    JScrollPane classTreeScrollPane = new JScrollPane();
    JTree classTree = new JTree();

    public ClassBrowser(Frame frame, String title) {

		super(frame, title);

			this.selectedClass = null;

        try {

            jbInit();
			init();
        }
        catch(Exception except) {

			String msg = "Error initializing class browser\n" +
						 except.getMessage();
            JOptionPane.showMessageDialog(this, msg, "Class Browser Error",
										  JOptionPane.ERROR_MESSAGE);
        }
    }


	public String getSelectedClass () {

		return this.selectedClass;
	}

	//  Implement TreeSelectionListener Interface
    public void valueChanged(TreeSelectionEvent event) {

		ClassBrowserTreeNode selectedNode =
			(ClassBrowserTreeNode) event.getNewLeadSelectionPath()
										.getLastPathComponent();

		if (selectedNode != null) {
			if (selectedNode.isLeaf()) {

				okButton.setEnabled(true);
			}
			else {

				okButton.setEnabled(false);
			}
		}
    }


	private void init() throws Exception {

		String                  classPath =
									System.getProperty("java.class.path");

		if (ClassBrowser.classTreeModel == null) {

			ClassBrowser.classTreeModel = new ClassBrowserTreeModel(classPath);
		}

		DefaultTreeSelectionModel   selectionModel =
			new DefaultTreeSelectionModel ();
		selectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		this.classTree.setModel(ClassBrowser.classTreeModel);
		this.classTree.setSelectionModel(selectionModel);
		this.classTree.addTreeSelectionListener(this);

		this.okButton.setEnabled(false);
	}

    private void jbInit() throws Exception {

		//  Button Pane
        ButtonPane.setLayout(buttonPaneFlowLayout);

        okButton.setMaximumSize(new Dimension(85, 27));
        okButton.setMinimumSize(new Dimension(85, 27));
        okButton.setPreferredSize(new Dimension(85, 27));
        okButton.setText("OK");
        okButton.addActionListener(new ClassBrowser_okButton_actionAdapter(this));

		cancelButton.setMaximumSize(new Dimension(85, 27));
        cancelButton.setMinimumSize(new Dimension(85, 27));
        cancelButton.setPreferredSize(new Dimension(85, 27));
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ClassBrowser_cancelButton_actionAdapter(this));

        ButtonPane.add(okButton, null);
        ButtonPane.add(cancelButton, null);

		//  Control Pane
		ControlPane.setLayout(controlPaneGridBagLayout);

        classTree.setRootVisible(false);
        classTree.setShowsRootHandles(true);

        ControlPane.add(classTreeScrollPane, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(8, 8, 8, 8), 0, 0));
        classTreeScrollPane.getViewport().add(classTree, null);

		//  ContentPane
        this.getContentPane().add(ButtonPane, BorderLayout.SOUTH);
        this.getContentPane().add(ControlPane, BorderLayout.CENTER);

    }

    void okButton_actionPerformed(ActionEvent e) {

		ClassBrowserTreeNode    selectedNode =
			(ClassBrowserTreeNode)
				this.classTree.getSelectionPath().getLastPathComponent();

		this.selectedClass = selectedNode.getClassName();

		super.disposeDialog(ModalDialog.DIALOG_ACCEPT);
    }

    void cancelButton_actionPerformed(ActionEvent e) {

		this.selectedClass = null;
		super.disposeDialog(ModalDialog.DIALOG_CANCEL);
    }
}

class ClassBrowser_okButton_actionAdapter implements java.awt.event.ActionListener {
    ClassBrowser adaptee;

    ClassBrowser_okButton_actionAdapter(ClassBrowser adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.okButton_actionPerformed(e);
    }
}

class ClassBrowser_cancelButton_actionAdapter implements java.awt.event.ActionListener {
    ClassBrowser adaptee;

    ClassBrowser_cancelButton_actionAdapter(ClassBrowser adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.cancelButton_actionPerformed(e);
    }
}