public class Kontakt {

	private KontaktTyp typ;
	private String vorname;
	private String nachname;
	private String unternehmen;
	private String eMail;
	private String telefon;
	private boolean favorit;

	public Kontakt() {
	}

	public Kontakt(KontaktTyp typ, String vorname, String nachname, String unternehmen, String eMail, String telefon, boolean favorit) {
		this.typ = typ;
		this.vorname = vorname;
		this.nachname = nachname;
		this.unternehmen = unternehmen;
		this.eMail = eMail;
		this.telefon = telefon;
		this.favorit = favorit;
	}

	public Kontakt(KontaktTyp typ, String vorname, String nachname, String eMail, String telefon, boolean favorit) {
		this.typ = typ;
		this.vorname = vorname;
		this.nachname = nachname;
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

	public String getUnternehmen() {
		return unternehmen;
	}

	public void setUnternehmen(String unternehmen) {
		this.unternehmen = unternehmen;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
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

	public String typString(KontaktTyp typ){
		if(typ == KontaktTyp.LIEFERANT){
			return "LIEFERANT";
		} else if (typ == KontaktTyp.KUNDE) {
			return "KUNDE";
		}
		return "UNBEKANNT";
	}
	public String kontaktToCsv(){
		return typString(this.typ)+ "," +
				getVorname()+ "," +
				getNachname()+ "," +
				getUnternehmen()+ "," +
				geteMail()+ "," +
				getTelefon()+ "," +
				isFavorit();
	}

	@Override
	public String toString() {
		//return "|" + typString(this.typ) + "| " + nachname.toUpperCase() + " " + vorname + " // " + eMail + " // " + telefon + " // Favorit" + (isFavorit()? "[*]" : "[ ]");
		return String.format("%-10s %-12s %-12s %-20s %-35s %-15s %-8s",
				typString(this.typ), nachname.toUpperCase(), vorname, unternehmen, eMail, telefon, favorit ? "★" : "");
	}
}
