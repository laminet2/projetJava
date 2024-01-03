package gestion.classe.repository;

import java.util.ArrayList;
import java.util.Map;

import com.database.services.Repository;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Module;
import gestion.classe.entities.Professeur;

public interface ClasseRepository extends Repository<Classe>{
    boolean addModuleClasse(Classe classe,Professeur prof,Module module);
    Map<Professeur, ArrayList<Module>> getModulesAndProfesseurClasse(Classe classe) ;

}
