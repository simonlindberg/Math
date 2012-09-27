package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import session.Session;

public class Field extends JTextField implements ActionListener, KeyListener {
    private static final long serialVersionUID = -1335058191176580934L;

    private JTextArea ta;
    private Session se;
    private int stepsBack;
    private String originalString;

    public Field(JTextArea ta) {
	super(20);
	addActionListener(this);
	addKeyListener(this);
	this.ta = ta;
	se = new Session();
	stepsBack = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (!getText().equals("")) {
	    ta.setText(se.input(getText()) + "\n" + ta.getText());
	    setText("");
	    stepsBack = 0;
	}
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
	if (arg0.getKeyCode() == (KeyEvent.VK_UP)) {
	    try {
		if (stepsBack == 0) {
		    originalString = getText();
		}
		setText(se.getPreviousInput(++stepsBack));
		System.out.println("UP " + stepsBack);
	    } catch (ArrayIndexOutOfBoundsException e) {
		stepsBack--;
	    }
	} else if (arg0.getKeyCode() == (KeyEvent.VK_DOWN)) {
	    if (stepsBack > 1) {
		setText(se.getPreviousInput(--stepsBack));
		System.out.println("DOWN " + stepsBack);
	    } else if (stepsBack == 1) {
		setText(originalString);
		stepsBack--;
	    }
	}
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
}
