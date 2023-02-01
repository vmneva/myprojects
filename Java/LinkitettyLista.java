package demot;

/*
Ohjelma mallintaa kaksisuuntaista listaa. Jokainen alkio tietää seuraavan ja edellisen alkion listassa. Ohjelmassa on myös
esimerkki listan käytöstä, jossa käydään lista läpi alusta loppuun ja lopusta alkuun, ja lasketaan kaikkien alkioiden summa ja keskiarvo.
*/

public class LinkitettyLista {
	public static void main(String[] args) {  
        KaksisuuntainenLinkitettyLista lista = new KaksisuuntainenLinkitettyLista();  
          
        lista.lisaaAlkio(15);  
        lista.lisaaAlkio(35);  
        lista.lisaaAlkio(41);  
        lista.lisaaAlkio(67);  
        lista.lisaaAlkio(150);  
        lista.lisaaAlkio(400);  
        lista.lisaaAlkio(1550); 
        
        lista.tulostaAlkiot();
        System.out.println();
        lista.laskeSummaJaKeskiarvo();
    }  
}
class KaksisuuntainenLinkitettyLista {    
    Alkio ekaAlkio, vikaAlkio = null; 	//Asetetaan oletuksena eka ja vika alkio arvoksi null
    //Metodi alkion lisäämiselle
    public void lisaaAlkio(int arvo) {  
        Alkio uusiAlkio = new Alkio(arvo);  
        if(ekaAlkio == null) {  
        	ekaAlkio = vikaAlkio = uusiAlkio;  	//Jos lista on tyhjä niin eka ja vika alkio ovat automaattisesti uusiAlkio
        	ekaAlkio.edellinen = null;  		//Näin ollen myös ekan edellinen ja vikan seuraava on null, sillä niitä ei vielä ole
        	vikaAlkio.seuraava = null;  
        } 
        else {  
        	vikaAlkio.seuraava = uusiAlkio;  	//Jos lista ei ole tyhjä, aseteteen uusi alkio listan loppuun eli vikan seuraavaksi
        	uusiAlkio.edellinen = vikaAlkio;  	//Samoin asetetaan uuden alkion edelliseksi vanha viimeinen alkio
            vikaAlkio = uusiAlkio;  			//Nyt siis vika alkio on uusi alkio
            vikaAlkio.seuraava = null;  		//Edelleen vikan seuraava on null, sitä ei ole
        }  
    } public void tulostaAlkiot() {  
        Alkio alkio = ekaAlkio;  
        if(ekaAlkio == null) {  
            System.out.println("Lista on tyhj");  
            return;  
        } 
        System.out.println("Listan alkiot: ");  
        while(alkio != null) {   				//Käydään lista läpi ja tulostetaan alusta loppuun
            System.out.print(alkio.arvo + " ");  
            alkio = alkio.seuraava;  
        }  
        Alkio alkio2 = vikaAlkio;
        System.out.println("\nListan alkiot lopusta alkuun: "); 
        while(alkio2 != null) {   				//Käydään lista taas läpi ja tulostetaan lopusta alkuun
            System.out.print(alkio2.arvo + " ");  
            alkio2 = alkio2.edellinen;  
        }  
    }  
    //Käydään lista läpi ja lasketaan alkioiden summa ja keskiarvo
    public void laskeSummaJaKeskiarvo() {
    	int summa = 0;
    	int laskuri = 0;
    	double keskiarvo = 0;
    	Alkio a = ekaAlkio;
    	
        while (a != null) {
           summa += a.arvo;
           a = a.seuraava;
           laskuri += 1.0;
        }
        keskiarvo = Double.valueOf(summa) / laskuri;
        System.out.println("\nSumma: " + summa + "\nKeskiarvo: " + (Math.round(keskiarvo*100.0)/100.0));
    }
}
