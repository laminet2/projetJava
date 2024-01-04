package gestion.classe.services.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Cours;
import gestion.classe.entities.Lieux;
import gestion.classe.entities.Niveau;
import gestion.classe.entities.Professeur;
import gestion.classe.repository.CoursRepository;
import gestion.classe.services.CoursService;

public class CoursServiceImple implements CoursService{
    
    private CoursRepository coursRepository;
    public CoursServiceImple(CoursRepository coursRepository){
        this.coursRepository=coursRepository;
    }
    @Override
    public boolean plannifierCours(Cours cours) {
        return coursRepository.insertOrUpdate(cours)!=0;
    }
    @Override
    public List<Lieux> listerLieux() {
        return Arrays.asList(new Lieux("EN LIGNE"),new Lieux("EN PRESENTIEL"));
    }
    @Override
    public ArrayList<Cours> listerCoursByProfesseur(Professeur professeur) {
        return coursRepository.findCoursByProfesseur(professeur);
    }
    @Override
    public ArrayList<Cours> listerCoursByClasse(Classe classe) {
        return coursRepository.findCoursByClasse(classe);
    }
   
}
