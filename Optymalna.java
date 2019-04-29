package optymalnaBudowa;

import java.util.ArrayList;

/**
 * Wsp�lna nadklasa dla strategii ekonomicznej i ekologicznej.
 * <p>
 * Klasa odpowiada za realizacje optymalnych strategii. Metoda
 * sprawdzWszystkieKonfiguracje(Pret p, boolean[] ktoreBrac, Wynik wynik, int
 * zaglebienie, Wynik kandydat) sprawdza wszystkie mo�liwe sposoby zakupu pr�t�w
 * i ich podzia�u aktualizuj�c wynik w zale�no�ci od wyniku metody
 * kopiujOptymalnyWynik(wynik, kandydat) kt�ra jest abstrakcyjna.
 * 
 * @author fs395216
 *
 */
public abstract class Optymalna extends Strategia {

    /**
     * atrybuty cennik i opisProjektu to atrubuty wej�cia takie jak w specyfikacji
     * zdania.
     */
    private final Pret[] cennik;
    private final int[] opisProjektu;

    public Optymalna(Wejscie wejscie) {
        cennik = wejscie.dajCennik();
        opisProjektu = wejscie.dajOpisProjektu();
    }

    /**
     * Sprawdza czy dany ci�g warto�ci typu boolean (odpowiadaj�cych wyborom pr�t�w
     * z opisu projektu) jest sensowny to znaczy czy d�ugo�ci pr�t�w sumuj� si� do
     * warto�ci mniejszej lub r�wnej d�ugo�ci pr�ta z cennika oraz czy nie zostaje
     * zbyt du�y odpad czyli czy nie mie�ci sie jeszcze jeden pr�z z opisu projektu.
     * 
     * @param s            ci�g zero jedynkowy m�wi�cy kt�re z pozosta�ych pr�t�w
     *                     nale�y wyci�� z pr�ta p
     * @param p            pr�t z cennika z kt�rego b�d� wycinane pr�ty realizuj�ce
     *                     projekt
     * @param ktoreZabrane tablica m�wi�ca kt�re pr�ty z opisu ju� s� wyci�te
     * @return je�eli ci�g spe�nia dwa wymagania przedstawione w opise to zwracana
     *         jest warto�� true w przeciwnym przypadku dostajemy false
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
     * Generuje ci�g zer i jedynek na podstawie kt�rego dokonaj� si� wybory pr�t�w z
     * opisu projektu (tzn. kt�re pr�ty nale�a�o b�dzie wyci�� z pewnego pr�ta z
     * cennika).
     * 
     * @param n            d�ugo�� ci�g�w zero jedynkowych
     * @param p            pr�t z cennika na podstawie kt�rego b�dzie oceniana
     *                     optymalno�� ci�g�w
     * @param ktoreZabrane tablica m�wi�ca kt�r� pr�ty ju� zosta�y wyci�te
     * @return zwracana jest lista sensownych napis�w zerojedynkowych
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
     * Zwraca odpowiedz na pytanie czy wszystkie pr�ty z opisu projektu s�
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
     * Stanowi przygotowanie do rekurecnji przechodz�cej przez wszystkie
     * konfiguracje zakupu i podzia�u pr�t�w.
     * <p>
     * Wynik to zmienna kt�ra przechodz�c przez drzewo mo�liwo�ci zbiera dane
     * wyj�ciowe a kandydat to zmienna pod kt�r� zapisuje si� po��dany wynik w
     * zale�no�ci od implementacji metody kopiujOptymalnyWynik z klasy Wynik.
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
     * Przechodzi rekurencyjnie przez wszystkie mo�liwe sposoby realizacji projektu.
     * Na ka�dym kolejnym poziomie generowane s� wszystkie ci�gi zero jedynkowe
     * d�ugo�ci "ileZer" gdzie "ileZer" oznacza liczbe nie zrealizowanych jeszcze
     * pr�t�w z opisu projektu. Tablica ktoreBracKopia jest potrzebna do
     * oddtwarzania oryginalnego ci�gu przed przej�ciem do wyboru kolejnego pr�ta.
     * Metoda kopiuj Optymalny wynik zapisuje optymalny wynik pod zmienn� kandydat.
     * 
     * @param p                     kolejny pr�t z cennika kt�ry kupimy
     * @param ktoreBrac             tablica m�wi�ca kt�re pr�ty wytniemy z pr�ta p
     * @param wynik                 zmienna przechowuj�ca aktualny stan wyniku
     * @param zaglebienie
     * @param kandydat              pod t� zmienn� zapisze si� optymalny wynik
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
