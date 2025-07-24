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


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
					//MusterKontakte.generateKontakts();
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
			writer.write("Typ,Nachname,Vorname,Firma,Adresse,PLZ,Stadt,Email,Telefon,Favorit");
			writer.newLine();
		} catch (IOException e) {
			System.out.println("Fehler beim Schreiben der Datenstruktur.");
			e.printStackTrace();
		}
	}
	//Add Kontakt String für Formular

	private static void agendaBkp(){
		try {
			Path source = Path.of(dataBase.getPath());
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
			Path backup = Path.of("BackUps/Agenda_backup_" + timestamp + ".csv");

			//Backup
			Files.copy(source, backup, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Backup erstellt: " + backup.getFileName());

			File backupsPfad = new File("BackUps");
			File[] backups = backupsPfad.listFiles((dir, name) -> name.startsWith("Agenda_backup_") && name.endsWith(".csv"));
			if (backups != null && backups.length>3){
				Arrays.sort(backups, (f1, f2) -> f2.getName().compareTo(f1.getName()));

				for (int i = 3; i < backups.length; i++) {
					if (backups[i].delete()) {
						System.out.println("Alter Backup gelöscht: " + backups[i].getName());
					} else {
						System.out.println("Konnte Backup nicht löschen: " + backups[i].getName());
					}
				}
			}
		} catch (IOException ex) {
			System.out.println("Backup konnte nicht erstellt werden: " + ex.getMessage());
		}
	}

	public static void agendaAufLaden(){
		agendaBkp();
		try (BufferedReader br = new BufferedReader(new FileReader(dataBase))) {
			String line;
			br.readLine();
			int index = 1;
			while ((line = br.readLine()) != null) {
				String[] daten = line.split(",",-1);
				if (daten.length == 10) {
					String typ = daten[0];
					String nachname = daten[1];
					String vorname = daten[2];
					String firma = daten[3];
					String adresse = daten[4];
					String plz = daten[5];
					String stadt = daten[6];
					String email = daten[7];
					String telefon = daten[8];
					boolean favorit = daten[9].equalsIgnoreCase("true") || daten[9].equalsIgnoreCase("ja");

					KontaktTyp kontaktTyp = typErkennung(typ.trim());

					Kontakt kontakt = new Kontakt(kontaktTyp,nachname,vorname,firma,adresse,plz,stadt,email, telefon, favorit);
					kontakte.add(kontakt);

					//Um zu sehen, die Kontakte in der Konsole während Aufladung:
					//System.out.println(index + ": " + kontakt);
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
	public static void agendaAbladen(){
		try {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataBasePfad))) {
				writer.write("typ,nachname,vorname,firma,adresse,plz,stadt,email,telefon,favorit");
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
	//Archivierte Methoden
	//Add Kontakt von ein String
	public static void addKontaktString(String content){
		String[] daten = content.trim().split(",");

		if (daten.length == 10) {
			try {
				KontaktTyp typ = typErkennung(daten[0].trim());
				String nachname = daten[1].trim();
				String vorname = daten[2].trim();
				String firma = daten[3].trim();
				String adresse = daten[4].trim();
				String plz = daten[5].trim();
				String stadt = daten[6].trim();
				String email = daten[7].trim();
				String telefon = daten[8].trim();
				boolean favorit = Boolean.parseBoolean(daten[9].trim());

				Kontakt kontakt = new Kontakt(typ, nachname, vorname, firma, adresse, plz, stadt, email, telefon, favorit);
				kontakte.add(kontakt);
				System.out.println("Kontakt hinzugefügt: " + kontakt);

			} catch (Exception e) {
				System.out.println("Fehler beim Parsen der Kontakt-Daten: " + e.getMessage());
			}
		} else {
			System.out.println("Ungültiges Format: " + content);
		}
	}

	//Kontakt Speichern
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
	//Database Lesen durch Konsole
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


}
