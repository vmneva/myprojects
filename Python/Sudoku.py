# Ohjelmassa tarkastetaan sudokun rivien ja sarakkeiden lisäksi se, että jokaisessa 3x3-kokoisessa ”pikkuruuukossa” luvut
# 1-9 esiintyvät korkeintaan kerran. Koko 9x9 –kokoisen ruudukon voi siis ajatella koostuvan yhdeksästä pikkuruudukosta. 
# Ohjelmassa on valmiina kaksi sudokua, ylempi on oikein ja alempi väärin.

# tämä sudoku on oikein:
sudoku =    [[7, 3, 0, 0, 1, 4, 8, 9, 2],
            [8, 4, 2, 9, 7, 3, 5, 6, 1],
            [9, 6, 1, 2, 8, 5, 3, 7, 4],
            [0, 8, 6, 3, 4, 9, 1, 5, 7],
            [0, 1, 3, 8, 5, 7, 9, 2, 6],
            [5, 7, 9, 1, 2, 6, 4, 3, 8],
            [1, 5, 7, 4, 9, 2, 6, 8, 3],
            [6, 9, 4, 7, 3, 8, 2, 1, 5],
            [3, 2, 8, 5, 6, 1, 7, 4, 9]]

# tämä sudoku on väärin:
sudoku2 =   [[7, 3, 3, 0, 1, 4, 8, 9, 2],
            [8, 4, 2, 9, 7, 3, 5, 6, 1],
            [9, 6, 1, 2, 8, 5, 3, 8, 4],
            [0, 8, 6, 3, 4, 9, 1, 5, 7],
            [0, 1, 3, 8, 5, 7, 9, 2, 6],
            [5, 7, 9, 1, 2, 6, 4, 3, 8],
            [1, 5, 5, 4, 9, 2, 6, 8, 3],
            [6, 9, 4, 7, 3, 8, 2, 1, 5],
            [3, 2, 8, 5, 6, 1, 7, 4, 9]]

def tarkista_sudoku(sudoku):
    for rivi in sudoku:
        if not tarkista_rivi(rivi):
            return False

    for i in range(len(sudoku)):
        sarake = []
        for rivi in sudoku:
            sarake.append(rivi[i])
        if not tarkista_rivi(sarake):
            return False
    
    return True

def tarkista_rivi(rivi):
    tarkistus = []
    for luku in rivi:
        if luku != 0 and luku in tarkistus:
            return False
        else: 
            tarkistus.append(luku)
    
    return True

print("Sudokusi näyttää tältä:")
for rivi in sudoku:
    print(rivi)
if tarkista_sudoku(sudoku):
    print("Sudoku on oikein!")
else:
    print("Sudoku ei ole oikein")
