package optymalnaBudowa;

import java.util.Arrays;

/**
 * Przechowuje informacje o pojedyñczym wierszu wyjœcia.
 * <p>
 * Klasa jest pomocna przy generowaniu wyniku podczas u¿ywania strategii
 * maksymalistycznej oraz minimalistycznej.
 * 
 * 
 * @author fs395216
 *
 */
public class Wyjscie {
    /**
     * sta³e finalne okreœlaj¹ o ile powiêkszaæ tablicê przechowuj¹ca informacje
     * wiersza wyjscia
     */
    private static final int MNOZNIK = 3;
    private static final int DZIELNIK = 2;
    /**
     * iteratorWiersza to ostatni niezerowy element tablicy wierszWyjscia
     */
    private int iteratorWiersza;
    private int[] wierszWyjscia;

    /**
     * Tworz¹c obiekt klasy wyjœie na pocz¹tku podaje dlugoœæ prêta z jakiego bêd¹
     * wycinane kolejne prêty z projektu.
     * 
     * @param x d³ugoœæ prêta z cennika z którego bêd¹ wycinane prêty
     */
    Wyjscie(int x) {
        wierszWyjscia = new int[1];
        wierszWyjscia[0] = x;
        iteratorWiersza++;
    }

    private static int wiecej(int d) {
        return 1 + d * MNOZNIK / DZIELNIK;
    }

    private static int[] dopisz(int[] a, int k, int x) {
        int[] b;
        if (k == a.length) {
            b = Arrays.copyOf(a, wiecej(a.length));
        } else {
            b = a;
        }
        b[k] = x;
        return b;
    }

    /**
     * Dopisuje do wiersza dlugosc kolejnego prêta.
     * 
     * @param x dlugoœæ kolejnego prêta z opisu projektu który bêdzie wyciêty z
     *          prêta z cennika którego d³ugoœæ znajduje siê pod indeksem 0 w
     *          tablicy wierszWyjscia
     */
    public void dodajDoWierszaWyjscia(int x) {
        this.wierszWyjscia = dopisz(this.wierszWyjscia, iteratorWiersza, x);
        iteratorWiersza++;
    }

    /**
     * wypisuje tablice wierszWyjscia
     */
    public void wypiszWiersz() {
        int i = 0;
        while (i < wierszWyjscia.length && wierszWyjscia[i] != 0) {
            System.out.print(wierszWyjscia[i] + " ");
            i++;
        }
        System.out.println();
    }

}
