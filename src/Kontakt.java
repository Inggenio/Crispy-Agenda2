public class Kontakt {

	private String vorname;
	private String nachname;
	private String eMail;
	private String telefon;
	private boolean favorit;

	public Kontakt() {
	}

	public Kontakt(String vorname, String nachname, String eMail, String telefon, boolean favorit) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.eMail = eMail;
		this.telefon = telefon;
		this.favorit = favorit;
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

	@Override
	public String toString() {
		return nachname + " " + vorname + " // " + eMail + " // " + telefon + "Favorit" + (isFavorit()? "[*]" : "[ ]");
	}
}
