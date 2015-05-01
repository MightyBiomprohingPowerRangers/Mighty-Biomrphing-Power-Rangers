package MBPR;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

public class EvolutionView extends JPanel
{
	private JPanel pane;
	private Grow grow;
	private JLabel currentImage;
	private JLabel geneLabel;
	private ArrayList<int[]> genes;
	private ArrayList<JLabel> mutatedImages;
	
	private int imageX = 200;
	private int imageY = 200;

	public EvolutionView() 
	{
		createBiomorphComponents();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		pane = new JPanel(new GridLayout(3,3,3,3));
		pane.add(mutatedImages.get(0));
		pane.add(mutatedImages.get(3));
		pane.add(mutatedImages.get(5));
		pane.add(mutatedImages.get(1));
		pane.add(currentImage);
		pane.add(mutatedImages.get(6));
		pane.add(mutatedImages.get(2));
		pane.add(mutatedImages.get(4));
		pane.add(mutatedImages.get(7));
		add(pane);
		add(geneLabel);
	}
	
	private void createBiomorphComponents()
	{
		grow = new Grow();
//		setImageDimensions(imageScale);
		
		geneLabel = new JLabel();
		geneLabel.setText(grow.getCurrentGenesString());
		geneLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
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

}