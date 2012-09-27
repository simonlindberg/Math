package gui;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Gui extends JFrame {

    private static final long serialVersionUID = -260769760033548728L;

    public Gui() {
	super("Calculate");
	setLayout(new BorderLayout());
	JTextArea ta = new JTextArea(15, 20);
	ta.setEditable(false);
	Panel c = new Panel();
	JScrollPane scroll = new JScrollPane(ta);
	scroll.setHorizontalScrollBar(null);
	c.add(scroll, BorderLayout.CENTER);
	c.add(new Field(ta), BorderLayout.SOUTH);
	add(c);

	setSize(260, 310);
	setResizable(false);

	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
	new Gui().setVisible(true);
    }
}
