package gestion.classe.services;

import java.util.ArrayList;
import java.util.List;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Cours;
import gestion.classe.entities.Lieux;
import gestion.classe.entities.Niveau;
import gestion.classe.entities.Professeur;

public interface CoursService {
    boolean plannifierCours(Cours cours);
    List<Lieux> listerLieux();
    ArrayList<Cours> listerCoursByProfesseur(Professeur professeur);
    ArrayList<Cours> listerCoursByClasse(Classe classe);
    
} 