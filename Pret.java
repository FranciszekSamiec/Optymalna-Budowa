package optymalnaBudowa;

/**
 * Przechowuje cechy prêta.
 * <p>
 * Metoda toString nie potrzebna do prawid³owego wykonania programu.
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
