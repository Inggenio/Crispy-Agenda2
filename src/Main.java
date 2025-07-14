public class Main {
	public static void main(String[] args) {

		//Neue Eintraege Sub-Programm
//
//		Neueintraege neu = new Neueintraege();
//		neu.go();

		//BaseManager.checkDatabase();
		//Main.run();
		//BaseManager.kontakteAufladen();
		Kontakt uno = new Kontakt(KontaktTyp.KUNDE,"Charles","Pezold", "Planeta", "beispiel@gmail.com","17547544",true);
		BaseManager.writeFile("Database/Agenda.csv", uno.kontaktToCsv(uno));
	}
	public static void run(){
		System.out.println("Main GUI");
	}
}
