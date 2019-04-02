/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package binarysearch;

/**
 *
 * @author kamaj
 */
public class Menu {
//main alkaa-----------------------------------------------------------------------------
        public static void main(String[] args) {

                        printMenu();

        }
//main loppuu --------------------------------------------------------------------------
//printMenu alkaa------------------------------------------------------------------
        private static void printMenu() {
            
                char select;
                int key;
                Binarysearch bs = new Binarysearch(); // luodaan tyhjä etsintäpuu

                do {

                        System.out.println("\n\t\t\t1. Lisää avain.");
                        System.out.println("\t\t\t2. Etsi avaimella.");
                        System.out.println("\t\t\t3. Käy puu läpi esijärjestyksessä."); // Sisäjärjestys opettajan mukaan
                        System.out.println("\t\t\t4. lopetus ");
                        System.out.print("\n\n"); // tehdään tyhjiä rivejä
                        select = Lue.merkki();
                        switch (select) {
                        case '1':
                            System.out.println("Anna uusi avain (merkkijono)");
                            key = Integer.parseInt(new String(Lue.rivi()));
                            bs.insert(key);
                            break;
                        case '2':                           
                                System.out.println("Anna etsittävä avain (merkkijono)");
                                key = Integer.parseInt(Lue.rivi());
                                if (bs.searchMiddle(key)){
                                    System.out.println("Avain löytyi.");
                                }
                                else
                                    System.out.println("Avainta ei löytynyt.");
                                                           
                            break;
                        case '3':
                            bs.print();
                            break;
                        case '4':
                            break;
                        }
                }
                while (select != '4');
        }
//printMenu loppuu ----------------------------------------------------------------
}

