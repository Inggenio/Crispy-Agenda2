import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MusterKontakte {
	public static void main(String[] args) {
		generateKontakts();
	}
	public static void generateKontakts(){
		String pfad = "Database/Agenda.csv";
		String[] musterKontakte = {
				"Kunde,Max,Musterman,,123456789,max.musterman@musteremail.com,true",
				"Kunde,Ana,Gómez,,987654321,ana.gomez@email.com,false",
				"Lieferant,Luis,Perez,PerezCompanc,555123456,luis.perez@email.com,true",
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
