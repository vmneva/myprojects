package fi.utu.tech.ooj.exercise3;
import fi.utu.tech.ooj.exercise3.tehtava5.Säähavainto;

import java.util.ArrayList;

public class SäähavaintoMain {
    public static void main(String[] args) throws Exception {
        // Luodaan kymmenen säähavaintoa listaksi, järjestetään ne molemmilla metodeilla ja tulostetaan.
        ArrayList<Säähavainto> havainnot = new ArrayList<Säähavainto>();
        for (int i = 0; i < 10; i++) {
            Säähavainto sh = Säähavainto.keksi();
            havainnot.add(sh);
        }
        System.out.println("Lista ennen järjestystä:");
        for (Säähavainto havainto : havainnot) {
            System.out.println(havainto.toString());
        }
        System.out.println("\nJärjestetään lista aikaleiman mukaisesti uusimmasta vanhimpaan:");
        ArrayList<Säähavainto> järjestetyt1 = Säähavainto.järjestäAikaleimanMukaisesti(havainnot);
        for (Säähavainto havainto : järjestetyt1) {
            System.out.println(havainto.toString());
        }
        System.out.println("\nJärjestetään lista sijainnin mukaan, ensisijaisesti pituusaste ja toissijaisesti leveysaste:");
        ArrayList<Säähavainto> järjestetyt2 = Säähavainto.järjestäAsteidenMukaisesti(havainnot);
        for (Säähavainto havainto : järjestetyt2) {
            System.out.println(havainto.toString());
        }


    }
}
