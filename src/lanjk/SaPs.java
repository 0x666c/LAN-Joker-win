package lanjk;

import static lanjk.OutColor.Clr.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SaPs {
	
	private static Computer[] pcs;
	
	public static void main(String[] args) throws Exception {
		clearScreen();
		
		OutColor.set(BLACK, LIGHT_GRAY);
		System.out.print("Parsing local network...");
		
		pcs = parseLocalNetwork();
		
		OutColor.set(BLACK, GREEN);
		System.out.println(" Done");
		
		System.out.println();
		
		while(true) {
			OutColor.set(BLACK, WHITE);
			System.out.println("Welcome to SaPs " + (char)1 + "\n");
			
			OutColor.set(BLACK, YELLOW);
			System.out.println("Type 0 to list all computers");
			System.out.println("Type 1 to shutdown all computers");
			System.out.println("Type 2 to shutdown specific computers");
			System.out.println("Type 3 to exit");
			
			OutColor.set(BLACK, WHITE);
			System.out.print("\n>");
			
			int c = System.in.read();
			int in = Character.getNumericValue(c);
			if(in == 0) {
				System.out.println();
				
				OutColor.set(BLACK, YELLOW);
				System.out.println("Listing all recognized computers:");
				
				OutColor.set(BLACK, MAGENTA);
				for (Computer pc : pcs) {
					System.out.println(pc.getName());
				}
				OutColor.set(BLACK, YELLOW);
				System.out.println("Press return to continue...");
				
				waitForReturn();
			} else if(in == 1) {
				OutColor.set(BLACK, YELLOW);
				System.out.println();
				
				int inc = 0;
				for (Computer pc : pcs) {
					clearCurrentLine();
					inc++;
					System.out.print("Shutting down pc " + inc + "/" + pcs.length);
					
					pc.shutdown();
				}
			} else if(in == 2) {
				
			} else if(in == 3) {
				System.exit(0);
			}
			clearScreen();
		}
	}
	
	private static void clearCurrentLine() {
		System.out.println("\r                           ");
	}
	
	private static void waitForReturn() throws Exception {
		System.in.read(); // Idk why won't one work, so here is 3
		System.in.read();
		System.in.read();
	}
	
	private static void clearScreen() throws Exception {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
	
	private static Computer[] parseLocalNetwork() throws Exception {
		Process proc = Runtime.getRuntime().exec("net view");

		BufferedReader stdInput = new BufferedReader(new 
		     InputStreamReader(proc.getInputStream()));
		
		String line = null;
		List<Computer> list = new ArrayList<>();
		while ((line = stdInput.readLine()) != null) {
			if(line.startsWith("\\")) {
				line = line.substring(2, line.indexOf(' '));
				line.trim();
				list.add(new Computer(line));
			}
		}
		return list.toArray(new Computer[] {});
	}
}