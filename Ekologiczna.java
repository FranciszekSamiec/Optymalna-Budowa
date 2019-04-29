package optymalnaBudowa;

/**
 * Realizuje strategiê ekonomiczn¹ implementuj¹c odpowiednio metodê
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
     * Kopiuje lepszy pod wzglêdem d³ugoœci odpadów wynik
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
