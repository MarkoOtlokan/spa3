public class Automobil{
  int maxBrzina, cena, brojSedista, potrosnja;
  String marka, model;

  Automobil(int brzina, int c, int bS, int p, String m, String mo){
    maxBrzina = brzina;
    cena = c;
    brojSedista = bS;
    potrosnja = p;
    marka = m;
    model = mo;
  }

	@Override
	public String toString() {
		return "Automobil marke "+marka+" model "+model + " cena "+cena;
	}

}
