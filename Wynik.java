package optymalnaBudowa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Przechowuje postaæ wyniku. Jest wygodna z punktu widzenia strateg optymalnej.
 * Sposoby podzia³u prêtów s¹ przechowywane w liœcie tablic
 * sposobPodzialuKupionychPretow.
 * 
 * @author fs395216
 *
 */
public class Wynik {
    private static final int MNOZNIK = 3;
    private static final int DZIELNIK = 2;
    /**
     * 
     */
    // private int dlugoscOdpadow;
    // private int kosztZakupuPretow;
    private BigInteger dlugoscOdpadow;
    private BigInteger kosztZakupuPretow;
    private boolean czyPierwszePorownanie;
    /**
     * zawiera kolejne wiersze wyjœcia
     */
    private List<int[]> sposobPodzialuKupionychPretow;

    public Wynik() {
        this.sposobPodzialuKupionychPretow = new ArrayList<int[]>();
        // dlugoscOdpadow = 0;
        // kosztZakupuPretow = 0;
        this.czyPierwszePorownanie = true;
        dlugoscOdpadow = new BigInteger("0");
        kosztZakupuPretow = new BigInteger("0");
    }

    /**
     * Potrzebna do utworzenia kandydata w metodzie przeprowadzOptymalizacje z klasy
     * Optymalna.
     * 
     * @param koszt
     */
    public Wynik(int koszt) {
        this.sposobPodzialuKupionychPretow = new ArrayList<int[]>();
        // dlugoscOdpadow = koszt;
        // kosztZakupuPretow = koszt;
        this.czyPierwszePorownanie = true;
        dlugoscOdpadow = new BigInteger(Integer.toString(koszt));
        kosztZakupuPretow = new BigInteger(Integer.toString(koszt));

    }

    public void ustawCzyPierwszePorownanie(boolean x) {
        this.czyPierwszePorownanie = x;
    }

    public boolean dajCzyPierwszePorownanie() {
        return this.czyPierwszePorownanie;
    }

    public void ustawKoszt(int koszt) {
        // this.kosztZakupuPretow = koszt;
        this.kosztZakupuPretow = new BigInteger(Integer.toString(koszt));
    }

    public void ustawDlugoscOdpadow(int dlugosc) {
        // dlugoscOdpadow = dlugosc;
        this.dlugoscOdpadow = new BigInteger(Integer.toString(dlugosc));

    }

