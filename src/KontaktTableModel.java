import javax.swing.table.AbstractTableModel;
import java.util.List;

public class KontaktTableModel extends AbstractTableModel {
	private final String[] spalten = {
			"Typ",
			"Nachname",
			"Vorname",
			"Unternehmen",
			"Email",
			"Telefon",
			"Favorit"
	};

	private final List<Kontakt> kontakte;

	public KontaktTableModel(List<Kontakt> kontakte) {
		this.kontakte = kontakte;
	}

	@Override
	public int getRowCount() {
		return kontakte.size();
	}

	@Override
	public int getColumnCount() {
		return spalten.length;
	}

	@Override
	public String getColumnName(int column) {
		return spalten[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Kontakt k = kontakte.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> k.getTyp();
			case 1 -> k.getNachname();
			case 2 -> k.getVorname();
			case 3 -> k.getUnternehmen();
			case 4 -> k.geteMail();
			case 5 -> k.getTelefon();
			case 6 -> k.isFavorit() ? "★" : "";
			default -> "";
		};
	}
	public Kontakt getKontaktAt(int rowIndex) {
		return kontakte.get(rowIndex);
	}
	public void refresh(){
		fireTableDataChanged();
	}
}
