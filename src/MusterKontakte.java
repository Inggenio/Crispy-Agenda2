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
