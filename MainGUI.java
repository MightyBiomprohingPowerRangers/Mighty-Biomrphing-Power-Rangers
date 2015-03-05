package MBPR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI extends JFrame 
{

//	private JLabel celsiusLabel;
//	private JButton generateButton;
//	private JLabel fahrenheitLabel;
//	private JTextField tempTextField;
		
	private JLabel currentImageLabel;
	private JLabel currentImage;
	private JLabel editLabel;
	private JLabel historyLabel;
	private GUI gui;
	private JButton generateButton;
	private JLabel historyImage1;
	
	private Container pane;
	
	private JPanel leftPane;
	private JPanel centerPane;
	private JPanel rightPane;

	public MainGUI() 
	{
		initComponents();
	}

	private void initComponents() 
	{
		
		currentImageLabel = new JLabel();
		currentImage = new JLabel();
		editLabel = new JLabel();
		historyLabel = new JLabel();
		gui = new GUI();
		generateButton = new JButton();
		historyImage1 = new JLabel();
		
		pane = getContentPane();
		
		leftPane = new JPanel();
		centerPane = new JPanel();
		rightPane = new JPanel();
		
//		tempTextField = new JTextField();
//		celsiusLabel = new JLabel();
//		generateButton = new JButton();
//		fahrenheitLabel = new JLabel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Biomorph Generator");
		
		currentImageLabel.setText("Current Image");
		editLabel.setText("Edit Section");
		historyLabel.setText("History");
		historyImage1.setIcon(null);
		currentImage.setIcon(new ImageIcon(gui.getCanvas().getImg()));
		
		generateButton.setText("Generate");
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				generateButtonActionPerformed(evt);
			}
		});
		
//		celsiusLabel.setText("Celsius");
//
//		generateButton.setText("Generate");
//		generateButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				generateButtonActionPerformed(evt);
//			}
//		});
//
//		fahrenheitLabel.setText("Fahrenheit");
		
		BorderLayout layout2 = new BorderLayout(10,10);
		
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.PAGE_AXIS));
		pane.add(leftPane, BorderLayout.LINE_START);
		
		centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.PAGE_AXIS));
		pane.add(centerPane, BorderLayout.CENTER);
		
		rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.PAGE_AXIS));
		pane.add(rightPane, BorderLayout.LINE_END);
		
		currentImageLabel.setPreferredSize(new Dimension(100, 20));
		editLabel.setPreferredSize(new Dimension(100, 20));
		historyLabel.setPreferredSize(new Dimension(100, 20));
		currentImage.setPreferredSize(new Dimension(500, 500));
		historyImage1.setPreferredSize(new Dimension(200, 200));
		
		leftPane.add(currentImageLabel);
		leftPane.add(currentImage);
		leftPane.add(generateButton);
		
		centerPane.add(editLabel);
		
		rightPane.add(historyLabel);
		rightPane.add(historyImage1);

//		GroupLayout layout = new GroupLayout(getContentPane());
//		getContentPane().setLayout(layout);
//		layout.setHorizontalGroup(
//				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//				.addGroup(layout.createSequentialGroup()
//						.addContainerGap()
//						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//								.addGroup(layout.createSequentialGroup()
//										.addComponent(tempTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//										.addComponent(celsiusLabel))
//										.addGroup(layout.createSequentialGroup()
//												.addComponent(generateButton)
//												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//												.addComponent(fahrenheitLabel)))
//												.addContainerGap(27, Short.MAX_VALUE))
//				);
//
//		layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {generateButton, tempTextField});
//
//		layout.setVerticalGroup(
//				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//				.addGroup(layout.createSequentialGroup()
//						.addContainerGap()
//						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//								.addComponent(tempTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//								.addComponent(celsiusLabel))
//								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//										.addComponent(generateButton)
//										.addComponent(fahrenheitLabel))
//										.addContainerGap(21, Short.MAX_VALUE))
//				);
		pack();
	}

	private void generateButtonActionPerformed(ActionEvent evt) 
	{
		historyImage1.setIcon(new ImageIcon(gui.getCanvas().getScaledImage(200, 200)));
		gui.generate();
		currentImage.setIcon(new ImageIcon(gui.getCanvas().getImg()));
		
	}

	public static void main(String args[]) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainGUI().setVisible(true);
			}
		});
	}
}