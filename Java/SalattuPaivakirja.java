package demot;

/*
Ohjelma mallintaa salattua päiväkirjaa. 
Päiväkirjalle asetetaan salasana, ja asetus- ja havainnointi vaativat aina oikean salasanan välittämisen. 
Päiväkirjaan voi lisätä sisältöä, tulostaa sisällön ja asettaa uuden salasanan.
*/

import java.util.Scanner;

public class SalattuPaivakirja {
	public static void main(String[] args) {
		try (Scanner lukija = new Scanner(System.in)) {
			Paivakirja kirja = new Paivakirja("", "JavaPython123");
			while (true) {
				System.out.println("Mit haluat tehd?");
				System.out.println("0. Lopettaa\n1. Lisätä sisältöä\n2. Tulostaa sisällön\n3. Asettaa uuden salasanan");
				System.out.print("Valintasi: ");
				int valinta = Integer.valueOf(lukija.nextLine());
				
				if (valinta == 0) {
					System.out.println("Ohjelma lopetettu, kiitos!");
					break;
				} if (valinta == 1) {
					if (kirja.getSalasana() != null) {
						System.out.println("Salasana oikein!\nKirjoita lisättävä sisältö: ");
						String lisays = lukija.nextLine();
						kirja.lisaaSisaltoa(lisays);
						System.out.println();
					} else {
						System.out.println("Salasana väärin, aloita alusta!\n");
						}
				}
					
				if (valinta == 2) {
					System.out.println(kirja.getSisalto());
				}
					
				if (valinta == 3) {
					if (kirja.getSalasana() != null) {
						System.out.println("Salasana oikein!\nAnna uusi salasana:\n");
						String uusi = lukija.nextLine();
						kirja.setSalasana(uusi);
						System.out.println("Uusi salasana asetettu!\n");
					} else {
						System.out.println("Salasana väärin, aloita alusta!");
					}
				}
			
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class Paivakirja {
	String sisalto = "";
	String salasana;
	
	public Paivakirja(String sisalto, String salasana) {
		this.sisalto = sisalto;
		this.salasana = salasana;	
	}
	public String getSisalto() {
		if (this.salasana.equals(getSalasana()) == true) {
			System.out.print("Salasana oikein! Päiväkirjan sisältö:\n");
			return sisalto;
		} else {
			System.out.print("Salasana väärin, aloita alusta!\n");
			return null;
		}
    }
	public String getSalasana() {
		if (salasana.equals(this.salasana) == true) {
			return salasana;
		} else {
			return null;
		}
    }
	public void setSalasana(String salasana) {
		if (this.salasana.equals(getSalasana()) == true) {
			this.salasana = salasana;
		} 	
	}
	public void lisaaSisaltoa(String lause) {
		if (salasana.equals(this.salasana) == true) {
			sisalto += lause+"\n";
		}
	}
}
