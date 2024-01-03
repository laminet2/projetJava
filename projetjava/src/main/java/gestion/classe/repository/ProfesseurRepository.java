package gestion.classe.repository;

import java.util.ArrayList;
import java.util.Map;

import com.database.services.Repository;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Module;
import gestion.classe.entities.Professeur;

public interface ProfesseurRepository extends Repository<Professeur>{
    ArrayList<Module> getProfesseursModules(Professeur prof);
    boolean addModuleProf(Professeur prof,Module module);
    Map<Classe, ArrayList<Module>> getModulesAndClasseProfesseur(Professeur professeur);
     ArrayList<Professeur> getProfesseurByModule(Module module);
    ArrayList<Classe> findClasseByModuleAndProfesseur(Professeur professeur,Module module);
}
