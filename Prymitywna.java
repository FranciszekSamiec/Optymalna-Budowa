package optymalnaBudowa;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Wspólna nadklasa dla strategii maksymalistycznej i minimalistycznej.
 * Realizuje optymalizacje we w³asciwy dla odpowiednich strategii sposób,
 * wyodrêbnaj¹c wspólne cechy strategii maksymalistycznej i minimalistycznej.
 * Jedyna ró¿nica miêdzy obiema strategiami to sposób wybierania kolejnego prêta
 * z cennika dlatego metoda wybierzPretZCennika(int najdluzszyBrakujacyOdcinek)
 * jest abstrakcyjna. Wynik jest przechowywany w tablicy wierszy wyjscia. Typ
 * Wyjscie zawiera informacje o jednym wierszu wyjscia i potrafi go wypisaæ.
 * 
 * @author fs395216
 *
 */
public abstract class Prymitywna extends Strategia {
    private static final int MNOZNIK = 3;
    private static final int DZIELNIK = 2;
    private final int[] opisProjektu;
    private Wyjscie[] tablicaWierszyWyjscia;

    Prymitywna(Wejscie wejscie) {
        opisProjektu = wejscie.dajOpisProjektu();
        tablicaWierszyWyjscia = new Wyjscie[1];
    }

    private static int wiecej(int d) {
        return 1 + d * MNOZNIK / DZIELNIK;
    }

    private static Wyjscie[] dopisz(Wyjscie[] a, int k, Wyjscie x) {
        Wyjscie[] b;
        if (k == a.length) {
            b = Arrays.copyOf(a, wiecej(a.length));
        } else {
            b = a;
        }
        b[k] = x;
        return b;
    }

    public abstract Pret wybierzPretZCennika(int najdluzszyBrakujacyOdcinek);

    @Override
    public void przeprowadzOptymalizacje() {
        BigInteger koszt = new BigInteger("0");
        BigInteger dlgOdpadow = new BigInteger("0");
        int ktoryWiersz = 0;
        for (int i = opisProjektu.length - 1; i >= 0; i--) {
            if (opisProjektu[i] != 0) {

                Pret pret = wybierzPretZCennika(opisProjektu[i]);
                Wyjscie wiersz = new Wyjscie(pret.dajDlugosc());
                BigInteger cenaPreta = new BigInteger(Integer.toString(pret.dajCene()));
                koszt = koszt.add(cenaPreta);
                int dlgPreta = pret.dajDlugosc();
                int j = i;
                while (j >= 0) {
                    if (opisProjektu[j] != 0 && dlgPreta - opisProjektu[j] >= 0) {
                        dlgPreta -= opisProjektu[j];

                        wiersz.dodajDoWierszaWyjscia(opisProjektu[j]);
                        opisProjektu[j] = 0;
                        j--;
                    } else {
                        j--;
                    }

                }
                BigInteger nowyOdpad = new BigInteger(Integer.toString(dlgPreta));
                dlgOdpadow = dlgOdpadow.add(nowyOdpad);

                tablicaWierszyWyjscia = dopisz(tablicaWierszyWyjscia, ktoryWiersz, wiersz);
                ktoryWiersz++;
            }
        }
        System.out.println(koszt);
        System.out.println(dlgOdpadow);
        for (Wyjscie i : tablicaWierszyWyjscia) {
            if (i != null)
                i.wypiszWiersz();
        }
    }

}