package optymalnaBudowa;

/**
 * Klasa konkretna, odpowiada za realizacje strategii minimalistycznej.
 * <p>
 * Zawiera implementacj� metody odpowiedzialnej za wyb�r kolejnego pr�ta z
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
     * Zwraca kolejny pr�t z cennika w tym przypadku jest to najkr�tszy pr�t kt�ry
     * jest d�u�szy od najd�u�szego brakuj�cego odcinka.
     */
    public Pret wybierzPretZCennika(int najdluzszyBrakujacyOdcinek) {
        int i = 0;
        while (i < cennik.length && cennik[i].dajDlugosc() < najdluzszyBrakujacyOdcinek) {
            i++;
        }

        return cennik[i];
    }
}
