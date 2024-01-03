package gestion.classe.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.database.services.Repository;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Module;
import gestion.classe.entities.Professeur;

public interface ModuleRepository extends Repository<Module>{
        //Map<Professeur,ArrayList<Module>> getModulesClasse(Classe classe);
        //boolean addProfesseursModules(Module module,Classe classe);
        //ArrayList<Professeur> getProfesseur(Module module);
}
