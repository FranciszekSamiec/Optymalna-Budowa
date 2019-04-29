package optymalnaBudowa;

/**
 * Przechowuje cechy pr�ta.
 * <p>
 * Metoda toString nie potrzebna do prawid�owego wykonania programu.
 * 
 * @author fs395216
 *
 */
public class Pret {
    private final int dlugosc;
    private final int cena;

    Pret(int dlugosc, int cena) {
        this.dlugosc = dlugosc;
        this.cena = cena;
    }

    public int dajDlugosc() {
        return this.dlugosc;
    }

    public int dajCene() {
        return this.cena;
    }

    @Override
    public String toString() {
        return dlugosc + " " + cena;
    }

}
