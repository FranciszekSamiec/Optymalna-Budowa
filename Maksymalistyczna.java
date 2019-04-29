package optymalnaBudowa;

/**
 * Odpowiada za realizacje strategii maksymalistycznej.
 * <p>
 * Implementuje metod� rozr�niaj�c� strategie minimalistyczn� od
 * maksymalistycznej.
 * 
 * @author fs395216
 *
 */
public class Maksymalistyczna extends Prymitywna {
    private final Pret[] cennik;

    Maksymalistyczna(Wejscie wejscie) {
        super(wejscie);
        cennik = wejscie.dajCennik();
    }

    /**
     * Zawsze zwraca najd�u�szy pr�t z cennika kt�rego po�o�enie znamy gdy� pr�ty w
     * cenniku s� posortowane rosn�co.
     */
    public Pret wybierzPretZCennika(int najdluzszyBrakujacyOdcinek) {

        return cennik[cennik.length - 1];

    }
}
