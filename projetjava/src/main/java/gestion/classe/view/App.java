package gestion.classe.view;



import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.database.services.Database;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Filiere;
import gestion.classe.entities.Niveau;
import gestion.classe.repository.*;
import gestion.classe.repository.implementation.*;
import gestion.classe.services.*;
import gestion.classe.services.implementation.*;

import jsontomap.JsonToMap;

import com.database.services.implementation.*;

public class App{
    static Database sgbd=new MySql("jdbc:mysql://localhost:3306/javaprojetdatabase","root","");

    static JsonToMap json= new JsonToMap("configuration.json");
    static Map <String,String> data=json.convert();
    
    
    public static void main(String[] args) throws Exception{
        //ClasseRepository
        Class classeRepositoryImplementation=Class.forName(data.get("classeRepository"));
        // ClasseRepository classeRepository =(ClasseRepository) classeRepositoryImplementation.newInstance();
        java.lang.reflect.Constructor<?> constructor = classeRepositoryImplementation.getConstructor(Database.class);
        ClasseRepository classeRepository =(ClasseRepository) constructor.newInstance(sgbd);

        //ClasseService
        Class classeServiceImplementation= Class.forName(data.get("classeService")) ;
        constructor = classeServiceImplementation.getConstructor(ClasseRepository.class);
        ClasseService classeService=(ClasseService) constructor.newInstance(classeRepository);

        //CoursRepository
        Class coursRepositoryImplementation=Class.forName(data.get("coursRepository"));
        constructor = coursRepositoryImplementation.getConstructor(Database.class);
        CoursRepository coursRepository =(CoursRepository) constructor.newInstance(sgbd);

        //CoursServices
        Class coursServiceImplementation= Class.forName(data.get("coursService")) ;
        constructor = coursServiceImplementation.getConstructor(CoursRepository.class);
        CoursService coursService=(CoursService) constructor.newInstance(coursRepository);

        //ModuleRepository
        Class moduleRepositoryImpl=Class.forName(data.get("moduleRepository"));
        constructor= moduleRepositoryImpl.getConstructor(Database.class);
        ModuleRepository moduleRepository=(ModuleRepository) constructor.newInstance(sgbd);

        //ModuleServices
        Class modulesServiceImple= Class.forName(data.get("moduleService"));
        constructor = modulesServiceImple.getConstructor(ModuleRepository.class);
        ModuleService moduleService=(ModuleService) constructor.newInstance(moduleRepository);

        //ProfesseurRepository
        Class professeurRepositoryImple=Class.forName(data.get("professeurRepository"));
        constructor = professeurRepositoryImple.getConstructor(Database.class);
        ProfesseurRepository professeurRepository=(ProfesseurRepository) constructor.newInstance(sgbd);

        classeService.listerClasses();
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
            System.out.println("11-Afficher les cours d'un professeur");
            System.out.println("12-Quittez");
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
                                classeService.listerClasses()
                                      .forEach(System.out::println); 
                                Thread.sleep(3000);
                            break;
                        case 2:
                            sc.nextLine();

                            System.out.println("Choisir une Filiere");
                            classeService.listerFiliere().forEach(System.out::println);
                            int idFiliere=sc.nextInt();

                            System.out.println("Choisir un Niveau");
                            classeService.listerNiveaux().forEach(System.out::println);
                            int idNiveau=sc.nextInt();

                            Filiere filiereSelect=classeService.listerFiliere().get(idFiliere-1);
                            Niveau niveauSelect=classeService.listerNiveaux().get(idNiveau-1);
                            String nomClasse=String.format("%s %s",niveauSelect.getLibelle(),filiereSelect.getLibelle());

                            Classe classe=new Classe(0,nomClasse,niveauSelect.getLibelle(),filiereSelect.getLibelle());

                            if(classeService.ajouterClasse(classe)!=0){
                                System.out.println("--- Succes ---");
                                System.out.println("Classe ajouter avec success");
                                System.out.println("--- Succes ---");

                              }else{
                                System.out.println("--- Failure ---");
                                System.out.println("Classe Already Define");
                                System.out.println("--- Failure ---");

                              }
                            Thread.sleep(3000);
                            break;
                        case 3:
                            System.out.println("Selectionner la Classe");
        
                             ArrayList<Classe> classes=classeService.listerClasses();
                               int i=0;
                              for (Classe cl  : classes) {
                                i++;
                                System.out.println(String.format("%d) %s",i,cl.getLibelle()));
                              }
                              Classe classeSelect=classeService.listerClasses().get(sc.nextInt() - 1);
                              //Modifier quoi ?
                              //libelle
                              System.out.println("Voulez vous modifier le libelle [oui,non]");
                              sc.nextLine();
                              String ok=sc.nextLine();
                              if((ok.toLowerCase()).equals("oui")){
                                System.out.println("Entrer le nouveau libelle");
                                classeSelect.setLibelle(sc.nextLine());
                              }
                              System.out.println("Voulez vous modifier le niveau [oui,non]");
                              ok=sc.nextLine();
                              if((ok.toLowerCase()).equals("oui")){
                                System.out.println("Choisir un Niveau");

                                List<Niveau> niveaux=classeService.listerNiveaux();
                                i=0;
                                for (Niveau niv  : niveaux) {
                                    i++;
                                    System.out.println(String.format("%d) %s",i,niv.getLibelle()));
                                }
                                 idNiveau=sc.nextInt();     
                                 niveauSelect=classeService.listerNiveaux().get(idNiveau-1);
                                 classeSelect.setNiveau(niveauSelect.getLibelle());
                                }

                                System.out.println("Voulez vous modifier la filiere [oui,non]");
                                sc.nextLine();
                                ok=sc.nextLine();
                                if((ok.toLowerCase()).equals("oui")){
                                    System.out.println("Choisir une Filiere");
                                    List<Filiere> filieres=classeService.listerFiliere();
                                    i=0;
                                    for (Filiere filiere  : filieres) {
                                        i++;
                                        System.out.println(String.format("%d) %s",i,filiere.getLibelle()));
                                    }

                                    idFiliere=sc.nextInt();     
                                    filiereSelect=classeService.listerFiliere().get(idFiliere-1);
                                    classeSelect.setFiliere(filiereSelect.getLibelle());
                                }
                            
                                if(classeService.modifierClasse(classeSelect)){
                                    System.out.println("--- Update ---");
                                    System.out.println("Classe Update");
                                    System.out.println("--- Update ---");
                                }
                              Thread.sleep(3000);
                              break;
                        case 4:
                              System.out.println("Selectionner la Classe");
        
                              classes=classeService.listerClasses();
                                i=0;
                              for (Classe cl  : classes) {
                                i++;
                                System.out.println(String.format("%d) %s",i,cl.getLibelle()));
                              }
                               classeSelect=classeService.listerClasses().get(sc.nextInt() - 1);
                                classeService.archiverClasse(classeSelect);
                                 System.out.println("--- Archiver ---");
                                    System.out.println("Classe Archiver avec succes");
                                    System.out.println("--- Archiver ---");
                                    Thread.sleep(3000);

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
                    System.out.println("1- Lister Professeur");
                    System.out.println("2- Ajouter Professeur");
                    System.out.println("3- Modifier Professeur");
                    System.out.println("4- Archiver une Professeur");
                    break;
                case 7:
                    //Afficher les classes d'un Prof ainsi que les modules enseigner
                    break;
                case 8:
                    //Gerer les salles
                    System.out.println("1- Lister Salle");
                    System.out.println("2- Ajouter Salle");
                    System.out.println("3- Modifier Salle");
                    System.out.println("4- Archiver une Salle");
                    break;
                case 9:
                    //Plannifier Cours
                    break;
                case 10:
                    //Afiicher les cours d'une classes
                    break;
                case 11:
                    //Afiicher les cours d'un professeur

                    break;
                case 12:
                    System.out.println("------ GOOD-BYE ------");
                    Thread.sleep(3000);

                    break;
            }
            

        }while(choix!=12);
    }
}
