package gestion.classe.services;

import java.util.ArrayList;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Module;

public interface ModuleService {
    ArrayList<Module> listerModule();
    boolean archiverModule(Module module);
    boolean modifierModule(Module module);
    boolean ajouterModule(Module module);
            
    boolean affecterModuleAUneClasse(Module module,Classe classe);
    ArrayList<Classe> afficherClasseByModule(Module module);
}
