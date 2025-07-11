public class Main {
	public static void main(String[] args) {

		Neueintraege neu = new Neueintraege();
		neu.go();

		BaseManager.checkDatabase();
		//Main.run();
		BaseManager.kontakteAufladen();
	}
	public static void run(){
		System.out.println("Main GUI");
	}
}
