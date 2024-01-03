package gestion.classe.services.implementation;

import java.util.ArrayList;
import java.util.Map;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Module;
import gestion.classe.entities.Professeur;
import gestion.classe.repository.ProfesseurRepository;
import gestion.classe.services.ProfesseurService;

public class ProfesseurServiceImple implements ProfesseurService{
    
    private ProfesseurRepository professeurRepository;
    public ProfesseurServiceImple(ProfesseurRepository professeurRepository){
        this.professeurRepository=professeurRepository;
    }

    @Override
    public int ajouterProfesseur(Professeur professeur) {
        if(professeurExiste(professeur)){
            return 0;
        }
        return professeurRepository.insertOrUpdate(professeur);
    }

    @Override
    public ArrayList<Professeur> listerProfesseur() {
        return professeurRepository.findAll();
    }

    @Override
    public boolean modifierProfesseur(Professeur professeur) {
        return professeurRepository.insertOrUpdate(professeur)==1;
    }

    

    @Override
    public boolean archiverPorfesseur(Professeur professeur) {
        return professeurRepository.archiver(professeur);
    }

    @Override
    public Map<Classe, ArrayList<Module>> afficherClasseAndModuleEnseignerByProfesseur(Professeur professeur,
            Classe classe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'afficherClasseAndModuleEnseignerByProfesseur'");
    }

    @Override
    public ArrayList<Module> afficherModuleByProfesseur(Professeur prof) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'afficherModuleByProfesseur'");
    }

    @Override
    public boolean professeurExiste(Professeur professeur) {
        ArrayList<Professeur> professeurs=professeurRepository.findAll();
        for (Professeur prof : professeurs) {
        
            if(professeur.equals(prof)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean affecterModuleAProfesseur(Professeur prof, Module module) {
        return professeurRepository.addModuleProf(prof,module);
    }
    
}
