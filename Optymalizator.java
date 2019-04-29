package optymalnaBudowa;

/**
 * Odpowiada za przeprowadzenie optymalizacji u�ywaj�c odpowiedniej strategii.
 * 
 * @author fs395216
 *
 */
public class Optymalizator {
    /**
     * Tworzy odpowiedni obiekt typu Strategia i wywo�uje metode przeprowadzaj�c�
     * optymalizacje.
     * 
     * @param wejscie klasa na kt�rej atrybutach zapisane s� dane wej�ciowe
     */
    public static void optymalizuj(Wejscie wejscie) {
        String nazwaStrategi = wejscie.dajStrategie();

        if (nazwaStrategi.equals("minimalistyczna")) {
            Strategia strategia = new Minimalistyczna(wejscie);
            strategia.przeprowadzOptymalizacje();
        } else if (nazwaStrategi.equals("maksymalistyczna")) {
            Strategia strategia = new Maksymalistyczna(wejscie);
            strategia.przeprowadzOptymalizacje();
        } else if (nazwaStrategi.equals("ekonomiczna")) {
            Strategia strategia = new Ekonomiczna(wejscie);
            strategia.przeprowadzOptymalizacje();
        } else {
            Strategia strategia = new Ekologiczna(wejscie);
            strategia.przeprowadzOptymalizacje();
        }
    }

    public static void main(String[] args) {
        Wejscie wejscie = new Wejscie();

        optymalizuj(wejscie);

    }

}
