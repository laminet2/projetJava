package gestion.classe.view;

import java.util.Map;
import java.util.Scanner;

import com.database.services.Database;

import gestion.classe.repository.*;
import gestion.classe.repository.implementation.*;
import gestion.classe.services.*;
import gestion.classe.services.implementation.*;

import jsontomap.JsonToMap;

import com.database.services.implementation.MySqlimple;

public class App{
    Database dataBase =new MySqlimple("jdbc:mysql://localhost:3306/javaprojetdatabase","root","");

    static JsonToMap json= new JsonToMap("configuration.json");
    static Map <String,String> data=json.convert();

    
    
    public static void main(String[] args) throws Exception{
        //ClasseRepository
        Class classeRepositoryImplementation=Class.forName(data.get("classeRepository"));
        ClasseRepository classeRepository =(ClasseRepository) classeRepositoryImplementation.newInstance();
        
        //ClasseService
        Class classeServiceImplementation= Class.forName(data.get("classeService")) ;
        java.lang.reflect.Constructor<?> constructor = classeServiceImplementation.getConstructor(ClasseRepository.class);
        ClasseService classeService=(ClasseService) constructor.newInstance(classeRepository);

        //CoursRepository
        Class coursRepositoryImplementation=Class.forName(data.get("coursRepository"));
        CoursRepository coursRepository =(CoursRepository) coursRepositoryImplementation.newInstance();

        //CoursServices
        Class coursServiceImplementation= Class.forName(data.get("coursService")) ;
        constructor = coursServiceImplementation.getConstructor(CoursRepository.class);
        CoursService coursService=(CoursService) constructor.newInstance(coursRepository);

        //ModuleRepository
        Class moduleRepositoryImpl=Class.forName(data.get("moduleRepository"));
        ModuleRepository moduleRepository=(ModuleRepository) moduleRepositoryImpl.newInstance();

        //ModuleServices
        Class modulesServiceImple= Class.forName(data.get("moduleService"));
        constructor = modulesServiceImple.getConstructor(ModuleRepository.class);
        ModuleService moduleService=(ModuleService) constructor.newInstance(moduleRepository);

        //ProfesseurRepository
        Class professeurRepositoryImple=Class.forName(data.get("professeurRepository"));
        ProfesseurRepository professeurRepository=(ProfesseurRepository) professeurRepositoryImple.newInstance();


        Scanner sc = new Scanner(System.in);
        classeService.listerClasses();
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
            int choix1;

            switch (choix) {

                case 1:
                    //Gerer Classe
                    System.out.println("1- Lister Classe");
                    System.out.println("2- Ajouter Classe");
                    System.out.println("3- Modifier Classe");
                    System.out.println("4- Archiver une Classe");
                    choix1=sc.nextInt();
                    switch (choix1) {
                        case 1:
                            
                            break;
                        case 2:
                            break;
                    
                        default:
                            break;
                    }

                    break;
                case 2:
                    //Gerer Module
                    System.out.println("1- Lister Module");
                    System.out.println("2- Ajouter Module");
                    System.out.println("3- Modifier Module");
                    System.out.println("4- Archiver une Module");
                    break;
                case 3:
                    //Affecter Module a une classe
                    break;
                case 4:
                    //Afficher les Modules d'une Classe
                    break;
                case 5:
                    //Afficher les classes qui font un modules

                    break;
                case 6:
                    //Gerer Professeur

                    break;
                case 7:
                    //Afficher les classes d'un Prof ainsi que les modules enseigner
                    break;
                case 8:
                    //Gerer les salles
                    break;
                case 9:
                    //Plannifier Cours
                    break;
                case 10:
                    //Afiicher les cours d'une classes
                    break;
                default:
                    System.out.println("------ GOOD-BYE ------");
                    break;
            }
            

        }while(choix!=12);
    }
}
