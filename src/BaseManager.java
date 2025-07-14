import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaseManager {
	private static List<Kontakt> kontakte = new ArrayList<>();
	private static final String dataBasePfad = "Database/Agenda.csv";
	static File dataBase = new File(dataBasePfad);

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

	public static void writeFile(String filename, String content){

		try {
			FileWriter fileWriter = new FileWriter(filename, true);
			fileWriter.write(System.lineSeparator());
			fileWriter.write(content);
			fileWriter.close();

		} catch (IOException e) {
			throw new RuntimeException(e);
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

	public static void kontakteAufladen(){
		try (BufferedReader br = new BufferedReader(new FileReader(dataBase))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] daten = line.split(",");
				if (daten.length == 6) {
					String typ = daten[0];
					String vorname = daten[1];
					String nachname = daten[2];
					String unternehmen = daten[3];
					String email = daten[4];
					String telefon = daten[5];
					boolean favorit = Boolean.parseBoolean(daten[6]);
					KontaktTyp kontaktTyp = typErkennung(typ);
					kontakte.add(new Kontakt(kontaktTyp,vorname, nachname, unternehmen, email, telefon, favorit ));

					System.out.println(kontakte.toString());
				}
			}
		} catch (IOException e) {
			System.out.println("Database nicht gefunden.");
		}
	}
	private static KontaktTyp typErkennung(String typ){
		if (typ.equalsIgnoreCase("LIEFERANT")){
			return KontaktTyp.LIEFERANT;
		} else if (typ.equalsIgnoreCase("KUNDE")) {
			return KontaktTyp.KUNDE;
		}
		return KontaktTyp.NICHT_ANGEGEBEN;
	}
	public void kontaktSpeichern() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataBase))) {
			for (Kontakt c : kontakte) {
				bw.write(c.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Beim Kontakt Speichern gab ein Fehler: " + e.getMessage());
		}
	}


}
