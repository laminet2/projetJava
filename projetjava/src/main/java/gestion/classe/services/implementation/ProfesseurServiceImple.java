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
    public boolean ajouterProfesseur(Professeur professeur) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ajouterProfesseur'");
    }

    @Override
    public ArrayList<Professeur> listerProfesseur() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listerProfesseur'");
    }

    @Override
    public boolean modifierProfesseur(Professeur professeur) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifierProfesseur'");
    }

    @Override
    public boolean archiverPorfesseur(Professeur professeur) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'archiverPorfesseur'");
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
    
}
