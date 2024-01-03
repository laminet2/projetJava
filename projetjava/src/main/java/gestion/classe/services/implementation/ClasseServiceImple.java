package gestion.classe.services.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Cours;
import gestion.classe.entities.Filiere;
import gestion.classe.entities.Module;
import gestion.classe.entities.Niveau;
import gestion.classe.entities.Professeur;
import gestion.classe.repository.ClasseRepository;
import gestion.classe.services.ClasseService;

public class ClasseServiceImple implements ClasseService{
    private ClasseRepository classeRepository;
    
    
    public ClasseServiceImple(ClasseRepository classeRepository){
        this.classeRepository=classeRepository;
    }
    
    @Override
    public boolean classeExiste(Classe classe) {

        ArrayList<Classe> classes=listerClasses();        
        for (Classe clas : classes) {
            System.out.println(clas);
            if(classe.equals(clas)){
                System.out.println(clas);
                return true;
            }
        }

        return false;
    }

    @Override
    public int ajouterClasse(Classe classe) {
        if(classeExiste((classe))){
            return 0;
        }
        return this.classeRepository.insertOrUpdate(classe);
    }

    @Override
    public boolean archiverClasse(Classe classe) {
        return classeRepository.archiver(classe);

    }

    @Override
    public ArrayList<Classe> listerClasses() {
        
        return classeRepository.findAll();
    }

    @Override
    public boolean modifierClasse(Classe classe) {

        return this.classeRepository.insertOrUpdate(classe)==1;
    }

    @Override
    public ArrayList<Module> listerModulesByClasse(Classe classe) {
       return classeRepository.findModuleByClasse(classe);
    }

    @Override
    public ArrayList<Cours> listerCoursByClasse(Classe classe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listerCoursByClasse'");
    }
     @Override
    public List<Filiere> listerFiliere() {
        return Arrays.asList(new Filiere("MAIE"),new Filiere("GLRS"),new Filiere("CDSD"));
    }

    @Override
    public List<Niveau> listerNiveaux() {
      
         return Arrays.asList(new Niveau("L1"),new Niveau("L2")
         ,new Niveau("L3"));
    }

    @Override
    public boolean affecterModuleAUneClasse(Module module,Professeur professeur, Classe classe) {
        return classeRepository.addModuleClasse(classe, professeur, module);

        // classeRepository.getModulesClasse(classe).putIfAbsent(professeur, new ArrayList<Module>());
        // if(!classe.getProfesseurModules().get(professeur).contains(module)){
        //     classe.getProfesseurModules().get(professeur).add(module);
        //     classeRepository.insertOrUpdate(classe);
        //     return true;  
        //   }
        // return false;  
    }

    @Override
    public ArrayList<Classe> listerClasseByModule(Module module) {
        return classeRepository.findClasseByModule(module);
    }
    

    
}
