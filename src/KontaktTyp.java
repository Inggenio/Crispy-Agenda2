public enum KontaktTyp {
	UNBEKANNT("Unbekannt"),
	KUNDE("Kunde"),
	LIEFERANT("Lieferant");

	private final String anzeigeText;

	KontaktTyp(String text) {
		this.anzeigeText = text;
	}

	@Override
	public String toString() {
		return anzeigeText;
	}

}
