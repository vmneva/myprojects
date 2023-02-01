# Ohjelma pyytää käyttäjältä verkkosivun osoitteen muodossa ”https://” ja tulostaa sitten sivun otsikon.
# Ohjelma voisi toimia esimerkiksi näin:
# Anna sivun osoite: https://www.google.com
# Sivun otsikko on Google

while True:
    osoite = input("Anna verkkosivun osoite: ")

    if osoite == "lopeta":
        break

    if "https://" not in osoite:
        osoite = "https://" + osoite

    from urllib.request import urlopen
    sivu = urlopen(osoite)
    sisalto = str(sivu.read())

    osajono = sisalto.find("<title>") # etsitään osajono <title>
    osajono2 = sisalto.find("</title>") # etsitään osajono </title>
    osajono3 = sisalto[osajono:osajono2] # etsitään osajono joka on muotoa <title>otsikko

    otsikko = osajono3.replace("<title>", "") # poistetaan osajonosta3 <title> ja saadaan otsikko
    
    print("Sivun otsikko on", otsikko)
