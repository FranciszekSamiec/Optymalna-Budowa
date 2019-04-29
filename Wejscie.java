package optymalnaBudowa;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Przypisuje na swoje atrybuty dane wejsciowe.
 * <p>
 * Atrybutom s� nadawane warto�ci w konstruktorze klasy. Klasa posiada komplet
 * getter�w.
 * 
 * @author fs395216
 *
 */
public class Wejscie {
    /**
     * Zawiera tablice pr�tow jakie s� w cenniku
     */
    private final Pret[] cennik;
    /**
     * Zawiera tablice dlugosci pr�t�w kt�re wymaga projekt
     */
    private final int[] opisProjektu;
    private final String nazwaStrategi;

    Wejscie() {
        Scanner wiersze = new Scanner(System.in);
        String wiersz = wiersze.nextLine();
        int dlugoscCennika = Integer.parseInt(wiersz);
        cennik = new Pret[dlugoscCennika];

        int i = 0;
        while (dlugoscCennika > 0) {
            wiersz = wiersze.nextLine();
            Scanner paraDlugoscCena = new Scanner(wiersz);
            this.cennik[i] = new Pret(Integer.parseInt(paraDlugoscCena.next()),
                    Integer.parseInt(paraDlugoscCena.next()));

            dlugoscCennika--;
            i++;
            paraDlugoscCena.close();
        }

        wiersz = wiersze.nextLine();

        int dlugoscProjektu = Integer.parseInt(wiersz);
        opisProjektu = new int[dlugoscProjektu];
        wiersz = wiersze.nextLine();
        Scanner odcinki = new Scanner(wiersz);
        for (int j = 0; j < dlugoscProjektu; j++) {
            opisProjektu[j] = Integer.parseInt(odcinki.next());
        }
        odcinki.close();
        wiersz = wiersze.nextLine();
        nazwaStrategi = wiersz;
        wiersze.close();
    }

    public Pret[] dajCennik() {
        return Arrays.copyOf(this.cennik, this.cennik.length);
    }

    public int[] dajOpisProjektu() {
        return Arrays.copyOf(this.opisProjektu, this.opisProjektu.length);
    }

    public String dajStrategie() {
        return this.nazwaStrategi;
    }
}
