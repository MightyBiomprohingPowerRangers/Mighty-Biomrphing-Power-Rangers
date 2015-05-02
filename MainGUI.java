package MBPR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicArrowButton;

public class MainGUI extends JFrame {
	
	private Container pane;
	
	private JPanel leftPane;
	private JPanel centerPane;
	private JPanel rightPane;
	private JPanel bottomPane;
	
	private boolean hofShowing = true;
	private BasicArrowButton openHoFButton;
	private HallOfFame hallOfFame;

	public MainGUI() 
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Biomorph Generator");
		
		openHoFButton = new BasicArrowButton(SwingConstants.EAST);
		openHoFButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				openHoF(evt);
			}
		});
		
		hallOfFame = new HallOfFame();
		
		pane = getContentPane();
		
		leftPane = new JPanel();
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.PAGE_AXIS));
		pane.add(leftPane, BorderLayout.LINE_START);
		
		centerPane = new JPanel();
		centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.PAGE_AXIS));
		pane.add(centerPane, BorderLayout.CENTER);
		
		rightPane = new JPanel();
		rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.PAGE_AXIS));
		pane.add(rightPane, BorderLayout.LINE_END);
		
		leftPane.add(new EvolutionView());
		centerPane.add(openHoFButton);
		rightPane.add(hallOfFame);
		pack();
	}

	protected void openHoF(ActionEvent evt) {
		
		if (hofShowing == true)
		{
			rightPane.remove(hallOfFame);
			pack();
			hofShowing = false;
		}
		else if (hofShowing == false)
		{
			rightPane.add(hallOfFame);
			pack();
			hofShowing = true;
		}
	}

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				new MainGUI().setVisible(true);
			}
		});
	}

}
