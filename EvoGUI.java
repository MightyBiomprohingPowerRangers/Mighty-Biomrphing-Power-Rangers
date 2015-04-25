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
	private JPanel bottomPane;
	
	private JLabel geneLabel;
	private Grow grow;
	private JLabel currentImage;
	private ArrayList<JLabel> mutatedImages;
	private ArrayList<int[]> genes;
	
	private int imageX;
	private int imageY;


	public EvoGUI() 
	{
		initComponents();
	}
	
	private void setImageDimensions(double scale)
	{
		imageX = (int) (grow.getCanvasX()*scale);
		imageY = (int) (grow.getCanvasY()*scale);
	}

	private void initComponents() 
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Biomorph Generator");
		
		createBiomorphComponents();
		createLayout();
	}
	
	private void createBiomorphComponents()
	{
		grow = new Grow();
		setImageDimensions(0.1);
		
		geneLabel = new JLabel();
		geneLabel.setText(grow.getCurrentGenesString());
		
		currentImage = new JLabel();
		currentImage.setIcon(new ImageIcon(grow.getCanvas().getScaledImage(imageX,imageY)));
		currentImage.setPreferredSize(new Dimension(imageX, imageY));
		currentImage.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent evt) {
				currentImageClicked(evt);
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
		
		genes = new ArrayList<int[]>();
		
		mutatedImages = new ArrayList<JLabel>();
		for (int i = 0; i < 8; i++)
			mutatedImages.add(new JLabel());
		for (JLabel label: mutatedImages)
		{
			grow.mutate();
			genes.add(grow.getNewGenes());
			label.setIcon(new ImageIcon(grow.getCanvas().getScaledImage(imageX,imageY)));
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
			label.setPreferredSize(new Dimension(imageX, imageY));
		
		
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
				geneLabel.setText(grow.getCurrentGenesString());
				currentImage.setIcon(mutatedImages.get(i).getIcon());
				for (int j = 0; j < mutatedImages.size(); j++)
				{
					grow.mutate();
					genes2.add(grow.getNewGenes());
					mutatedImages.get(j).setIcon(new ImageIcon(grow.getCanvas().getScaledImage(imageX, imageY)));
					
				}
				genes = genes2;
			}
		}
	}
	
	protected void currentImageClicked(MouseEvent evt) 
	{
		ArrayList<int[]> genes2 = new ArrayList<int[]>();
		for (int j = 0; j < mutatedImages.size(); j++)
		{
			grow.mutate();
			genes2.add(grow.getNewGenes());
			mutatedImages.get(j).setIcon(new ImageIcon(grow.getCanvas().getScaledImage(imageX, imageY)));
		}
		genes = genes2;
	}

	private void createLayout() 
	{
		pane = getContentPane();
		
		leftPane = new JPanel();
		centerPane = new JPanel();
		rightPane = new JPanel();
		bottomPane = new JPanel();
		
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.PAGE_AXIS));
		pane.add(leftPane, BorderLayout.WEST);
		
		centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.PAGE_AXIS));
		pane.add(centerPane, BorderLayout.CENTER);
		
		rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.PAGE_AXIS));
		pane.add(rightPane, BorderLayout.EAST);
		
		bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.PAGE_AXIS));
		pane.add(bottomPane, BorderLayout.SOUTH);
		
		leftPane.add(mutatedImages.get(0));
		leftPane.add(mutatedImages.get(1));
		leftPane.add(mutatedImages.get(2));
		
		centerPane.add(mutatedImages.get(3));
		centerPane.add(currentImage);
		centerPane.add(mutatedImages.get(4));
		
		rightPane.add(mutatedImages.get(5));
		rightPane.add(mutatedImages.get(6));
		rightPane.add(mutatedImages.get(7));
		
		bottomPane.add(geneLabel);
		
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