    public void kopiuj(Wynik w) {

        // this.dlugoscOdpadow = w.dajDlugoscOdpadow();
        // this.kosztZakupuPretow = w.dajKosztZakupuPretow();

        this.dlugoscOdpadow = new BigInteger(w.dajDlugoscOdpadow().toString());
        this.kosztZakupuPretow = new BigInteger(w.dajKosztZakupuPretow().toString());

        for (int i = this.sposobPodzialuKupionychPretow.size() - 1; i >= 0; i--) {
            this.sposobPodzialuKupionychPretow.remove(i);
        }

        for (int i = 0; i < w.dajSposobPodzialu().size(); i++) {
            this.dajSposobPodzialu().add(w.dajSposobPodzialu().get(i));
        }

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

    public List<int[]> dajSposobPodzialu() {
        return sposobPodzialuKupionychPretow;
    }

    public BigInteger dajDlugoscOdpadow() {
        return dlugoscOdpadow;
    }

    public BigInteger dajKosztZakupuPretow() {
        return kosztZakupuPretow;
    }

    /**
     * Dodaje d³ugoœæ odpadów do danego wyniku.
     * <p>
     * Napisana tak by by³o wygodnie u¿ywaæ jej wewn¹trz rekurecji optymalnej
     * strategii.
     * 
     * @param ktoreBrac    mówi które prêty maj¹ byæ zabrane w danym kroku
     * @param ktoreZabrane mówi które prêty by³y zabrane w poprzednim kroku
     * @param pretZCennika prêt z kórego wycinamy
     * @param opisProjektu opisProjektu taki sam jak na wejœciu programu
     * @return
     */
    public int dodajDlugoscOdpadow(boolean[] ktoreBrac, boolean[] ktoreZabrane, Pret pretZCennika,
            int[] opisProjektu) {

        int doDodania = pretZCennika.dajDlugosc();
        for (int i = 0; i < opisProjektu.length; i++) {
            if (ktoreZabrane[i] == false) {

                if (ktoreBrac[i] == true) {
                    doDodania -= opisProjektu[i];
                }
            }
        }

        BigInteger nowyOdpad = new BigInteger(Integer.toString(doDodania));
        // dlugoscOdpadow += doDodania;
        dlugoscOdpadow = dlugoscOdpadow.add(nowyOdpad);

        return doDodania;
    }

    public void odejmijDlugoscOdpadow(int x) {
        // dlugoscOdpadow -= x;
        BigInteger doOdjecia = new BigInteger(Integer.toString(x));
        dlugoscOdpadow = dlugoscOdpadow.subtract(doOdjecia);
    }

    public void odejmijKoszt(Pret p) {
        // this.kosztZakupuPretow -= p.dajCene();
        BigInteger doOdjecia = new BigInteger(Integer.toString(p.dajCene()));
        kosztZakupuPretow = kosztZakupuPretow.subtract(doOdjecia);
    }

    public void dodajKoszt(Pret p) {
        // this.kosztZakupuPretow += p.dajCene();
        BigInteger doDodania = new BigInteger(Integer.toString(p.dajCene()));
        this.kosztZakupuPretow = this.kosztZakupuPretow.add(doDodania);
    }

    /**
     * Daje nowy wiersz wyjœcia w tablicy który nastêpnie w metodzie
     * dodajPodzialPreta bêdzie dodany do listy wynikowych wierszy. Argumenty te
     * same co w metodzie dodajDlugoscOdpadow.
     * 
     * @param ktoreBrac
     * @param ktoreZabrane
     * @param zJakiegoWycinac
     * @param opisProjektu
     * @return
     */
    public int[] dajNowyWiersz(boolean[] ktoreBrac, boolean[] ktoreZabrane, Pret zJakiegoWycinac,
            int[] opisProjektu) {
        int[] nowyWiersz = new int[1];
        int iteratorKolumn = 0;
        dopisz(nowyWiersz, iteratorKolumn, zJakiegoWycinac.dajDlugosc());
        iteratorKolumn++;

        for (int i = 0; i < ktoreBrac.length; i++) {
            if (ktoreZabrane[i] == false) {
                if (ktoreBrac[i] == true) {
                    nowyWiersz = dopisz(nowyWiersz, iteratorKolumn, opisProjektu[i]);
                    iteratorKolumn++;
                }
            }
        }

        nowyWiersz = Arrays.copyOf(nowyWiersz, iteratorKolumn);
        return nowyWiersz;
    }

    /**
     * Dodaje zgodny ze specyfikacj¹ zadania wiersz wyjœcia do listy.
     * 
     * @param ktoreBrac
     * @param ktoreZabrane
     * @param zJakiegoWycinac
     * @param iteratorWierszy
     * @param opisProjektu
     */
    public void dodajPodzialPreta(boolean[] ktoreBrac, boolean[] ktoreZabrane, Pret zJakiegoWycinac,
            int iteratorWierszy, int[] opisProjektu) {

        sposobPodzialuKupionychPretow
                .add(dajNowyWiersz(ktoreBrac, ktoreZabrane, zJakiegoWycinac, opisProjektu));

    }

    public void usunPodzialPreta(int indeks) {
        sposobPodzialuKupionychPretow.remove(indeks);
    }

    public void wypiszWynik() {

        System.out.println(kosztZakupuPretow.toString());
        System.out.println(dlugoscOdpadow.toString());

        for (int i = 0; i < sposobPodzialuKupionychPretow.size(); i++) {
            for (int j = 0; j < sposobPodzialuKupionychPretow.get(i).length; j++) {
                System.out.print(sposobPodzialuKupionychPretow.get(i)[j] + " ");
            }
            System.out.println();
        }

    }

}
