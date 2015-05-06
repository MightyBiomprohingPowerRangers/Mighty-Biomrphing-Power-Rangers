package MBPR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
	private EvolutionView evoView;


	public MainGUI() throws IOException 
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Biomorph Generator");
		setPreferredSize(new Dimension(895, 661));
		setResizable(false);
		
		openHoFButton = new BasicArrowButton(SwingConstants.EAST);
		openHoFButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				openHoF(evt);
			}
		});
		hallOfFame = new HallOfFame(this);
		evoView = new EvolutionView(this);
		
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
		
		leftPane.add(evoView);
		centerPane.add(openHoFButton);
		rightPane.add(hallOfFame);
		pack();
	}

	public void notifyMeHOF() throws IOException
	{
		hallOfFame.reCreate();
	}
	
	public void notifyMeEvo(int[] gene)
	{
		evoView.loadFromHof(gene);
	}
	
	protected void openHoF(ActionEvent evt) {
		
		if (hofShowing == true)
		{
			rightPane.remove(hallOfFame);
			setPreferredSize(new Dimension(623, 661));
			pack();
			hofShowing = false;
		}
		else if (hofShowing == false)
		{
			rightPane.add(hallOfFame);
			setPreferredSize(new Dimension(895, 661));
			pack();
			hofShowing = true;
		}
	}

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try {
					new MainGUI().setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
