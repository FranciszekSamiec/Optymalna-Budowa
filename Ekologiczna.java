package optymalnaBudowa;

/**
 * Realizuje strategi� ekonomiczn� implementuj�c odpowiednio metod�
 * kopiujOptymalnyWynik(Wynik wynik, Wynik kandydat).
 * 
 * @author fs395216
 *
 */
public class Ekologiczna extends Optymalna {

    public Ekologiczna(Wejscie wejscie) {
        super(wejscie);
    }

    /**
     * Kopiuje lepszy pod wzgl�dem d�ugo�ci odpad�w wynik
     */
    @Override
    public void kopiujOptymalnyWynik(Wynik wynik, Wynik kandydat) {

        // if (wynik.dajDlugoscOdpadow() < kandydat.dajDlugoscOdpadow()) {
        // kandydat.kopiuj(wynik);
        // }

        if (wynik.dajDlugoscOdpadow().compareTo(kandydat.dajDlugoscOdpadow()) == -1) {
            kandydat.kopiuj(wynik);
        }

    }

}
