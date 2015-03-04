package MBPR;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame implements ActionListener
{
	JPanel panel;
	JButton button;
	
	public static void main(String[] args) 
	{
		Main main = new Main();
		main.setTitle("Biomorph");
		main.setSize(550, 700);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);

	}
	
	public Main() 
	{	
		button = new JButton("Generate");
		button.addActionListener(this);
		
		panel = new JPanel();
		panel.add(button);
		this.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	


}
