import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {

	JFrame window = new JFrame("Kontakt Manager");
	JPanel panel = new JPanel();
	GridBagLayout layout = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	JPanel buttonPanel = new JPanel(new BorderLayout());

	//Buttons
	JLabel filterLabel = new JLabel("Filtern nach...");
	String[] filterListe = {"Filtern Auswählen","Typ","Nachnahme", "Vorname","Unternehmen", "E-Mail","Telefon Nr.", "Favorite Kontakte"};
	JComboBox filterBox = new JComboBox<>(filterListe);
	JButton filterButton = new JButton("Filter Ausführen");

	JButton kontaktHinzu = new JButton("Kontakt Hinzufügen");
	JButton kontaktLoschen = new JButton("Kontakt Löschen");

	public void go() {
		//Kontakte im Program Aufladen
		BaseManager.AgendaAufLaden();

		// Table Config
		KontaktTableModel tabelle = new KontaktTableModel(BaseManager.kontakte);
		JTable kontaktTable = new JTable(tabelle);
		kontaktTable.setFillsViewportHeight(true);
		kontaktTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		//Tabelle Farben
		kontaktTable.setBackground(Color.lightGray);
		kontaktTable.getTableHeader().setBackground(Color.DARK_GRAY);
		kontaktTable.getTableHeader().setForeground(Color.ORANGE);
		kontaktTable.setGridColor(Color.DARK_GRAY);

		// Scroll Pane
		JScrollPane scrollPane = new JScrollPane(kontaktTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(700,400));

		filterLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		filterLabel.setForeground(Color.ORANGE);
		filterBox.setBackground(Color.DARK_GRAY);
		filterBox.setForeground(Color.ORANGE);
		filterButton.setBackground(Color.darkGray);
		filterButton.setForeground(Color.orange);

		//Panel Definition
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(layout);

		//Button Panel für Hinzufügen und Löschen
		buttonPanel.setBackground(Color.darkGray);
		kontaktLoschen.setBackground(Color.darkGray);
		kontaktLoschen.setForeground(Color.ORANGE);
		kontaktHinzu.setBackground(Color.darkGray);
		kontaktHinzu.setForeground(Color.ORANGE);
		buttonPanel.add(kontaktHinzu, BorderLayout.WEST);
		buttonPanel.add(kontaktLoschen, BorderLayout.EAST);

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

		//Filter Box
		gbc.gridx = 1;
		panel.add(filterBox,gbc);

		//Filter Ausführen
		gbc.gridx = 2;
		panel.add(filterButton,gbc);

		// Scroll Panel mit GridBag
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 5;
		gbc.gridheight = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(scrollPane, gbc);

		//Kontakt Management
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 5;
		gbc.weightx = 1.0;
		gbc.weighty = 0;
		panel.add(buttonPanel, gbc);


		//Panel Definition
		//gbc.insets = new Insets(15, 5, 5, 5);

		// Window Definition
		window.add(panel);
		window.setSize(new Dimension(800, 600));
		window.setVisible(true);
		//Window Listener um Daten zur Datenbank aktualisieren
		window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				int option = JOptionPane.showConfirmDialog(
						window,
						"Möchten Sie die Änderungen speichern und das Programm schließen?",
						"Programm beenden",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE
				);

				if (option == JOptionPane.YES_OPTION) {
					Main.close(); // Database Speichern
					System.exit(0); // Exit Program + Speichern
				} else if (option == JOptionPane.NO_OPTION) {
					System.exit(0); // Exit Program ohne Speichern
				}
			}
		});
		kontaktHinzu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Neueintraege neuKontakt = new Neueintraege(tabelle); // tabelle ist der Instanz der KontaktTabelleModel Klasse
				neuKontakt.go();
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
	}


}



