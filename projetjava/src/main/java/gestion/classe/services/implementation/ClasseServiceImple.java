package gestion.classe.services.implementation;

import java.util.ArrayList;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Cours;
import gestion.classe.entities.Module;
import gestion.classe.repository.ClasseRepository;
import gestion.classe.services.ClasseService;

public class ClasseServiceImple implements ClasseService{
    private ClasseRepository classeRepository;
    
    public ClasseServiceImple(ClasseRepository classeRepository){
        this.classeRepository=classeRepository;
    }

    @Override
    public boolean ajouterClasse(Classe classe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ajouterClasse'");
    }

    @Override
    public boolean archiverClasse(Classe classe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'archiverClasse'");
    }

    @Override
    public ArrayList<Classe> listerClasses(Classe classe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listerClasses'");
    }

    @Override
    public boolean modifierClasse(Classe classe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifierClasse'");
    }

    @Override
    public ArrayList<Module> listerModulesByClasse(Classe classe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listerModulesByClasse'");
    }

    @Override
    public ArrayList<Cours> listerCoursByClasse(Classe classe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listerCoursByClasse'");
    }
    
}
