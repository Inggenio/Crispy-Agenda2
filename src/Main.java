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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
	public static void main(String[] args) {
		run();
	}
	public static void run(){
		System.out.println("-->>Programm wird inisialisiert <<--");
		System.out.println("Init:" + timeStamp());
		System.out.println("Checking Database...");
		BaseManager.checkDatabase();
		System.out.println("Database wird aufgeladen werden...");
		MainGUI gui = new MainGUI();
		gui.go();
	}
	private static String timeStamp(){
		LocalDateTime jetz = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
		return jetz.format(format);
	}
}
