package gestion.classe.services;

import java.util.ArrayList;
import java.util.Map;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Module;
import gestion.classe.entities.Professeur;

public interface ProfesseurService {
    int ajouterProfesseur(Professeur professeur);
    ArrayList<Professeur> listerProfesseur();
    boolean modifierProfesseur(Professeur professeur);
    boolean archiverPorfesseur(Professeur professeur);

    Map<Classe,ArrayList<Module>> afficherClasseAndModuleEnseignerByProfesseur(Professeur professeur,Classe classe);
    ArrayList<Module> afficherModuleByProfesseur(Professeur prof);
    boolean affecterModuleAProfesseur(Professeur prof,Module module);
    boolean professeurExiste(Professeur professeur);
}
