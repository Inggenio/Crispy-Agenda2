import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Neueintraege {

	JFrame window = new JFrame("Neue Eintrage");
	JPanel panel = new JPanel();
	GridBagLayout layout = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	JButton sendButton = new JButton("Eintage Bestätigen");

	//Buttons
	//Anrede
	String[] eintrageTyp = {"Bitte wählen","Kunde","Lieferant"};
	JComboBox typ = new JComboBox<>(eintrageTyp);
	JLabel typLabel = new JLabel("Kontakt Typ");

	//Name
	JLabel lblName = new JLabel("Name");
	JTextField textName = new JTextField(20);

	//Vorname
	JLabel lblVorname = new JLabel("Vorname");
	JTextField textVorname = new JTextField(20);

	//Email
	JLabel lblEmail = new JLabel("E-Mail");
	JTextField textEmail = new JTextField(20);

	//Adresse
	JLabel lblAdresse = new JLabel("Adresse");
	JTextField textAdresse = new JTextField(20);

	//Telefonnummer
	JLabel lblTelefon = new JLabel("Telefonnummer");
	JTextField textTelefon = new JTextField(20);

	JLabel lblGeneral = new JLabel("Favorit Kontakt?");
	JCheckBox checkBox = new JCheckBox();

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
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVorname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdresse.setHorizontalAlignment(SwingConstants.RIGHT);
		typLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefon.setHorizontalAlignment(SwingConstants.RIGHT);

		//Kontakttyp
		typ.setBackground(Color.GRAY);
		typ.setSelectedIndex(0);

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

		//Name
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		panel.add(lblName,gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(textName,gbc);

		//Vorname
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		panel.add(lblVorname,gbc);

		gbc.gridx = 4;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(textVorname,gbc);

		//Adresse
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		panel.add(lblAdresse,gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(textAdresse,gbc);

		//Email
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(lblEmail,gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(textEmail,gbc);

		//Telefonnnummer
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(lblTelefon,gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(textTelefon,gbc);

		gbc.insets = new Insets(15,5,5,5);

		//Favorit Kontakt?
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(lblGeneral,gbc);

		//Checkbox
		checkBox.setBackground(Color.LIGHT_GRAY);
		checkBox.setHorizontalAlignment(SwingConstants.RIGHT);
		gbc.gridx = 1;
		gbc.gridy = 5;
		panel.add(checkBox,gbc);

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

		//Send Button Action
		checker();
	}
	private void checker(){
		ActionListener sendFormular = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textName.getText().trim();
				String vorname = textVorname.getText().trim();
				String email = textEmail.getText().trim();
				String telefon = textTelefon.getText().trim();


				//E-Mail RegEx zum Checken
				if (!isValidEmail(email)) {
					JOptionPane.showMessageDialog(window, "Check das E-Mail-Adresse!", "E-Mail Adresse Fehler", JOptionPane.ERROR_MESSAGE);
					return;
				}


				System.out.println("Name Ok?: " + !name.isEmpty() + " - " + name);
				System.out.println("Vorname Ok?: " + !vorname.isEmpty() + " - " + vorname);
				System.out.println("Emailcheck: "+ isValidEmail(email)+ ": " + email);
				System.out.println("Favorit?: " + checkBox.isSelected());

				if (name.isEmpty() &&
						vorname.isEmpty() &&
						email.isEmpty() &&
						telefon.isEmpty()
				){

					JOptionPane.showMessageDialog(window, "Mindestens ein Feld muss nicht leer sein", "Erfolg", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(window, "Bitte füllen Sie alle Felder korrekt aus.", "Fehler", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		};
		sendButton.addActionListener(sendFormular);
	}
	private boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
