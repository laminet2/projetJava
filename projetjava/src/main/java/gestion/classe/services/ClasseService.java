package gestion.classe.services;

import java.util.ArrayList;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Cours;
import gestion.classe.entities.Module;

public interface ClasseService {
    boolean ajouterClasse(Classe classe);
    boolean archiverClasse(Classe classe);
    ArrayList<Classe> listerClasses();
    boolean modifierClasse(Classe classe);

    ArrayList<Module> listerModulesByClasse(Classe classe);
    ArrayList<Cours> listerCoursByClasse(Classe classe);

}
