package m4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class M4 {
	
	static Scanner sc = new Scanner(System.in);
	static HashMap<String, Integer> menu = new HashMap<String, Integer>();
	static ArrayList<String> comanda = new ArrayList<String>();

	public static void main(String[]args) {
		
		//int b5, b10, b20, b50, b100, b200, b500, total;

		
		String[] plats = {"Bufalina", "4 Formaggi", "Pino Daniele", "Margherita", "Prosciutto", "Parmiggiana", "Al Tonno", "Carbonara", "Massimo Troise", "Ortolana"};
		int[] preus = {12, 12, 18, 9, 10, 11, 13, 11, 17, 12};
		
		
		//guardem el menu en un map i l'omplim amb les arrays
		
		for (int i = 0; i < 10; i++) {
			menu.put(plats[i], preus[i]);
		}
		
		
		boolean sortir = false;
		
		
		//Menu per fer la comanda
		
		while(sortir == false) {
			System.out.println("Benvinguts al restaurant, que voleu demanar?");
			for(int i = 0; i < plats.length; i++) {
				System.out.println(plats[i] + ": " + preus[i] + "€.");
			}
			
			String select = sc.nextLine();
			
			//creem una funcio per comprovar que el producte existeix i llenci exception en cas negatiu
			try {
				check(select);
				comanda.add(select);
				System.out.println(select + " afegit a la comanda.");
			} catch (ProducteInexistent e) {
				System.out.println("El producte " + select + " no existeix.");
			}
			
			//codi per sortir del bucle de comanda o continuar
			int tancar = 0;
			while(tancar == 0) {
				try {			
					int opcio = sortir();
			
					if(opcio == 0) {
						sortir = true;
						tancar = 1;
					} else {
						sortir = false;
						tancar = 1;
					}
				} catch (OpcioInvalida e) {
					System.out.println("Introdueix 1 per continuar o 0 per sortir.");
				}
			}
			
		}
		
		//una vegada finalitzada la comanda, comprovem que els productes coincideixin i sumem el preu al total
		int preuTotal = 0;
		System.out.println("Comanda:");
		try {
			preuTotal = comprovaComanda();
		} catch (ErrorComanda e) {
			System.out.println("No hi ha productes vàlids.");
		}
		
		System.out.println("El total de la comanda es: " + preuTotal + "€.");
			
		sc.close();
	}
	
	public static void check(String text) throws ProducteInexistent {
		boolean ch = false;
		for(String i : menu.keySet()) {
			if(i.equalsIgnoreCase(text)) {
				ch = true;
				break;
			} else {
				ch = false;
			}
		}
		
		if ( ch == false) {
			throw new ProducteInexistent("El producte no existeix.");
		}
	}
	
	public static int sortir() throws OpcioInvalida {
		
		System.out.println("Vol seguir demanant? \n1.Si \n0.No");
		
		String opcio = sc.nextLine();
			
		if(opcio.equals("0")) {
			return 0;
		} else if(opcio.equals("1")){
			return 1;
		} else {
			throw new OpcioInvalida();
		}	
	}
	
	public static int comprovaComanda() throws ErrorComanda{
		boolean check = false;
		int total = 0;
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
				throw new ErrorComanda();
			}
		}
		return total;
	}
}


