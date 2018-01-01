package com.ecdsinc.uiutiltest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.ecdsinc.uiutil.*;
import javax.swing.border.*;


@SuppressWarnings("serial")
public class TextFieldsDlg extends ModalDialog {

    public TextFieldsDlg(Frame parentFrame) {
		super(parentFrame, "Test Custom Text Fields");

        try {

            jbInit();

			setSize(325, 225);
			setResizable(false);
			getRootPane().setDefaultButton(okBtn);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

	public void setNumber (long numberVal) {
		m_Number = numberVal;
		updateControls();
	}

	public long getNumber() {
		return m_Number;
	}

	public void setConstrainedNumber (int numberVal) {
		m_ConstrainedNumber = numberVal;
		updateControls();
	}

	public int getConstrainedNumber() {
		return m_ConstrainedNumber;
	}

    void okBtn_actionPerformed(ActionEvent e) {
		String  numberStr = numberFieldCtrl.getText();
		if (numberStr.length() == 0) {
			m_Number = 0;
		}
		else {
			try {
			    m_Number = Long.parseLong(numberStr);
			}
			catch (NumberFormatException except) {
				Toolkit.getDefaultToolkit().beep();
				numberFieldCtrl.requestFocus();
				System.out.println("Unconstrained Number not a valid long integer");
				return;
			}
		}

		numberStr = constrainedNumCtrl.getText();
		if (numberStr.length() == 0) {
			m_ConstrainedNumber = 0;
		}
		else {
			try {
			    m_ConstrainedNumber = Integer.parseInt(numberStr);
			}
			catch (NumberFormatException except) {
				Toolkit.getDefaultToolkit().beep();
				constrainedNumCtrl.requestFocus();
				System.out.println("Constrained Number not a valid integer");
				return;
			}
		}

		disposeDialog(ModalDialog.DIALOG_ACCEPT);
    }

    void cancelBtn_actionPerformed(ActionEvent e) {
		disposeDialog(ModalDialog.DIALOG_CANCEL);
    }

    void setConstraintsBtn_actionPerformed(ActionEvent e) {

		//  Clear the contents of the constrained number control before
		//  changing the constraints
		constrainedNumCtrl.setText("");
		constrainedNumCtrl.setMaxDigits(Integer.parseInt(maxDigitsCtrl.getText()));
		constrainedNumCtrl.setAllowNegative(allowNegativeCtrl.isSelected());
    }

	private void updateControls() {
	    numberFieldCtrl.setText(String.valueOf(m_Number));
		constrainedNumCtrl.setText(String.valueOf(m_ConstrainedNumber));

	}

	private void jbInit() throws Exception {
        titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(134, 134, 134)),"Constraints");
        component1 = Box.createHorizontalStrut(5);
        numberFieldCtrl.setPreferredSize(new Dimension(150, 21));
        numberFieldCtrl.setMinimumSize(new Dimension(150, 21));
        constrainedNumLabel.setText("Constrained Number:");
        constrainedNumLabel.setLabelFor(constrainedNumCtrl);
        constrainedNumLabel.setDisplayedMnemonic('C');
        constrainedNumLabel.setPreferredSize(new Dimension(132, 17));
        constrainedNumCtrl.setMaxDigits(5);
		constrainedNumCtrl.setAllowNegative(true);
        constrainedNumCtrl.setPreferredSize(new Dimension(150, 21));
        constrainedNumCtrl.setMinimumSize(new Dimension(150, 21));
        numberFieldLabel.setText("Unconstrained Number:");
        numberFieldLabel.setLabelFor(numberFieldCtrl);
        numberFieldLabel.setDisplayedMnemonic('U');
        controlPanel.setLayout(controlFlowLayout);
        controlFlowLayout.setAlignment(FlowLayout.LEFT);
        buttonPanel.setLayout(buttonFlowLayout);
        okBtn.setPreferredSize(new Dimension(75, 27));
        okBtn.setText("OK");
        okBtn.addActionListener(new TextFieldsDlg_okBtn_actionAdapter(this));
        cancelBtn.setPreferredSize(new Dimension(75, 27));
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new TextFieldsDlg_cancelBtn_actionAdapter(this));
        mainPanel.setLayout(mainGridLayout);
        mainGridLayout.setRows(2);
        mainGridLayout.setColumns(1);
        constraintsPanel.setLayout(flowLayout1);
        maxDigitsLabel.setText("Max Digits:");
        flowLayout1.setAlignment(FlowLayout.LEFT);
        constraintsPanel.setBorder(titledBorder1);
        allowNegativeCtrl.setSelected(true);
        allowNegativeCtrl.setText("AllowNegative");
        maxDigitsCtrl.setMinimumSize(new Dimension(50, 21));
        maxDigitsCtrl.setPreferredSize(new Dimension(50, 21));
        maxDigitsCtrl.setText("5");
		maxDigitsCtrl.setMaxDigits(2);
		maxDigitsCtrl.setAllowNegative(false);
        setConstraintsBtn.setText("Set");
		setConstraintsBtn.setDefaultCapable(false);
        setConstraintsBtn.addActionListener(new TextFieldsDlg_setConstraintsBtn_actionAdapter(this));
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(okBtn, null);
        buttonPanel.add(cancelBtn, null);
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, null);
        controlPanel.add(numberFieldLabel, null);
        controlPanel.add(numberFieldCtrl, null);
        controlPanel.add(constrainedNumLabel, null);
        controlPanel.add(constrainedNumCtrl, null);
        mainPanel.add(constraintsPanel, null);
        constraintsPanel.add(maxDigitsLabel, null);
        constraintsPanel.add(maxDigitsCtrl, null);
        constraintsPanel.add(component1, null);
        constraintsPanel.add(allowNegativeCtrl, null);
        constraintsPanel.add(setConstraintsBtn, null);
    }

	//  Data members
	private long        m_Number = 0;
	private int         m_ConstrainedNumber = 0;

	//  UI Controls
    JPanel buttonPanel = new JPanel();
    FlowLayout buttonFlowLayout = new FlowLayout();
    JButton okBtn = new JButton();
    JButton cancelBtn = new JButton();
    JPanel mainPanel = new JPanel();
    GridLayout mainGridLayout = new GridLayout();
    FlowLayout controlFlowLayout = new FlowLayout();
    JPanel controlPanel = new JPanel();
    JLabel numberFieldLabel = new JLabel();
    NumberField constrainedNumCtrl = new NumberField();
    JLabel constrainedNumLabel = new JLabel();
    NumberField numberFieldCtrl = new NumberField();
    JPanel constraintsPanel = new JPanel();
    FlowLayout flowLayout1 = new FlowLayout();
    JLabel maxDigitsLabel = new JLabel();
    TitledBorder titledBorder1;
    NumberField maxDigitsCtrl = new NumberField();
    JCheckBox allowNegativeCtrl = new JCheckBox();
    Component component1;
    JButton setConstraintsBtn = new JButton();

}

class TextFieldsDlg_okBtn_actionAdapter implements java.awt.event.ActionListener {
    TextFieldsDlg adaptee;

    TextFieldsDlg_okBtn_actionAdapter(TextFieldsDlg adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.okBtn_actionPerformed(e);
    }
}

class TextFieldsDlg_cancelBtn_actionAdapter implements java.awt.event.ActionListener {
    TextFieldsDlg adaptee;

    TextFieldsDlg_cancelBtn_actionAdapter(TextFieldsDlg adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.cancelBtn_actionPerformed(e);
    }
}

class TextFieldsDlg_setConstraintsBtn_actionAdapter implements java.awt.event.ActionListener {
    TextFieldsDlg adaptee;

    TextFieldsDlg_setConstraintsBtn_actionAdapter(TextFieldsDlg adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.setConstraintsBtn_actionPerformed(e);
    }
}