public class Main {
	public static void main(String[] args) {

		//Kontakt dos = new Kontakt(KontaktTyp.KUNDE,"Este","mecansa", "NiIdea", "nidea@gmail.com","17547544",false);
		run();
		//close();
	}
	public static void run(){
		System.out.println("Checking Database...");
		BaseManager.checkDatabase();
		System.out.println("Database wird aufgeladen werden...");
		BaseManager.AgendaAufLaden();
		System.out.println("Print Database von ArrayList");
		for(Kontakt kontakt: BaseManager.kontakte){
			System.out.println(kontakt);
		}
		System.out.println("Borrar contacto");
		BaseManager.kontaktLoschenNachname("FAMILIO");
		String contacto = "KontaktTyp.KUNDE,tercero,familio, ,nidea@gmail.com,17547544,false";

		BaseManager.kontaktLoschenNachname("FAMILIO");
		for(Kontakt kontakt: BaseManager.kontakte){
			System.out.println(kontakt);
		}

		System.out.println("Reimpresion");
		for(Kontakt kontakt: BaseManager.kontakte){
			System.out.println(kontakt);
		}




	}
	public static void close(){
		BaseManager.AgendaAbladen();
	}
}
