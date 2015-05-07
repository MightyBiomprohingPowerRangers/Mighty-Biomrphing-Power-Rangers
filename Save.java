package MBPR;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Handles the saving of images
 * @author MBPR
 *
 */
public class Save 
{

	public Save(BufferedImage image, Component frame)
	{
		JFileChooser filechooser = new JFileChooser("C:\\Users\\carlmendoza\\Desktop\\Saving\\Images");
		filechooser.setDialogTitle("Save Biomorph image");
		int res = filechooser.showSaveDialog(null);

		if(res == JFileChooser.APPROVE_OPTION){
			File file = new File(filechooser.getSelectedFile() + ".png");
			try{
				ImageIO.write(image, "png", file);
				JOptionPane.showMessageDialog(frame, "Image saved!");
			}catch (IOException ex){
				JOptionPane.showMessageDialog(frame, "Oops! Something gone wrong while saving." + ex.getMessage(),
						"Error!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
