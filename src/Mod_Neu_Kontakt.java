/*
 MIT License

 Copyright (c) 2025 [Tu Nombre o Usuario GitHub]

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mod_Neu_Kontakt {

	private KontaktTableModel tableModel; // Referenz an Original-Instanz der Tabelle
	private Kontakt kontakt;
	private boolean istModifikation;

	public Mod_Neu_Kontakt(KontaktTableModel model, Kontakt kontakt) {
		this.tableModel = model;
		this.kontakt = kontakt;
		this.istModifikation = true;
	}
	public Mod_Neu_Kontakt(KontaktTableModel model) {
		this.tableModel = model;
		this.kontakt = new Kontakt(); // leeres Objekt
		this.istModifikation = false;
	}

	JFrame window = new JFrame("Neue Eintrage");
	JPanel panel = new JPanel();
	GridBagLayout layout = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	JButton sendButton = new JButton("Eintage Bestätigen");

	//Buttons
	//Anrede
	JComboBox<KontaktTyp> typBox = new JComboBox<>(KontaktTyp.values());
	JLabel lblTyp = new JLabel("Kontakt Typ");

	//Name
	JLabel lblName = new JLabel("Name");
	JTextField textName = new JTextField(20);

	//Vorname
	JLabel lblVorname = new JLabel("Vorname");
	JTextField textVorname = new JTextField(20);

	//Unternehmen
	JLabel lblFirma = new JLabel("Unternehmen");
	JTextField textFirma = new JTextField(20);

	//Adresse
	JLabel lblAdresse = new JLabel("Adresse");
	JTextField textAdresse = new JTextField(20);

	//PLZ
	JLabel lblPlz = new JLabel("PLZ");
	JTextField textPlz = new JTextField(20);

	//Stadt
	JLabel lblStadt = new JLabel("Stadt");
	JTextField textStadt = new JTextField(20);

	//Email
	JLabel lblEmail = new JLabel("E-Mail");
	JTextField textEmail = new JTextField(20);

	//Telefonnummer
	JLabel lblTelefon = new JLabel("Telefonnummer");
	JTextField textTelefon = new JTextField(20);

	//CheckBox
	JLabel lblFavorit = new JLabel("Favorit Kontakt?");
	JCheckBox checkBox = new JCheckBox();

	public void go(){
		//Layout
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5,5,5,5);
		gbc.weightx = 0.2;
		gbc.gridwidth = 5;
		gbc.ipadx = 10;
		gbc.ipady = 10;

		//Panel Definition
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(layout);

		//Buttons und Felder
		//Label Alignment
		lblTyp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVorname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirma.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdresse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlz.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStadt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefon.setHorizontalAlignment(SwingConstants.RIGHT);

		lblTyp.setForeground(Color.ORANGE);
		lblName.setForeground(Color.ORANGE);
		lblVorname.setForeground(Color.ORANGE);
		lblFirma.setForeground(Color.ORANGE);
		lblAdresse.setForeground(Color.ORANGE);
		lblPlz.setForeground(Color.ORANGE);
		lblStadt.setForeground(Color.ORANGE);
		lblEmail.setForeground(Color.ORANGE);
		lblTelefon.setForeground(Color.ORANGE);
		lblFavorit.setForeground(Color.ORANGE);

		//TypBox

		checkBox.setOpaque(true);
		checkBox.setBackground(Color.darkGray);
		checkBox.setForeground(Color.darkGray);

		sendButton.setForeground(Color.orange);
		sendButton.setBackground(Color.DARK_GRAY);

		//Kontakttyp
		typBox.setBackground(Color.GRAY);
		typBox.setSelectedIndex(0);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		panel.add(lblTyp,gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(typBox,gbc);

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

		//Firma
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		panel.add(lblFirma,gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(textFirma,gbc);

		//Adresse
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		panel.add(lblAdresse,gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(textAdresse,gbc);

		//PLZ
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		panel.add(lblPlz,gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(textPlz,gbc);

		//Stadt
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		panel.add(lblStadt,gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(textStadt,gbc);

		//Email
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(lblEmail,gbc);

		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(textEmail,gbc);

		//Telefonnummer
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(lblTelefon,gbc);

		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1;
		panel.add(textTelefon,gbc);

		gbc.insets = new Insets(15,5,5,5);

		//Favorit Kontakt?
		gbc.gridx = 0;
		gbc.gridy = 8;
		panel.add(lblFavorit,gbc);

		//Checkbox
		checkBox.setBackground(Color.LIGHT_GRAY);
		checkBox.setHorizontalAlignment(SwingConstants.RIGHT);
		gbc.gridx = 1;
		gbc.gridy = 8;
		panel.add(checkBox,gbc);

		gbc.insets = new Insets(15,5,5,5);

		gbc.gridx = 1;
		gbc.gridy = 9;
		gbc.gridwidth = 2;
		panel.add(sendButton, gbc);

		// Window Definition
		window.add(panel);
		window.setSize(new Dimension(800,700));
		window.setVisible(true);
		window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		if(istModifikation){
			//Felder zum Kontakt
			typBox.setSelectedItem(kontakt.getTyp());
			textName.setText(kontakt.getNachname());
			textVorname.setText(kontakt.getVorname());
			textFirma.setText(kontakt.getFirma());
			textAdresse.setText(kontakt.getAdresse());
			textPlz.setText(kontakt.getPlz());
			textStadt.setText(kontakt.getStadt());
			textEmail.setText(kontakt.geteMail());
			textTelefon.setText(kontakt.getTelefon());
			checkBox.setSelected(kontakt.isFavorit());
		}
		//Send Button Action
		checker();
		// ENTER-Taste: Formular absenden
		panel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(KeyStroke.getKeyStroke("ENTER"), "submit");

		panel.getActionMap().put("submit", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendButton.doClick();
			}
		});
		// ESC-Taste schließt das Fenster
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
		panel.getActionMap().put("escape", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.dispose(); // Fenster zu
			}
		});
	}
	private void checker(){
		ActionListener sendFormular = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KontaktTyp typ = (KontaktTyp) typBox.getSelectedItem();
				String name = textName.getText().trim();
				String vorname = textVorname.getText().trim();
				String firma = textFirma.getText().trim();
				String adresse = textAdresse.getText().trim();
				String plz = textPlz.getText().trim();
				String stadt = textStadt.getText().trim();
				String email = textEmail.getText().trim();
				String telefon = textTelefon.getText().trim();
				boolean favorit = checkBox.isSelected();

				// Validierung: Zumindest ein Feld muss nicht leer sein
				if (email.isEmpty() && telefon.isEmpty()) {
					JOptionPane.showMessageDialog(window, "Eine E-Mail Adresse oder Telefonnummer muss ausgefüllt sein", "Fehler", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Validierung: Zumindest Name, Vorname oder Unternehmen muss nicht leer sein
				if (name.isEmpty() && vorname.isEmpty() && firma.isEmpty()) {
					JOptionPane.showMessageDialog(window, "Bitte geben Sie mindestens Name, Vorname oder Unternehmen ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//Warnings:
				String warnung = "";
				if (name.isEmpty()) warnung += "- Name Feld ist leer\n";
				if (vorname.isEmpty()) warnung += "- Vorname Feld ist leer\n";
				if (firma.isEmpty()) warnung += "- Firma Feld ist leer\n";
				if (adresse.isEmpty()) warnung += "- Adresse Feld ist leer\n";
				if (!isValidPlz(plz)) warnung += "- PLZ muss 5 Zahlen lang sein\n";
				if (stadt.isEmpty()) warnung += "- Adresse Feld ist leer\n";
				if (telefon.isEmpty()) warnung += "- Telefonnummer Feld ist leer\n";
				if (!isValidTelefon(telefon)) warnung += "- Überprüfen Telefonnummer(zu Kurz oder mit Buchstaben\n";
				if (email.isEmpty()) warnung += "- E-Mail Feld ist leer\n";
				if (!isValidEmail(email)) warnung += "- Überprüfen Sie das Format der E-Mail-Adresse\n";

				String nachricht = warnung.isEmpty()
						? "Möchten Sie den Kontakt speichern?"
						: "Folgende Felder sind leer oder unvollständig:\n" + warnung + "\nTrotzdem speichern?";

				int option = JOptionPane.showConfirmDialog(
						window,
						nachricht,
						"Kontakt speichern",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE
				);

				if (option == JOptionPane.YES_OPTION) {
					kontakt.setTyp(typ);
					kontakt.setNachname(name);
					kontakt.setVorname(vorname);
					kontakt.setFirma(firma);
					kontakt.setAdresse(adresse);
					kontakt.setPlz(plz);
					kontakt.setStadt(stadt);
					kontakt.seteMail(email);
					kontakt.setTelefon(telefon);
					kontakt.setFavorit(favorit);

					// Endet Kontakt-Hinzufügen/Modifizieren
					if(!istModifikation){
						BaseManager.kontakte.add(kontakt);
					}
					tableModel.refresh();
					window.dispose();
				} else {
					//Zurück ins-SubProgramm
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
	private boolean isValidTelefon(String telefonnummer){
		return telefonnummer.matches("^[0-9 +]{7,20}$");
	}
	private boolean isValidPlz(String plz){
		return (plz.length() <= 5 && plz.length() >= 3);
	}

}
