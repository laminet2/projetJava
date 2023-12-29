package gestion.classe.view;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choix;
        do {
            System.out.println("1-Gerer Classe ");
            System.out.println("2-Gerer Module");
            System.out.println("3-Affecter Module a une classe");
            System.out.println("4- Afficher les Modules d'une Classe");
            System.out.println("5-Afficher les classes qui font un modules ");
            System.out.println("6-Gerer Professeur");
            System.out.println("7-Afficher les classes d'un Prof ainsi que les modules enseigner");
            System.out.println("8-Gerer les salles");
            System.out.println("9-Plannifier cours");
            System.out.println("10-Afiicher les cours d'une classes");
            System.out.println("11-Quittez");
            choix=sc.nextInt();

            switch (choix) {
                case 1:
                    
                    break;
            
                default:
                    break;
            }

        } while (choix!=12);
    }
}
