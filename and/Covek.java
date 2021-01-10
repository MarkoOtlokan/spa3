public class Covek{
  String ime, prezime;
  Automobil[] automobili;
  int trenutniBroj = 0;

  Covek(String i, String p, int brojAutomobila){
    ime = i;
    prezime = p;
    automobili = new Automobil[brojAutomobila];
  }

  public void dodajAutomobil(Automobil a){
    automobili[trenutniBroj] = a;
    trenutniBroj++;
  }

  public String ispisiAutomobile(){
    String text = "";
    for(int i = 0; i<trenutniBroj; i++){
      text = text + " | "+automobili[i].toString()+" | ";
    }
    return text;
  }

	@Override
	public String toString() {
		return "Covek " +ime+ " "+prezime+" "+ ispisiAutomobile();
	}
}
