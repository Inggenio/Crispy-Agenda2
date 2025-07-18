import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MusterKontakte {

	/* Format der Klasse Kontakt
	 KontaktTyp typ;
	 String nachname;
	 String vorname;
	 String unternehmen;
	 String eMail;
	 String telefon;
	 boolean favorit;
	 */

	public static void main(String[] args) {
		generateKontakts();
	}
	public static void generateKontakts(){
		String pfad = "Database/Agenda.csv";
		String[] musterKontakte = {
				"Kunde,Max,Musterman,,123456789,max.musterman@musteremail.com,true",
				"Kunde,Ana,Gómez,,ana.gomez@email.com,987654321,false",
				"Lieferant,Luis,Perez,PerezCompanc,luis.perez@email.com,555123456,true",
		};
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(pfad, true ))) {
			for (String kontakte : musterKontakte) {
				bw.write(kontakte);
				bw.newLine();
			}
			System.out.println("Datei '" + pfad + "' generiert.");
		} catch (IOException e) {
			System.out.println("Database Aufladen Fehler : " + e.getMessage());
		}
	}
}
