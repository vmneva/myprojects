package demot;

/*
Tämä ohjelma mallintaa jalkapallojoukkueen toiminnallisuuksia kuten joukkueen perustamista sekä pelaajien lisäämistä.
*/

public class JoukkueMain {
	public static void main(String[] args) throws OmaPoikkeus {
		Jalkapallojoukkue joukkue1 = new Jalkapallojoukkue("RaKe", "Rajamen stadion");
		Jalkapallojoukkue joukkue2 = new Jalkapallojoukkue("HyPs", "Hyvinkn stadion");
		
		Jalkapallopelaaja pelaaja1 = new Jalkapallopelaaja("Janne Palloilija", "hykkj", 40);
		Jalkapallopelaaja pelaaja2 = new Jalkapallopelaaja("Rasmus Koppari", "maalivahti", 1);
		Jalkapallopelaaja pelaaja3 = new Jalkapallopelaaja("Petra Krkkri", "maalivahti", 10);
		Jalkapallopelaaja pelaaja4 = new Jalkapallopelaaja("Joonas Potkija", "puolustaja", 55);
		Jalkapallopelaaja pelaaja5 = new Jalkapallopelaaja("Annika Kierrepotku", "hykkj", 33);
		
		for (int i=0; i<15; i++) {
			joukkue1.lisaaPelaaja(pelaaja1);
		}
		
		joukkue1.lisaaPelaaja(pelaaja1);
		joukkue1.lisaaPelaaja(pelaaja2);
		joukkue1.lisaaPelaaja(pelaaja3);
		joukkue1.lisaaPelaaja(pelaaja4);
		joukkue1.lisaaPelaaja(pelaaja5);
		joukkue2.lisaaPelaaja(pelaaja3);
		joukkue2.lisaaPelaaja(pelaaja4);
		joukkue2.lisaaPelaaja(pelaaja5);
		
		joukkue1.tulostaJoukkue();
		System.out.println();
		joukkue2.tulostaJoukkue();
		
		System.out.println("\nTestataan equals metodia. Verrataan joukkuetta " + joukkue1.nimi + " joukkueeseen " + joukkue2.nimi);
        System.out.println("equals: " + (joukkue1.equals(joukkue2)));
        System.out.println("Verrataan sitten itseens: ");
        System.out.println("equals: " + (joukkue1.equals(joukkue1)));
        
        System.out.println("\nTestataan equals metodia. Verrataan pelaajaa " + pelaaja1.getPelaajanNimi() + " pelaajaan " + pelaaja2.getPelaajanNimi());
        System.out.println("equals: " + (pelaaja1.equals(pelaaja2)));
        System.out.println("Verrataan sitten itseens: ");
        System.out.println("equals: " + (pelaaja1.equals(pelaaja1)));
		
		
	}
}
