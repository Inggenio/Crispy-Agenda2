public class Main {
	public static void main(String[] args) {
		run();
	}
	public static void run(){
		System.out.println("Checking Database...");
		BaseManager.checkDatabase();
		System.out.println("Database wird aufgeladen werden...");
		MainGUI gui = new MainGUI();
		gui.go();
	}

}
