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


import javax.swing.table.AbstractTableModel;
import java.util.List;

public class KontaktTableModel extends AbstractTableModel {
	private final String[] spalten = {
			"Typ",
			"Nachname",
			"Vorname",
			"Unternehmen",
			"Adresse",
			"PLZ",
			"Stadt",
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
			case 3 -> k.getFirma();
			case 4 -> k.getAdresse();
			case 5 -> k.getPlz();
			case 6 -> k.getStadt();
			case 7 -> k.geteMail();
			case 8 -> k.getTelefon();
			case 9 -> k.isFavorit() ? "★" : "";
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
