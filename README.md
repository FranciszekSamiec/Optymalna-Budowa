# Optymalna-Budowa
Podczas robót budowlanych zachodzi potrzeba rozwiązania problemu optymalizacyjnego dotyczącego przygotowania prętów do konstrukcji stalowych.

Projekt budowy określa liczbę i długości potrzebnych odcinków prętów. W cenniku sprzedawcy są długości prętów i ich ceny. Rozwiązanie wskazuje, ile prętów jakiej długości należy kupić i jak podzielić je na odcinki. Pręt dzielimy na odcinki, tnąc go. Niewykorzystaną część pręta, jeśli taka zostanie, odrzucamy. Łączenie prętów nie jest możliwe.

Załóżmy, że w cenniku są:

- pręt długości 4000, w cenie 100,

- pręt długości 4500, w cenie 160,

- pręt długości 10000, w cenie 200,

- gdzie długość podana jest w milimetrach a cena w PLN.

Przyjmijmy, że projekt zakłada użycie:

- dwóch odcinków długości 200,

- trzech odcinków długości 350,

- jednego odcinka długości 600,

- dwóch odcinków długości 1500,

- jednego odcinka długości 3000,

- jednego odcinka długości 4500.

Przykładowym rozwiązaniem problemu jest kupno jednego pręta długości 4500 i trzech prętów długości 4000. Następnie:

- pręt długości 4500 w całości przeznaczamy na odcinek długości 4500,

- pierwszy pręt długości 4000 dzielimy na odcinki długości 3000, 600, 350 i odpad długości 50,

- drugi pręt długości 4000 dzielimy na odcinki długości 1500, 1500, 350, 350, 200 i odpad długości 100,

- trzeci pręt długości 4000 dzielimy na odcinek długości 200 i odpad długości 3800.

Dla tego rozwiązania:

- całkowity koszt zakupu prętów jest równy 460,

- łączna długość odpadów jest równa 3950.

Wśród strategii wyboru rozwiązania są:

- strategia minimalistyczna:
Działa zachłannie. Dopóki problem nie jest rozwiązany, z cennika wybiera najkrótszy pręt, w którym mieści się najdłuższy brakujący odcinek. Następnie rozważa brakujące odcinki w kolejności od najdłuższych. Jeśli rozważany odcinek mieści się w części pręta, która jeszcze pozostała, jest od niej odcinany. To, co zostanie z pręta, po rozważeniu ostatniego odcinka, jest odpadem.

- strategia maksymalistyczna:
Działa tak, jak strategia minimalistyczna, ale z cennika zawsze wybiera najdłuższy pręt.

- strategia ekonomiczna:
Znajduje jedno z, być może wielu, rozwiązań minimalizujących koszt zakupu prętów.

- strategia ekologiczna:
Znajduje jedno z, być może wielu, rozwiązań minimalizujących długość odpadów.

Program
- czyta ze standardowego wejścia cennik prętów, opis projektu i nazwę strategii,

- za pomocą wskazanej strategii rozwiązuje problem optymalizacyjny,

- pisze na standardowe wyjście rozwiązanie, określając jego jakość, kupione pręty i sposób ich podziału.

Postać danych
- Dane programu to ciąg wierszy. We wszystkich, z wyjątkiem ostatniego, są liczby całkowite w zapisie dziesiętnym. Między każdą parą liczb sąsiadujących w wierszu jest jedna spacja.

- W pierwszym wierszu danych jest długość cennika C.

- W C kolejnych wierszach są pary dodatnich liczb całkowitych. Pierwsza z tych liczb określa długość pręta a druga to jego cena. Pary są uporządkowane rosnąco po długości pręta.

- Po cenniku jest wiersz z długością projektu P.

- W następnym wierszu jest P dodatnich liczb całkowitych, uporządkowanych niemalejąco. Liczby określają długości odcinków, potrzebnych do realizacji projektu.

- W ostatnim wierszu danych, po projekcie, jest słowo minimalistyczna, maksymalistyczna, ekonomiczna lub ekologiczna, będące nazwą wybranej strategii.

Postać wyniku

Wynik programu jest ciągiem wierszy z dziesiętnym zapisem liczb całkowitych. Między każdą parą liczb sąsiadujących w wierszu jest jedna spacja.

- W pierwszym wierszu wyniku jest koszt zakupu prętów.

- W drugim wierszu jest łączna długość odpadów.

- Pozostałe wiersze określają sposób podziału kupionych prętów. Dla każdego pręta jest jeden wiersz.

Na początku wiersza określającego podział jest długość pręta. Po niej są długości odcinków, na które pręt został podzielony, z pominięciem ewentualnego pozostałego odpadu. Suma długości odcinków jest więc mniejsza lub równa długości pręta, z którego powstały.
