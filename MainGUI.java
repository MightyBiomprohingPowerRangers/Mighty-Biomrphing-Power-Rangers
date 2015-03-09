package MBPR;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

public class MainGUI extends JFrame 
{
	private Container pane;
	
	private JPanel leftPane;
	private JPanel centerPane;
	private JPanel rightPane;
	
	private JLabel currentImageLabel;
	private JLabel historyLabel;
	private JLabel editLabel;
	
	private Grow grow;
	private JLabel currentImage;
	private ArrayList<JLabel> historyImages;
	private JLabel redrawTime;
	private JLabel saveTime;
	
	private JButton generateButton;
	private JButton saveButton;
	
	private ArrayList<JSlider> sliders;
	private ArrayList<JLabel> sliderLabels;
	private int noSliders = 4;

	public MainGUI() 
	{
		initComponents();
	}

	private void initComponents() 
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Biomorph Generator");
		
		createLabels();
		createBiomorphComponents();
		createButtons();
		createSliders();
		createLayout();
	}

	private void createLabels()
	{
		currentImageLabel = new JLabel();
		currentImageLabel.setText("Current Image");
		currentImageLabel.setPreferredSize(new Dimension(310, 20));
		
		editLabel = new JLabel();
		editLabel.setText("Gene Pool");
		editLabel.setPreferredSize(new Dimension(70, 20));

		historyLabel = new JLabel();
		historyLabel.setText("History");
		historyLabel.setPreferredSize(new Dimension(100, 20));
	}
	
	private void createBiomorphComponents()
	{
		grow = new Grow();
		
		currentImage = new JLabel();
		currentImage.setIcon(new ImageIcon(grow.getCanvas().getImg()));
		currentImage.setPreferredSize(new Dimension(500, 500));
		
		historyImages = new ArrayList<JLabel>();
		for (int i = 0; i < 3; i++)
			historyImages.add(new JLabel());
		for (JLabel label: historyImages)
			label.setIcon(null);
		for (JLabel label: historyImages)
			label.setPreferredSize(new Dimension(200, 200));
		
		redrawTime = new JLabel();
		redrawTime.setText("Time:");
		redrawTime.setPreferredSize(new Dimension(170, 20));
		
		saveTime = new JLabel();
		saveTime.setText("Time:");
		saveTime.setPreferredSize(new Dimension(170, 20));
	}
	
	private void createButtons()
	{
		generateButton = new JButton();
		generateButton.setText("Generate");
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				generateButtonActionPerformed(evt);
			}
		});
		generateButton.setPreferredSize(new Dimension(100, 20));
		
		saveButton = new JButton();
		saveButton.setText("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				saveButtonActionPerformed(evt);
			}
		});
		saveButton.setPreferredSize(new Dimension(100, 20));
	}
	
	private void generateButtonActionPerformed(ActionEvent evt) 
	{
		long startTime = (new Date()).getTime();
		
		
		for (int i = 2; i > 0; i--)
			historyImages.get(i).setIcon(historyImages.get(i-1).getIcon());
		historyImages.get(0).setIcon(new ImageIcon(grow.getCanvas().getScaledImage(200, 200)));
		grow.setComplexity(sliders.get(0).getValue());
		grow.setLength(sliders.get(1).getValue());
		grow.setHeight(sliders.get(2).getValue());
		grow.setWidth(sliders.get(3).getValue());
		grow.generate();
		currentImage.setIcon(new ImageIcon(grow.getCanvas().getImg()));
		
		
		long endTime = (new Date()).getTime();
		long elapsedTime = endTime - startTime;
		redrawTime.setText("Generate Time: (" + String.format("%.3f", elapsedTime / 1000.0) + "s) ");
		saveTime.setText("Save Time: (" + String.format("%.3f", elapsedTime / 1000.0) + "s) ");
		//System.out.println("(" + String.format("%.3f", elapsedTime / 1000.0) + "s) ");
	}
	
	private void saveButtonActionPerformed(ActionEvent evt) 
	{
		long startTime = (new Date()).getTime();
		
		
		long endTime = (new Date()).getTime();
		long elapsedTime = endTime - startTime;
		saveTime.setText("Generate Time: (" + String.format("%.3f", elapsedTime / 1000.0) + "s) ");
		//System.out.println("(" + String.format("%.3f", elapsedTime / 1000.0) + "s) ");
	}
	
	private void createSliders()
	{
		sliderLabels = new ArrayList<JLabel>();
		sliderLabels.add(createSliderLabels("Complexity"));
		sliderLabels.add(createSliderLabels("Limb Length"));
		sliderLabels.add(createSliderLabels("Height"));
		sliderLabels.add(createSliderLabels("Width"));
		
		sliders = new ArrayList<JSlider>();
		for (int i = 0; i < noSliders; i++)
			sliders.add(createSlider());
	}
	
	private JLabel createSliderLabels(String label)
	{
		JLabel sliderLabel = new JLabel();
		sliderLabel = new JLabel();
		sliderLabel.setText(label);
		sliderLabel.setPreferredSize(new Dimension(310, 20));
		return sliderLabel;
	}
	
	private JSlider createSlider()
	{
		JSlider slider = new JSlider();
		slider.setMaximum(10);
		slider.setMinimum(0);
		slider.setValue(5);
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		slider.createStandardLabels(1);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setPreferredSize(new Dimension(300, 50));
		return slider;
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
		
		leftPane.add(currentImageLabel);
		leftPane.add(currentImage);
		leftPane.add(generateButton);
		leftPane.add(saveButton);
		leftPane.add(redrawTime);
		
		centerPane.add(editLabel);
		for (int i = 0; i < noSliders; i++)
		{
			centerPane.add(sliderLabels.get(i));
			centerPane.add(sliders.get(i));
		}
		
		centerPane.add(generateButton);
		centerPane.add(saveButton);
		
		rightPane.add(historyLabel);
		for (JLabel label: historyImages)
			rightPane.add(label);
		
		pack();	
	}

	public static void main(String args[]) 
	{
		long startTime = (new Date()).getTime();
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainGUI().setVisible(true);
			}
		});

		
		long endTime = (new Date()).getTime();
		long elapsedTime = endTime - startTime;
//		System.out.println("(" + String.format("%.3f", elapsedTime / 1000.0) + "s) ");
	}
}