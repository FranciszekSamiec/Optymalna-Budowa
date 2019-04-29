package optymalnaBudowa;

import java.util.ArrayList;

/**
 * Wspólna nadklasa dla strategii ekonomicznej i ekologicznej.
 * <p>
 * Klasa odpowiada za realizacje optymalnych strategii. Metoda
 * sprawdzWszystkieKonfiguracje(Pret p, boolean[] ktoreBrac, Wynik wynik, int
 * zaglebienie, Wynik kandydat) sprawdza wszystkie mo¿liwe sposoby zakupu prêtów
 * i ich podzia³u aktualizuj¹c wynik w zale¿noœci od wyniku metody
 * kopiujOptymalnyWynik(wynik, kandydat) która jest abstrakcyjna.
 * 
 * @author fs395216
 *
 */
public abstract class Optymalna extends Strategia {

    /**
     * atrybuty cennik i opisProjektu to atrubuty wejœcia takie jak w specyfikacji
     * zdania.
     */
    private final Pret[] cennik;
    private final int[] opisProjektu;

    public Optymalna(Wejscie wejscie) {
        cennik = wejscie.dajCennik();
        opisProjektu = wejscie.dajOpisProjektu();
    }

    /**
     * Sprawdza czy dany ci¹g wartoœci typu boolean (odpowiadaj¹cych wyborom prêtów
     * z opisu projektu) jest sensowny to znaczy czy d³ugoœci prêtów sumuj¹ siê do
     * wartoœci mniejszej lub równej d³ugoœci prêta z cennika oraz czy nie zostaje
     * zbyt du¿y odpad czyli czy nie mieœci sie jeszcze jeden prêz z opisu projektu.
     * 
     * @param s            ci¹g zero jedynkowy mówi¹cy które z pozosta³ych prêtów
     *                     nale¿y wyci¹æ z prêta p
     * @param p            prêt z cennika z którego bêd¹ wycinane prêty realizuj¹ce
     *                     projekt
     * @param ktoreZabrane tablica mówi¹ca które prêty z opisu ju¿ s¹ wyciête
     * @return je¿eli ci¹g spe³nia dwa wymagania przedstawione w opise to zwracana
     *         jest wartoœæ true w przeciwnym przypadku dostajemy false
     */
    public boolean czyOptymalnyCiag(String s, Pret p, boolean[] ktoreZabrane) {
        int zmienna = p.dajDlugosc();

        int j = 0;
        int indeksZera = 0;
        boolean pierwszy = true;

        for (int i = 0; i < opisProjektu.length; i++) {
            if (ktoreZabrane[i] == false) {
                if (s.charAt(j) == '1') {
                    zmienna -= opisProjektu[i];
                    j++;
                } else {
                    if (pierwszy)
                        indeksZera = i;
                    pierwszy = false;
                    j++;
                }
            }
        }

        if (zmienna < 0)
            return false;

        if (pierwszy == false) {
            if (indeksZera < opisProjektu.length && opisProjektu[indeksZera] <= zmienna)
                return false;
        }

        return true;
    }

    /**
     * Generuje ci¹g zer i jedynek na podstawie którego dokonaj¹ siê wybory prêtów z
     * opisu projektu (tzn. które prêty nale¿a³o bêdzie wyci¹æ z pewnego prêta z
     * cennika).
     * 
     * @param n            d³ugoœæ ci¹gów zero jedynkowych
     * @param p            prêt z cennika na podstawie którego bêdzie oceniana
     *                     optymalnoœæ ci¹gów
     * @param ktoreZabrane tablica mówi¹ca którê prêty ju¿ zosta³y wyciête
     * @return zwracana jest lista sensownych napisów zerojedynkowych
     */
    public ArrayList<String> dajPodzbiory(int n, Pret p, boolean[] ktoreZabrane) {

        ArrayList<String> tab = new ArrayList<String>();

        tab.add("0");
        tab.add("1");

        int i, j;
        for (i = 2; i < (1 << n); i = i << 1) {

            for (j = i - 1; j >= 0; j--)
                tab.add(tab.get(j));

            for (j = 0; j < i; j++)
                tab.set(j, "0" + tab.get(j));

            for (j = i; j < 2 * i; j++)
                tab.set(j, "1" + tab.get(j));
        }

        int rozmiar = tab.size();
        for (int k = 0; k < rozmiar;) {

            if (czyOptymalnyCiag(tab.get(k), p, ktoreZabrane) == false) {
                tab.remove(k);
                rozmiar--;
            } else {
                k++;
            }

        }

        return tab;

    }

    /**
     * Zwraca odpowiedz na pytanie czy wszystkie prêty z opisu projektu s¹
     * zrealizowane.
     * 
     * @param opisProjektu
     * @return
     */
    public boolean czyZostalPret(boolean[] opisProjektu) {
        boolean wynik = false;
        for (boolean i : opisProjektu) {
            if (i == false)
                wynik = true;
        }
        return wynik;
    }

