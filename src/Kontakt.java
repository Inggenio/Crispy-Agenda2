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


public class Kontakt {

	private KontaktTyp typ;
	private String nachname;
	private String vorname;
	private String firma;
	private String adresse;
	private String plz;
	private String stadt;
	private String eMail;
	private String telefon;
	private boolean favorit;

	public Kontakt() {
	}

	public Kontakt(KontaktTyp typ, String nachname, String vorname, String firma, String adresse, String plz, String stadt, String eMail, String telefon, boolean favorit) {
		this.typ = typ;
		this.nachname = nachname;
		this.vorname = vorname;
		this.firma = firma;
		this.adresse = adresse;
		this.plz = plz;
		this.stadt = stadt;
		this.eMail = eMail;
		this.telefon = telefon;
		this.favorit = favorit;
	}

	public Kontakt(KontaktTyp typ, String nachname, String vorname, String firma, String eMail, String telefon, boolean favorit) {
		this.typ = typ;
		this.nachname = nachname;
		this.vorname = vorname;
		this.firma = firma;
		this.eMail = eMail;
		this.telefon = telefon;
		this.favorit = favorit;
	}

	public Kontakt(KontaktTyp typ, String nachname,String vorname, String eMail, String telefon, boolean favorit) {
		this.typ = typ;
		this.nachname = nachname;
		this.vorname = vorname;
		this.eMail = eMail;
		this.telefon = telefon;
		this.favorit = favorit;
	}

	public KontaktTyp getTyp() {
		return typ;
	}

	public void setTyp(KontaktTyp typ) {
		this.typ = typ;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public boolean isFavorit() {
		return favorit;
	}

	public void setFavorit(boolean favorit) {
		this.favorit = favorit;
	}

	public String typToString(KontaktTyp typ){
		if(typ == KontaktTyp.LIEFERANT){
			return "LIEFERANT";
		} else if (typ == KontaktTyp.KUNDE) {
			return "KUNDE";
		}
		return "UNBEKANNT";
	}
	public String kontaktToCsv(){
		return typToString(this.typ)+ "," +
				getNachname()+ "," +
				getVorname()+ "," +
				getFirma()+ "," +
				getAdresse()+ "," +
				getPlz()+ "," +
				getStadt()+ "," +
				geteMail()+ "," +
				getTelefon()+ "," +
				isFavorit();
	}

	@Override
	public String toString() {
		return String.format("%-10s %-12s %-12s %-20s %-20s %-35s %-12s %-12s %-15s %-8s",
				typToString(this.typ), nachname.toUpperCase(), vorname, firma, adresse, plz, stadt, eMail, telefon, favorit ? "★" : "");
	}
	//Archiv
	public static KontaktTyp typStringToTyp(String typ){
		if(typ.equalsIgnoreCase("LIEFERANT")){
			return KontaktTyp.LIEFERANT;
		} else if (typ.equalsIgnoreCase("KUNDE")) {
			return KontaktTyp.KUNDE;
		}
		return KontaktTyp.UNBEKANNT;
	}
}
