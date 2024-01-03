package gestion.classe.services.implementation;

import java.util.ArrayList;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Module;
import gestion.classe.entities.Professeur;
import gestion.classe.repository.ClasseRepository;
import gestion.classe.repository.ModuleRepository;
import gestion.classe.services.ModuleService;

public class ModuleServiceImple implements ModuleService{
    private ModuleRepository moduleRepository;
    public ModuleServiceImple(ModuleRepository moduleRepository){
        this.moduleRepository=moduleRepository;
    }
    @Override
    public ArrayList<Module> listerModule() {
        return moduleRepository.findAll();
    }
    @Override
    public boolean archiverModule(Module module) {
        return moduleRepository.archiver(module);
    }
    @Override
    public boolean modifierModule(Module module) {
        return moduleRepository.insertOrUpdate(module)==1;
    }
    @Override
    public int ajouterModule(Module module) {
        if(moduleExiste(module)){
            return 0;
        }
        return moduleRepository.insertOrUpdate(module);
    }

   
    // @Override
    // public ArrayList<Classe> afficherClasseByModule(Module module) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'afficherClasseByModule'");
    // }

    @Override
    public boolean moduleExiste(Module module){
        ArrayList<Module> modules=moduleRepository.findAll();
        for (Module module2 : modules) {
        
            if(module2.equals(module)){
                return true;
            }
        }
        return false;

    }
    
    
    
}
