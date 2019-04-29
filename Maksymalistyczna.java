package optymalnaBudowa;

/**
 * Odpowiada za realizacje strategii maksymalistycznej.
 * <p>
 * Implementuje metodê rozró¿niaj¹c¹ strategie minimalistyczn¹ od
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
     * Zawsze zwraca najd³u¿szy prêt z cennika którego po³o¿enie znamy gdy¿ prêty w
     * cenniku s¹ posortowane rosn¹co.
     */
    public Pret wybierzPretZCennika(int najdluzszyBrakujacyOdcinek) {

        return cennik[cennik.length - 1];

    }
}
