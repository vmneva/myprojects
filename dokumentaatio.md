## Dokumentaatio - Seiska

# Aihe ja kuvaus

Harjoitustyössä tehtiin ohjelma, jolla 2-7 pelaajaa pystyy pelaamaan Seiska-korttipeliä. 

Kun käyttäjä aloittaa ohjelman, tulostuu näytölle ensin tiedostosta saannot.txt pelin säännöt. Tämän 
jälkeen ohjelma aloittaa pelin kysymällä pelaajien määrän. Ohjelmassa rakennetaan 52 kortin pakka ja 
se sekoitetaan. Mikäli pelaajia on yli 6, käytössä on kaksi pakkaa. Tämän jälkeen pakasta jaetaan 7 
korttia jokaiselle pelaajalle ja peli pääsee alkamaan.

Pelissä jokainen pelaaja lyö vuorollaan pöytään kortin, jolla on sama numero tai maa kuin pöydän 
päälimmäisella kortilla. Pelin voittaa se, jonka käsi on ensimmäisenä tyhjä. Kun peli on päättynyt, 
ohjelma kirjoittaa tiedostoon vikatkortit.txt pelaajien käteen jäävät kortit.

Seiska ja ässä ovat erikoiskortteja, joista seiska käy minkä kortin päälle vain ja sen lyömisen jälkeen 
pelaaja päättää uuden maan pöytään. Ässän lyömisen jälkeen seuraava pelaaja nostaa kolme korttia, 
mutta muuten ässä pelataan normaalisti.

# Työn rakenne

Ohjelmassa käytettiin paljon eri listoja kuvaamaan niin korttipakkaa, pelaajien käsikortteja kuin
pöydässä olevia korttejakin. Ehtorakenteen if-elif-else avulla tarkistetaan toteutuvatko erinäiset ehdot 
ohjelmassa, kuten kortin oikeellisuus tai erikoiskorttien toiminnot. Pelin kulun seuraamiseen 
käytetään booleania, kun peli on käynnissä on arvo True ja kun peli päättyy, on arvo False. Ohjelmassa 
myös luetaan tiedostosta saannot.txt ohjeet pelille sekä kirjoitetaan tiedostoon vikatkortit.txt pelin 
päättyessä.

# Funktiot ja kirjastot

Funktiot ohjelmassa ovat:

- rakenna_pakka(), joka luo pakan neljästä maasta ja 14 numerosta
- sekoita(), joka sekoittaa luodun pakan
- jaa(), joka jakaa määrän X kortteja pelaajalle
- nayta_kasi(), joka tulostaa pelaajan sen hetkisen käden, esim.

        Pelaajan 1 vuoro.
        Sinun korttisi:
        1) pata 13
        2) risti ässä
        3) hertta 6
        4) pata 6
        5) risti 6
        6) hertta 2
        7) ruutu 11
- voiko_pelata(), joka tarkistaa käykö pelaajan korteista jokin pöytään

Ulkoisina kirjastoina ohjelmassa käytettiin vain random-kirjastoa
