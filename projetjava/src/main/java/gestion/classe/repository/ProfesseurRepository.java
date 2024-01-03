package gestion.classe.repository;

import java.util.ArrayList;

import com.database.services.Repository;

import gestion.classe.entities.Module;
import gestion.classe.entities.Professeur;

public interface ProfesseurRepository extends Repository<Professeur>{
    ArrayList<Module> getProfesseursModules(Professeur prof);
    boolean addModuleProf(Professeur prof,Module module);
}
