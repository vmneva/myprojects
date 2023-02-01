# Ohjelmassa, kysytään asiakkaalta tietoja mökkkivarausta varten. Huomioidaan seuraavat seikat:
    # Tarjolla on pieni sekä iso mökki. Pieneen mökkiin mahtuu maksimissaan 4 henkilöä.
    # Mikäli asiakkaalla on klubikortti, saa hän 25% alennusta varauksesta.
    # Hotellin sesonkiaikoja ovat joulukuu sekä heinäkuu, tällöin hinnat ovat 20% kalliimmat ja minimivarausaika on 7vrk.
# Lopuksi ohjelma tulostaa asiakkaalle tiedot varauksesta.

hlo_maara = int(input("Anna henkilömäärä varausta varten: "))
pvm = input("Milloin varauksenne alkaisi? Syötä päivämäärä muodossa pv.kk. : ")
alennus = input("Onko sinulla klubukorttia? (k/e): ")

if pvm[3:5] == "07" or pvm[3:5] == "12": # jos kuukausi on siis 07 tai 12 (heinä- tai joulukuu) on sesonkiaika
    kesto = int(input("Sesonkiaikana varaus on mahdollista tehdä vähintään 7 vuorokaudeksi. Kestääkö varauksenne siis kuinka kauan? "))
    hinta = 50 * kesto * 1.20
else:
    kesto = int(input("Kuinka kauan varauksenne kestää? Minimivuokrausaika on 3vrk. "))
    hinta = 50 * kesto


if hlo_maara <= 4:
    hinta = hinta * 4 # mikäli henkilöitä on enintään 4, tarjotaan pientä mökkiä
else:
    hinta = hinta * hlo_maara


if alennus == "k":
    hinta = hinta * 0.75
    print(f"Varauksenne hinta {hlo_maara} :lle henkilölle {kesto} vuorokaudeksi {pvm} alkaen on {hinta} euroa")
else:
    print(f"Varauksenne hinta {hlo_maara} :lle henkilölle {kesto} vuorokaudeksi {pvm} alkaen on {hinta} euroa")
