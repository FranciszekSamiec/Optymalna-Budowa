package optymalnaBudowa;

/**
 * Klasa konkretna, odpowiada za realizacje strategii minimalistycznej.
 * <p>
 * Zawiera implementacjê metody odpowiedzialnej za wybór kolejnego prêta z
 * cennika.
 * 
 * @author fs395216
 *
 */
public class Minimalistyczna extends Prymitywna {
    private final Pret[] cennik;

    Minimalistyczna(Wejscie wejscie) {
        super(wejscie);
        cennik = wejscie.dajCennik();
    }

    /**
     * Zwraca kolejny prêt z cennika w tym przypadku jest to najkrótszy prêt który
     * jest d³u¿szy od najd³u¿szego brakuj¹cego odcinka.
     */
    public Pret wybierzPretZCennika(int najdluzszyBrakujacyOdcinek) {
        int i = 0;
        while (i < cennik.length && cennik[i].dajDlugosc() < najdluzszyBrakujacyOdcinek) {
            i++;
        }

        return cennik[i];
    }
}
