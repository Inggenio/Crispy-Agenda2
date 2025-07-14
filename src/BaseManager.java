import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaseManager {
	private static List<Kontakt> kontakte = new ArrayList<>();
	private static final String database = "Database/Agenda.csv";
	static File dataBase = new File(database);

	public static void checkDatabase(){
		// filename = Absoluter oder Relativer Pfad
		File file = new File(database);

		try{
			boolean fileExists = file.createNewFile();
			if(fileExists){
				System.out.println("Database nicht gefunden. Datei wurde neu angelegt");
			} else {
				System.out.println("Die Datei gab es bereits.");
			}
		} catch (IOException e){
			System.out.println("Es gab ein Problem bei der Dateierstellung");
			e.printStackTrace();
		}
	}

	public static void writeFile(String filename, String content){

		try {
			FileWriter fileWriter = new FileWriter(filename, true); //cuando Append es Falso, reemplaza todo
			fileWriter.write(content);
			//fileWriter.write(System.lineSeparator());
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
				if (daten.length == 5) {
					String vorname = daten[0];
					String nachname = daten[1];
					String email = daten[3];
					String telefon = daten[2];
					boolean favorit = Boolean.parseBoolean(daten[4]);
					kontakte.add(new Kontakt(vorname, nachname, email, telefon, favorit));
					System.out.println(kontakte.toString());
				}
			}
		} catch (IOException e) {
			System.out.println("Database nicht gefunden.");
		}
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
