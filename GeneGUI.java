package MBPR;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Creates a frame which allows you to change each gene value specifically
 * @author MBPR
 *
 */
public class GeneGUI extends JFrame 
{
	private Container pane;
	
	private JPanel leftPane;
	private JPanel centerPane;
	private JPanel rightPane;
	private JPanel buttonPane;
	
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
	private JButton biomorphEvolverButton;
	
	private int[] initialGenes;
	private int[] loadGenes;
	private ArrayList<JSlider> sliders;
	private ArrayList<JLabel> sliderLabels;
	private int noSliders = 5;
	private ColourPicker colourPicker1;
	private ColourPicker colourPicker2;
	
	private EvolutionView panel;
	private GeneGUI geneView;
	
	private int currentImageX = 480;
	private int currentImageY = 480;
	
	private int historyImageX = 200;
	private int historyImageY = 200;


	/**
	 * Initialises all the components 
	 * @param panel
	 */
	public GeneGUI(EvolutionView panel) 
	{
		this.panel = panel;
		loadGenes = this.panel.getCurrentGene();
		initComponents();
	}

	/**
	 * helper to the initialisation
	 */
	private void initComponents() 
	{
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Biomorph Generator");
		
		setPreferredSize(new Dimension(1150, 500));
		setResizable(false);
		
		createLabels();
		createBiomorphComponents();
		createButtons();
		createSliders();
		createLayout();
	}

	/**
	 * creates the labels used for presentation
	 */
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
	
	/**
	 * creates the components required to evolve an image
	 */
	private void createBiomorphComponents()
	{
		grow = new Grow(loadGenes);
		initialGenes = loadGenes;
		
		currentImage = new JLabel();
		currentImage.setIcon(new ImageIcon(grow.getCanvas().getScaledImage(currentImageX,currentImageY)));
		currentImage.setPreferredSize(new Dimension(currentImageX, currentImageY));
		
		historyImages = new ArrayList<JLabel>();
		for (int i = 0; i < 3; i++)
			historyImages.add(new JLabel());
		for (JLabel label: historyImages)
			label.setIcon(null);
		for (JLabel label: historyImages)
			label.setPreferredSize(new Dimension(historyImageX, historyImageY));
		
		redrawTime = new JLabel();
		redrawTime.setText("Time:");
		redrawTime.setPreferredSize(new Dimension(170, 20));
		
		saveTime = new JLabel();
		saveTime.setText("Time:");
		saveTime.setPreferredSize(new Dimension(170, 20));
		colourPicker1 = new ColourPicker(initialGenes[5]);
		colourPicker2 = new ColourPicker(initialGenes[6]);
	}
	
	/**
	 * Creates the buttons
	 */
	private void createButtons()
	{
		generateButton = new JButton();
		generateButton.setText("Generate");
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				createNewBiomorph(evt);
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
		
		biomorphEvolverButton = new JButton();
		biomorphEvolverButton.setText("Open in Biomorph Evolver");
		geneView = this;
		biomorphEvolverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				panel.notifyMeGene(loadGenes);
				geneView.dispose();
				
			}
		});
		biomorphEvolverButton.setPreferredSize(new Dimension(200, 20));
	}
	
	/**
	 * Resets the genes and generates the image
	 * @param evt
	 */
	private void createNewBiomorph(ActionEvent evt) 
	{
		for (int i = 2; i > 0; i--)
			historyImages.get(i).setIcon(historyImages.get(i-1).getIcon());
		historyImages.get(0).setIcon(new ImageIcon(grow.getCanvas().getScaledImage(historyImageX, historyImageY)));
		grow.setComplexity(sliders.get(0).getValue());
		grow.setLength(sliders.get(1).getValue());
		grow.setHeight(sliders.get(2).getValue());
		grow.setWidth(sliders.get(3).getValue());
		grow.setSeed(sliders.get(4).getValue());
		grow.setRgb1(colourPicker1.getColour());
		grow.setRgb2(colourPicker2.getColour());
		grow.generate();
		loadGenes = grow.getCurrentGenes();
		currentImage.setIcon(new ImageIcon(grow.getCanvas().getScaledImage(currentImageX,currentImageY)));
	}
	
	/**
	 * Saves the current image
	 * @param evt
	 */
	private void saveButtonActionPerformed(ActionEvent evt) 
	{
		new Save(new Grow(loadGenes).getCanvas().getImg(), this);
	}
	
	/**
	 * Creates all of the slider components
	 */
	private void createSliders()
	{
		sliderLabels = new ArrayList<JLabel>();
		sliderLabels.add(createSliderLabels("Complexity"));
		sliderLabels.add(createSliderLabels("Limb Length"));
		sliderLabels.add(createSliderLabels("Height"));
		sliderLabels.add(createSliderLabels("Width"));
		sliderLabels.add(createSliderLabels("Seed"));
		
		sliders = new ArrayList<JSlider>();
			sliders.add(createSlider(20, 1, initialGenes[0], 3, 1));
			sliders.add(createSlider(10, 1, initialGenes[1], 3, 1));
			sliders.add(createSlider(8, 2, initialGenes[2], 2, 1));
			sliders.add(createSlider(8, 2, initialGenes[3], 2, 1));
			sliders.add(createSlider(10, 0, initialGenes[4], 5, 1));
	}
	
	/**
	 * creates presentation components for the sliders
	 * @param label
	 * @return
	 */
	private JLabel createSliderLabels(String label)
	{
		JLabel sliderLabel = new JLabel();
		sliderLabel = new JLabel();
		sliderLabel.setText(label);
		sliderLabel.setPreferredSize(new Dimension(310, 20));
		return sliderLabel;
	}
	
	/**
	 * creates a single slider 
	 * @param max
	 * @param min
	 * @param val
	 * @param maj
	 * @param mn
	 * @return
	 */
	private JSlider createSlider(int max, int min, int val, int maj, int mn)
	{
		JSlider slider = new JSlider();
		slider.setMaximum(max);
		slider.setMinimum(min);
		slider.setValue(val);
		slider.setMajorTickSpacing(maj);
		slider.setMinorTickSpacing(mn);
		slider.createStandardLabels(1);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setPreferredSize(new Dimension(300, 50));
		return slider;
	}

	/**
	 * adds all the components to the frame and organises it
	 */
	private void createLayout() 
	{
		pane = getContentPane();
		
		leftPane = new JPanel();
		centerPane = new JPanel();
		rightPane = new JPanel();
		buttonPane = new JPanel();
		
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.PAGE_AXIS));
		pane.add(leftPane, BorderLayout.LINE_START);
		
		centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.PAGE_AXIS));
		pane.add(centerPane, BorderLayout.CENTER);
		
		rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.PAGE_AXIS));
		pane.add(rightPane, BorderLayout.LINE_END);
		
		leftPane.add(currentImageLabel);
		leftPane.add(currentImage);
		leftPane.add(redrawTime);
		
		centerPane.add(editLabel);
		for (int i = 0; i < noSliders; i++)
		{
			centerPane.add(sliderLabels.get(i));
			centerPane.add(sliders.get(i));
		}
		centerPane.add(colourPicker1);
		centerPane.add(colourPicker2);
		
		buttonPane.setLayout(new FlowLayout());
		centerPane.add(buttonPane);
		
		buttonPane.add(generateButton);
		buttonPane.add(saveButton);
		buttonPane.add(biomorphEvolverButton);
		
		rightPane.add(historyLabel);
		for (JLabel label: historyImages)
			rightPane.add(label);
		
		pack();	
	}
}