    /**
     * Stanowi przygotowanie do rekurecnji przechodz¹cej przez wszystkie
     * konfiguracje zakupu i podzia³u prêtów.
     * <p>
     * Wynik to zmienna która przechodz¹c przez drzewo mo¿liwoœci zbiera dane
     * wyjœciowe a kandydat to zmienna pod któr¹ zapisuje siê po¿¹dany wynik w
     * zale¿noœci od implementacji metody kopiujOptymalnyWynik z klasy Wynik.
     */
    @Override
    public void przeprowadzOptymalizacje() {
        boolean[] ktoreBrac = new boolean[opisProjektu.length];
        Wynik wynik = new Wynik();
        // Wynik kandydat = new Wynik(Integer.MAX_VALUE);
        Wynik kandydat = new Wynik();

        for (int j = 0; j < cennik.length; j++) {
            for (int g = 0; g < ktoreBrac.length; g++) {
                ktoreBrac[g] = false;
            }
            sprawdzWszystkieKonfiguracje(cennik[j], ktoreBrac, wynik, 0, kandydat);
        }

        System.out.println();
        kandydat.wypiszWynik();

    }

    /**
     * Przechodzi rekurencyjnie przez wszystkie mo¿liwe sposoby realizacji projektu.
     * Na ka¿dym kolejnym poziomie generowane s¹ wszystkie ci¹gi zero jedynkowe
     * d³ugoœci "ileZer" gdzie "ileZer" oznacza liczbe nie zrealizowanych jeszcze
     * prêtów z opisu projektu. Tablica ktoreBracKopia jest potrzebna do
     * oddtwarzania oryginalnego ci¹gu przed przejœciem do wyboru kolejnego prêta.
     * Metoda kopiuj Optymalny wynik zapisuje optymalny wynik pod zmienn¹ kandydat.
     * 
     * @param p                     kolejny prêt z cennika który kupimy
     * @param ktoreBrac             tablica mówi¹ca które prêty wytniemy z prêta p
     * @param wynik                 zmienna przechowuj¹ca aktualny stan wyniku
     * @param zaglebienie
     * @param kandydat              pod t¹ zmienn¹ zapisze siê optymalny wynik
     * @param czyPierwszePorownanie
     */
    public void sprawdzWszystkieKonfiguracje(Pret p, boolean[] ktoreBrac, Wynik wynik,
            int zaglebienie, Wynik kandydat) {

        if (czyZostalPret(ktoreBrac)) {

            wynik.dodajKoszt(p);

            int ileZer = 0;

            boolean[] ktoreZabrane = new boolean[ktoreBrac.length];
            for (int a = 0; a < ktoreBrac.length; a++) {
                if (ktoreBrac[a] == false)
                    ileZer++;
                ktoreZabrane[a] = ktoreBrac[a];
            }

            ArrayList<String> tab = dajPodzbiory(ileZer, p, ktoreZabrane);

            boolean czyJestZmiana = false;
            for (int j = 0; j < tab.size(); j++) {

                int b = 0;
                boolean[] ktoreBracKopia = new boolean[ktoreBrac.length];
                for (int k = 0; k < ktoreBrac.length; k++) {

                    ktoreBrac[k] = ktoreZabrane[k];

                    if (ktoreBrac[k] == false) {
                        if (tab.get(j).charAt(b) == '1') {
                            ktoreBrac[k] = true;
                            czyJestZmiana = true;

                        }
                        b++;

                    }
                    ktoreBracKopia[k] = ktoreBrac[k];
                }

                if (czyJestZmiana) {
                    wynik.dodajPodzialPreta(ktoreBrac, ktoreZabrane, p, zaglebienie, opisProjektu);
                    int ileDodano = wynik.dodajDlugoscOdpadow(ktoreBrac, ktoreZabrane, p,
                            opisProjektu);
                    for (int i = 0; i < cennik.length; i++) {

                        for (int f = 0; f < ktoreBrac.length; f++) {
                            ktoreBrac[f] = ktoreBracKopia[f];
                        }

                        sprawdzWszystkieKonfiguracje(cennik[i], ktoreBrac, wynik, zaglebienie + 1,
                                kandydat);

                    }
                    wynik.odejmijDlugoscOdpadow(ileDodano);
                    wynik.usunPodzialPreta(wynik.dajSposobPodzialu().size() - 1);
                }
                czyJestZmiana = false;

            }
            wynik.odejmijKoszt(p);
        } else {
            if (wynik.dajCzyPierwszePorownanie()) {
                kandydat.kopiuj(wynik);
                wynik.ustawCzyPierwszePorownanie(false);
            }
            kopiujOptymalnyWynik(wynik, kandydat);
        }

    }

    public abstract void kopiujOptymalnyWynik(Wynik wynik, Wynik kandydat);

}
