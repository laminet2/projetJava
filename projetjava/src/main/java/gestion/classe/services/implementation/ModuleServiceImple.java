package gestion.classe.services.implementation;

import java.util.ArrayList;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Module;
import gestion.classe.repository.ModuleRepository;
import gestion.classe.services.ModuleService;

public class ModuleServiceImple implements ModuleService{
    private ModuleRepository moduleRepository;
    public ModuleServiceImple(ModuleRepository moduleRepository){
        this.moduleRepository=moduleRepository;
    }
    @Override
    public ArrayList<Module> listerModule() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listerModule'");
    }
    @Override
    public boolean archiverModule(Module module) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'archiverModule'");
    }
    @Override
    public boolean modifierModule(Module module) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifierModule'");
    }
    @Override
    public boolean ajouterModule(Module module) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ajouterModule'");
    }
    @Override
    public boolean affecterModuleAUneClasse(Module module, Classe classe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'affecterModuleAUneClasse'");
    }
    @Override
    public ArrayList<Classe> afficherClasseByModule(Module module) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'afficherClasseByModule'");
    }
    
    
}
