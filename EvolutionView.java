package MBPR;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class EvolutionView extends JPanel
{
	private JPanel pane;
	private Grow grow;
	private JLabel currentImage;
	private int[] currentGene;
	private ArrayList<int[]> genes;
	private ArrayList<JLabel> mutatedImages;
	private JPopupMenu cPopup;
	private JPopupMenu mPopup;
	private MainGUI gui;
	private JButton geneViewButton;

	private int imageX = 200;
	private int imageY = 200;

	public EvolutionView(MainGUI gui) 
	{
		this.gui = gui;
		createBiomorphComponents();
		createCurrentImagePopupMenu();
		createMutatedImagePopupMenu();
		setLayout(new BorderLayout());
		geneViewButton = new JButton("Open Gene View");
		geneViewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() 
					{
							new GeneGUI(currentGene).setVisible(true);
					}
				});
			}
		});
		this.add(geneViewButton, BorderLayout.PAGE_START);
		this.add(new JLabel("Biomorph Evolver", SwingConstants.CENTER), BorderLayout.CENTER);
		pane = new JPanel(new GridLayout(3,3));
		pane.add(mutatedImages.get(0));
		pane.add(mutatedImages.get(3));
		pane.add(mutatedImages.get(5));
		pane.add(mutatedImages.get(1));
		pane.add(currentImage);
		pane.add(mutatedImages.get(6));
		pane.add(mutatedImages.get(2));
		pane.add(mutatedImages.get(4));
		pane.add(mutatedImages.get(7));
		this.add(pane, BorderLayout.PAGE_END);
	}

	private void createBiomorphComponents()
	{
		grow = new Grow();
		currentGene = grow.getCurrentGenes();
		Border etchedBevel = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		currentImage = new JLabel();
		currentImage.setBorder(etchedBevel);
		currentImage.setIcon(new ImageIcon(grow.getCanvas().getScaledImage(imageX,imageY)));
		currentImage.setPreferredSize(new Dimension(imageX, imageY));
		currentImage.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent evt) {
				if (SwingUtilities.isLeftMouseButton(evt))
					currentImageLeftClicked(evt);
				else if (SwingUtilities.isRightMouseButton(evt))
					currentImageRightClicked(evt);
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
			label.setBorder(etchedBevel);
			label.setIcon(new ImageIcon(grow.getCanvas().getScaledImage(imageX,imageY)));
			label.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent evt) {
					if (SwingUtilities.isLeftMouseButton(evt))
						mutatedImageLeftClicked(evt);
					else if (SwingUtilities.isRightMouseButton(evt))
						mutatedImageRightClicked(evt);
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

	private void createCurrentImagePopupMenu()
	{
		cPopup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Add to Hall of Fame");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				//				add gene to recall list
				try 
				{
					ArrayList<int[]> temp = new ArrayList<int[]>();
					ArrayList<int[]> newhof = new Recall().getList();
					temp.add(currentGene);
					temp.addAll(newhof);
					if (temp.size() > 10)
					{
						JOptionPane.showMessageDialog((Component)gui, "Hall Of Fame limit reached. Remove an image from the Hall Of Fame to make room for another", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
					else 
					{
						new Store(temp);
						gui.notifyMeHOF();
					}
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}

		});
		cPopup.add(menuItem);
		menuItem = new JMenuItem("Save to file");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				//				for (int i = 0; i < mutatedImages.size(); i++)
				//				{
				//					if (cPopup.getInvoker() == mutatedImages.get(i))
				//					{
				new Save(new Grow(currentGene).getCanvas().getImg(), gui);
				//					}
				//				}
			}

		});
		cPopup.add(menuItem);
	}

	private void createMutatedImagePopupMenu()
	{
		mPopup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Add to Hall of Fame");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				for (int i = 0; i < mutatedImages.size(); i++)
				{
					if (mPopup.getInvoker() == mutatedImages.get(i))
					{
						try 
						{
							int[] gene = genes.get(i);
							ArrayList<int[]> temp = new ArrayList<int[]>();
							ArrayList<int[]> newhof = new Recall().getList();
							temp.add(gene);
							temp.addAll(newhof);
							if (temp.size() > 10)
							{
								JOptionPane.showMessageDialog((Component)gui, "Hall Of Fame limit reached. Remove an image from the Hall Of Fame to make room for another", "Error", JOptionPane.INFORMATION_MESSAGE);
								break;
							}
							new Store(temp);
							gui.notifyMeHOF();
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
					}
				}
			}

		});
		mPopup.add(menuItem);
		menuItem = new JMenuItem("Save to file");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				for (int i = 0; i < mutatedImages.size(); i++)
				{
					if (mPopup.getInvoker() == mutatedImages.get(i))
					{
						new Save(new Grow(genes.get(i)).getCanvas().getImg(), gui);
					}
				}
			}
		});
		mPopup.add(menuItem);
	}

	protected void currentImageRightClicked(MouseEvent evt) {
		cPopup.show(evt.getComponent(), evt.getX(), evt.getY());

	}

	protected void mutatedImageRightClicked(MouseEvent evt) {
		mPopup.show(evt.getComponent(), evt.getX(), evt.getY());

	}

	protected void currentImageLeftClicked(MouseEvent evt) 
	{
		ArrayList<int[]> tempGenes = new ArrayList<int[]>();
		for (int j = 0; j < mutatedImages.size(); j++)
		{
			grow.mutate();
			tempGenes.add(grow.getNewGenes());
			mutatedImages.get(j).setIcon(new ImageIcon(grow.getCanvas().getScaledImage(imageX, imageY)));
		}
		genes = tempGenes;
	}

	protected void mutatedImageLeftClicked(MouseEvent evt) 
	{
		ArrayList<int[]> tempGenes = new ArrayList<int[]>();
		for (int i = 0; i < mutatedImages.size(); i++)
		{
			if (evt.getComponent() == mutatedImages.get(i))
			{
				int[] gene = genes.get(i);
				grow.setGenes(gene);
				grow.setCurrentGenes(gene);
				currentGene = gene;
				currentImage.setIcon(mutatedImages.get(i).getIcon());
				for (int j = 0; j < mutatedImages.size(); j++)
				{
					grow.mutate();
					tempGenes.add(grow.getNewGenes());
					mutatedImages.get(j).setIcon(new ImageIcon(grow.getCanvas().getScaledImage(imageX, imageY)));

				}
				genes = tempGenes;
			}
		}
	}

	public void loadFromHof(int[] gene)
	{
		ArrayList<int[]> tempGenes = new ArrayList<int[]>();
		grow = new Grow(gene);
		currentGene = gene;
		currentImage.setIcon(new ImageIcon(grow.getCanvas().getScaledImage(imageX, imageY)));
		for (int j = 0; j < mutatedImages.size(); j++)
		{
			grow.mutate();
			tempGenes.add(grow.getNewGenes());
			mutatedImages.get(j).setIcon(new ImageIcon(grow.getCanvas().getScaledImage(imageX, imageY)));

		}
		genes = tempGenes;
	}

}