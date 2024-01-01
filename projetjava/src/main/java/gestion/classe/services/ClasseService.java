package gestion.classe.services;

import java.util.ArrayList;
import java.util.List;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Cours;
import gestion.classe.entities.Filiere;
import gestion.classe.entities.Module;
import gestion.classe.entities.Niveau;

public interface ClasseService {
    int ajouterClasse(Classe classe);
    boolean archiverClasse(Classe classe);
    ArrayList<Classe> listerClasses();
    boolean modifierClasse(Classe classe);

    ArrayList<Module> listerModulesByClasse(Classe classe);
    ArrayList<Cours> listerCoursByClasse(Classe classe);

    List<Filiere> listerFiliere();
    List<Niveau> listerNiveaux();

    boolean classeExiste(Classe classe);
}
