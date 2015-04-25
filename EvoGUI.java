package MBPR;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

public class EvoGUI extends JFrame 
{
	private Container pane;
	
	private JPanel leftPane;
	private JPanel centerPane;
	private JPanel rightPane;
	
	private Grow grow;
	private JLabel currentImage;
	private ArrayList<JLabel> mutatedImages;
	private ArrayList<int[]> genes;
	
	private int currentImageX = 200;
	private int currentImageY = 200;


	public EvoGUI() 
	{
		initComponents();
	}

	private void initComponents() 
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Biomorph Generator");
		
		createBiomorphComponents();
		createLayout();
		System.out.println();
	}
	
	private void createBiomorphComponents()
	{
		grow = new Grow();
		
		currentImage = new JLabel();
		currentImage.setIcon(new ImageIcon(grow.getCanvas().getScaledImage(currentImageX,currentImageY)));
		currentImage.setPreferredSize(new Dimension(currentImageX, currentImageY));
		
		genes = new ArrayList<int[]>();
		
		mutatedImages = new ArrayList<JLabel>();
		for (int i = 0; i < 8; i++)
			mutatedImages.add(new JLabel());
		for (JLabel label: mutatedImages)
		{
			grow.mutate();
			genes.add(grow.getNewGenes());
			label.setIcon(new ImageIcon(grow.getCanvas().getScaledImage(currentImageX,currentImageY)));
			label.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent evt) {
					mutatedImageClicked(evt);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});	
		}
		for (JLabel label: mutatedImages)
			label.setPreferredSize(new Dimension(currentImageX, currentImageY));
		
		
	}
	
	protected void mutatedImageClicked(MouseEvent evt) 
	{
		ArrayList<int[]> genes2 = new ArrayList<int[]>();
		for (int i = 0; i < mutatedImages.size(); i++)
		{
			if (evt.getComponent() == mutatedImages.get(i))
			{
				int[] gene = genes.get(i);
				grow.setGenes(gene);
				grow.setCurrentGenes(gene);
				currentImage.setIcon(mutatedImages.get(i).getIcon());
				for (int j = 0; j < mutatedImages.size(); j++)
				{
					grow.mutate();
					genes2.add(grow.getNewGenes());
					mutatedImages.get(j).setIcon(new ImageIcon(grow.getCanvas().getScaledImage(currentImageX, currentImageY)));
					
				}
				genes = genes2;
			}
		}
		
	}

	private void createLayout() 
	{
		pane = getContentPane();
		
		leftPane = new JPanel();
		centerPane = new JPanel();
		rightPane = new JPanel();
		
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.PAGE_AXIS));
		pane.add(leftPane, BorderLayout.LINE_START);
		
		centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.PAGE_AXIS));
		pane.add(centerPane, BorderLayout.CENTER);
		
		rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.PAGE_AXIS));
		pane.add(rightPane, BorderLayout.LINE_END);
		
		leftPane.add(mutatedImages.get(0));
		leftPane.add(mutatedImages.get(1));
		leftPane.add(mutatedImages.get(2));
		
		centerPane.add(mutatedImages.get(3));
		centerPane.add(currentImage);
		centerPane.add(mutatedImages.get(4));
		
		rightPane.add(mutatedImages.get(5));
		rightPane.add(mutatedImages.get(6));
		rightPane.add(mutatedImages.get(7));
		
		pack();	
	}

	public static void main(String args[]) 
	{
		long startTime = (new Date()).getTime();
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new EvoGUI().setVisible(true);
			}
		});

		
		long endTime = (new Date()).getTime();
		long elapsedTime = endTime - startTime;
//		System.out.println("(" + String.format("%.3f", elapsedTime / 1000.0) + "s) ");
	}
}