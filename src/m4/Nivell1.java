package m4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Nivell1 {

	public static void main(String[]args) {
		//Fase 1
		
		int b5, b10, b20, b50, b100, b200, b500, total;

		
		String[] plats = {"Bufalina", "4 Formaggi", "Pino Daniele", "Margherita", "Prosciutto", "Parmiggiana", "Al Tonno", "Carbonara", "Massimo Troise", "Ortolana"};
		int[] preus = {12, 12, 18, 9, 10, 11, 13, 11, 17, 12};
		
		//Fase 2
		
		 HashMap<String, Integer> menu = new HashMap<String, Integer>();
		
		for (int i = 0; i < 10; i++) {
			menu.put(plats[i], preus[i]);
		}
		
		Scanner sc = new Scanner(System.in);
		Scanner scan = new Scanner(System.in);
		
		boolean sortir = false;
		ArrayList<String> comanda = new ArrayList<String>();
		//String select;
		
		//Menu per fer la comanda
		while(sortir == false) {
			System.out.println("Benvinguts al restaurant, que voleu demanar?");
			for(int i = 0; i < plats.length; i++) {
				System.out.println(plats[i] + ": " + preus[i] + "€.");
			}
			
			String select = sc.nextLine();
				try {
					check(select);
					comanda.add(select);
					System.out.println(select + " afegit a la comanda.");
				} catch (Exception e) {
					System.out.println("El producte no existeix");
				}
						
			int opcio = sortir();
			
			if(opcio == 0) {
				sortir = true;
			} else {
				sortir = false;
			}
			/*System.out.println("Vol seguir demanant? \n1.Si \n0.No");
			//int opcio = scan.nextInt();
			try {
				int opcio = scan.nextInt();
				if(opcio == 0) {
					sortir = true;
				} else {
					sortir = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Introdueix 1 per continuar o 0 per sortir.");
				sortir = true;
			}*/
		}
		
		//una vegada finalitzada la comanda, comprovem que els productes coincideixin i sumem el preu al total
		total = 0;
		System.out.println("Comanda:");
		boolean check = false;
		
		for(String i : comanda) {
			for(String z : menu.keySet()) {
				if(i.equalsIgnoreCase(z)) {
					total = total + menu.get(z);
					System.out.println(z);
					check = false;
					break;
				} else {
					check = true;					
				}
			}
			if(check == true) {
				System.out.println("El producte " + i + " no existeix.");
			}
		}
		
		sc.close();
		scan.close();
		
	}
	
	public static void check(String text) throws Exception {
		String[] plats = {"Bufalina", "4 Formaggi", "Pino Daniele", "Margherita", "Prosciutto", "Parmiggiana", "Al Tonno", "Carbonara", "Massimo Troise", "Ortolana"};
		boolean ch = false;
		for(String i : plats) {
			if(i.equalsIgnoreCase(text)) {
				ch = true;
				break;
			} else {
				ch = false;
			}
		}
		
		if ( ch == false) {
			throw new Exception("El producte no existeix.");
		}
	}
	
	public static int sortir() throws InputMismatchException {
		Scanner in = new Scanner (System.in);
		
		System.out.println("Vol seguir demanant? \n1.Si \n0.No");
		try {
			int opcio = in.nextInt();
			in.close();
			
			if(opcio == 0) {
				return 0;
			} else if(opcio == 1){
				return 1;
			} else {
				System.out.println("Introdueix 1 per continuar o 0 per sortir.");
				return sortir();
			}
		} catch (InputMismatchException e) {
			System.out.println("Introdueix 1 per continuar o 0 per sortir.");
			return sortir();
		}
		
	}
}


