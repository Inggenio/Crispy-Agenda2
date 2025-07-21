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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MusterKontakte {

	/* Format der Klasse Kontakt
	 KontaktTyp typ;
	 String nachname;
	 String vorname;
	 String unternehmen;
	 String adresse;
	 String plz;
	 String stadt;
	 String eMail;
	 String telefon;
	 boolean favorit;
	 */

	public static void main(String[] args) {
		generateKontakts();
		generateotherKontakts();
	}

	public static void generateKontakts() {
		String pfad = "Database/Agenda.csv";
		String[] nachnamen = {"Müller", "Schmidt", "Schneider", "Fischer", "Weber", "Meyer", "Wagner", "Becker", "Hoffmann", "Koch"};
		String[] vornamen = {"Max", "Anna", "Lukas", "Lea", "Paul", "Mia", "Ben", "Lena", "Finn", "Laura"};
		String[] unternehmen = {"AlphaTech", "MediCorp", "EduWorld", "Foodies", "TransLog", "BuildPro", "EcoSystems", "FinSecure"};
		String[] adressen = {"Hauptstr. 12", "Bahnhofstr. 7", "Berliner Allee 22", "Gartenweg 3", "Lindenweg 14","Goethestr. 8",				"Schillerweg 15",
				"Mozartallee 3","Ringstr. 29","Bergweg 12","Tulpenweg 5","Ahornstr. 44","Rosenweg 6","Waldstr. 13","Am Markt 21","Schlossallee 18",
				"Dorfstr. 1","Postweg 10","Wiesenweg 2","Lindenstr. 17","Eichenweg 9","Feldstr. 25","Am Hang 7","Kiefernweg 4","Neubauweg 33"};
		String[] staedte = {"Berlin", "Hamburg", "München", "Köln", "Frankfurt", "Stuttgart", "Leipzig", "Düsseldorf", "Dortmund"};
		KontaktTyp[] typen = KontaktTyp.values();
		Random rand = new Random();

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(pfad, false))) {
			for (int i = 0; i < 100; i++) {
				KontaktTyp typ = typen[rand.nextInt(typen.length)];
				String nachname = nachnamen[rand.nextInt(nachnamen.length)];
				String vorname = vornamen[rand.nextInt(vornamen.length)];
				String firma = unternehmen[rand.nextInt(unternehmen.length)];
				String adresse = adressen[rand.nextInt(adressen.length)];
				String plz = String.valueOf(10000 + rand.nextInt(90000));
				String stadt = staedte[rand.nextInt(staedte.length)];
				String email = (vorname + "." + nachname + "@example.com").toLowerCase();
				String telefon = "+49 " + (300000000 + rand.nextInt(699999999));
				boolean favorit = rand.nextBoolean();

				String kontaktZeile = String.join(",",
						typ.name(),
						nachname,
						vorname,
						firma,
						adresse,
						plz,
						stadt,
						email,
						telefon,
						String.valueOf(favorit)
				);

				bw.write(kontaktZeile);
				bw.newLine();
			}
			System.out.println("100 zufällige Kontakte wurden zu '" + pfad + "' hinzugefügt.");
		} catch (IOException e) {
			System.out.println("Fehler beim Schreiben in die Datei: " + e.getMessage());
		}
	}
	public static void generateotherKontakts(){
		String pfad = "Database/Agenda.csv";
		String[] musterKontakte = {
				"Kunde,Max,Musterman,,123456789,max.musterman@musteremail.com,true",
				"Kunde,Ana,Gómez,,ana.gomez@email.com,987654321,false",
				"Lieferant,Luis,Perez,PerezCompanc,luis.perez@email.com,555123456,true",
				"Lieferant,Luduenia,Eber,ComediaEber,San Martin 732, 5463, Buenos Aires,eber@ludueña.com,+54 11231 ,true"
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





