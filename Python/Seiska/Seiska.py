#Ohjelma Seiska-korttipelin pelaamiselle. Ohjelmassa 2-7 pelaajaa voi pelata peliä ja voittaja on se, jonka käsi on ensimmäisenä tyhjä.
#Ohjelmassa käytetään kahta pakkaa mikäli pelaajia on yli 6.
#Käyttäjä valitsee pelattavan kortin kokonaislukuna annetuista korttien numeroista.
#Alussa ohjelma tulostaa pelin säännöt pelaajille.
#Lopussa ohjelma kirjoittaa pelaajien jäljelle jääneet kortit tiedostoon vikatkortit.txt

import random
maat = ["hertta", "ruutu", "pata", "risti"]
numerot = ["ässä", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"]
#Rakennetaan 52 kortin pakka neljästä maasta:
def rakenna_pakka() -> list:
    korttipakka = []
    for maa in maat:
        for numero in numerot:
            kortti = "{} {}".format(maa, numero)
            korttipakka.append(kortti)
    return korttipakka

#Sekoitetaan pakka:
def sekoita(pakka:list):
    return random.shuffle(pakka)

#Jaetaan haluttu määrä kortteja:
def jaa(pakka:list, maara:int) -> list:
    kasi = []
    for i in range(maara):
        kasi.append(pakka.pop(0))
    return kasi

#Näytetään listana pelaajan käsi:
def nayta_kasi(pelaaja:int, kasi:list):
    print(f"Pelaajan {pelaaja+1} vuoro.")
    print("Sinun korttisi:")
    print(" ")
    y = 1
    for kortti in kasi:
        print("{}) {}".format(y,kortti))
        y += 1
    print("")

#Funktio tarkistaa voiko pelaaja pelata kortin vai ei:
#Seiska käy minkä kortin päälle vain.
def voiko_pelata(maa:str, numero:str, kasi:list) -> bool:
    for kortti in kasi:
        if "7" in kortti:
            return True
        if maa in kortti or numero in kortti:
           return True
    return False

print("------------------")
print("Tervetuloa pelaamaan Seiskaa!")
print("------------------")
print("Luethan pelin säännöt ennen aloittamista: ")
print("")

#Tulostetaan säännöt ja muotoillaan ne normaaliin muotoon:
with open("saannot.txt") as saannot:
    sisalto = saannot.read()
    sisalto = sisalto.replace("Ã¤", "ä")
    sisalto = sisalto.replace("Ã¶", "ö")
    sisalto = sisalto.replace("„", "")
    sisalto = sisalto.replace("Ã–", "Ö")
print(sisalto)

poyta = []
pelaajat = []
print("")
print("Nyt kun säännöt ovat selvillä, voimme aloittaa pelin!")
lkm = int(input("Kuinka monta pelaajaa (2-7)? "))
print(" ")

#Käytetään yhtä tai kahta pakkaa riippuen pelaajien määrästä ja sekoitetaan:
if lkm < 7:
    korttipakka = rakenna_pakka()
    sekoita(korttipakka)
else:
    korttipakka = rakenna_pakka()
    korttipakka += rakenna_pakka()
    sekoita(korttipakka)

#Jaetaan pelaajille omat kortit:
i = 0
while lkm < 2 or lkm > 7:
    lkm = int(input("Virhe. Seiskaa voi pelata 2-7 henkilöä. Syötä lukumäärä uudelleen: "))
for pelaaja in range(lkm):
    pelaajat.append(jaa(korttipakka,7))
    i += 1

#Merkitään muuttujia pelaajan_vuoro ja pelin_suunta 0 ja 1, jotta niiden käyttö on yksinkertaista.
#Muuttuja peli on boolean, joka on tosi niin kauan kuin peli on käynnissä.
pelaajan_vuoro = 0
pelin_suunta = 1
peli = True
#Otetaan pakasta ensimmäinen kortti pöytään.
poyta.append(korttipakka.pop(0))
splitKortti = poyta[0].split(" ",1)

#Seiskalla tai ässällä ei voi aloittaa, tarkistetaan siis se. Ohjelma huomioi kaksi kertaa tämän ja kääntää siis max 2 uutta korttia:
if "7" in splitKortti or "ässä" in splitKortti:
    print(f"Aloituskortiksi tuli {splitKortti}. Otetaan uusi kortti.")
    korttipakka.append(splitKortti)
    poyta.append(korttipakka.pop(0))
    splitKortti = poyta[0].split(" ",1)
    if "7" in splitKortti or "ässä" in splitKortti:
        print(f"Aloituskortiksi tuli {splitKortti}. Otetaan uusi kortti.")
        poyta.append(korttipakka.pop(0))
        korttipakka.append(splitKortti)
        splitKortti = poyta[0].split(" ",1)

nykyinen_numero = splitKortti[1]
if nykyinen_numero != "7":
    nykyinen_maa = splitKortti[0]
else:
    nykyinen_maa = "Any"

print(f"Pöydän ensimmäinen kortti: {poyta[-1]}")
print("")
#Pelin kulku:
while peli:
    if len(korttipakka) < 3:
        print(f"Pakassa enää {len(korttipakka)} korttia, sekoitetaan pakka uudestaan.")
        if lkm < 7:
            korttipakka = rakenna_pakka()
            sekoita(korttipakka)
        else:
            korttipakka = rakenna_pakka()
            korttipakka += rakenna_pakka()
            sekoita(korttipakka)
    
    nayta_kasi(pelaajan_vuoro, pelaajat[pelaajan_vuoro])
    #Tarkistetaan funktion voiko_pelata() avulla onko pelaajan kädessä kelvollista korttia pöytään:
    if voiko_pelata(nykyinen_maa, nykyinen_numero, pelaajat[pelaajan_vuoro]):
        valittu_kortti = int(input("Minkä kortin haluat pelata? "))
        print(f"Pelasit {(pelaajat[pelaajan_vuoro][valittu_kortti-1])}")
        while not voiko_pelata(nykyinen_maa, nykyinen_numero, [pelaajat[pelaajan_vuoro][valittu_kortti-1]]):
            valittu_kortti = int(input("Kortti ei käy. Minkä kortin haluat pelata? "))
        poyta.append(pelaajat[pelaajan_vuoro].pop(valittu_kortti-1))
        #Tarkistus voittiko pelaaja, jos voitti niin peli päättyy:
        if len(pelaajat[pelaajan_vuoro]) == 0:
            peli = False
            voittaja = f"Pelaaja {pelaajan_vuoro+1}"
        else:   
            nykyinen_kortti = poyta[-1].split(" ",1)
            nykyinen_maa = nykyinen_kortti[0]
            nykyinen_numero = nykyinen_kortti[1]
            print("")
            print(f"Pöydässä tällä hetkellä {nykyinen_maa.upper()} {nykyinen_numero}")
            #Tarkistus erikoiskorttien eli seiskan ja ässän osalta:
            if nykyinen_numero == "7":
                for i in range(len(maat)):
                    print(f"{i+1}) {maat[i]}")
                uusi_maa = int(input("Minkä maan haluat valita? "))
                while uusi_maa < 1 or uusi_maa > 4:
                    uusi_maa = int(input("Virhe. Minkä maan haluat valita? "))
                nykyinen_maa = maat[uusi_maa-1]
                print("")
                print("*****")
                print(f"Uudeksi maaksi valittu {maat[uusi_maa-1]}")
                print("*****")
            elif nykyinen_numero == "ässä":
                nostava_pelaaja = pelaajan_vuoro+pelin_suunta
                if len(korttipakka) < 3:
                    print(f"Pakassa enää {len(korttipakka)} korttia, sekoitetaan pakka uudestaan.")
                    if lkm < 7:
                        korttipakka = rakenna_pakka()
                        sekoita(korttipakka)
                    else:
                        korttipakka = rakenna_pakka()
                        korttipakka += rakenna_pakka()
                        sekoita(korttipakka)
                if nostava_pelaaja == lkm:
                    nostava_pelaaja = 0
                elif nostava_pelaaja < 0:
                    nostava_pelaaja = lkm-1
                print(f"Pelaaja {nostava_pelaaja+1} nostaa 3 korttia.")
                pelaajat[nostava_pelaaja].extend(jaa(korttipakka, 3))
        
    #Jos pelaajalla ei ole käypiä kortteja, joutuu hän nostamaan max 3 korttia kunnes jokin niistä käy.        
    else:
        i = 0
        while i < 3:
            print("Et voi pelata, sinun tulee nostaa kortteja.")
            pelaajat[pelaajan_vuoro].extend(jaa(korttipakka, 1))
            i += 1
            if voiko_pelata(nykyinen_maa, nykyinen_numero, pelaajat[pelaajan_vuoro]):
                break
        print(" ")

    #Tarkistus siitä, jos vuorossa olevan pelaajan indeksi menee yli pelaajien lukumäärän. Tällöin indeksi palautuu nollaksi.
    pelaajan_vuoro += pelin_suunta
    if pelaajan_vuoro >= lkm:
        pelaajan_vuoro = 0
print("*************")
print("Peli loppui!")
print(f"{voittaja} voitti pelin!")
print("Kirjoitetaan vielä pelaajien käteen jääneet kortit vikatkortit.txt tiedostoon.")
print("-------------")
print("Kiitos pelaamisesta!")
print("-------------")
#Järjestetään pelaajat järjestykseen kädessä olevien korttien lkm perusteella.
#Kirjoitetaan jäljelle jääneiden korttien tiedot tiedostoon vikatkortit.txt:
pelaajat.sort(key=len)
with open("vikatkortit.txt", "w") as tiedosto:
    x = 1
    while x <= lkm:
        for i in range(len(pelaajat)):
            tiedosto.write(f"Pelaaja {x}: {str(pelaajat[i])}\n")
            x += 1
    if x > lkm:
        exit()
