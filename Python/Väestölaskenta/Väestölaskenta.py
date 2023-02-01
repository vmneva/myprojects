# Aineistossa vaestolaskenta.csv on n. 0.1 % satunnainen otos suomalaisista eri vuosilta. Aineisto on tilastokeskuksen julkaisema ja vapaasti
# käytettävissä. Dokumentissa vaestolaskentapaneeli_kuvaus.pdf on kuvattu CSV-tiedoston sisältö.

# Ohjelmassa käyttäjä voi valita tulostukseen:
#   1. Lukumäärän naisista, joiden äidinkieleksi on merkattu suomi
#   2. Lukumäärän henkilöistä, joiden perheen koko on vähintään 4
#   3. Tiedon Etelä-Suomen alueen henkilöistä
#   4. Tiedon Länsi-Suomen sekä Ahvenanmaan alueen henkilöistä
#   5. Tiedon Itä-Suomen alueen henkilöistä
#   6. Tiedon Pohjois-Suomen alueen henkilöistä

with open("vaestolaskenta_data.csv") as tiedosto:
    sisalto = tiedosto.readlines()
    naiset_suomi_aidinkielena = 0
    perheet = 0
    etela_suomi = 0
    lansi_suomi = 0
    ita_suomi = 0
    pohjois_suomi = 0

    for i in range(len(sisalto)):
        palat = sisalto[i].split(",")
        
        #Naiset, joiden äidinkielenä suomi:
        if "2" in palat[2] and "1" in palat[4] and "2010" in palat[1]:
            naiset_suomi_aidinkielena += 1
    
        #Henkilöt, joiden perheen koko on väh. 4hlö:
        if "4" in palat[6] or "5" in palat[6] or "6" in palat[6] or "7" in palat[6] and "2010" in palat[1]:
            perheet += 1

        #Jakaumat alueen mukaan:
        if "1" in palat[7] and "2010" in palat[1]:
            etela_suomi += 1
        elif "2" in palat[7] and "2010" in palat[1]:
            lansi_suomi += 1
        elif "3" in palat[7] and "2010" in palat[1]:
            ita_suomi += 1
        elif "4" in palat[7] and "2010" in palat[1]:
            pohjois_suomi += 1        
        
    yht = etela_suomi+lansi_suomi+ita_suomi+pohjois_suomi

    while True:
        print("Mitä haluat tietää tiedostosta? Tyhjä valinta lopettaa ohjelman.")
        print("1. Lukumäärän naisista, joiden äidinkieleksi on merkattu suomi")
        print("2. Lukumäärän henkilöistä, joiden perheen koko on vähintään 4")
        print("3. Tiedon Etelä-Suomen alueen henkilöistä")
        print("4. Tiedon Länsi-Suomen sekä Ahvenanmaan alueen henkilöistä")
        print("5. Tiedon Itä-Suomen alueen henkilöistä")
        print("6. Tiedon Pohjois-Suomen alueen henkilöistä")
        valinta = input("Anna numero: ")

        if valinta == "1":
            print(f"Tiedostosta löytyy {naiset_suomi_aidinkielena} kpl henkilöitä, joiden sukupuolena on nainen ja äidinkielenä suomi.")
        elif valinta == "2":
            print(f"Tiedostosta löytyy {perheet} kpl henkilöitä, joiden perheessä on vähintään 4 henkilöä.")
        elif valinta == "3":
            print(f"Tiedoston henkilöistä {(etela_suomi/yht)*100:.2f} % eli {etela_suomi} kpl on Etelä-Suomesta.")
        elif valinta == "4":
            print(f"Tiedoston henkilöistä {(lansi_suomi/yht)*100:.2f} % eli {lansi_suomi} kpl on Länsi-Suomesta tai Ahvenanmaalta.")
        elif valinta == "5":
            print(f"Tiedoston henkilöistä {(ita_suomi/yht)*100:.2f} % eli {ita_suomi} kpl on Itä-Suomesta.")
        elif valinta == "6":
            print(f"Tiedoston henkilöistä {(pohjois_suomi/yht)*100:.2f} % eli {pohjois_suomi} kpl on Pohjois-Suomesta.")
        
        if valinta == "":
            print("Kiitos!")
            break
