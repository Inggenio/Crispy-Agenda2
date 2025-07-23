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
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class MainGUI {

	JFrame window = new JFrame("Kontakt Manager");
	JPanel panel = new JPanel();
	GridBagLayout layout = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	JPanel buttonPanel = new JPanel(new GridLayout(1,3,20,0));

	//Buttons
	JLabel filterLabel = new JLabel("Filtern nach...");
	String[] filterListe = {"Filtern Auswählen","Typ","Nachnahme", "Vorname","Unternehmen", "Adresse","PLZ","Stadt","E-Mail","Telefon Nr.", "Favorite Kontakte"};
	JComboBox filterBox = new JComboBox<>(filterListe);
	JButton filterButton = new JButton("Filter Ausführen >>");
	JButton filterWeg = new JButton("Filter Löschen");
	JTextField filterFeld = new JTextField();

	JButton kontaktHinzu = new JButton("Kontakt Hinzufügen");
	JButton kontaktModifizieren = new JButton("Kontakt Modifizieren");
	JButton kontaktLoschen = new JButton("Kontakt Löschen");

	public void go() {
		//Kontakte im Program Aufladen
		BaseManager.AgendaAufLaden();

		//Color
		Color colorBuchstaben = Color.ORANGE;
		Color colorBackground = Color.DARK_GRAY;
		Color colorForeground = Color.LIGHT_GRAY;
		Color colorForeground2 = Color.ORANGE;

		// Table Config
		KontaktTableModel tabelle = new KontaktTableModel(BaseManager.kontakte);
		JTable kontaktTable = new JTable(tabelle);
		kontaktTable.setFillsViewportHeight(true);
		kontaktTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		TableRowSorter<KontaktTableModel> sorter = new TableRowSorter<>(tabelle);
		kontaktTable.setRowSorter(sorter);


		kontaktTable.setFont(new Font("SansSerif", Font.PLAIN,16));
		kontaktTable.setRowHeight(24);
		kontaktTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));

		//Verschiedene Font:
		//new Font("Segoe UI", Font.PLAIN, 16);
		//new Font("Arial", Font.PLAIN, 16);
		//new Font("Courier New", Font.PLAIN, 16);

		//Kontakt Table Breite:
		kontaktTable.getColumnModel().getColumn(0).setPreferredWidth(70);  // Typ
		kontaktTable.getColumnModel().getColumn(1).setPreferredWidth(120); // Nachname
		kontaktTable.getColumnModel().getColumn(2).setPreferredWidth(80); // Vorname
		kontaktTable.getColumnModel().getColumn(3).setPreferredWidth(120); // Unternehmen
		kontaktTable.getColumnModel().getColumn(4).setPreferredWidth(200); // Adresse
		kontaktTable.getColumnModel().getColumn(5).setPreferredWidth(80);  // PLZ
		kontaktTable.getColumnModel().getColumn(6).setPreferredWidth(150); // Stadt
		kontaktTable.getColumnModel().getColumn(7).setPreferredWidth(250); // E-Mail
		kontaktTable.getColumnModel().getColumn(8).setPreferredWidth(120); // Telefon
		kontaktTable.getColumnModel().getColumn(9).setPreferredWidth(25);  // Favorit

		//Key Binding:
		InputMap im = kontaktTable.getInputMap(JTable.WHEN_FOCUSED);
		ActionMap am = kontaktTable.getActionMap();

		im.put(KeyStroke.getKeyStroke("DELETE"), "deleteRow");
		am.put("deleteRow", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = kontaktTable.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(window, "Bitte wählen Sie einen Kontakt zum Löschen aus.", "Kein Kontakt ausgewählt", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int modelRow = kontaktTable.convertRowIndexToModel(selectedRow);
				KontaktTableModel model = (KontaktTableModel) kontaktTable.getModel();
				Kontakt kontaktToDelete = model.getKontaktAt(modelRow);

				int confirm = JOptionPane.showConfirmDialog(window,
						"Möchten Sie den Kontakt wirklich löschen?",
						"Kontakt löschen",
						JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					BaseManager.kontakte.remove(kontaktToDelete);
					model.refresh();
				}
			}
		});

		//Key Binding Filter
		filterFeld.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "applyFilter");
		filterFeld.getActionMap().put("applyFilter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filterButton.doClick();
			}
		});
		// ESCAPE zum Filter löschen
		InputMap imFilter = filterFeld.getInputMap(JComponent.WHEN_FOCUSED);
		ActionMap amFilter = filterFeld.getActionMap();

		imFilter.put(KeyStroke.getKeyStroke("ESCAPE"), "clearFilter");
		amFilter.put("clearFilter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filterFeld.setText("");
				sorter.setRowFilter(null);
			}
		});



		//Tabelle Farben
		kontaktTable.setBackground(colorForeground);
		kontaktTable.getTableHeader().setBackground(colorBackground);
		kontaktTable.getTableHeader().setForeground(colorBuchstaben);
		kontaktTable.setGridColor(colorBackground);

		// Scroll Pane
		JScrollPane scrollPane = new JScrollPane(kontaktTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(1000,400));
		scrollPane.setBorder(BorderFactory.createLineBorder(colorBackground,20));

		//Filter Bereich
		filterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		filterLabel.setForeground(colorBuchstaben);
		filterBox.setBackground(colorBackground);
		filterBox.setForeground(colorForeground2);
		filterButton.setBackground(colorBackground);
		filterButton.setForeground(colorBuchstaben);
		filterFeld.setBackground(colorForeground);
		filterWeg.setBackground(colorBackground);
		filterWeg.setForeground(colorBuchstaben);


		//Panel Definition
		panel.setBackground(colorBackground);
		panel.setLayout(layout);

		//Button Panel für Hinzufügen Modifizieren und Löschen
		buttonPanel.setBackground(colorBackground);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
		kontaktHinzu.setBackground(colorBackground);
		kontaktHinzu.setForeground(colorBuchstaben);
		kontaktModifizieren.setBackground(colorBackground);
		kontaktModifizieren.setForeground(colorBuchstaben);
		kontaktLoschen.setBackground(colorBackground);
		kontaktLoschen.setForeground(colorBuchstaben);

		//Button Panel Config
		buttonPanel.add(kontaktHinzu);
		buttonPanel.add(kontaktModifizieren);
		buttonPanel.add(kontaktLoschen);

		//Layout Config
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 0.2;
		gbc.gridwidth = 5;

		gbc.ipadx = 10;
		gbc.ipady = 10;

		//Filter Label
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		panel.add(filterLabel,gbc);

		//Filter ComboBox
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 0.2;
		panel.add(filterBox,gbc);

		//Filter TextFeld
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 0.4;
		panel.add(filterFeld,gbc);

		//Filter Ausführen
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		panel.add(filterButton,gbc);

		//Filter Weg
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		panel.add(filterWeg,gbc);

		//Leer Zeile
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 5;
		gbc.weighty = 0;
		panel.add(Box.createVerticalStrut(10), gbc);

		// Scroll Panel mit GridBag
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 5;
		gbc.gridheight = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(scrollPane, gbc);

		//Kontakt Management Knöpfe
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 5;
		gbc.weightx = 1.0;
		gbc.weighty = 0;
		panel.add(buttonPanel, gbc);


		// Window Definition
		window.add(panel);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setSize(new Dimension(1000, 800));
		window.setVisible(true);
		//Window Listener um Daten zur Datenbank aktualisieren
		window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				int option = JOptionPane.showConfirmDialog(
						window,
						"Möchten Sie die Änderungen beim Schließen des Programms speichern?",
						"Programm beenden",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE
				);

				if (option == JOptionPane.YES_OPTION) {
					BaseManager.AgendaAbladen();// Database Speichern
					System.exit(0); // Exit Program + Speichern
				} else if (option == JOptionPane.NO_OPTION) {
					System.exit(0); // Exit Program ohne Speichern
				}
			}
		});
		kontaktHinzu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Für Normale KontaktHinzu Klass

				//NeueKontakt neuKontakt = new NeueKontakt(tabelle); // tabelle ist der Instanz der KontaktTabelleModel Klasse
				//neuKontakt.go();


				//Für NeuModiKontakt
				NeuModiKontakt neuKontakt = new NeuModiKontakt(tabelle); // tabelle ist der Instanz der KontaktTabelleModel Klasse
				neuKontakt.go();


			}
		});
		kontaktModifizieren.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = kontaktTable.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(window, "Bitte wählen Sie einen Kontakt zum Bearbeiten aus.", "Kein Kontakt ausgewählt", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int modelRow = kontaktTable.convertRowIndexToModel(selectedRow);
				KontaktTableModel model = (KontaktTableModel) kontaktTable.getModel();
				Kontakt kontakt = model.getKontaktAt(modelRow);

				//ModKontakt modFenster = new ModKontakt(model, kontakt);
				//modFenster.go();

				NeuModiKontakt modFenster = new NeuModiKontakt(model, kontakt);
				modFenster.go();
			}
		});
		kontaktLoschen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = kontaktTable.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(window, "Bitte wählen Sie einen Kontakt zum Löschen aus.", "Kein Kontakt ausgewählt", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int modelRow = kontaktTable.convertRowIndexToModel(selectedRow);
				KontaktTableModel model = (KontaktTableModel) kontaktTable.getModel();
				Kontakt kontaktToDelete = model.getKontaktAt(modelRow);


				int confirm = JOptionPane.showConfirmDialog(window,
						"Möchten Sie den Kontakt wirklich löschen?",
						"Kontakt löschen",
						JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					BaseManager.kontakte.remove(kontaktToDelete);
					model.refresh();
				}
			}
		});
		filterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String kriterium = filterBox.getSelectedItem().toString();
				String text = filterFeld.getText().trim();

				if (kriterium.equals("Filtern Auswählen") || text.isEmpty()) {
					sorter.setRowFilter(null);
					return;
				}

				int columnIndex = switch (kriterium) {
					case "Typ" -> 0;
					case "Nachnahme" -> 1;
					case "Vorname" -> 2;
					case "Unternehmen" -> 3;
					case "Adresse" -> 4;
					case "PLZ" -> 5;
					case "Stadt" -> 6;
					case "E-Mail" -> 7;
					case "Telefon Nr." -> 8;
					case "Favorite Kontakte" -> 9;
					default -> -1;
				};

				if (columnIndex == -1) return;

				if (kriterium.equals("Favorite Kontakte")) {
					// Favorite Filtern
					sorter.setRowFilter(RowFilter.regexFilter("★", columnIndex));
				} else {
					// Filter eingegebene Text
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text), columnIndex));
				}
			}
		});
		filterWeg.addActionListener(e -> {
			filterFeld.setText("");
			sorter.setRowFilter(null);
		});
	}
}



