package tests;

import gui.Field;
import gui.Gui;

import javax.swing.JTextArea;

import junit.framework.TestCase;

public class testGui extends TestCase {
    public void test1() {
	Field f = new Field(new JTextArea());
	f.setText("tan(0)");
	f.actionPerformed(null);
    }

    public void test2() {
	new Gui();
    }
}
