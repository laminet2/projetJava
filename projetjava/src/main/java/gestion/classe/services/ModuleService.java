package gestion.classe.services;

import java.util.ArrayList;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Module;
import gestion.classe.entities.Professeur;

public interface ModuleService {
    ArrayList<Module> listerModule();
    boolean archiverModule(Module module);
    boolean modifierModule(Module module);
    int ajouterModule(Module module);
            
    // ArrayList<Classe> afficherClasseByModule(Module module);
    //ArrayList<Professeur> listerProfesseurByModule(Module module);

    boolean moduleExiste(Module module);
}
