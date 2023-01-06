package demot;
import java.util.ArrayList;

/*
Ohjelma mallintaa jalkapallojoukkuetta sekä jalkapallopelaajia. 
	- Joukkueessa pelaajien maksimimäärä on 22 (2 maalivahtia, 20 kenttäpelaajaa). Lisäksi joukkuueella on nimi sekä kotistadion.
	- Jalkapallopelaajilla on nimi, pelipaikka sekä pelinumero.
*/

public class Jalkapallojoukkue {
	ArrayList<Jalkapallopelaaja> pelaajat = new ArrayList<>();
	int pelaajienLkm;
	int maalivahdit = 0;
	String nimi;
	String kotistadion;
	public Jalkapallojoukkue(String nimi, String kotistadion) {
		this.nimi = nimi;
		this.kotistadion = kotistadion;
		pelaajat = new ArrayList<Jalkapallopelaaja>();
	}
	public Jalkapallojoukkue() {}
	
	public String getNimi() {
		return nimi;
	} public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	public String getKotistadion() {
		return kotistadion;
	} public void setKotistadion(String kotistadion) {
		this.kotistadion = kotistadion;
	}
	public void lisaaPelaaja(Jalkapallopelaaja pelaaja) throws OmaPoikkeus {
		if (this.pelaajienLkm > 21) {
			throw new OmaPoikkeus("Liikaa pelaajia! Sallittu mr 22 pelaajaa. Joukkueessa jo " + this.pelaajienLkm + " pelaajaa!");
		}
		if (pelaaja.getPelipaikka().contains("maalivahti") == true) {
			if (maalivahdit == 2) {
				throw new OmaPoikkeus("Joukkueessa jo " + this.maalivahdit + " maalivahtia!");
			} 
			maalivahdit++;
		}
		pelaajat.add(pelaaja);
    	this.pelaajienLkm++;
    	
	}
	@Override
	public String toString() {
		return "Joukkueen nimi: " + nimi + "\nKotistadion: " + kotistadion + "\nPelaajien lkm: " + pelaajienLkm;
	}
	public void tulostaJoukkue() {
		System.out.println(toString() + "\n\nPelaajat:");
		for (Jalkapallopelaaja pelaaja : pelaajat) {
			System.out.println(pelaaja.toString());
		}
	}
	public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } if (obj == null) {
            return false;
        } if (obj.getClass() != Jalkapallojoukkue.class) {
            return false;
        }
        Jalkapallojoukkue toinen = (Jalkapallojoukkue) obj;
        return pelaajienLkm == toinen.pelaajienLkm && nimi == toinen.nimi && kotistadion == toinen.kotistadion && pelaajat == toinen.pelaajat;
    }
}
class Jalkapallopelaaja extends Jalkapallojoukkue {
	private String pelaajanNimi;
	private String pelipaikka;
	private int numero;
	
	public Jalkapallopelaaja(String pelaajanNimi, String pelipaikka, int numero){
		this.pelaajanNimi = pelaajanNimi;
		this.pelipaikka = pelipaikka;
		this.numero = numero;
	}
	public String getPelaajanNimi() {
		return pelaajanNimi;
	} public void setPelaajanNimi(String pelaajanNimi) {
		this.pelaajanNimi = pelaajanNimi;
	}
	public String getPelipaikka() {
		return pelipaikka;
	} public void setPelipaikka(String pelipaikka) {
		this.pelipaikka = pelipaikka;
	}
	public int getNumero() {
		return numero;
	} public void setNumero(int numero) {
		this.numero = numero;
	}
	@Override
	public String toString() {
		return "Pelaajan nimi: " + pelaajanNimi + "\nPelipaikka: " + pelipaikka + "\nNumero: " + numero;
	}
	public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } if (obj == null) {
            return false;
        } if (obj.getClass() != Jalkapallopelaaja.class) {
            return false;
        }
        Jalkapallopelaaja toinen = (Jalkapallopelaaja) obj;
        return pelaajanNimi == toinen.pelaajanNimi && pelipaikka == toinen.pelipaikka && numero == toinen.numero;
    }	
}

