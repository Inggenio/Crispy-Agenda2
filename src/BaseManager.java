import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BaseManager {
	public static List<Kontakt> kontakte = new ArrayList<>();
	public static final String dataBasePfad = "Database/Agenda.csv";
	static File dataBase = new File(dataBasePfad);

	public static String getPfad(){
		return dataBasePfad;
	}

	public static void checkDatabase(){
		File file = new File(dataBasePfad);

		if (!file.exists()) {
			try {
				boolean created = file.createNewFile();
				if (created) {
					System.out.println("Database nicht gefunden. Datei wurde neu angelegt.");
					dataBaseStruktur();
					MusterKontakte.generateKontakts();
				} else {
					System.out.println("Die Datei konnte nicht erstellt werden.");
				}
			} catch (IOException e) {
				System.out.println("Es gab ein Problem bei der Dateierstellung.");
				e.printStackTrace();
			}
		} else {
			System.out.println("Datei existiert bereits.");
		}
	}
	public static void dataBaseStruktur(){
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataBasePfad))) {
			writer.write("typ,vorname,nachname,unternehmen,email,telefon,favorit");
			writer.newLine();
		} catch (IOException e) {
			System.out.println("Fehler beim Schreiben der Datenstruktur.");
			e.printStackTrace();
		}
	}
	//Add Kontakt String für Formular
	public static void addKontaktString(String content){
		String[] daten = content.trim().split(",");

		if (daten.length == 7) {
			try {
				KontaktTyp typ = typErkennung(daten[0].trim());
				String vorname = daten[1].trim();
				String nachname = daten[2].trim();
				String unternehmen = daten[3].trim();
				String email = daten[4].trim();
				String telefon = daten[5].trim();
				boolean favorit = Boolean.parseBoolean(daten[6].trim());

				Kontakt kontakt = new Kontakt(typ, vorname, nachname, unternehmen, email, telefon, favorit);
				kontakte.add(kontakt);
				System.out.println("Kontakt hinzugefügt: " + kontakt);

			} catch (Exception e) {
				System.out.println("Fehler beim Parsen der Kontakt-Daten: " + e.getMessage());
			}
		} else {
			System.out.println("Ungültiges Format: " + content);
		}
	}

	public static void addKontaktStringobsolete(String filename, String content){

		try {
			FileWriter fileWriter = new FileWriter(filename, true);
			fileWriter.write(System.lineSeparator());
			fileWriter.write(content);
			fileWriter.write(System.lineSeparator());
			fileWriter.close();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void addKontaktObj(ArrayList kontaktList, Kontakt kontakt){
		kontaktList.add(kontakt);
	}

	public static void readDatabase() {
		int index = 1;
		for (Kontakt kontakt : kontakte) {
			System.out.println(index + ": " + kontakt);
			index++;
		}
	}
	public static void readFile(String filename){
		File file = new File(filename);
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext()){
				String line = scanner.nextLine();
				System.out.println(line);
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("Datei nicht da");
		}

	}
	public static void deleteFile(String filename){
		File file = new File(filename);
		if (file.delete()){
			System.out.println("Die Datei wurde gelöscht");
		} else {
			System.out.println("Der Datei Überlebt");
		}
	}

	public static void AgendaAufLaden(){
		try (BufferedReader br = new BufferedReader(new FileReader(dataBase))) {
			String line;
			br.readLine();
			int index = 1;
			while ((line = br.readLine()) != null) {
				String[] daten = line.split(",",-1);
				if (daten.length == 7) {
					String typ = daten[0];
					String vorname = daten[1];
					String nachname = daten[2];
					String unternehmen = daten[3];
					String email = daten[4];
					String telefon = daten[5];
					boolean favorit = daten[6].equalsIgnoreCase("true") || daten[6].equalsIgnoreCase("ja");

					KontaktTyp kontaktTyp = typErkennung(typ.trim());

					Kontakt kontakt = new Kontakt(kontaktTyp, vorname, nachname, unternehmen, email, telefon, favorit);
					kontakte.add(kontakt);

					System.out.println(index + ": " + kontakt);
					index++;
				} else {
					System.out.println("Ungültige Zeile (" + index + ") " + line);
				}
			}
			System.out.println("Ingesamt geladene Kontakte: " + kontakte.size());
		} catch (IOException e) {
			System.out.println("Database nicht gefunden.");
		}
	}
	public static void AgendaAbladen(){
		try {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataBasePfad))) {
				writer.write("typ,vorname,nachname,unternehmen,email,telefon,favorit");
				writer.newLine();
				for (Kontakt kontakt : kontakte){
					writer.write(kontakt.kontaktToCsv());
					writer.write(System.lineSeparator());
				}
				System.out.println("Kontakte erfolgreich in CSV-Datei gespeichert.");
			}
		} catch (IOException e) {
			System.out.println("Fehler beim Schreiben der Datenbank: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
	private static KontaktTyp typErkennung(String s){
		s = s.trim().toLowerCase();
		return switch (s) {
			case "kunde" -> KontaktTyp.KUNDE;
			case "lieferant" -> KontaktTyp.LIEFERANT;
			default -> KontaktTyp.UNBEKANNT;
		};
	}
	public static void kontaktSpeichern() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataBase))) {
			for (Kontakt c : kontakte) {
				bw.write(c.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Beim Kontakt Speichern gab ein Fehler: " + e.getMessage());
		}
	}
	//Methode zum Kontakte Löschen
	public static void kontaktLoschenName(String nachname, String vorname){
		Iterator<Kontakt> iterator = kontakte.iterator();
		boolean gefunden = false;
		while (iterator.hasNext()){
			Kontakt k = iterator.next();
			if(k.getNachname().equalsIgnoreCase(nachname) && k.getVorname().equalsIgnoreCase(vorname)){
				iterator.remove();
				System.out.println("Kontakt " + k +" gelöscht");
				gefunden = true;
				break;
			}
		}
		if (!gefunden){
			System.out.println("Kontakt in Database nicht gefunden.");
		}
	}
	public static void kontaktLoschenNachname(String nachname){
		Iterator<Kontakt> iterator = kontakte.iterator();
		boolean gefunden = false;
		while (iterator.hasNext()){
			Kontakt k = iterator.next();
			if(k.getNachname().equalsIgnoreCase(nachname)){
				iterator.remove();
				System.out.println("Kontakt " + k +" gelöscht");
				gefunden = true;
				//break;
			}
		}
		if (!gefunden){
			System.out.println("Kontakt in Database nicht gefunden.");
		}
	}
	public void kontaktLoeschenNachIndex(int index) {
		if (index >= 0 && index < kontakte.size()) {
			Kontakt entfernt = kontakte.remove(index);
			System.out.println("Kontakt gelöscht: " + entfernt);
		} else {
			System.out.println("Ungültiger Index.");
		}
	}

}
