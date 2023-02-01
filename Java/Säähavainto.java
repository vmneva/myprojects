package fi.utu.tech.ooj.exercise3.tehtava5;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.*;

// Koodiin on merkattu ne kohdat, jotka eivät ole minun tekemiäni.

public class Säähavainto implements Comparable {
    // oletetaan muodoksi YYYY/MM/DD
    // esim. keskiyö 1.1.2000 on muotoa 2000/01/01
    private String aika;
    private String paikka;
    private float lämpotila;
    private boolean sataako;
    private float pituusaste;
    private float leveysaste;

    public Säähavainto(String aika,
                       String paikka,
                       float pituusaste,
                       float leveysaste,
                       float lämpotila,
                       boolean sataako) {
        this.aika = aika;
        this.paikka = paikka;
        this.pituusaste = pituusaste;
        this.leveysaste = leveysaste;
        this.lämpotila = lämpotila;
        this.sataako = sataako;
    }

    public void setPituusaste(float pituusaste) {
        this.pituusaste = pituusaste;
    }

    public float getPituusaste() {
        return pituusaste;
    }

    public void setLeveysaste(float leveysaste) {
        this.leveysaste = leveysaste;
    }

    public float getLeveysaste() {
        return leveysaste;
    }

    @Override
    public String toString() {
        return String.format(
                "Havainto %s (%.2f°N, %.2f°E) @ %s: lämpö %.2f°C, %s",
                paikka,
                pituusaste,
                leveysaste,
                aika,
                lämpotila,
                sataako ? "sataa" : "ei sada"
        );
    }

    private enum Kunta {
        Oulu,
        Turku,
        Tampere,
        Kuhmo
    }
    // Tämä funktio ei ole tekemäni, se oli valmiina tehtävässä
    public static Säähavainto keksi() {
        var päivä = new Random().nextInt(28) + 1;
        var kuukausi = new Random().nextInt(12) + 1;
        var vuosi = new Random().nextInt(100) + 1922;

        return new Säähavainto(
                String.format("%04d/%02d/%02d", vuosi, kuukausi, päivä),
                Kunta.values()[new Random().nextInt(4)].name(),
                new Random().nextFloat() * 360 - 180,
                new Random().nextFloat() * 360 - 180,
                new Random().nextFloat() * 80 - 40,
                new Random().nextBoolean()
        );
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    /* Tämä funktio on minun toteuttama.
    Säähavainnot järjestetään aikaleiman mukaisesti uusimmasta vanhimpaan */
    public static ArrayList<Säähavainto> järjestäAikaleimanMukaisesti(ArrayList<Säähavainto> havainnot) throws ParseException {
        ArrayList<String> aikaleimat = new ArrayList<>();
        for (Säähavainto sh : havainnot) {
            aikaleimat.add(sh.aika);
        }
        Collections.sort(aikaleimat, new Comparator<String>() {
            DateFormat f = new SimpleDateFormat("YYYY/MM/DD");
            @Override
            public int compare(String a, String b) {
                try {
                    return f.parse(a).compareTo(f.parse(b));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        ArrayList<Säähavainto> järjestetyt = new ArrayList<>();
        for (String leima : aikaleimat) {
            for (Säähavainto havainto : havainnot) {
                if (leima == havainto.aika) {
                    järjestetyt.add(havainto);
                }
            }
        }
        Collections.reverse(järjestetyt);
        return järjestetyt;
    }
    /* Tämä funktio on toteuttamani.
    Säähavainnot järjestetään ensisijaisesti pituusasteenmukaan ja toissijaisesti leveysasteen mukaan */
    public static ArrayList<Säähavainto> järjestäAsteidenMukaisesti(ArrayList<Säähavainto> havainnot) {
        Comparator<Säähavainto> vertaaAsteita = Comparator
                .comparing(Säähavainto::getPituusaste)
                .thenComparing(Säähavainto::getLeveysaste);
        ArrayList<Säähavainto> järjestetytAsteidenmukaan = (ArrayList<Säähavainto>) havainnot.stream()
                .sorted(vertaaAsteita)
                .collect(Collectors.toList());

        return järjestetytAsteidenmukaan;
    }



}
