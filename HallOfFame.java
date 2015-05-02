package MBPR;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HallOfFame extends JPanel {
	
	private JPanel imagePanel;
	private Grow grow;
	
	private int imageX = 250;
	private int imageY = 250;

	public HallOfFame() 
	{
		grow = new Grow();
		imagePanel = new JPanel();
		imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.PAGE_AXIS));
		//JTextArea t = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(imagePanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		this.setLayout(new BorderLayout());
		setPreferredSize(new Dimension(250,600));
		//for (int i = 0; i < 100; i++)
		//	t.append("Hello\n");
		for (int i = 0; i < 10; i++)
		{
			JLabel label = new JLabel();
			label.setIcon(new ImageIcon(grow.getCanvas().getScaledImage(imageX, imageY)));
			imagePanel.add(label);
			grow.mutate();
		}
//		this.add(imagePanel, BorderLayout.CENTER);
		this.add(scrollPane, BorderLayout.CENTER);
	}

}
