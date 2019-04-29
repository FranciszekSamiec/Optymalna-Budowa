package optymalnaBudowa;

/**
 * Implementuje metodê odpowiedzialn¹ za znajdowanie ekonomicznego rozwi¹zania
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
