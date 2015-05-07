package MBPR;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 * Creates a Panel to store the hall of fame images
 * @author MBPR
 *
 */
public class HallOfFame extends JPanel {

	private JPanel imagePanel;
	private ArrayList<int[]> genes;
	private Border etchedBevel;
	private JScrollPane scrollPane;
	private JPopupMenu popup;
	private ArrayList<JLabel> images;
	private MainGUI gui;
	private JButton clearButton;

	private int imageX = 250;
	private int imageY = 250;

	/**
	 * Initialises the components
	 * @param gui
	 * @throws IOException
	 */
	public HallOfFame(MainGUI gui) throws IOException 
	{
		this.gui = gui;
		genes = new Recall().getList();
		images = new ArrayList<JLabel>();
		imagePanel = new JPanel();
		clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Store(new ArrayList<int[]>());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				images = new ArrayList<JLabel>();
				genes = new ArrayList<int[]>();
				imagePanel.removeAll();
				scrollPane.getViewport().add(imagePanel);
			}
		});
		imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.PAGE_AXIS));
		scrollPane = new JScrollPane(imagePanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		this.setLayout(new BorderLayout());
		setPreferredSize(new Dimension(271,600));
		this.add(new JLabel("Hall Of Fame", SwingConstants.CENTER), BorderLayout.PAGE_START);
		this.add(clearButton, BorderLayout.PAGE_END);
		etchedBevel = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		createPopupMenu();
		if (genes.size() > 0)
		{
			for (int[] gene:genes)
			{
				createLabels(gene);
			}
		}
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * creates the image components
	 * @param gene
	 */
	private void createLabels(int[] gene)
	{
		JLabel label = new JLabel();
		label.setBorder(etchedBevel);
		label.setIcon(new ImageIcon(new Grow(gene).getCanvas().getScaledImage(imageX, imageY)));
		label.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent evt) {
				if (SwingUtilities.isRightMouseButton(evt))
					imageRightClicked(evt);
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
		images.add(label);
		imagePanel.add(label);
	}

	/**
	 * Resets the Hall Of Fame if  a change is made
	 * @throws IOException
	 */
	public void reCreate() throws IOException
	{
		genes = new Recall().getList();
		images.removeAll(images);
		imagePanel.removeAll();
		for (int[] gene:genes)
		{
			createLabels(gene);
		}
		scrollPane.getViewport().add(imagePanel);
	}
	
	/**
	 * Creates a pop menu
	 */
	private void createPopupMenu()
	{
		popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Remove from hall of fame");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				for (int i = 0; i < images.size(); i++)
				{
					if (popup.getInvoker() == images.get(i))
					{
						imagePanel.remove(images.get(i));
						images.remove(i);
						genes.remove(i);
						try {
							new Store(genes);
							genes = new Recall().getList();
							images.removeAll(images);
							imagePanel.removeAll();
							for (int[] gene:genes)
							{
								createLabels(gene);
							}
							scrollPane.getViewport().add(imagePanel);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
				}
			}

		});
		popup.add(menuItem);
		menuItem = new JMenuItem("Save to file");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				for (int i = 0; i < images.size(); i++)
				{
					if (popup.getInvoker() == images.get(i))
					{
						new Save(new Grow(genes.get(i)).getCanvas().getImg(), gui);
					}
				}
			}
		});
		popup.add(menuItem);
		menuItem = new JMenuItem("Open in Biomorph Evolver");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				for (int i = 0; i < images.size(); i++)
				{
					if (popup.getInvoker() == images.get(i))
					{
						gui.notifyMeEvo(genes.get(i));
					}
				}
			}
		});
		popup.add(menuItem);
	}
	
	/**
	 * shows the popup menu when an image is right clicked
	 */
	protected void imageRightClicked(MouseEvent evt) {
		popup.show(evt.getComponent(), evt.getX(), evt.getY());

	}
}
