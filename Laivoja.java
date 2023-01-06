package demot;

/*
Ohjelma mallintaa luokkaa Laiva. Luokka sisältää attribuutit nimi, pituus, syväys ja nopeus solmuina. 
Luokassa on lisäksi metodi, jolla voidaan laskea kuinka pitkään laivalla kestää annetun matkan kulkeminen.

Ohjelmassa on myös Laiva-luokkaa perivät luokat Rahtilaiva ja Autolautta:
	- Rahtilaiva: uusina attribuutteina kapasiteetti ja rahti. Rahtilaivaan ei voi lisätä kapasiteettia enempää rahtia. 
	Lisäksi rahdin määrä vaikuttaa laivan nopeuteen, mikä pitää huomioida rahtia lisätessä tai poistaessa.
	- Autolautta: uusina attribuutteina kapasiteetti, matkustajamäärä ja ajoneuvojen määrä. Yksi ajoneuvo vie tilaa 10 yksikköä 
	ja yksi matkustaja yhden yksikön; kapasiteettia ei voi ylittää.
*/

import java.util.Scanner;

public class Laivoja {
	public static void main(String[] args) throws OmaPoikkeus {
		try (Scanner lukija = new Scanner(System.in)) {
			Rahtilaiva rahti = new Rahtilaiva("Rahtialus", 399.9, 14.5, 12.5, 200000.13, 50000.78);
			Autolautta autolautta = new Autolautta("Autolautta", 35.0, 2.0, 5.5, 100, 30, 5);
			
			System.out.println("Aluksen tiedot\n" + rahti.tulostaTiedot() + "\n");
			System.out.println("Aluksen tiedot\n" + autolautta.tulostaTiedot() + "\n");
			
			System.out.print("Anna matka merimaileina kokonaislukuna: ");
			int matka = Integer.valueOf(lukija.nextLine());
			System.out.println(matka + " merimailin kulkemiseen kuluu aluksella " + rahti.nimi + " " + rahti.kuinkaPitkaan(rahti.nopeus, matka) + " tuntia.");
			System.out.println(matka + " merimailin kulkemiseen kuluu aluksella " + autolautta.nimi + " " + autolautta.kuinkaPitkaan(autolautta.nopeus, matka) + " tuntia.");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
@SuppressWarnings("serial")
class OmaPoikkeus extends Exception {
    public OmaPoikkeus(String viesti) {
        super(viesti);
    }
}
class Laiva {
	String nimi;
	double pituus;
	double syvays;
	double nopeus;
		
	public Laiva(String nimi, double pituus, double syvays, double nopeus) {
		this.nimi = nimi;
		this.pituus = pituus;
		this.syvays = syvays;
		this.nopeus = nopeus;	
	}
	public String getNimi() {
        return nimi;
    } public double getPituus() {
	    return pituus;
	} public double getSyvays() {
	    return syvays;
	} public double getNopeus() {
        return nopeus;
    } public void setNimi(String nimi) {
        this.nimi = nimi;
    } public void setPituus(double pituus) {
        this.pituus = pituus;
    } public void setSyvays(double syvays) {
        this.syvays = syvays;
    } public void setNopeus(double nopeus) {
        this.nopeus = nopeus;
    }

    public double kuinkaPitkaan(double nopeus, int matka) {
		double aika = matka/nopeus;
		return Math.round(aika*100.0)/100.0;
	}	
	public String tulostaTiedot() {
		String tiedot = "Nimi: " + this.nimi + "\nPituus: " + this.pituus + " metri\nSyvys: " + this.syvays + " metri\nNopeus: " + this.nopeus + " solmua\n";
		return tiedot;
	}	
}
class Rahtilaiva extends Laiva {
	private double kapasiteetti;
	private double rahti;
	public Rahtilaiva(String nimi, double pituus, double syvays, double nopeus, double kapasiteetti, double rahti)throws OmaPoikkeus {
		super(nimi, pituus, syvays, nopeus*(1.0-(rahti/kapasiteetti)));
		this.kapasiteetti = kapasiteetti;
		if (rahti > kapasiteetti) {					//Jos rahtia yli kapasiteetin tai kapasiteetin verran, heitetn virheviesti
    		throw new OmaPoikkeus("Liikaa rahtia!");
    	}
		if (rahti == kapasiteetti) {
    		throw new OmaPoikkeus("Rahtia kapasiteetin verran, nopeus 0 solmua!");
    	}
		this.rahti = rahti;
	}
	public double getKapasiteetti() {
        return kapasiteetti;
    } public void setKapasiteetti(double kapasiteetti) {
        this.kapasiteetti = kapasiteetti;
    } public double getRahti() {
        return rahti;
    } public void setRahti(double rahti) {
        this.rahti = rahti;
    }
	//Ylikirjoitetaan yliluokan metodi, jotta saadaan tulostus muutettua
	public String tulostaTiedot() {
		String tiedot = "Nimi: " + this.nimi + "\nPituus: " + this.pituus + " metri\nSyvys: " + this.syvays + " metri\nNopeus rahti huomioiden: " + Math.round(this.nopeus*100.0)/100.0 + 
				" solmua\nKapasiteetti: " + this.kapasiteetti + " tonnia\nRahti: " + this.rahti + " tonnia";
		return tiedot;
	}	
}
class Autolautta extends Laiva {
	private final int kapasiteetti;
	private int matkustajamaara;
	private int ajoneuvojenMaara;
	public Autolautta(String nimi, double pituus, double syvays, double nopeus, final int kapasiteetti, int matkustajamaara, int ajoneuvojenMaara) throws OmaPoikkeus {
		super(nimi, pituus, syvays, nopeus);
		this.kapasiteetti = kapasiteetti;
		if (matkustajamaara + ajoneuvojenMaara*10 > kapasiteetti) {		//Jos matkustajien ja ajoneuvojen yksikt ylitt kapasiteetin, heitetn virheviesti
    		throw new OmaPoikkeus("Liikaa matkustajia ja ajoneuvoja!");
    	}
		if (matkustajamaara + ajoneuvojenMaara*10 == kapasiteetti) {
    		throw new OmaPoikkeus("Matkustajia ja ajoneuvoja kapasiteetin verran, nopeus 0 solmua!");
    	}
		this.matkustajamaara = matkustajamaara;
		this.ajoneuvojenMaara = ajoneuvojenMaara;
	}
	public int getKapasiteetti() {
        return kapasiteetti;
    } public double getMatkustajamaara() {
        return matkustajamaara;
    } public void setMatkustajamaara(int matkustajamaara) {
        this.matkustajamaara = matkustajamaara;
    } public int getAjoneuvojenMaara() {
        return ajoneuvojenMaara;
    } public void setAjoneuvojenMaara(int ajoneuvojenMaara) {
        this.ajoneuvojenMaara = ajoneuvojenMaara;
    }
    
	//Ylikirjoitetaan yliluokan metodi, jotta saadaan tulostus muutettua
		public String tulostaTiedot() {
			String tiedot = "Nimi: " + this.nimi + "\nPituus: " + this.pituus + " metri\nSyvys: " + this.syvays + " metri\nNopeus: " + this.nopeus + 
					" solmua\nKapasiteetti: " + this.kapasiteetti + " yksikk\nMatkustajamr : " + this.matkustajamaara + " kpl\nAjoneuvojen mr: " 
					+ this.ajoneuvojenMaara + " kpl eli " + this.ajoneuvojenMaara*10 + " yksikk.";
			return tiedot;
		}
}
