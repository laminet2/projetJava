package gestion.classe.view;



import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestWord;


import com.database.services.Database;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Cours;
import gestion.classe.entities.Filiere;
import gestion.classe.entities.Lieux;
import gestion.classe.entities.Module;
import gestion.classe.entities.Niveau;
import gestion.classe.entities.Professeur;
import gestion.classe.entities.Salle;
import gestion.classe.repository.*;
import gestion.classe.repository.implementation.*;
import gestion.classe.services.*;
import gestion.classe.services.implementation.*;

import jsontomap.JsonToMap;

import com.database.services.implementation.*;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestWord;

public class App{
    static Database sgbd=new MySql("jdbc:mysql://localhost:3306/javaprojetdatabase","root","");

    static JsonToMap json= new JsonToMap("C:\\Users\\TRAORE\\OneDrive\\Bureau\\java\\javaProject\\projetjava\\configuration.json");
    static Map <String,String> data=json.convert();
    
    
    public static void main(String[] args) throws Exception{

      //ModuleRepository
        Class moduleRepositoryImpl=Class.forName(data.get("moduleRepository"));
        java.lang.reflect.Constructor<?> constructor= moduleRepositoryImpl.getConstructor(Database.class);
        ModuleRepository moduleRepository=(ModuleRepository) constructor.newInstance(sgbd);

        //ModuleServices
        Class modulesServiceImple= Class.forName(data.get("moduleService"));
        constructor = modulesServiceImple.getConstructor(ModuleRepository.class);
        ModuleService moduleService=(ModuleService) constructor.newInstance(moduleRepository);

        //ClasseRepository
        Class classeRepositoryImplementation=Class.forName(data.get("classeRepository"));
        // ClasseRepository classeRepository =(ClasseRepository) classeRepositoryImplementation.newInstance();
         constructor = classeRepositoryImplementation.getConstructor(Database.class,ModuleRepository.class);
        ClasseRepository classeRepository =(ClasseRepository) constructor.newInstance(sgbd,moduleRepository);

        //ClasseService
        Class classeServiceImplementation= Class.forName(data.get("classeService")) ;
        constructor = classeServiceImplementation.getConstructor(ClasseRepository.class);
        ClasseService classeService=(ClasseService) constructor.newInstance(classeRepository);

      //ProfesseurRepository
        Class professeurRepositoryImple=Class.forName(data.get("professeurRepository"));
          constructor = professeurRepositoryImple.getConstructor(Database.class,ClasseRepository.class,ModuleRepository.class);
        ProfesseurRepository professeurRepository=(ProfesseurRepository) constructor.newInstance(sgbd,classeRepository,moduleRepository);
        
        //ProfesseurServices
        Class professeurServiceClass=Class.forName(data.get("professeurService"));
        constructor = professeurServiceClass.getConstructor(ProfesseurRepository.class);
        ProfesseurService professeurService=(ProfesseurService) constructor.newInstance(professeurRepository);
        //SalleRepository
        Class salleRepositoryCLASS=Class.forName(data.get("salleRepository"));
        constructor=salleRepositoryCLASS.getConstructor(Database.class);
        SalleRepository salleRepository=(SalleRepository) constructor.newInstance(sgbd);

        
        //SalleService
        Class salleServiceClass=Class.forName(data.get("salleService"));
        constructor=salleServiceClass.getConstructor(SalleRepository.class);
        SalleService salleService=(SalleService) constructor.newInstance(salleRepository);

        //CoursRepository
        Class coursRepositoryImplementation=Class.forName(data.get("coursRepository"));
        constructor = coursRepositoryImplementation.getConstructor(Database.class,ModuleRepository.class,SalleRepository.class);
        CoursRepository coursRepository =(CoursRepository) constructor.newInstance(sgbd,moduleRepository,salleRepository);

        //CoursServices
        Class coursServiceImplementation= Class.forName(data.get("coursService")) ;
        constructor = coursServiceImplementation.getConstructor(CoursRepository.class);
        CoursService coursService=(CoursService) constructor.newInstance(coursRepository);

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
            System.out.println("12-Affecter un module a un prof");
            System.out.println("13-Quittez");
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
                            String nomClasse=String.format("%s %s",niveauSelect.getLibelle().toUpperCase(),filiereSelect.getLibelle().toUpperCase());

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
                                classeSelect.setLibelle(sc.nextLine().toUpperCase());
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
                    choix1 = sc.nextInt();
                    switch (choix1) {
                        case 1:
                                moduleService.listerModule()
                                      .forEach(System.out::println); 
                                Thread.sleep(3000);
                            break;
                        case 2:
                            sc.nextLine();

                            System.out.println("Entrer le libelle");
                            String libelle=sc.nextLine();
                            Module module=new Module(0,libelle.toUpperCase());

                            if(moduleService.ajouterModule(module)!=0){
                                System.out.println("--- Succes ---");
                                System.out.println("Module ajouter avec success");
                                System.out.println("--- Succes ---");

                              }else{
                                System.out.println("--- Failure ---");
                                System.out.println("Module Already Define");
                                System.out.println("--- Failure ---");

                              }
                            Thread.sleep(3000);
                            break;
                        case 3:
                            System.out.println("Selectionner le module");
        
                             ArrayList<Module> modules=moduleService.listerModule();
                               int i=0;
                              for (Module mod  : modules) {
                                i++;
                                System.out.println(String.format("%d) %s",i,mod.getLibelle()));
                              }
                              Module moduleSelect=moduleService.listerModule().get(sc.nextInt() - 1);
                              //Modifier quoi ?
                              System.out.println("Voulez vous modifier le libelle [oui,non]");
                              sc.nextLine();
                              String ok=sc.nextLine();
                              if((ok.toLowerCase()).equals("oui")){
                                System.out.println("Entrer le nouveau libelle");
                                moduleSelect.setLibelle(sc.nextLine());
                              }                          
                                if(moduleService.modifierModule(moduleSelect)){
                                    System.out.println("--- Update ---");
                                    System.out.println("Module Update");
                                    System.out.println("--- Update ---");
                                }
                              Thread.sleep(3000);
                              break;
                        case 4:
                              System.out.println("Selectionner le Module");
        
                              modules=moduleService.listerModule();
                              i=0;
                              for (Module mod  : modules) {
                                i++;
                                System.out.println(String.format("%d) %s",i,mod.getLibelle()));
                              }
                               moduleSelect=moduleService.listerModule().get(sc.nextInt() - 1);
                                moduleService.archiverModule(moduleSelect);
                                 System.out.println("--- Archiver ---");
                                    System.out.println("Module Archiver avec succes");
                                    System.out.println("--- Archiver ---");
                                    Thread.sleep(3000);

                              break;
                        default:
                            break;
                    }break;

                case 3:
                    //Affecter Module a une classe
                    String answer;
                    do{

                    
                    System.out.println("Choissiser la classe a qui on doit affecter le module");
                    ArrayList<Classe> classes=classeService.listerClasses();
                    int i=0;
                     for (Classe cl  : classes) {
                       i++;
                        System.out.println(String.format("%d) %s",i,cl.getLibelle()));
                      }
                      Classe classeSelect=classeService.listerClasses().get(sc.nextInt() - 1);                   

                    System.out.println("Choissisez le module a affecter");
                    ArrayList<Module> modules=moduleService.listerModule();
                                i=0;
                              for (Module mod  : modules) {
                                i++;
                                System.out.println(String.format("%d) %s",i,mod.getLibelle()));
                              }
                          Module moduleSelect=moduleService.listerModule().get(sc.nextInt() - 1);
                    System.out.println("Voici une liste des professeur qui enseigne ce module");
                    System.out.println("Merci d'en selectionner un");

                    ArrayList<Professeur> moduleProfesseurs= professeurService.listerProfesseurByModule(moduleSelect);

                              i=0;
                              for (Professeur prof  : moduleProfesseurs) {
                                i++;
                                System.out.println(String.format("%d) %s",i,prof.getNomComplet()));
                              }
                             Professeur professeurSelect=moduleProfesseurs.get(sc.nextInt()-1);


                    if(classeService.affecterModuleAUneClasse(moduleSelect,professeurSelect,classeSelect)){
                      System.out.println("--- SAVE ---");
                                    System.out.println("Cette Classe suit desormais ce Module ");
                                    System.out.println("--- SAVE ---");
                    }else{
                      System.out.println("--- FAILURE ---");
                                    System.out.println("Cette Classe suit DEJA ce Module");
                                    System.out.println("--- FAILURE ---");

                    } 
                    Thread.sleep(3000);

                    System.out.println("Vouliez vous poursuivre [oui,non]");
                    sc.nextLine();
                    answer=sc.nextLine();
                  }while(answer.toUpperCase().equals("oui"));
                    break;
                case 4:
                    //Afficher les Modules d'une Classe
                    System.out.println("Selectionner la classe concerné");
                    ArrayList<Classe> classes=classeService.listerClasses();
                     int i=0;
                     for (Classe cl  : classes) {
                       i++;
                        System.out.println(String.format("%d) %s",i,cl.getLibelle()));
                      }
                      Classe classeSelect=classeService.listerClasses().get(sc.nextInt() - 1);                   
                    classeService.listerModulesByClasse(classeSelect).forEach(System.out::println);
                    Thread.sleep(2000);
                    break;
                case 5:
                    //Afficher les classes qui font un modules
                      System.out.println("Choissisez le module dont vous souhaiteriez voir les classes");
                    ArrayList<Module> modules=moduleService.listerModule();
                                i=0;
                              for (Module mod  : modules) {
                                i++;
                                System.out.println(String.format("%d) %s",i,mod.getLibelle()));
                              }
                          Module moduleSelect=moduleService.listerModule().get(sc.nextInt() - 1);
                          classeService.listerClasseByModule(moduleSelect).forEach(System.out::println);;
                          Thread.sleep(3000);
                    break;
                case 6:
                    //Gerer Professeur
                    System.out.println("1- Lister Professeur");
                    System.out.println("2- Ajouter Professeur");
                    System.out.println("3- Modifier Professeur");
                    System.out.println("4- Archiver une Professeur");
                    choix1=sc.nextInt();
                    switch (choix1) {
                        case 1:
                                professeurService.listerProfesseur()
                                      .forEach(System.out::println); 
                                Thread.sleep(3000);
                            break;
                        case 2:
                            sc.nextLine();
                            System.out.println("Entrez le Nom Complet du Prof");
                            Professeur professeur=new Professeur(0,sc.nextLine().toUpperCase());

                              if(professeurService.ajouterProfesseur(professeur)!=0){
                                  System.out.println("--- Succes ---");
                                  System.out.println("Professeur ajouter avec success");
                                  System.out.println("--- Succes ---");

                                }else{
                                  System.out.println("--- Failure ---");
                                  System.out.println("Professeur Already Existe");
                                  System.out.println("--- Failure ---");
                                }
                            Thread.sleep(3000);
                            break;

                        case 3:
                            System.out.println("Selectionner le prof");
        
                             ArrayList<Professeur> professeurs=professeurService.listerProfesseur();
                                i=0;
                              for (Professeur prof  : professeurs) {
                                i++;
                                System.out.println(String.format("%d) %s",i,prof.getNomComplet()));
                              }
                              Professeur professeurSelect=professeurService.listerProfesseur().get(sc.nextInt() - 1);

                              //Modifier quoi ?
                              System.out.println("Voulez vous modifier le Nom du prof [oui,non]");
                              sc.nextLine();
                              String ok=sc.nextLine();
                              if((ok.toLowerCase()).equals("oui")){
                                System.out.println("Entrer le nom");
                                professeurSelect.setNomComplet(sc.nextLine());
                              }
                            
                                if(professeurService.modifierProfesseur(professeurSelect)){
                                    System.out.println("--- Update ---");
                                    System.out.println("Professeur information Update");
                                    System.out.println("--- Update ---");
                                }
                              Thread.sleep(3000);
                              break;
                        case 4:
                              System.out.println("Selectionner le professeur");
        
                              professeurs=professeurService.listerProfesseur();
                              i=0;
                              for (Professeur prof  : professeurs) {
                                i++;
                                System.out.println(String.format("%d) %s",i,prof.getNomComplet()));
                              }
                               professeurSelect=professeurService.listerProfesseur().get(sc.nextInt() - 1);
                                professeurService.archiverPorfesseur(professeurSelect);
                                    System.out.println("--- Archiver ---");
                                    System.out.println("Professeur Archiver avec succes");
                                    System.out.println("--- Archiver ---");
                                    Thread.sleep(3000);
                              break;
                        default:
                            break;
                    }

                    break;
                case 7:
                    //Afficher les classes d'un Prof ainsi que les modules enseigner
                    
                    System.out.println("Selectionner le professeur");
        
                             ArrayList<Professeur> professeurs=professeurService.listerProfesseur();
                              i=0;
                              for (Professeur prof  : professeurs) {
                                i++;
                                System.out.println(String.format("%d) %s",i,prof.getNomComplet()));
                              }
                      Professeur professeurSelect=professeurService.listerProfesseur().get(sc.nextInt() - 1);
                      Map<Classe,ArrayList<Module>> classeModules=professeurService.afficherClasseAndModuleEnseignerByProfesseur(professeurSelect);
                      System.out.println("Il enseigne à : ");
                      for (Classe classe : classeModules.keySet()) {
                         modules = classeModules.get(classe);
                        System.out.println("---");
                        System.out.println("Classe:"+classe.getLibelle());
                        System.out.println("Modules:");
                        modules.forEach(System.out::println);
                        System.out.println("---");

                    }

                    break;
                case 8:
                    //Gerer les salles
                    System.out.println("1- Lister Salle");
                    System.out.println("2- Ajouter Salle");
                    System.out.println("3- Modifier Salle");
                    System.out.println("4- Archiver une Salle");
                    choix1=sc.nextInt();
                    switch (choix1) {
                        case 1:
                                salleService.listerSalle()
                                      .forEach(System.out::println); 
                                Thread.sleep(3000);
                            break;
                        case 2:
                            sc.nextLine();
                            System.out.println("Entrer le libelle de la salle");
                            String libelle=sc.nextLine();

                            Salle salle=new Salle(0,libelle);

                            if(salleService.ajouterSalle(salle)!=0){
                                System.out.println("--- Succes ---");
                                System.out.println("Salle ajouter avec success");
                                System.out.println("--- Succes ---");

                              }else{
                                System.out.println("--- Failure ---");
                                System.out.println("Salle Already Define");
                                System.out.println("--- Failure ---");

                              }
                            Thread.sleep(3000);
                            break;
                        case 3:
                            System.out.println("Selectionner la Salle");
        
                             ArrayList<Salle> salles= salleService.listerSalle();
                                i=0;
                              for (Salle sal  : salles) {
                                i++;
                                System.out.println(String.format("%d) %s",i,sal.getLibelle().toUpperCase()));
                              }
                              Salle salleSelct=salleService.listerSalle().get(sc.nextInt() - 1);
                              //Modifier quoi ?
                              System.out.println("Voulez vous modifier le libelle [oui,non]");
                              sc.nextLine();
                              String ok=sc.nextLine();
                              if((ok.toLowerCase()).equals("oui")){
                                System.out.println("Entrer le nouveau libelle");
                                salleSelct.setLibelle(sc.nextLine());
                              }
                              
                            
                                if(salleService.modifierSalle(salleSelct)){
                                    System.out.println("--- Update ---");
                                    System.out.println("Salle Update");
                                    System.out.println("--- Update ---");
                                }
                              Thread.sleep(3000);
                              break;
                        case 4:
                              System.out.println("Selectionner la Salle");
        
                              salles=salleService.listerSalle();
                                i=0;
                              for (Salle sal  : salles) {
                                i++;
                                System.out.println(String.format("%d) %s",i,sal.getLibelle()));
                              }
                               salleSelct=salleService.listerSalle().get(sc.nextInt() - 1);
                                salleService.archiverSalle(salleSelct);
                                  System.out.println("--- Archiver ---");
                                    System.out.println("Salle Archiver avec succes");
                                    System.out.println("--- Archiver ---");
                                  Thread.sleep(3000);
                              break;
                        default:
                            break;
                    }

                    break;
                case 9:
                    sc.nextLine();
                    //Plannifier Cours
                    System.out.println("Entrez le libellé du cours");
                    Cours cours=new Cours(0,sc.nextLine());
                    System.out.println("Selectionner le module");
                     modules=moduleService.listerModule();
                    i=0;
                    for (Module mod  : modules) {
                       i++;
                    System.out.println(String.format("%d) %s",i,mod.getLibelle()));
                   }
                   moduleSelect=moduleService.listerModule().get(sc.nextInt() - 1);
                   cours.setModule(moduleSelect);
                 System.out.println("Voici une liste des professeur qui enseigne ce module");
                  System.out.println("Merci d'en selectionner un");

                  ArrayList<Professeur> moduleProfesseurs= professeurService.listerProfesseurByModule(moduleSelect);
                  i=0;
                  for (Professeur prof  : moduleProfesseurs) {
                    i++;
                    System.out.println(String.format("%d) %s",i,prof.getNomComplet()));
                  }
                  professeurSelect=moduleProfesseurs.get(sc.nextInt()-1);
                  cours.setProfesseur(professeurSelect);

                  ArrayList<Classe>classesSelect=new ArrayList();
                  do{
                      System.out.println("Selectionner une classe");

                      System.out.println(professeurSelect.getNomComplet()+"Enseigne à :");

                      classes=professeurService.listerClasseProfesseurByModule(professeurSelect,moduleSelect);
                      i=0;
                      for (Classe classe  : classes) {
                          i++;
                          System.out.println(String.format("%d) %s",i,classe.getLibelle()));
                        }
                   classeSelect=professeurService.listerClasseProfesseurByModule(professeurSelect, moduleSelect).get(sc.nextInt() - 1);
                   if(classesSelect.contains(classeSelect)){
                      System.out.println("Vous avez deja selectionner cette classe");
                   }else{
                      classesSelect.add(classeSelect);
                   }
                   System.out.println("voulez vous rajouter une classe ? [oui,non]");
                    sc.nextLine();
                    answer=sc.nextLine();
                  }while(answer.toUpperCase().equals("OUI"));
                  cours.setClasses(classesSelect);

                  System.out.print("Veuillez saisir une date (format yyyy-MM-dd) : ");
                  String inputDate = sc.nextLine();
                 
                  LocalDate dateSelect = LocalDate.parse(inputDate);
                  cours.setDate(dateSelect);
                  System.out.print("Veuillez saisir l'heure de début du cours (format HH:mm) : ");
                  String inputTime = sc.nextLine();
                  LocalTime heureDebutSelect;
         
            
                      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

                       heureDebutSelect = LocalTime.parse(inputTime, formatter);
                        cours.setHeureDebut(heureDebutSelect);


                  System.out.print("Veuillez saisir l'heure de fin du cours (format HH:mm) : ");
                   inputTime = sc.nextLine();
                   LocalTime heureFinSelect;
            
                    formatter = DateTimeFormatter.ofPattern("H:mm");
                    heureFinSelect = LocalTime.parse(inputTime, formatter);
                    cours.setHeureFin(heureFinSelect);
                  
                  
                  if(salleService.salleDispo(dateSelect,heureDebutSelect)==null){
                      System.out.println("Aucune Salle n'est dispo a cette date et heure-ce pourquoi il ne peut se faire qu'en ligne");
                      cours.setLieux("EN LIGNE");

                  }else{
                      System.out.println("Selectionner le lieux");
                      List<Lieux> lieux=coursService.listerLieux();
                        i=0;
                        for (Lieux li  : lieux) {
                              i++;
                              System.out.println(String.format("%d) %s",i,li.getLibelle()));
                              }
                         
                            int idLieux=sc.nextInt();     
                            Lieux lieuxSelect=coursService.listerLieux().get(idLieux-1);
                            cours.setLieux(lieuxSelect.getLibelle());
                    }
                    
                    if(cours.getLieux().equals("EN LIGNE") ){
                                System.out.println("Entrer le code du cours");
                                sc.nextLine();
                                String codeReunion=sc.nextLine();
                                String lienMeet = "https://meet.google.com/" + codeReunion;
                                cours.setLien(lienMeet);
                                System.out.println("Lien vers le cours : " + lienMeet);
                    }else{
                      //Lister les salles dispo;
                      System.out.println("Voici les salles dispo slectionnez en une");
                      ArrayList<Salle> salles= salleService.salleDispo(dateSelect,heureDebutSelect);
                      i=0;
                      for (Salle salle : salles) {
                        i++;
                        System.out.println(i+")"+salle.getLibelle());
                      }
                      Salle salleSelect= salles.get(sc.nextInt()-1);
                      cours.setSalle(salleSelect);
                    }
                    if(coursService.plannifierCours(cours)){
                      System.out.println("--- PLANNIFER ---");
                                    System.out.println("Cours Plannifier avec succes");
                                    System.out.println("--- PLANNIFER ---");
                    }else{
                       System.out.println("--- FAILED ---");
                                    System.out.println("Cours NOT Plannifier ");
                                    System.out.println("--- FAILED ---");
                    }      Thread.sleep(3000);
                    break;
                case 10:
                    //Afiicher les cours d'une classe
                      System.out.println("Choissiser la classe a qui on doit affecter le module");
                    classes=classeService.listerClasses();
                     i=0;
                     for (Classe cl  : classes) {
                       i++;
                        System.out.println(String.format("%d) %s",i,cl.getLibelle()));
                      }
                       classeSelect=classes.get(sc.nextInt() - 1);                   

                      // //TRAITEMENT
                       ArrayList<Cours> listeCours=coursService.listerCoursByClasse(classeSelect);
                       listeCours.forEach(System.out::println);
                      // String leftAlignFormat = "| %-15s | %-4s | %-4s | %-4s |%-4s |%-4s |%-4s |%-4s |%n";

                      // System.out.format("+----------+----------+------------+-------+--------+-----+-------------+-----------%n");
                      // System.out.format("| Date     | Module   | Professeur | Lieux | Salle | Lien | Heure Debut | Heure Fin %n");
                      // System.out.format("+----------+----------+------------+-------+--------+-----+-------------+-----------%n");

                      // for (Cours cour : listeCours) {
                      //   String value=    cour.getDate().toString()+" "+cour.getModule().getLibelle()+" "+" "+cour.getLieux()+" "+cour.getHeureDebut()+" "+cour.getHeureFin();
                      //     System.out.println(value);
                      // }
                      // System.out.format("+----------+----------+------------+-------+--------+-----+-------------+-----------%n");
                 
                        // String[] entete={"Date","Module","Professeur","Lieux","Salle","Lien","HeureDebut","HeureFin"};
                        //     AsciiTable table = new AsciiTable();
                        //     table.addRule();
                        //     for (String entry : entete) {
                        //       table.addRow(entry);
                        //       table.addRule();
                        //   }
                        //   for (Cours cour : listeCours) {
                        //     Map<String, String>informations=cour.toTable();
                        //     for (Map.Entry<String, String> entry : informations.entrySet()) {
                        //       table.addRow(entry.getValue());
                        //       table.addRule();
                        //     }
                        //   }

                          
                          

        // Configurer la largeur des colonnes en fonction du mot le plus long dans chaque colonne

        // Afficher la table


                      Thread.sleep(4000);
                    break;
                case 11:
                    //Afiicher les cours d'un professeur
                    System.out.println("Selectionner le professeur");
        
                              professeurs=professeurService.listerProfesseur();
                              i=0;
                              for (Professeur prof  : professeurs) {
                                i++;
                                System.out.println(String.format("%d) %s",i,prof.getNomComplet()));
                              }
                         professeurSelect=professeurService.listerProfesseur().get(sc.nextInt() - 1);
                      //Traitement
                      listeCours= coursService.listerCoursByProfesseur(professeurSelect);
                      listeCours.forEach(System.out::println);

                      // //Affichage sous forme de tableau
                      //  leftAlignFormat = "| %-15s | %-4s | %-4s | %-4s |%-4s |%-4s |%-4s |%-4s |%n";

                      // System.out.format("+----------+----------+------------+-------+--------+-----+-------------+-----------%n");
                      // System.out.format("| Date     | Module   | Classe     | Lieux | Salle | Lien | Heure Debut | Heure Fin %n");
                      // System.out.format("+----------+----------+------------+-------+--------+-----+-------------+-----------%n");

                      // for (Cours cour : listeCours) {
                      //     System.out.format(leftAlignFormat,cour.getDate().toString() ,cour.getModule().getLibelle(),cour.getClasses().forEach(System.out::println),cour.getLieux(),cour.getHeureDebut(),cour.getHeureFin());
                      // }
                      // System.out.format("+----------+----------+------------+-------+--------+-----+-------------+-----------%n");

                      Thread.sleep(4000);
                    break;
                case 12:
                    //Affecter un module a un prof
                    System.out.println("Selectionner le professeur");
        
                       professeurs=professeurService.listerProfesseur();
                              i=0;
                              for (Professeur prof  : professeurs) {
                                i++;
                                System.out.println(String.format("%d) %s",i,prof.getNomComplet()));
                              }
                               professeurSelect=professeurService.listerProfesseur().get(sc.nextInt() - 1);
                    System.out.println("Choissisez le module a affecter");
                     modules=moduleService.listerModule();
                                i=0;
                              for (Module mod  : modules) {
                                i++;
                                System.out.println(String.format("%d) %s",i,mod.getLibelle()));
                              }
                           moduleSelect=moduleService.listerModule().get(sc.nextInt() - 1);
                    if(professeurService.affecterModuleAProfesseur(professeurSelect, moduleSelect)){
                        System.out.println("--- ADD ---");
                                    System.out.println("THIS Professeur TEACH THIS COURSE WITH succes");
                           System.out.println("---  ADD ---");
                    }else{
                      System.out.println("--- ERROR ---");
                                    System.out.println("THIS Professeur ALREADY teach this course");
                           System.out.println("---  ERROR ---");

                    }
                           Thread.sleep(3000);
                    break;
                case 13:
                    System.out.println("------ GOOD-BYE ------");
                    Thread.sleep(3000);

                    break;
            }
            

        }while(choix!=13);
    }
}