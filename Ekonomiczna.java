package optymalnaBudowa;

/**
 * Implementuje metod� odpowiedzialn� za znajdowanie ekonomicznego rozwi�zania
 * zadania.
 * 
 * @author fs395216
 *
 */
public class Ekonomiczna extends Optymalna {

    public Ekonomiczna(Wejscie wejscie) {
        super(wejscie);
    }

    @Override
    public void kopiujOptymalnyWynik(Wynik wynik, Wynik kandydat) {

        // if (wynik.dajKosztZakupuPretow() < kandydat.dajKosztZakupuPretow()) {
        // kandydat.kopiuj(wynik);
        // }

        if (wynik.dajKosztZakupuPretow().compareTo(kandydat.dajKosztZakupuPretow()) == -1) {
            kandydat.kopiuj(wynik);
        }

    }

}
