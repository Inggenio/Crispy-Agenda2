import javax.swing.*;
import java.awt.*;

public class MainGUI {

	JFrame window = new JFrame("Agenda");
	JPanel panel = new JPanel();
	GridBagLayout layout = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	JButton sendButton = new JButton("xxxxx");

	//Buttons

	public void go(){

		//Layout
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5,5,5,5);
		gbc.weightx = 0.2;
		gbc.gridwidth = 5;
		gbc.ipadx = 10;
		gbc.ipady = 10;

		//Panel Deifnition
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(layout);

		//Buttons und Felder
		//Label Alignment
		/*
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVorname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdresse.setHorizontalAlignment(SwingConstants.RIGHT);
		typLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefon.setHorizontalAlignment(SwingConstants.RIGHT);

		 */

		/*
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		panel.add(typLabel,gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(typ,gbc);

		 */








		gbc.insets = new Insets(15,5,5,5);


		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		panel.add(sendButton, gbc);

		// Window Definition
		window.add(panel);
		window.setSize(new Dimension(800,600));
		window.setVisible(true);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



	}

}
