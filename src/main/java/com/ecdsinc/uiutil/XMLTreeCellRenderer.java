package com.ecdsinc.uiutil;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.*;

@SuppressWarnings("serial")
public class XMLTreeCellRenderer extends DefaultTreeCellRenderer {

    public XMLTreeCellRenderer() {
		init();
    }

	public Component getTreeCellRendererComponent(JTree tree,
												  Object value,
												  boolean sel,
												  boolean expanded,
												  boolean leaf,
												  int row,
												  boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded,
									       leaf, row, hasFocus);

		XMLTreeNode     treeNode = (XMLTreeNode) value;

		if (leaf) {

		    XMLTreeNode     parentNode = treeNode.getParent();

			setFont(m_NormalFont);
			if (parentNode.isAttribute()) {
				setIcon(m_TreeIcons[ATTRIBUTE_VALUE_ICON]);
			} else {
				setIcon(m_TreeIcons[ELEMENT_VALUE_ICON]);
			}
		}
		else {
			if (treeNode.isAttribute()) {
				setFont(m_ItalicFont);
				setIcon(m_TreeIcons[ATTRIBUTE_NODE_ICON]);
			}
			else {
				setFont(m_NormalFont);
				setIcon(m_TreeIcons[ELEMENT_NODE_ICON]);
			}
		}

        return this;
    }
	private void init() {

		//  Load and save the icons
		m_TreeIcons[ELEMENT_NODE_ICON] =
			new ImageIcon(getClass().getResource("images/elementNodeIcon.gif"));

		m_TreeIcons[ELEMENT_VALUE_ICON] =
			new ImageIcon(getClass().getResource("images/elementValueIcon.gif"));

		m_TreeIcons[ATTRIBUTE_NODE_ICON] =
			new ImageIcon(getClass().getResource("images/attribNodeIcon.gif"));

		m_TreeIcons[ATTRIBUTE_VALUE_ICON] =
			new ImageIcon(getClass().getResource("images/attribValueIcon.gif"));

		//  Initialize the fonts
		initFonts();

	}

	private void initFonts() {

		if ((m_NormalFont == null) || (m_ItalicFont == null)) {

			validate();

			//  Get the default font and create an italic font from it.
			m_NormalFont = new Font("Dialog", Font.PLAIN, 12);
			m_ItalicFont = m_NormalFont.deriveFont(Font.ITALIC);
		}

	}
	private ImageIcon           m_TreeIcons[] = new ImageIcon [MAX_TREE_ICONS];
	private Font                m_NormalFont = null;
	private Font                m_ItalicFont = null;

	private static final int    MAX_TREE_ICONS = 4;
	private static final int    ELEMENT_NODE_ICON = 0;
	private static final int    ELEMENT_VALUE_ICON = 1;
	private static final int    ATTRIBUTE_NODE_ICON = 2;
	private static final int    ATTRIBUTE_VALUE_ICON = 3;

}