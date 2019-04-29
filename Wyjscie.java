package optymalnaBudowa;

import java.util.Arrays;

/**
 * Przechowuje informacje o pojedy�czym wierszu wyj�cia.
 * <p>
 * Klasa jest pomocna przy generowaniu wyniku podczas u�ywania strategii
 * maksymalistycznej oraz minimalistycznej.
 * 
 * 
 * @author fs395216
 *
 */
public class Wyjscie {
    /**
     * sta�e finalne okre�laj� o ile powi�ksza� tablic� przechowuj�ca informacje
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
     * Tworz�c obiekt klasy wyj�ie na pocz�tku podaje dlugo�� pr�ta z jakiego b�d�
     * wycinane kolejne pr�ty z projektu.
     * 
     * @param x d�ugo�� pr�ta z cennika z kt�rego b�d� wycinane pr�ty
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
     * Dopisuje do wiersza dlugosc kolejnego pr�ta.
     * 
     * @param x dlugo�� kolejnego pr�ta z opisu projektu kt�ry b�dzie wyci�ty z
     *          pr�ta z cennika kt�rego d�ugo�� znajduje si� pod indeksem 0 w
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
